package org.example.models;

import java.util.HashMap;

public class Module {
    private final String name;
    HashMap<String, Integer> exercises = new HashMap<>();
    HashMap<String, Integer> homeworks = new HashMap<>();
    protected final int pointsForActivity;
    protected final int pointsForSeminary;

    public Module(String name, HashMap<String, Integer> exercises, HashMap<String, Integer> homeworks, int activity, int seminary) {
        this.name = name;
        this.exercises = exercises;
        this.homeworks = homeworks;
        this.pointsForActivity = activity;
        this.pointsForSeminary = seminary;
    }

    public String getName() {
        return name;
    }

    public int getPointsForActivity() {
        return pointsForActivity;
    }

    public int getPointsForSeminary() {
        return pointsForSeminary;
    }

    public int getPointsFromExercises() {
        var result = 0;
        for (var entry : exercises.entrySet()) {
            result += entry.getValue();
        }
        return result;
    }

    public int getPointsFromHomeworks() {
        var result = 0;
        for (var entry : homeworks.entrySet()) {
            result += entry.getValue();
        }
        return result;
    }

    public String pointsForAllDisciplines() {
        return String.format("Активность - %s; Упражнения - %s; ДЗ - %s; Семинарские - %s;", pointsForActivity, getPointsFromExercises(), getPointsFromHomeworks(), pointsForSeminary);
    }
}
