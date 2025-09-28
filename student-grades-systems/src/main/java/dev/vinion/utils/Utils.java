package dev.vinion.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
    public static void createAllFolders() {
        try {
            Files.createDirectories(Paths.get("./student-grades-systems/database/users"));
            Files.createDirectories(Paths.get("./student-grades-systems/database/students"));
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }
}
