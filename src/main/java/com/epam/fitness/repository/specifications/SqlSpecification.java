package com.epam.fitness.repository.specifications;

import java.util.List;

public interface SqlSpecification {
    String getSql();
    List<Object> getParameters();
    boolean isFieldRequired(String filed);
}
