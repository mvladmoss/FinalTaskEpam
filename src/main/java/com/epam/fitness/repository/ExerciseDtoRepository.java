package com.epam.fitness.repository;

import com.epam.fitness.builder.ExerciseDtoBuilder;
import com.epam.fitness.model.Exercise;
import com.epam.fitness.model.dto.ExerciseDto;
import com.epam.fitness.repository.specifications.SqlSpecification;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ExerciseDtoRepository extends AbstractRepository<ExerciseDto> {

    private final static String INSERT_QUERY = "insert into exercise_program (id,program_id,exercise_id," +
            "repeat_number,set_number,number_train_day) " +
            "values(?,?,?,?,?,?) " +
            "on duplicate key " +
            "update id = values(id), program_id = values(program_id), exercise_id = values(exercise_id)," +
            "repeat_number = values(repeat_number), set_number = values(set_number), " +
            "number_train_day = values(number_train_day)";

    private final static String DELETE_QUERY = "delete from exercise_program where id=(?)";

    public ExerciseDtoRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    public List<ExerciseDto> query(SqlSpecification specification) throws RepositoryException{
        String query = "select * from exercise " + specification.getSql();
        List<ExerciseDto> exercises = executeQuery(query,new ExerciseDtoBuilder(), specification.getParameters());
        return exercises;
    }

    @Override
    public Optional<ExerciseDto> queryForSingleResult(SqlSpecification specification) throws RepositoryException {
        List<ExerciseDto> exercise = query(specification);
        return exercise.size() == 1 ?
                Optional.of(exercise.get(0)) :
                Optional.empty();
    }

    public Long save(ExerciseDto exerciseDto) throws RepositoryException {
        Optional<Long> id = Optional.ofNullable(exerciseDto.getId());
        String idString;
        idString = id.map(String::valueOf).orElse(null);
        long programId = exerciseDto.getProgramId();
        String programIdString = String.valueOf(programId);
        long exerciseId = exerciseDto.getExercise().getId();
        String exerciseIdString = String.valueOf(exerciseId);
        int repeatNumber = exerciseDto.getRepeatNumber();
        String repeatNumberString = String.valueOf(repeatNumber);
        int setNumber = exerciseDto.getSetNumber();
        String setNumberString = String.valueOf(setNumber);
        int trainDay = exerciseDto.getNumberTrainDay();
        String trainDayString = String.valueOf(trainDay);
        return executeUpdate(INSERT_QUERY,Arrays.asList(idString,programIdString,exerciseIdString,repeatNumberString,setNumberString,trainDayString));
    }

    public Long delete(long exerciseDtoId) throws RepositoryException {
        return executeUpdate(DELETE_QUERY,Arrays.asList(exerciseDtoId));
    }
}
