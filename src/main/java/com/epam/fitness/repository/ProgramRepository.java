package com.epam.fitness.repository;

import com.epam.fitness.builder.ProgramBuilder;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.OrderInformation;
import com.epam.fitness.model.Program;
import com.epam.fitness.repository.database.constants.OrderInformationTableConstants;
import com.epam.fitness.repository.database.constants.ProgramTableConstants;
import com.epam.fitness.repository.specifications.SqlSpecification;

import java.sql.Connection;
import java.util.*;

public class ProgramRepository extends AbstractRepository<Program> {

    private static final String TABLE_NAME = "program";

    public ProgramRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public List<Program> query(SqlSpecification specification) throws RepositoryException {
        String query = "select * from program " + specification.getSql();
        List<Program> programs = executeQuery(query,new ProgramBuilder(), specification.getParameters());
        return programs;
    }

    @Override
    public Optional<Program> queryForSingleResult(SqlSpecification specification) throws RepositoryException {
        List<Program> program = query(specification);
        return program.size() == 1 ?
                Optional.of(program.get(0)) :
                Optional.empty();
    }

    @Override
    protected Map<String, Object> getFields(Program program) {
        Map<String,Object> fields = new HashMap<>();
        fields.put(ProgramTableConstants.ID.getFieldName(),program.getId());
        fields.put(ProgramTableConstants.NUTRITION_ID.getFieldName(),program.getNutrition().getId());
        fields.put(ProgramTableConstants.TRAINS_PER_WEEK.getFieldName(),program.getTrainsPerWeek());
        return fields;
    }


}
