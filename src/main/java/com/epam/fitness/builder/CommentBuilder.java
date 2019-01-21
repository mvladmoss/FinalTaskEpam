package com.epam.fitness.builder;

import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Comment;
import com.epam.fitness.repository.database.constants.CommentTableConstants;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentBuilder implements Builder<Comment> {
    @Override
    public Comment build(ResultSet resultSet) throws RepositoryException {
        Comment comment = new Comment();
        try{
            Long commentId = resultSet.getLong(CommentTableConstants.ID.getFieldName());
            comment.setId(commentId);
            Long clientId = resultSet.getLong(CommentTableConstants.CLIENT_ID.getFieldName());
            comment.setClientId(clientId);
            Long coachId = resultSet.getLong(CommentTableConstants.COACH_ID.getFieldName());
            comment.setCoachId(coachId);
            String commentContent = resultSet.getString(CommentTableConstants.COMMENT_CONTENT.getFieldName());
            comment.setCommentContent(commentContent);
        }catch (SQLException exception){
            throw new RepositoryException(exception.getMessage(),exception);
        }
        return comment;
    }
}
