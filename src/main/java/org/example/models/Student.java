package org.example.models;

import java.util.ArrayList;

public class Student {
    private final String name;
    private final Group group;

    ArrayList<Module> modules = new ArrayList<>();

    public Student(String name, Group group, ArrayList<Module> modules) {
        this.name = name;
        this.group = group;
        this.modules = modules;
    }

    public String getName() {
        return name;
    }

    public Group getGroup(){
        return group;
    }

    public String toString(){
        return name + " " + group.toString();
    }
}
