package com.epam.fitness.uitls.search;

import java.util.List;

public interface SearchSystem<T> {
    List<T> findItemsAppropriateToSearchArgument(List<T> itemsToCheck, String searchArgument);
}
