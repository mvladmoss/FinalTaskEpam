package com.epam.fitness.command;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.dto.ProgramDto;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.service.ProgramDtoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ClientProgramCommand implements Command {

    private final static String PROGRAM = "programDto";
    private final static String PROGRAM_PAGE = "/WEB-INF/clientProgram.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        long clientId = (long) session.getAttribute("id");
        ClientService clientService = new ClientService();
        Optional<Client> client = clientService.findById(clientId);
        if(client.isPresent()) {
            Optional<Long> programId = Optional.of(client.get().getProgramId());
            if (programId.isPresent()) {
                ProgramDtoService programDtoService = new ProgramDtoService();
                Optional<ProgramDto> programDto = programDtoService.findProgramDtoById(programId.get());
                request.setAttribute(PROGRAM, programDto.get());
            }
        }
        return new CommandResult(PROGRAM_PAGE,false);
    }
}
