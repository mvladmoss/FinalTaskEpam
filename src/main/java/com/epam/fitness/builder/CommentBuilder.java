package com.epam.fitness.builder;

import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Comment;
import com.epam.fitness.repository.database.constants.CommentTableConstants;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Designed to build an object of type {@link com.epam.fitness.model.Comment} with specified characteristics.
 */
public class CommentBuilder implements Builder<Comment> {

    /**
     * Builds an object of type Comment with properties.
     *
     * @param resultSet Instance of {@link java.sql.ResultSet} with property set to build an object type Comment.
     * @return Returns built object type Comment.
     * @throws RepositoryException Throws when SQL Exception is caught.
     */

    @Override
    public Comment build(ResultSet resultSet) throws RepositoryException {
        try{
            Long commentId = resultSet.getLong(CommentTableConstants.ID.getFieldName());
            Long clientId = resultSet.getLong(CommentTableConstants.CLIENT_ID.getFieldName());
            Long coachId = resultSet.getLong(CommentTableConstants.COACH_ID.getFieldName());
            String commentContent = resultSet.getString(CommentTableConstants.COMMENT_CONTENT.getFieldName());
            return new Comment(commentId,clientId,coachId,commentContent);
        }catch (SQLException exception){
            throw new RepositoryException(exception.getMessage(),exception);
        }
    }
}
