package com.serghienco.ibm.tool;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GenerateEmployeesJson {

    private static final String[] states = new String[]{"AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final ObjectWriter OBJECT_WRITER = OBJECT_MAPPER.writer(new DefaultPrettyPrinter());

    @AllArgsConstructor
    @ToString(includeFieldNames = false)
    @EqualsAndHashCode
    @Data
    private static class EmployeeJson {
        private String firstName;
        private String lastName;
        private String address;
        private String city;
        private String state;
        private int zip;
        private String department;
        private String jobRole;
    }

    public static void main(String[] args) throws IOException {

        Set<EmployeeJson> employees = readEmployees("./names/us", "./names/departments");
        System.out.println(employees.size());
        OBJECT_WRITER.writeValue(new File("./names/employees.json"), employees);
    }

    private static String randomState() {
        return states[(int) (Math.random() * states.length)];
    }

    private static int randomZip() {
        return (int) (Math.random() * 99999) + 1;
    }

    private static String randomString(List<String> list) {
        return list.get((int) (Math.random() * list.size()));
    }

    public static Set<EmployeeJson> readEmployees(String newsPath, String departmentsPath) throws IOException {
        Map<String, List<String>> departments = readDepartments(departmentsPath);
        List<String> departmentNames = new ArrayList<>(departments.keySet());
        Set<EmployeeJson> nodes = new HashSet<>();
        for (File file : readFiles(newsPath)) {
            (OBJECT_MAPPER.readTree(file).get("data")).forEach(node ->
            {
                String[] names = node.get(0).asText().split("\\s");
                String department = randomString(departmentNames);
                String job = randomString(departments.get(department));
                nodes.add(new EmployeeJson(names[0], names[1], node.get(1).asText(), node.get(2).asText(), randomState(), randomZip(), department, job));
            });
        }
        return nodes;
    }

    public static File[] readFiles(String directory) {
        return new File((directory)).listFiles(x -> x.getName().endsWith(".json"));
    }

    public static Map<String, List<String>> readDepartments(String path) throws IOException {
        Map<String, List<String>> map = new HashMap<>();
        for (File f : new File(path).listFiles(x -> x.getName().endsWith(".txt"))) {
            String department = f.getName().substring(0, f.getName().length() - 4);
            List<String> jobs = new ArrayList<>();
            map.put(department, jobs);
            BufferedReader reader = new BufferedReader(new FileReader(f));
            for (String job; (job = reader.readLine()) != null; ) {
                jobs.add(job.trim());
            }
        }
        return map;
    }
}
