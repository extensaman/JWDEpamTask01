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

    /**
     * The method is designed to generate a list of tariffs
     * based on the criteria specified in the parameters of this method
     *
     * @param firstConnectionFeeLow     low limit of the first connection fee for the tariff
     * @param firstConnectionFeeHigh    high limit of the first connection fee for the tariff
     * @param periodicalFeeLow          low limit of the periodical fee for the tariff
     * @param periodicalFeeHigh         high limit of the periodical fee for the tariff
     * @param minuteAmountLow           low limit of the minutes amount included in the tariff
     * @param minuteAmountHigh          high limit of the minutes amount included in the tariff
     * @param SMSAmountLow              low limit of the SMS amount included in the tariff
     * @param SMSAmountHigh             high limit of the SMS amount included in the tariff
     * @param internetTrafficAmountLow  low limit of the Internet traffic included in the tariff
     * @param internetTrafficAmountHigh high limit of the Internet traffic included in the tariff
     * @param limit                     the maximum allowable number of tariffs to be included in the returned list
     * @param comparator                comparator for sorting tariffs in the returned list
     * @return the list of tariffs generated based on the criteria specified in the method parameters
     */
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

    /**
     * Method for generating list of all tariffs contained in DAO layer
     *
     * @return the list of all tariffs contained in DAO layer
     */
    public List<Tariff> getAllTariff() {
        return dao.find();
    }

    /**
     * Method that returns amount of mobile company clients contained in DAO layer
     *
     * @return amount of mobile company clients contained in DAO layer
     */
    public Long getAbonentAmount() {
        return TariffCreator.getInstance().getAmount();
    }

    /**
     * Method for generating list of tariffs with Internet traffic,
     * periodical payment less than <i>periodicalFeeHighLimit</i>
     * and also sorted by connection fee value
     *
     * @param periodicalFeeHighLimit high bound for periodical fee
     * @return the list of tariffs with Internet traffic,
     * periodical payment less than <i>periodicalFeeHighLimit</i>
     * and also sorted by connection fee value
     */
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

    /**
     * Method that returns list of tariffs sorted by periodical fee
     *
     * @return list of tariffs sorted by periodical fee
     */
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
