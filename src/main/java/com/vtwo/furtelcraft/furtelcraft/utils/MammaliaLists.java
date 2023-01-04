package com.vtwo.furtelcraft.furtelcraft.utils;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MammaliaLists {
    static List<String> m_classes = List.of("W");
    static List<String> m_species = List.of("W", "F", "C", "T", "E", "L", "D", "O", "P", "H", "S", "A", "R", "M", "Y", "B", "X");
    static List<String> m_features = List.of("F");
    static List<String> m_furcolor_f = List.of("G", "O", "Y", "B", "W", "R", "C", "E", "L", "P");
    static List<String> m_module = List.of("B", "J", "F", "T", "Z", "H");
    static List<String> m_furcolor_s = List.of("G", "O", "Y", "B", "W", "R", "C", "E", "L", "P");
    static List<String> m_module_p = List.of("P");

    public static List<String> getSoildColorList() {
        List list = new ArrayList<>();
        list.add(m_classes);
        list.add(m_species);
        list.add(m_features);
        list.add(m_furcolor_f);
        list.add(m_module_p);
        return (List<String>) ArrayUtils.combineLists(list, Object::toString);
    }

    public static @NotNull List<String> getAllLists() {
        List list = new ArrayList<>();
        list.add(m_classes);
        list.add(m_species);
        list.add(m_features);
        list.add(m_furcolor_f);
        list.add(m_module);
        list.add(m_furcolor_s);
        List<String> list2 = new ArrayList<>();
        list2.addAll(ArrayUtils.combineLists(list, Object::toString));
        ArrayUtils.iteratorRemoveSoild(list2);
        list2.addAll(getSoildColorList());
        return list2;
    }

    public static String getMammaliaAssign(int index) {
        return getAllLists().get(index);
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

    public static List<String> getWolfAllList() {
        return ArrayUtils.filtList(getAllLists(), "WWF");
    }

    public static String getWolfAssignSequence(int index) {
        return getWolfAllList().get(index);
    }

    public static String getWolfRandomSequence() {
        return getWolfAllList().get(new Random().nextInt(getWolfAllList().size()));
    }

    public static List<String> getFoxAllList() {
        return ArrayUtils.filtList(getAllLists(), "WFF");
    }

    public static String getFoxAssignSequence(int index) {
        return getFoxAllList().get(index);
    }

    public static String getFoxRandomSequence() {
        return getFoxAllList().get(new Random().nextInt(getFoxAllList().size()));
    }
}
