package dev.vinion.home;

import dev.vinion.hook.StudentsHook;
import dev.vinion.screens.dto.GlobalPropsDto;
import dev.vinion.screens.dto.StudentDto;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.*;

public class HomeScreen {
    GlobalPropsDto props;
    ScrollPane scrollPane;
    HBox menu;
    BorderPane root;

    public HomeScreen(GlobalPropsDto props) {
        this.props = props;
    }

    private Map<String, Node> createFormsToAdd() {
        TextField name = new TextField();
        TextField note = new TextField();

        name.setMaxWidth(200);
        name.setPromptText("Type student name");

        note.setMaxWidth(200);
        note.setPromptText("Type student note");

        Button addGrades = new Button("Add Grade");
        addGrades.setStyle("-fx-background-color: green;");
        addGrades.setDisable(true);

        name.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!name.textProperty().getValue().isEmpty() && !note.textProperty().getValue().isEmpty()) {
                addGrades.setDisable(false);
            } else {
                addGrades.setDisable(true);
            }
        });

        note.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                note.setText(newValue.replaceAll("\\D", ""));
            }

            if (!name.textProperty().getValue().isEmpty() && !note.textProperty().getValue().isEmpty()) {
                addGrades.setDisable(false);
            } else {
                addGrades.setDisable(true);
            }
        });

        addGrades.setOnAction(event -> {
            StudentsHook.createGrade(
                    this.props.getUserId(),
                    name.textProperty().getValue(),
                    Integer.parseInt(note.textProperty().getValue())
            );

            name.textProperty().setValue("");
            note.textProperty().setValue("");
        });

        Map<String, Node> forms = new HashMap<String, Node>();
        forms.put("name", name);
        forms.put("note", note);
        forms.put("addGrades", addGrades);

        return forms;

    }

    private void createMenu() {
        Button listStudents = new Button("List Students");
        Button addNote = new Button("Add Note");

        listStudents.setStyle("-fx-background-color: blue");
        addNote.setStyle("-fx-background-color: orange");

        listStudents.setOnAction(event -> {
            this.createScrollPane();
            this.root.setCenter(this.scrollPane);
        });

        Map<String, Node> forms = this.createFormsToAdd();

        addNote.setOnAction(event -> {
            this.root.setCenter(null);

            VBox div = new VBox(10, forms.get("name"), forms.get("note"),forms.get("addGrades"));
            div.setAlignment(Pos.CENTER);

            this.root.setCenter(div);
        });

        this.menu = new HBox(10, listStudents, addNote);
        menu.setAlignment(Pos.CENTER);
        menu.setMinHeight(200);
    }

    private void createScrollPane() {
        VBox listBox = new VBox(5);
        listBox.setAlignment(Pos.CENTER);
        listBox.setStyle("-fx-background-color: black;");

        List<StudentDto> students = StudentsHook.getStudents();
        students = new ArrayList<>(students.stream().filter(student -> Objects.equals(student.getUserId(), this.props.getUserId())).toList());

        for (int i = 0; i < students.toArray().length; i++) {
            Label label = new Label(String.format(
                "Name: %s, note: %d",
                students.get(i).getName(),
                students.get(i).getNote().intValue()
            ));
            label.setStyle("-fx-text-fill: white; fx-font-weight: bold");
            label.maxWidth(200);

            listBox.getChildren().add(label);
        }

        this.scrollPane = new ScrollPane(listBox);
        this.scrollPane.setStyle("-fx-background: black; -fx-background-color: black; -fx-border-color: transparent;");
        this.scrollPane.setFitToWidth(true);
    }

    public void execute() {
        this.props.getVbox().getChildren().clear();

        this.createMenu();
        this.createScrollPane();

        this.root = new BorderPane();

        this.root.setTop(this.menu);
        this.root.setCenter(this.scrollPane);

        this.props.getVbox().getChildren().add(this.root);
    }
}
