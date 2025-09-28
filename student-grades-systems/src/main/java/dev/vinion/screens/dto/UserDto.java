package dev.vinion.screens.dto;

import lombok.Getter;
import lombok.Setter;

public class UserDto {
    @Getter @Setter
    Number id;

    @Getter @Setter
    String email;

    @Getter @Setter
    String password;
}
