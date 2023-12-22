package org.example.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Report {
    private ArrayList<Student> students = new ArrayList<>();

    public Report(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Student> getStudents(){
        return students;
    }

    /*public HashMap<Student, String> getTotalPoints() {
        HashMap<Student, String> report = new HashMap<>();
        StringBuilder totalPoints = new StringBuilder();
        for (var student : students) {
            var activityPoints = 0;
            var exercisesPoints = 0;
            var homeworksPoints = 0;
            var seminaryPoints = 0;
            for (int i = 0; i < student.modules.size(); i++) {
                var allPoints = student.modules.get(i).pointsForAllDisciplines().split(" ");
                activityPoints += Integer.parseInt(allPoints[0]);
                exercisesPoints += Integer.parseInt(allPoints[1]);
                homeworksPoints += Integer.parseInt(allPoints[2]);
                seminaryPoints += Integer.parseInt(allPoints[3]);
                if (i == student.modules.size() - 1){
                    totalPoints.append(activityPoints + " " + exercisesPoints + " " + homeworksPoints + " " + seminaryPoints);
                }
            }
            report.put(student, totalPoints.toString());
        }
        return report;
    }*/

    public void report(){
        for(var student: students){
            System.out.printf(String.format("%s %s : Активность - %s; Упражнения - %s; ДЗ - %s; Семинарские - %s;",student.getName(),student.getGroup().name(),
            student.getActivityPoints(), student.getExercisesPoints(), student.getHomeworksPoints(), student.getSeminaryPoints()));
            System.out.println();
        }
    }
}
