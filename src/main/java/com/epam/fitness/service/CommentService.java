package com.epam.fitness.service;

import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.Comment;
import com.epam.fitness.repository.CommentRepository;
import com.epam.fitness.repository.creator.RepositoryCreator;
import com.epam.fitness.repository.specifications.SqlSpecification;
import com.epam.fitness.repository.specifications.comment.CommentByCoachId;

import java.util.List;
import java.util.Optional;

public class CommentService {

    public Optional<Comment> findById(long id) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            CommentRepository commentRepository = repositoryCreator.getCommentRepository();
            return commentRepository.findById(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public long save(Comment comment) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            CommentRepository commentRepository = repositoryCreator.getCommentRepository();
            return commentRepository.save(comment);
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public List<Comment> findByCoachId(long coachId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            CommentRepository commentRepository = repositoryCreator.getCommentRepository();
            SqlSpecification specification = new CommentByCoachId(coachId);
            return commentRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
