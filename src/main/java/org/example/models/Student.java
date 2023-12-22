package org.example.models;

import java.util.ArrayList;

public class Student {
    private final String name;
    private final Group group;

    private final int activityPoints;
    private final int exercisesPoints;
    private final int homeworksPoints;
    private final int seminaryPoints;
    private ArrayList<Module> modules = new ArrayList<>();

    public Student(String name, Group group,ArrayList<Module> modules, int activityPoints,
                   int exercisesPoints, int homeworksPoints, int seminaryPoints) {
        this.name = name;
        this.group = group;
        this.modules = modules;
        this.activityPoints = activityPoints;
        this.exercisesPoints = exercisesPoints;
        this.homeworksPoints = homeworksPoints;
        this.seminaryPoints = seminaryPoints;
    }

    public String getName() {
        return name;
    }

    public Group getGroup() {
        return group;
    }

    public int getActivityPoints() {
        return activityPoints;
    }

    public int getExercisesPoints() {
        return exercisesPoints;
    }

    public int getHomeworksPoints() {
        return homeworksPoints;
    }

    public int getSeminaryPoints() {
        return seminaryPoints;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public String toString() {
        return name + " " + group.toString();
    }
}
