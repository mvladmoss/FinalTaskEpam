package com.epam.fitness.command.comment;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Comment;
import com.epam.fitness.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowComments implements Command {

    private static final String COACH_COMMENTS_PAGE = "/WEB-INF/coachCommentsPage.jsp";
    private static final String COMMENTS = "comments";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Long coachId = (Long) session.getAttribute("id");
        CommentService commentService = new CommentService();
        List<Comment> comments = commentService.findByCoachId(coachId);
        request.setAttribute(COMMENTS,comments);
        for(Comment comment : comments){
            System.out.println(comment.getCommmentContent());
        }
        return new CommandResult(COACH_COMMENTS_PAGE,false);
    }
}
