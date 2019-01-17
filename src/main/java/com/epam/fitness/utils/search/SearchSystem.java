package com.epam.fitness.utils.search;

import java.util.List;

public interface SearchSystem<T> {
    List<T> findItemsAppropriateToSearchArgument(List<T> itemsToCheck, String searchArgument);
}
