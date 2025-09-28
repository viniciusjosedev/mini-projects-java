package dev.vinion.screens.dto;

import dev.vinion.screens.interfaces.ScreenChanger;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

public class GlobalPropsDto {
    @Getter @Setter
    VBox vbox;

    @Getter @Setter
    ScreenChanger onChangeScreen;

    @Getter @Setter
    Number userId;
}
