package com.epam.fitness.command.comment;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.command.session.SessionAttributes;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Comment;
import com.epam.fitness.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddComment implements Command {

    private final static String COACHES_PAGE = "/controller?command=coaches";
    private final static String COACH_ID = "coach_id";
    private final static String COMMENT_CONTENT = "commentContent";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Comment comment = buildComment(request);
        CommentService commentService = new CommentService();
        commentService.save(comment);
        return new CommandResult(COACHES_PAGE,true);
    }

    private Comment buildComment(HttpServletRequest request){
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute(SessionAttributes.ID);
        Long coachId = Long.valueOf(request.getParameter(COACH_ID));
        String commentContent  = request.getParameter(COMMENT_CONTENT);
        return new Comment(clientId,coachId,commentContent);
    }
}
