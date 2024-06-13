module com.example.studentmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.studentmanager to javafx.fxml;
    exports com.example.studentmanager;
    exports com.example.studentmanager.controllers;
    exports com.example.studentmanager.models;
    opens com.example.studentmanager.controllers to javafx.fxml;
}