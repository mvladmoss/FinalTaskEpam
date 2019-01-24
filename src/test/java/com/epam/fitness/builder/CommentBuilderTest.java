package com.epam.fitness.builder;

import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Comment;
import com.epam.fitness.model.Exercise;
import com.epam.fitness.repository.database.constants.CommentTableConstants;
import com.epam.fitness.repository.database.constants.ExerciseTableConstants;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommentBuilderTest {

    private static final Long ID_COMMENT = 1L;
    private static final Long CLIENT_ID = 3L;
    private static final Long COACH_ID = 2L;
    private static final String COMMENT_CONTENT = "best coach";

    private static final Comment EXPECTED_COMMENT = new Comment(
            ID_COMMENT,
            CLIENT_ID,
            COACH_ID,
            COMMENT_CONTENT);

    @Test
    public void shouldBuildAndReturnCommentWithParameters() throws SQLException, RepositoryException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong(CommentTableConstants.ID.getFieldName())).thenReturn(ID_COMMENT);
        when(resultSet.getLong(CommentTableConstants.CLIENT_ID.getFieldName())).thenReturn(CLIENT_ID);
        when(resultSet.getLong(CommentTableConstants.COACH_ID.getFieldName())).thenReturn(COACH_ID);
        when(resultSet.getString(CommentTableConstants.COMMENT_CONTENT.getFieldName())).thenReturn(COMMENT_CONTENT);

        CommentBuilder commentBuilder = new CommentBuilder();
        Comment actualComment = commentBuilder.build(resultSet);
        Assert.assertEquals(EXPECTED_COMMENT, actualComment);

    }
}
