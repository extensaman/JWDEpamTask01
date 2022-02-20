package com.epam.jwd.task01.dao.tariffdao;

import com.epam.jwd.task01.entity.Tariff;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Interface that defines methods for working with the tariff's database
 */
public interface TariffDAO {
    /**
     * Method for loading data on existing tariffs from a file into a storage in DAO
     *
     * @param filePath path to a file containing information about existing tariffs
     */
    void load(String filePath);

    /**
     * Method that returns a list of all tariffs stored in DAO-storage
     *
     * @return list of all tariffs stored in DAO-storage
     */
    List<Tariff> find();

    /**
     * Method generates tariff's list from DAO-storage corresponding to the conditions in <i>tariffFilter</i>
     *
     * @param tariffFilter conditions for selection of tariffs
     * @return tariff's list corresponding to given conditions
     */
    List<Tariff> find(Optional<Predicate<Tariff>> tariffFilter);

    /**
     * The method is designed to generate a list of tariffs from DAO-storage
     * based on the criteria specified in the parameters of this method
     *
     * @param tariffFilter     conditions for selection of tariffs
     * @param tariffLimit      the maximum allowable number of tariffs to be included in the returned list
     * @param tariffComparator comparator for sorting tariffs in the returned list
     * @return the list of tariffs generated based on the criteria specified in the method parameters
     */
    List<Tariff> find(Optional<Predicate<Tariff>> tariffFilter,
                      Optional<Integer> tariffLimit,
                      Optional<Comparator<Tariff>> tariffComparator);


    /**
     * In case of existence tariff with identifier <i>id</i>
     * method returns Optional reference to corresponding tariff,
     * in other case returns Optional.empty()
     *
     * @param id identifier of tariff
     * @return Optional reference to corresponding tariff or Optional.empty()
     */
    Optional<Tariff> getById(String id);

    /**
     * Add tariff's list to DAO-storage
     *
     * @param tariffs - tariff's list for adding to DAO-storage
     */
    void insert(List<Tariff> tariffs);

    /**
     * Method for delete all tariffs from DAO-storage
     */
    void deleteAll();
}
