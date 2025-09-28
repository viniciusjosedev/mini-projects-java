package dev.vinion.hook;

import com.google.gson.Gson;
import dev.vinion.screens.dto.UserDto;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class UserHook {
    private static String getPathUsers() {
        return "./student-grades-systems/database/users";
    }

    public static List<UserDto> getUsers() {
        try {
            String usersJson = Files.readString(Paths.get(getPathUsers() + "/index.json"));
            Gson json = new Gson();

            UserDto[] usersArray = json.fromJson(usersJson, UserDto[].class);

            return usersArray != null ? new ArrayList<>(Arrays.stream(usersArray).toList()) : new ArrayList<>();
        } catch (IOException err) {
            return new ArrayList<>();
        }
    }

    public static Optional<UserDto> findUser(String email, String password) {
        List<UserDto> users = getUsers();

        Stream<UserDto> usersStreamFiltered = users.stream().filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password));

        Object[] usersFiltered = usersStreamFiltered.toArray(UserDto[]::new);

        if (usersFiltered.length == 0) return Optional.empty();

        return (Optional<UserDto>) Optional.of((UserDto) usersFiltered[0]);
    }

    public static void createUser (String email, String password) {
        try {
            List<UserDto> users = getUsers();

            UserDto userToCreate = new UserDto();
            userToCreate.setId(users.toArray().length + 1);
            userToCreate.setEmail(email);
            userToCreate.setPassword(password);

            users.add(userToCreate);

            Gson json = new Gson();

            Files.write(Paths.get(getPathUsers() + "/index.json"), json.toJson(users).getBytes());
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }
 }
