package dev.vinion;

import dev.vinion.home.HomeScreen;
import dev.vinion.screens.Login.LoginScreen;
import dev.vinion.screens.dto.GlobalPropsDto;
import dev.vinion.screens.Register.RegisterScreen;
import dev.vinion.utils.Utils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentGradesSystems extends Application {
    private LoginScreen loginScreen;
    private RegisterScreen registerScreen;
    private HomeScreen homeScreen;

    private void changeScreen(String screen) {
        switch (screen) {
            case "register": {
                this.registerScreen.execute();
                break;
            }

            case "home": {
                this.homeScreen.execute();
                break;
            }

            default:
            case "login": {
                this.loginScreen.execute();
                break;
            }
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            Utils.createAllFolders();

            VBox vBox = new VBox(10);
            vBox.setStyle("-fx-alignment: center; -fx-background-color: black");
            Scene scene = new Scene(vBox);

            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();

            GlobalPropsDto globalPropsDto = new GlobalPropsDto();
            globalPropsDto.setVbox(vBox);
            globalPropsDto.setOnChangeScreen(this::changeScreen);

            this.loginScreen = new LoginScreen(globalPropsDto);
            this.registerScreen = new RegisterScreen(globalPropsDto);
            this.homeScreen = new HomeScreen(globalPropsDto);

            this.loginScreen.execute();
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}