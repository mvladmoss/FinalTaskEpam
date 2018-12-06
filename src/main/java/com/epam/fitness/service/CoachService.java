package com.epam.fitness.service;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.Coach;
import com.epam.fitness.repository.ClientRepository;
import com.epam.fitness.repository.CoachRepository;
import com.epam.fitness.repository.RepositoryException;
import com.epam.fitness.repository.creator.RepositoryCreator;
import com.epam.fitness.repository.specifications.client.ClientInfoByLogin;
import com.epam.fitness.repository.specifications.coach.CoachInfoById;
import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoachService  {

    private CoachRepository coachRepository = new CoachRepository();

    public Optional<Coach> login(String login, String password) throws ServiceException {
        /*Coach coach = new Coach();
        coach.setId(1);
        coach.setLogin("dvor");
        coach.setName("Leha");
        coach.setSurname("Dvornichenko");
        coach.setPassword("123");
        SqlSpecification specification = new ClientInfoByLogin(login);
        return Optional.of(coach);
        try {
//            return  repository.executeQueryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(),e);
        }*/
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            CoachRepository coachRepository = repositoryCreator.getCoachRepository();
            return coachRepository.findCoachByLogin(login, password);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Coach> findById(long id) throws ServiceException {
        Coach coach = new Coach();
        coach.setId(1);
        coach.setLogin("dvor");
        coach.setName("Leha");
        coach.setSurname("Dvornichenko");
        coach.setPassword("123");
        SqlSpecification specification = new CoachInfoById(id);
        return Optional.of(coach);
        /*try {
//            return  repository.executeQueryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(),e);
        }*/
    }

    public Optional<List<Coach>> findAllCoaches(){
        Coach coach1 = new Coach();
        coach1.setId(1);
        coach1.setLogin("dvor");
        coach1.setName("Leha");
        coach1.setSurname("Dvornichenko");
        coach1.setPassword("123");

        Coach coach2 = new Coach();
        coach2.setId(1);
        coach2.setLogin("dvor2");
        coach2.setName("Dima");
        coach2.setSurname("Yakovenko");
        coach2.setPassword("123");

        List<Coach> coaches = new ArrayList<>();
        coaches.add(coach1);
        coaches.add(coach2);

        return Optional.of(coaches);
    }


}
