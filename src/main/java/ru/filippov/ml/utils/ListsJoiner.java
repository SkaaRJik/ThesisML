package ru.filippov.ml.utils;

import java.util.ArrayList;
import java.util.List;


public class ListsJoiner<T> {
    public List<T> joinLists(List<List<T>> lists){
        int totalSize = lists.stream().mapToInt(List::size).sum();

        List<T> newList = new ArrayList<>(totalSize);

        for (List<T> list: lists) {
            newList.addAll(list);
        }


        return newList;
    }
}
