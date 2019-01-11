package com.epam.fitness.repository;

import com.epam.fitness.builder.CommentBuilder;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.Comment;
import com.epam.fitness.repository.specifications.SqlSpecification;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommentRepository extends AbstractRepository<Comment> {

    private static final String TABLE_NAME = "comment";
    private final static String INSERT_QUERY = "insert into comment (id_comment,coach_id,client_id,commentContent)" +
            " values(?,?,?,?);";

    public CommentRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public List<Comment> query(SqlSpecification specification) throws RepositoryException{
        String query = "select * from comment " + specification.getSql();
        List<Comment> comments = executeQuery(query,new CommentBuilder(), specification.getParameters());
        return comments;
    }

    @Override
    public Optional<Comment> queryForSingleResult(SqlSpecification specification) throws RepositoryException {
        List<Comment> comment = query(specification);
        return comment.size() == 1 ?
                Optional.of(comment.get(0)) :
                Optional.empty();
    }

    public Long save(Comment comment) throws RepositoryException {
        Long commentId = comment.getId();
        Long coachId = comment.getCoachId();
        Long clientId = comment.getClientId();

        String commentContent = comment.getCommmentContent();
        return executeUpdate(INSERT_QUERY,Arrays.asList(commentId,coachId,clientId,commentContent));

    }
}
