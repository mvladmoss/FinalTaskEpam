package com.epam.fitness.service;

import com.epam.fitness.model.Nutrition;
import com.epam.fitness.repository.NutritionRepository;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.repository.creator.RepositoryCreator;
import com.epam.fitness.repository.specifications.nutrition.NutritionByClientId;

import java.util.Optional;import com.epam.fitness.exception.ServiceException;


public class NutritionService {

    public Optional<Nutrition> findById(long id) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            NutritionRepository nutritionRepository = repositoryCreator.getNutritionRepository();
            return nutritionRepository.findById(id);
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public long save(Nutrition nutrition) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            NutritionRepository nutritionRepository = repositoryCreator.getNutritionRepository();
            return nutritionRepository.save(nutrition);
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public Optional<Nutrition> findByClientId(long clientId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            NutritionRepository nutritionRepository = repositoryCreator.getNutritionRepository();
            NutritionByClientId specification = new NutritionByClientId(clientId);
            return nutritionRepository.queryForSingleResult(specification);
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public Long getNextIdInTable() throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            NutritionRepository nutritionRepository = repositoryCreator.getNutritionRepository();
            return nutritionRepository.getNextTableId();
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }
}
