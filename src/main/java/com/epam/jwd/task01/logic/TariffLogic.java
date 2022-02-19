package com.epam.jwd.task01.logic;

import com.epam.jwd.task01.dao.tariffdao.TariffDAO;
import com.epam.jwd.task01.entity.Tariff;
import com.epam.jwd.task01.service.tariffcreator.TariffCreator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Сlass contains functionality for working with tariff objects
 * using data from the DAO layer
 */

public class TariffLogic {
    public static final int INTERNET_IS_CONNECTED = 1;
    public static final Comparator<Tariff> SORT_BY_PERIODICAL_FEE =
            Comparator.comparing(Tariff::getPeriodicalFee);
    public static final Comparator<Tariff> SORT_BY_FIRST_CONNECTION_FEE =
            Comparator.comparing(Tariff::getFirstConnectionFee);
    private final TariffDAO dao;

    public TariffLogic(TariffDAO dao) {
        this.dao = dao;
    }

    public List<Tariff> getTariffListByParameter(Optional<BigDecimal> firstConnectionFeeLow,
                                                 Optional<BigDecimal> firstConnectionFeeHigh,
                                                 Optional<BigDecimal> periodicalFeeLow,
                                                 Optional<BigDecimal> periodicalFeeHigh,
                                                 Optional<Integer> minuteAmountLow,
                                                 Optional<Integer> minuteAmountHigh,
                                                 Optional<Integer> SMSAmountLow,
                                                 Optional<Integer> SMSAmountHigh,
                                                 Optional<Integer> internetTrafficAmountLow,
                                                 Optional<Integer> internetTrafficAmountHigh,
                                                 Optional<Integer> limit,
                                                 Optional<Comparator<Tariff>> comparator) {
        List<Predicate<Tariff>> predicates = new ArrayList<>();

        firstConnectionFeeLow.ifPresent(lowLimit ->
                predicates.add(tariff ->
                        lowLimit.compareTo(tariff.getFirstConnectionFee()) <= 0));
        firstConnectionFeeHigh.ifPresent(highLimit ->
                predicates.add(tariff ->
                        highLimit.compareTo(tariff.getFirstConnectionFee()) >= 0));

        periodicalFeeLow.ifPresent(lowLimit ->
                predicates.add(tariff ->
                        lowLimit.compareTo(tariff.getPeriodicalFee()) <= 0));
        periodicalFeeHigh.ifPresent(highLimit ->
                predicates.add(tariff ->
                        highLimit.compareTo(tariff.getPeriodicalFee()) >= 0));

        minuteAmountLow.ifPresent(lowLimit ->
                predicates.add(tariff ->
                        tariff.getMinuteAmount() > lowLimit));
        minuteAmountHigh.ifPresent(highLimit ->
                predicates.add(tariff ->
                        tariff.getMinuteAmount() < highLimit));

        SMSAmountLow.ifPresent(lowLimit ->
                predicates.add(tariff ->
                        tariff.getSMSAmount() > lowLimit));
        SMSAmountHigh.ifPresent(highLimit ->
                predicates.add(tariff ->
                        tariff.getSMSAmount() < highLimit));

        internetTrafficAmountLow.ifPresent(lowLimit ->
                predicates.add(tariff ->
                        tariff.getInternetTrafficAmount() > lowLimit));
        internetTrafficAmountHigh.ifPresent(highLimit ->
                predicates.add(tariff ->
                        tariff.getInternetTrafficAmount() < highLimit));

        return dao.find(predicates.stream().reduce(Predicate::and), limit, comparator);
    }

    public List<Tariff> getAllTariff() {
        return dao.find();
    }

    public Long getAbonentAmount() {
        return TariffCreator.getInstance().getAmount();
    }

    public List<Tariff> getTariffWithInternetAndPeriodicalFeeLessThanNumber(BigDecimal periodicalFeeHighLimit) {
        return getTariffListByParameter(Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of(periodicalFeeHighLimit),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of(INTERNET_IS_CONNECTED),
                Optional.empty(),
                Optional.empty(),
                Optional.of(SORT_BY_FIRST_CONNECTION_FEE));
    }

    public List<Tariff> getTariffSortedByPeriodicalFee() {
        return getTariffListByParameter(Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of(SORT_BY_PERIODICAL_FEE));
    }

}
