package com.epam.fitness.repository;

import com.epam.fitness.builder.ProgramBuilder;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Program;
import com.epam.fitness.repository.specifications.SqlSpecification;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProgramRepository extends AbstractRepository<Program> {

    private final static String INSERT_QUERY = "insert into program (id_program,nutrition_id,description,trains_per_week) " +
            "values(?,?,?,?) " +
            "on duplicate key " +
            "update id_program = values(id_program), nutrition_id = values(nutrition_id), " +
            "description = values(description), trains_per_week = values(trains_per_week)";

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

    public Long save(Program program) throws RepositoryException {
        Long programId = program.getId();
        Long nutritionId = program.getNutrition().getId();
        String description = program.getDescription();
        int trainsPerWeek = program.getTrainsPerWeek();
        return executeUpdate(INSERT_QUERY,Arrays.asList(programId,nutritionId,description,trainsPerWeek));
    }


}
