package com.epam.fitness.utils.json;

import java.util.List;

public interface JsonCreator<T> {
     String makeJSON(List<T> items);
}
