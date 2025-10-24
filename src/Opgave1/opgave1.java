package Opgave1;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class opgave1 extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Opgave 1");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    // -------------------------------------------------------------------------

    private final TextField txfName = new TextField();
    private final TextField txfTitle = new TextField();
    private final CheckBox chkSenior = new CheckBox("Senior");
    private final ListView<Person> lvwPerson = new ListView<>();
    private final ArrayList<Person> persons = new ArrayList<>();

    private void initContent(GridPane pane) {
        this.initNames();

        // show or hide grid lines
        pane.setGridLinesVisible(false);
        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        // add a label to the pane (at col=0, row=0)
        Label lblName = new Label("Name:");
        pane.add(lblName, 0, 0);

        // add a label to the pane (at col=0, row=1)
        Label lblTitle = new Label("Title:");
        pane.add(lblTitle, 0, 1);

        // add a label to the pane (at col=0, row=2)
        Label lblPerson = new Label("Persons:");
        pane.add(lblPerson, 0, 2);
        GridPane.setValignment(lblPerson, VPos.TOP);

        // add a text field to the pane (at col=1, row=0)
        pane.add(txfName, 1, 0);

        // add a text field to the pane (at col=1, row=1)
        pane.add(txfTitle, 1, 1);

        pane.add(chkSenior, 2, 1);
        // add a listView to the pane(at col=1, row=2)
        pane.add(lvwPerson, 1, 2);
        lvwPerson.setPrefWidth(200);
        lvwPerson.setPrefHeight(200);
        persons.addAll(this.initNames());
        lvwPerson.getItems().setAll(persons);


        ChangeListener<Person> listener = (ov, oldString, newString) -> this.selectionChanged();
        lvwPerson.getSelectionModel().selectedItemProperty().addListener(listener);

        lvwPerson.getSelectionModel().clearSelection();

        // add a button to the pane (at col=4, row=0)
        Button btnAdd = new Button("Add");
        pane.add(btnAdd, 4, 0);
       // btnAdd.setDefaultButton(true);

        // connect a method to the button
        btnAdd.setOnAction(event -> this.addAction());

        // add a button to the pane (at col=4, row=1)
        Button btnDelete = new Button("Delete");
        pane.add(btnDelete, 4, 1);

        // connect a method to the button
        btnDelete.setOnAction(event -> this.deleteAction());
    }

    // -------------------------------------------------------------------------

    private ArrayList<Person> initNames() {
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("Jane", "Manager", false));
        list.add(new Person("Eva", "Developer", true));
        list.add(new Person("Lene", "Designer", false));
        return list;
    }

    // -------------------------------------------------------------------------
    // Selection changed actions

    private void selectionChanged() {
        Person selected = lvwPerson.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txfName.setText(selected.getNavn());
            txfTitle.setText(selected.getTitle());
            chkSenior.isSelected();

        } else {
            txfName.clear();
            txfTitle.clear();
            chkSenior.setSelected(false);
        }
    }

    // -------------------------------------------------------------------------
    // Button actions

    private void addAction() {
        String name = txfName.getText().trim();
        String title = txfTitle.getText().trim();
        boolean senior = chkSenior.isSelected();

        if (name.length() < 0 || title.length() < 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid input");
            alert.setContentText("Name and Title cannot be empty.");
            alert.showAndWait();
            return;
        }
        Person newPerson = new Person(name, title, senior);
        persons.add(newPerson);
        lvwPerson.getItems().setAll(persons);

        txfName.clear();
        txfTitle.clear();
        chkSenior.setSelected(false);
    }

    private void deleteAction() {
        int index = lvwPerson.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            persons.remove(index);
            txfName.clear();
            lvwPerson.getItems().setAll(persons);
        }
    }
}
