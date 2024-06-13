package com.example.studentmanager.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.example.studentmanager.models.Student;
import com.example.studentmanager.mysqlconnect;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

public class MainController implements Initializable {


    @FXML
    private TableView<Student> table_students;

    @FXML
    private TableColumn<Student, Integer> col_id;

    @FXML
    private TableColumn<Student, String> col_name;

    @FXML
    private TableColumn<Student, String> col_email;

    @FXML
    private TableColumn<Student, String> col_major;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_major;

    ObservableList<Student> listM;
    ObservableList<Student> dataList;



    int index = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    public void Add_users (){
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into students (id, name, email,major)values(?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.setString(2, txt_name.getText());
            pst.setString(3, txt_email.getText());
            pst.setString(4, txt_major.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Student Added");
            UpdateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


    //////// methode select users ///////
    @FXML
    void getSelected (MouseEvent event){
        index = table_students.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        txt_name.setText(col_name.getCellData(index));
        txt_email.setText(col_email.getCellData(index));
        txt_major.setText(col_major.getCellData(index));

    }

    public void Edit (){
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_name.getText();
            String value3 = txt_email.getText();
            String value4 = txt_major.getText();

            String sql = "update students set id= '"+value1+"',name= '"+value2+
                    "',email= '"+value3+"',major= '"+value4+"' where id='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Student Updated");
            UpdateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void Delete(){
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from students where id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Student Deleted");
            UpdateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }


    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Student,Integer>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
        col_email.setCellValueFactory(new PropertyValueFactory<Student,String>("email"));
        col_major.setCellValueFactory(new PropertyValueFactory<Student,String>("major"));

        listM = mysqlconnect.getStudents();
        table_students.setItems(listM);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
        // Code Source in description
    }
}