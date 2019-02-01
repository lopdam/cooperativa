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
public class ChoferModificar {

    private Stage sChofer;

    private StackPane rootChoferR;
    private Scene sceneChoferR;

    private Imagen imgFont;

    private VBox vboxMain;

    private Label txtChoferR;

    private Label txtCedula;
    private Label boxCedula;
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
    private Button btnGuardar;
    private Button btnLimpiar;
    private HBox hboxBtn;

    Connection con = DBConexion.conectarMySQL();
    private String chofer;

    public ChoferModificar(String chofer,String nombre,String apellido,String telefono,String celular,String bus) {
        this.chofer=chofer;
        
        sChofer = new Stage();

        rootChoferR = new StackPane();
        sceneChoferR = new Scene(rootChoferR, 440, 480);
        sChofer.setScene(sceneChoferR);
        sChofer.setTitle("Modificar Chofer");

        imgFont = new Imagen("FontAzul3.jpeg", 440, 480);

        txtChoferR = new Label("Modificar Chofer");

        txtCedula = new Label("Cedula:");
        boxCedula = new Label(chofer);
        hboxCedula = new HBox();
        hboxCedula.setSpacing(20);
        hboxCedula.getChildren().addAll(txtCedula, boxCedula);

        txtNombre = new Label("Nombre:");
        boxNombre = new TextField(nombre);
        hboxNombre = new HBox();
        hboxNombre.setSpacing(20);
        hboxNombre.getChildren().addAll(txtNombre, boxNombre);

        txtApellido = new Label("Apellido:");
        boxApellido = new TextField(apellido);
        hboxApellido = new HBox();
        hboxApellido.setSpacing(20);
        hboxApellido.getChildren().addAll(txtApellido, boxApellido);

        txtBus = new Label("Bus:");
        boxBus = new TextField(bus);
        hboxBus = new HBox();
        hboxBus.setSpacing(20);
        hboxBus.getChildren().addAll(txtBus, boxBus);

        txtTelefono = new Label("Telefono:");
        boxTelefono = new TextField(telefono);
        hboxTelefono = new HBox();
        hboxTelefono.setSpacing(20);
        hboxTelefono.getChildren().addAll(txtTelefono, boxTelefono);

        txtCelular = new Label("Celular:");
        boxCelular = new TextField(celular);
        hboxCelular = new HBox();
        hboxCelular.setSpacing(20);
        hboxCelular.getChildren().addAll(txtCelular, boxCelular);

        txtCheck = new Label();
        txtCheck.setTextFill(Color.RED);

        btnCancelar = new Button("Cancelar");
        btnGuardar = new Button("Guardar");
        btnLimpiar = new Button("Limpiar");
        hboxBtn = new HBox();
        hboxBtn.setAlignment(Pos.CENTER);
        hboxBtn.setSpacing(50);
        hboxBtn.getChildren().addAll(btnCancelar, btnGuardar, btnLimpiar);

        vboxMain = new VBox();
        vboxMain.getChildren().addAll(txtChoferR, hboxCedula, hboxNombre, hboxApellido, hboxBus, hboxTelefono, hboxCelular, txtCheck, hboxBtn);
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setSpacing(15);
        rootChoferR.getChildren().addAll(imgFont.getImagen(), vboxMain);
        Eventos();

    }

    public Stage getStageChoferMod() {
        return sChofer;
    }

    private void Eventos() {
        btnCancelar.setOnAction(e -> {
            sChofer.close();
        });

        btnLimpiar.setOnAction(e -> {
            
            boxNombre.setText("");
            boxApellido.setText("");
            boxBus.setText("");
            boxTelefono.setText("");
            boxCelular.setText("");
            txtCheck.setText("");

        });

        btnGuardar.setOnAction(e -> {
            try {

                String Query1 = "UPDATE CHOFER SET NOMBRE='" + boxNombre.getText() + "', APELLIDO='" + boxApellido.getText() + "', TELEFONO='" + boxTelefono.getText() +"', CELULAR='" + boxCelular.getText() + "' WHERE DNI='" + chofer + "'";
                Statement st = con.createStatement();
                java.sql.ResultSet resultSet;
                int res1 = st.executeUpdate(Query1);

                String Query2 = "UPDATE BUS SET DNI_CHOFER='" + chofer + "' WHERE PLACA='" + hboxBus + "'";
                int res2 = st.executeUpdate(Query2);




                //////////////////////
                txtCheck.setText("Cambios Guardados");

            } catch (SQLException ex) {
                System.out.println(ex);
            }

        });
    }

}
