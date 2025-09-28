package dev.vinion.screens.dto;

import lombok.Getter;
import lombok.Setter;

public class StudentDto {
    @Getter
    @Setter
    Number id;

    @Getter @Setter
    Number userId;

    @Getter @Setter
    String name;

    @Getter @Setter
    Number note;
}

