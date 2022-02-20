package com.epam.jwd.task01.dao.tariffdao;

import com.epam.jwd.task01.dao.fileparser.tarifffilereader.TariffFileReader;
import com.epam.jwd.task01.entity.Tariff;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class that implements TariffDAO interface
 * and serves as a layer between the Tariff database and the program logic
 */

public class TariffDAOImpl implements TariffDAO {
    public static final long AUTO_ID_INIT = 1L;
    private static final TariffDAOImpl INSTANCE = new TariffDAOImpl();
    private final HashMap<String, Tariff> tariffsHashMap = new HashMap<>();
    private long nextAutoId = AUTO_ID_INIT;

    private TariffDAOImpl() {
    }

    /**
     * Method that provides one single existing instance of the class TariffDAOImpl
     *
     * @return one single existing instance of the class TariffDAOImpl
     */
    public static TariffDAOImpl getInstance() {
        return INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load(String filePath) {
        TariffFileReader reader = new TariffFileReader(filePath);
        List<Tariff> tariffList = reader.readFileToTariffList();
        deleteAll();
        insert(tariffList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tariff> find() {
        return find(Optional.empty(), Optional.empty(), Optional.empty());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tariff> find(Optional<Predicate<Tariff>> filter) {
        return find(filter, Optional.empty(), Optional.empty());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tariff> find(Optional<Predicate<Tariff>> tariffFilter,
                             Optional<Integer> tariffLimit,
                             Optional<Comparator<Tariff>> tariffComparator) {
        Stream<Tariff> stream = tariffsHashMap.values().stream();
        if (tariffFilter.isPresent()) stream = stream.filter(tariffFilter.get());
        if (tariffLimit.isPresent()) stream = stream.limit(tariffLimit.get());
        if (tariffComparator.isPresent()) stream = stream.sorted(tariffComparator.get());
        return stream.collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(List<Tariff> tariffs) {
        tariffs.stream().forEach(tariff ->
                tariffsHashMap.put(Long.toString(nextAutoId++), tariff));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        tariffsHashMap.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Tariff> getById(String id) {
        Tariff tariff = tariffsHashMap.get(id);
        if (tariff != null) {
            return Optional.of(tariff);
        } else {
            return Optional.empty();
        }
    }
}
