/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Bases.DBConexion;
import Objetos.Imagen;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author anaklusmos
 */
public class ChoferRegistrar {

    private Stage sChofer;

    private StackPane rootChoferR;
    private Scene sceneChoferR;

    private Imagen imgFont;

    private VBox vboxMain;

    private Label txtChoferR;

    private Label txtCedula;
    private TextField boxCedula;
    private HBox hboxCedula;

    private Label txtNombre;
    private TextField boxNombre;
    private HBox hboxNombre;

    private Label txtApellido;
    private TextField boxApellido;
    private HBox hboxApellido;

    private Label txtBus;
    private TextField boxBus;
    private HBox hboxBus;

    private Label txtTelefono;
    private TextField boxTelefono;
    private HBox hboxTelefono;

    private Label txtCelular;
    private TextField boxCelular;
    private HBox hboxCelular;

    private Label txtCheck;

    private Button btnCancelar;
    private Button btnRegistrar;
    private Button btnLimpiar;
    private HBox hboxBtn;

    Connection con = DBConexion.conectarMySQL();

    public ChoferRegistrar() {
        sChofer = new Stage();

        rootChoferR = new StackPane();
        sceneChoferR = new Scene(rootChoferR, 440, 480);
        sChofer.setScene(sceneChoferR);
        sChofer.setTitle("Registrar Chofer");

        imgFont = new Imagen("FontAzul3.jpeg", 440, 480);

        txtChoferR = new Label("Registrar Chofer");

        txtCedula = new Label("Cedula:");
        boxCedula = new TextField();
        hboxCedula = new HBox();
        hboxCedula.setSpacing(20);
        hboxCedula.getChildren().addAll(txtCedula, boxCedula);

        txtNombre = new Label("Nombre:");
        boxNombre = new TextField();
        hboxNombre = new HBox();
        hboxNombre.setSpacing(20);
        hboxNombre.getChildren().addAll(txtNombre, boxNombre);

        txtApellido = new Label("Apellido:");
        boxApellido = new TextField();
        hboxApellido = new HBox();
        hboxApellido.setSpacing(20);
        hboxApellido.getChildren().addAll(txtApellido, boxApellido);

        txtBus = new Label("Bus:");
        boxBus = new TextField();
        hboxBus = new HBox();
        hboxBus.setSpacing(20);
        hboxBus.getChildren().addAll(txtBus, boxBus);

        txtTelefono = new Label("Telefono:");
        boxTelefono = new TextField();
        hboxTelefono = new HBox();
        hboxTelefono.setSpacing(20);
        hboxTelefono.getChildren().addAll(txtTelefono, boxTelefono);

        txtCelular = new Label("Celular:");
        boxCelular = new TextField();
        hboxCelular = new HBox();
        hboxCelular.setSpacing(20);
        hboxCelular.getChildren().addAll(txtCelular, boxCelular);

        txtCheck = new Label();
        txtCheck.setTextFill(Color.RED);

        btnCancelar = new Button("Cancelar");
        btnRegistrar = new Button("Registrar");
        btnLimpiar = new Button("Limpiar");
        hboxBtn = new HBox();
        hboxBtn.setAlignment(Pos.CENTER);
        hboxBtn.setSpacing(50);
        hboxBtn.getChildren().addAll(btnCancelar, btnRegistrar, btnLimpiar);

        vboxMain = new VBox();
        vboxMain.getChildren().addAll(txtChoferR, hboxCedula, hboxNombre, hboxApellido, hboxTelefono, hboxCelular, txtCheck, hboxBtn);
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setSpacing(15);
        rootChoferR.getChildren().addAll(imgFont.getImagen(), vboxMain);
        Eventos();

    }

    public Stage getStageChoferR() {
        return sChofer;
    }

    private void Eventos() {
        btnCancelar.setOnAction(e -> {
            sChofer.close();
        });

        btnLimpiar.setOnAction(e -> {
            boxCedula.setText("");
            boxNombre.setText("");
            boxApellido.setText("");
            boxBus.setText("");
            boxTelefono.setText("");
            boxCelular.setText("");
            txtCheck.setText("");

        });

        btnRegistrar.setOnAction(e -> {
            try {
                String Query = "start transaction";
                Statement st = con.createStatement();
                java.sql.ResultSet resultSet;
                int res1 = st.executeUpdate(Query);

            } catch (SQLException ex) {
                System.out.println(ex);
            }

            try {
                String Query = "INSERT INTO CHOFER VALUE ('"
                        + boxCedula.getText() + "','" + boxNombre.getText() + "', '" + boxApellido.getText() + "','" + boxTelefono.getText() + "','" + boxCelular.getText() + "')";
                Statement st = con.createStatement();
                java.sql.ResultSet resultSet;
                int res1 = st.executeUpdate(Query);

            } catch (SQLException ex) {
                System.out.println(ex);
            }

            if (!boxCedula.getText().equals("")
                    && !boxNombre.getText().equals("")
                    && !boxApellido.getText().equals("")
                    && !boxTelefono.getText().equals("")
                    && !boxCelular.getText().equals("")) {

                try {
                    String Query = "commit";
                    Statement st = con.createStatement();
                    java.sql.ResultSet resultSet;
                    int res1 = st.executeUpdate(Query);
                    //////////////////////
                    //////////////////////
                    boxCedula.setText("");
                    boxNombre.setText("");
                    boxApellido.setText("");
                    boxTelefono.setText("");
                    boxCelular.setText("");

                    txtCheck.setText("Registrado");

                } catch (SQLException ex) {
                    System.out.println(ex);
                }

            } else {
                try {
                    String Query = "rollback";
                    Statement st = con.createStatement();
                    java.sql.ResultSet resultSet;
                    int res1 = st.executeUpdate(Query);

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }

        });
    }

}
