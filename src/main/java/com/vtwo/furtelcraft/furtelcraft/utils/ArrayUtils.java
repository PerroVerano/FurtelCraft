package com.vtwo.furtelcraft.furtelcraft.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArrayUtils {
    public static <T> List<String> combineLists(List<List<T>> list, Function<T, String> mapper) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> resList = new ArrayList<>();
        List<String> cacheList;
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                resList.addAll(list.get(i).stream().map(mapper).toList());
            } else {
                cacheList = new ArrayList<>(resList);

                List<T> ts = list.get(i);
                for (int j = 0; j < ts.size(); j++) {
                    T t = ts.get(j);
                    if (j == 0) {
                        resList = resList.stream().map(e -> e + mapper.apply(t)).collect(Collectors.toList());
                    } else {
                        resList.addAll(cacheList.stream().map(e -> e + mapper.apply(t)).toList());
                    }

                }
            }
        }
        return resList;
    }

    public static List<String> filtList(List<String> list, String filter) {
        return list.stream()
                .filter(item -> item.startsWith(filter))
                .toList();
    }

    public static void iteratorRemoveSoild(List<String> list) {
        list.removeIf(cur -> Objects.equals(cur.charAt(3), cur.charAt(5)));
    }
}
