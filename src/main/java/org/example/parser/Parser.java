package org.example.parser;

import com.opencsv.*;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import org.example.models.Group;
import org.example.models.Module;
import org.example.models.Report;
import org.example.models.Student;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Parser {
    private final String filePath;
    private ArrayList<String> nameOfModules;
    private ArrayList<String> columnNames;
    private ArrayList<Student> students;
    private List<String[]> rows;

    public Parser(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    private CSVReader createCsvReader() throws Exception {
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(';')
                .withIgnoreQuotations(true)
                .build();
        Reader reader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8);
        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withSkipLines(0)
                .withCSVParser(parser)
                .build();

        return csvReader;
    }

    private void dataProcessing() throws Exception {

        var reader = createCsvReader();
        rows = new ArrayList<>();
        String[] line;
        while ((line = reader.readNext()) != null) {
            ArrayList<String> withoutEmptyList = new ArrayList<>();
            for (var data : line) {
                if (!data.isEmpty()) { //!!!!!!!!!!!!!!!!!!!!!
                    withoutEmptyList.add(data);
                }
            }
            rows.add(withoutEmptyList.toArray(new String[0]));
        }
        nameOfModules = new ArrayList<>();

        for (int i = 0; i < rows.get(0).length; i++) {
            if (i != 0 && i != 1 && i != 2) {
                nameOfModules.add(rows.get(0)[i]);
            }
        }

        columnNames = new ArrayList<>();
        columnNames.addAll(Arrays.asList(rows.get(1)));

    }

    public void loadDataToClasses() throws Exception {
        dataProcessing();
        String name;
        Group group;
        int totalAct;
        int totalEX;
        int totalHM;
        int totalSem;
        students = new ArrayList<>();
        for (int i = 3; i < rows.size(); i++) {
            ArrayList<Module> modules = new ArrayList<>();
            HashMap<String, Integer> exercise = new HashMap<String, Integer>();
            HashMap<String, Integer> homeworks = new HashMap<String, Integer>();
            int activityPoints = 0;
            int seminaryPoints = 0;
            name = rows.get(i)[0];
            var grp = rows.get(i)[1];
            group = new Group(grp);
            totalAct = Integer.parseInt(rows.get(i)[2]);
            totalEX = Integer.parseInt(rows.get(i)[3]);
            totalHM = Integer.parseInt(rows.get(i)[4]);
            totalSem = Integer.parseInt(rows.get(i)[5]);
            var counter = 0;
            for (int j = 8; j < columnNames.size(); j++) {
                if (columnNames.get(j).contains("кт") && columnNames.get(j).length() == 3) {
                    activityPoints = Integer.parseInt(rows.get(i)[j]);
                } else if (columnNames.get(j).equals("Сем")) {
                    seminaryPoints = Integer.parseInt(rows.get(i)[j]);
                    Module module = new Module(nameOfModules.get(counter), exercise, homeworks, activityPoints, seminaryPoints);
                    modules.add(module);
                    counter++;
                    exercise = new HashMap<>();
                    homeworks = new HashMap<>();
                } else if (columnNames.get(j).length() > 3 && columnNames.get(j).contains("Упр: ")) {
                    exercise.put(columnNames.get(j).substring(5), Integer.parseInt(rows.get(i)[j]));
                } else if (columnNames.get(j).length() > 3 && columnNames.get(j).contains("ДЗ")) {
                    homeworks.put(columnNames.get(j).substring(4), Integer.parseInt(rows.get(i)[j]));
                }

            }
            Student student = new Student(name, group, modules, totalAct, totalEX, totalHM, totalSem);
            students.add(student);
        }
    }
}