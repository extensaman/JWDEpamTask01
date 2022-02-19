package com.epam.jwd.task01.dao.tariffdao;

import com.epam.jwd.task01.entity.Tariff;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Interface that defines methods for working with the tariff database
 */

public interface TariffDAO {
    void load(String filePath);

    List<Tariff> find();

    List<Tariff> find(Optional<Predicate<Tariff>> tariffFilter);

    List<Tariff> find(Optional<Predicate<Tariff>> tariffFilter,
                      Optional<Integer> tariffLimit,
                      Optional<Comparator<Tariff>> tariffComparator);

    Optional<Tariff> getById(String id) throws CloneNotSupportedException;

    void insert(List<Tariff> tariffs);

    void deleteAll();
}
