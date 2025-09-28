package dev.vinion.hook;

import com.google.gson.Gson;
import dev.vinion.screens.dto.StudentDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class StudentsHook {
    private static String getPathStudents() {
        return "./student-grades-systems/database/students";
    }

    public static List<StudentDto> getStudents() {
        try {
            String studentsJson = Files.readString(Paths.get(getPathStudents() + "/index.json"));

            StudentDto[] studentsArray = new Gson().fromJson(studentsJson, StudentDto[].class);

            return studentsArray != null ? new ArrayList<>(Arrays.stream(studentsArray).toList()) : new ArrayList<StudentDto>();
        } catch (IOException err) {
            return new ArrayList<StudentDto>();
        }
    }

    public static void createGrade(Number userId, String name, Number note) {
        try {
            List<StudentDto> students = getStudents();

            StudentDto student = new StudentDto();

            student.setId(students.toArray().length + 1);
            student.setUserId(userId);
            student.setName(name);
            student.setNote(note);

            students.add(student);

            Files.write(Paths.get(getPathStudents() + "/index.json"), new Gson().toJson(students).getBytes());
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }
}
