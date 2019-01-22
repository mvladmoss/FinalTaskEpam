package com.epam.fitness.builder;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Nutrition;
import com.epam.fitness.model.Program;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.repository.database.constants.ProgramTableConstants;
import com.epam.fitness.service.NutritionService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ProgramBuilder implements Builder<Program> {
    @Override
    public Program build(ResultSet resultSet) throws RepositoryException {
        Program program = new Program();
        try {
            Long id = resultSet.getLong(ProgramTableConstants.ID.getFieldName());
            program.setId(id);
            Long nutritionId = resultSet.getLong(ProgramTableConstants.NUTRITION_ID.getFieldName());
            Integer trainsPerWeek = resultSet.getInt(ProgramTableConstants.TRAINS_PER_WEEK.getFieldName());
            return new Program();
        }catch (SQLException exception){
            throw new RepositoryException(exception.getMessage(),exception);
        }
    }
}
