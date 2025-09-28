package dev.vinion.screens.Login;

import dev.vinion.hook.UserHook;
import dev.vinion.screens.dto.GlobalPropsDto;
import dev.vinion.screens.dto.UserDto;
import dev.vinion.screens.interfaces.ScreenChanger;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.controlsfx.control.NotificationPane;

import java.util.Optional;


public class LoginScreen {
    String email;
    String password;
    GlobalPropsDto props;
    NotificationPane notificationPane;

    public LoginScreen(GlobalPropsDto props) {
        this.props = props;
        this.notificationPane = new NotificationPane();
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

    private Button createLoginButton(ScreenChanger onChangeScreen) {
        Button butonLogin = new Button("Login");
        butonLogin.setStyle("-fx-background-color: green;");

        butonLogin.setOnAction(event -> {
            this.notificationPane.hide();

            Optional<UserDto> user = UserHook.findUser(this.email, this.password);

            if (user.isEmpty()) {
                Label contentLabel = new Label("User no have account");

                this.notificationPane.setContent(contentLabel);

                this.props.getVbox().getChildren().add(this.notificationPane);
                this.notificationPane.show();

                return;
            }

            this.props.setUserId(user.get().getId());
            onChangeScreen.changeScreen("home");
        });

        return butonLogin;
    }

    private Label labelRegister(ScreenChanger onChangeScreen) {
        Label labelRegister = new Label("Register");
        labelRegister.setStyle("-fx-text-fill: white; fx-font-weight: bold");
        labelRegister.setOnMouseClicked(event -> onChangeScreen.changeScreen("register"));

        return labelRegister;
    }

    public void execute() {
        Label title = new Label("Student Grades Systems");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 30px; fx-font-weight: bold");

        this.props.getVbox().getChildren().setAll(title,
                createInputEmail(),
                createInputPassword(),
                createLoginButton(props.getOnChangeScreen()),
                labelRegister(props.getOnChangeScreen())
        );
    }
}
