package com.epam.fitness.command.comment;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Comment;
import com.epam.fitness.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddComment implements Command {

    private final static String COACHES_PAGE = "/controller?command=coaches";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Comment comment = buildComment(request);
        CommentService commentService = new CommentService();
        commentService.save(comment);
        return new CommandResult(COACHES_PAGE,true);
    }

    private Comment buildComment(HttpServletRequest request){
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute("id");
        Long coachId = Long.valueOf(request.getParameter("coach_id"));
        String commentContetnt  = request.getParameter("commentContent");
        return new Comment(clientId,coachId,commentContetnt);
    }
}
