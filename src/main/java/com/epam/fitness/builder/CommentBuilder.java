package com.epam.fitness.builder;

import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentBuilder implements Builder<Comment> {
    @Override
    public Comment build(ResultSet resultSet) throws RepositoryException {
        Comment comment = new Comment();
        try{
            Long commentid = resultSet.getLong("id_comment");
            comment.setId(commentid);
            Long clientId = resultSet.getLong("client_id");
            comment.setClientId(clientId);
            Long coachId = resultSet.getLong("coach_id");
            comment.setCoachId(coachId);
            String commentContent = resultSet.getString("commentContent");
            comment.setCommentContent(commentContent);
        }catch (SQLException exception){
            throw new RepositoryException(exception.getMessage(),exception);
        }
        return comment;
    }
}
