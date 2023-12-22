package org.example;

import org.example.models.Report;
import org.example.parser.Parser;

public class Main {
    public static void main(String[] args) throws Exception {
        Parser parser = new Parser("C:\\Users\\smak\\IdeaProjects\\project\\raw\\basicprogramming_2_1.csv");
        parser.loadDataToClasses();
        Report report = new Report(parser.getStudents());
        var students = report.getStudents();

        for (var student:students){
            System.out.println(student.getName());
            var modules = student.getModules();
            for(var module: modules){
                System.out.printf("%s : %s",module.getName(), module.pointsForAllDisciplines());
                System.out.println();
            }
            break;
        }
    }
}