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
public class ClienteRegistrar {

    private Stage sCliente;

    private StackPane rootClienteR;
    private Scene sceneClienteR;

    private Imagen imgFont;

    private VBox vboxMain;

    private Label txtClienteR;

    private Label txtCedula;
    private TextField boxCedula;
    private HBox hboxCedula;

    private Label txtNombre;
    private TextField boxNombre;
    private HBox hboxNombre;

    private Label txtApellido;
    private TextField boxApellido;
    private HBox hboxApellido;

    private Label txtFecha;
    private DatePicker boxFecha;
    private HBox hboxFecha;

    private Label txtTelefono;
    private TextField boxTelefono;
    private HBox hboxTelefono;

    private Label txtCelular;
    private TextField boxCelular;
    private HBox hboxCelular;

    private Label txtCorreo;
    private TextField boxCorreo;
    private HBox hboxCorreo;

    private Label txtDirecc;
    private TextField boxDirecc;
    private HBox hboxDirecc;

    private Label txtCheck;

    private Button btnCancelar;
    private Button btnRegistrar;
    private Button btnLimpiar;
    private HBox hboxBtn;

    Connection con = DBConexion.conectarMySQL();

    public ClienteRegistrar() {
        sCliente = new Stage();

        rootClienteR = new StackPane();
        sceneClienteR = new Scene(rootClienteR, 440, 480);
        sCliente.setScene(sceneClienteR);
        sCliente.setTitle("Registrar Cliente");

        imgFont = new Imagen("FontAzul3.jpeg", 440, 480);

        txtClienteR = new Label("Registrar Cliente");

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

        txtFecha = new Label("Fecha de Asociacion:");
        boxFecha = new DatePicker(LocalDate.now());
        hboxFecha = new HBox();
        hboxFecha.setSpacing(20);
        hboxFecha.getChildren().addAll(txtFecha, boxFecha);

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

        txtCorreo = new Label("Correo:");
        boxCorreo = new TextField();
        hboxCorreo = new HBox();
        hboxCorreo.setSpacing(20);
        hboxCorreo.getChildren().addAll(txtCorreo, boxCorreo);

        txtDirecc = new Label("Direccion:");
        boxDirecc = new TextField();
        hboxDirecc = new HBox();
        hboxDirecc.setSpacing(20);
        hboxDirecc.getChildren().addAll(txtDirecc, boxDirecc);

        txtCheck = new Label();
        txtCheck.setText("Ciudad,Manzana,Villa,Descripcion");
        txtCheck.setTextFill(Color.RED);

        btnCancelar = new Button("Cancelar");
        btnRegistrar = new Button("Registrar");
        btnLimpiar = new Button("Limpiar");
        hboxBtn = new HBox();
        hboxBtn.setAlignment(Pos.CENTER);
        hboxBtn.setSpacing(50);
        hboxBtn.getChildren().addAll(btnCancelar, btnRegistrar, btnLimpiar);

        vboxMain = new VBox();
        vboxMain.getChildren().addAll(txtClienteR, hboxCedula, hboxNombre, hboxApellido, hboxFecha, hboxTelefono, hboxCelular, hboxCorreo, hboxDirecc, txtCheck, hboxBtn);
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setSpacing(15);
        rootClienteR.getChildren().addAll(imgFont.getImagen(), vboxMain);
        Eventos();

    }

    public Stage getStageClienteR() {
        return sCliente;
    }

    private void Eventos() {
        btnCancelar.setOnAction(e -> {
            sCliente.close();
        });

        btnLimpiar.setOnAction(e -> {
            boxCedula.setText("");
            boxNombre.setText("");
            boxApellido.setText("");
            boxFecha.setValue(LocalDate.now());
            boxTelefono.setText("");
            boxCelular.setText("");
            boxCorreo.setText("");
            boxDirecc.setText("");
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
                String Query1 = "INSERT INTO CLIENTE VALUE ('"
                        + boxCedula.getText() + "','" + boxNombre.getText() + "', '" + boxApellido.getText() + "','" + boxFecha.getValue() + "')";
                Statement st = con.createStatement();
                java.sql.ResultSet resultSet;
                int res1 = st.executeUpdate(Query1);

                String Query2 = "INSERT INTO CONTACTO VALUE ('"
                        + boxCedula.getText() + "','" + boxTelefono.getText() + "', '" + boxCelular.getText() + "','" + boxCorreo.getText() + "')";

                int res2 = st.executeUpdate(Query2);

                String dir[] = boxDirecc.getText().split(",");

                String Query3 = "INSERT INTO DIRECCION VALUE ('"
                        + boxCedula.getText() + "','" + dir[0] + "', '" + dir[1] + "','" + dir[2] + "','" + dir[3] + "')";

                int res3 = st.executeUpdate(Query3);

            } catch (SQLException ex) {
                System.out.println(ex);
            }
            if (!boxCedula.getText().equals("")
                    && !boxNombre.getText().equals("")
                    && !boxApellido.getText().equals("")
                    && !boxTelefono.getText().equals("")
                    && !boxCelular.getText().equals("")
                    && !boxCorreo.getText().equals("")
                    && !boxDirecc.getText().equals("")) {
                try {
                    String Query = "commit";
                    Statement st = con.createStatement();
                    java.sql.ResultSet resultSet;
                    int res1 = st.executeUpdate(Query);
                    //////////////////////
                    boxCedula.setText("");
                    boxNombre.setText("");
                    boxApellido.setText("");
                    boxFecha.setValue(LocalDate.now());
                    boxTelefono.setText("");
                    boxCelular.setText("");
                    boxCorreo.setText("");
                    boxDirecc.setText("");

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
