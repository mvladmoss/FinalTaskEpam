package com.epam.fitness.uitls.json;

import java.util.List;

public interface JsonCreator<T> {
     String makeJSON(List<T> items);
}
