package com.epam.fitness.repository;

import com.epam.fitness.builder.OrderInformationBuilder;
import com.epam.fitness.builder.ProgramBuilder;
import com.epam.fitness.connection.ConnectionPoolException;
import com.epam.fitness.model.OrderInformation;
import com.epam.fitness.model.Program;
import com.epam.fitness.repository.specifications.SqlSpecification;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

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


}
