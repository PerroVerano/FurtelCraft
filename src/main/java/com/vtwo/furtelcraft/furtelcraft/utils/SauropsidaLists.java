package com.vtwo.furtelcraft.furtelcraft.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SauropsidaLists {
    static List<String> s_classes = List.of("G");
    static List<String> s_species = List.of("D");
    static List<String> s_features = List.of("F", "S");
    static List<String> s_furcolor_f = List.of("G", "O", "Y", "B", "W", "R", "C", "E", "L", "P");
    static List<String> s_module = List.of("B", "J", "F", "T", "Z", "H");
    static List<String> s_furcolor_s = List.of("G", "O", "Y", "B", "W", "R", "C", "E", "L", "P");
    static List<String> s_module_p = List.of("P");

    public static List<String> getSoildColorList() {
        List list = new ArrayList<>();
        list.add(s_classes);
        list.add(s_species);
        list.add(s_features);
        list.add(s_furcolor_f);
        list.add(s_module_p);
        return (List<String>) ArrayUtils.combineLists(list, Object::toString);
    }

    public static List<String> getAllLists() {
        List list = new ArrayList<>();
        list.add(s_classes);
        list.add(s_species);
        list.add(s_features);
        list.add(s_furcolor_f);
        list.add(s_module);
        list.add(s_furcolor_s);
        List<String> result = new ArrayList<>();
        result.addAll(ArrayUtils.combineLists(list, Object::toString));
        ArrayUtils.iteratorRemoveSoild(result);
        result.addAll(getSoildColorList());
        return result;
    }

    public static String getSauropsidaAssign(int index) {
        List<String> list = getAllLists();
        return list.get(index);
    }

    public static void outputFile(String path) {
        try {
            List<String> list = getAllLists();
            File file = new File(path);
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8), 40960);
            for (String s : list) {
                out.write(s + "\r\n");
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getDragonAllList() {
        return ArrayUtils.filtList(getAllLists(), "GD");
    }

    public static String getDragonAssignSequence(int index) {
        return getDragonAllList().get(index);
    }

    public static String getDragonRandomSequence() {
        return getDragonAllList().get(new Random().nextInt(getDragonAllList().size()));
    }
}
