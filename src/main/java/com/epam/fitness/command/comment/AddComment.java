package com.epam.fitness.command.comment;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.command.session.SessionAttributes;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Comment;
import com.epam.fitness.model.UserRole;
import com.epam.fitness.service.CommentService;
import com.epam.fitness.utils.RequestParameterValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.fitness.command.constants.TextConstants.MAX_NUMBER_SYMBOLS_ATTRIBUTE;
import static com.epam.fitness.command.constants.TextConstants.MAX_NUMBER_SYMBOLS_VALUE;

public class AddComment implements Command {

    private final static String COACHES_PAGE = "/controller?command=coaches";
    private final static String COACH_ID = "coach_id";
    private final static String COMMENT_CONTENT = "commentContent";
    private final RequestParameterValidator parameterValidator = new RequestParameterValidator();
    private final static String INCORRECT_INPUT_COMMENT_DATA_ERROR = "incorrect_input_comment_data_error";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String commentContent  = request.getParameter(COMMENT_CONTENT);
        request.setAttribute(MAX_NUMBER_SYMBOLS_ATTRIBUTE,MAX_NUMBER_SYMBOLS_VALUE);
        if(commentContent==null || !parameterValidator.isCommentContentValid(commentContent)){
            return forwardToCommentPageWithError(request);
        }
        Comment comment = buildComment(request,commentContent);
        CommentService commentService = new CommentService();
        commentService.save(comment);
        return new CommandResult(COACHES_PAGE,true);
    }

    private Comment buildComment(HttpServletRequest request,String commentContent){
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute(SessionAttributes.ID);
        Long coachId = Long.valueOf(request.getParameter(COACH_ID));
        return new Comment(clientId,coachId,commentContent);
    }

    private CommandResult forwardToCommentPageWithError(HttpServletRequest request) {
        request.setAttribute(INCORRECT_INPUT_COMMENT_DATA_ERROR, true);
        return new CommandResult(COACHES_PAGE,false);
    }
}
