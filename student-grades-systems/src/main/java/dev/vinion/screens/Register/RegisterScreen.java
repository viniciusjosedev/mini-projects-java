package dev.vinion.screens.Register;

import com.google.gson.Gson;
import dev.vinion.hook.UserHook;
import dev.vinion.screens.dto.GlobalPropsDto;
import dev.vinion.screens.dto.UserDto;
import dev.vinion.screens.interfaces.ScreenChanger;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;

public class RegisterScreen {
    String email;
    String password;
    GlobalPropsDto props;

    public RegisterScreen(GlobalPropsDto props) {
        this.props = props;
    }

    private TextField createInputEmail() {
        TextField inputEmail = new TextField();
        inputEmail.setMaxWidth(200);
        inputEmail.setPromptText("Type your email");
        inputEmail.textProperty().addListener((obs, oldValue, newValue) -> {
            this.email = newValue;
        });

        return inputEmail;
    }

    private TextField createInputPassword() {
        TextField inputPassword = new TextField();
        inputPassword.setMaxWidth(200);
        inputPassword.setPromptText("Type your password");
        inputPassword.textProperty().addListener((obs, oldValue, newValue) -> {
            this.password = newValue;
        });

        return inputPassword;
    }

    private Button createRegisterButton(ScreenChanger onChangeScreen) {
        Button butonLogin = new Button("Sign up");
        butonLogin.setStyle("-fx-background-color: green;");

        butonLogin.setOnAction(event -> {
            UserHook.createUser(this.email, this.password);
            onChangeScreen.changeScreen("login");
        });

        return butonLogin;
    }

    private Label labelLogin(ScreenChanger onChangeScreen) {
        Label labelRegister = new Label("Login");
        labelRegister.setStyle("-fx-text-fill: white; fx-font-weight: bold");
        labelRegister.setOnMouseClicked(event -> {
            onChangeScreen.changeScreen("login");
        });

        return labelRegister;
    }

    public void execute() {
        Label title = new Label("Student Grades Systems");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 30px; fx-font-weight: bold");

        this.props.getVbox().getChildren().setAll(
                title,
                createInputEmail(),
                createInputPassword(),
                createRegisterButton(props.getOnChangeScreen()),
                labelLogin(props.getOnChangeScreen())
        );
    }
}
