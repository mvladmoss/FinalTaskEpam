package com.epam.fitness.repository;

import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.List;
import java.util.Optional;

public interface Repository<T>{
    /**
     *
     * @param specification
     * @return
     * @throws RepositoryException
     */
    List<T> query(SqlSpecification specification) throws RepositoryException;

    /**
     *
     * @param specification
     * @return
     * @throws RepositoryException
     */
    Optional<T> queryForSingleResult(SqlSpecification specification) throws RepositoryException;

    /**
     *
     * @param id
     * @return
     * @throws RepositoryException
     */
    Optional<T> findById(Long id) throws RepositoryException;
    List<T> findAll() throws RepositoryException;
    Long save(T object) throws RepositoryException;

}
