package com.vtwo.furtelcraft.furtelcraft.utils;

public class ProgressBar {
    static StringBuilder builder = new StringBuilder();
    public static String ProgressBarTooltip(int percent, int total) {
        String complete = "-";
        for (int i = 0; i < percent / 20; i++) {
            builder.replace(i,total / 20, complete);
        }
        return builder.toString();
    }
}
