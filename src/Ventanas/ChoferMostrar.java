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
public class ChoferMostrar {

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
    private Label boxNombre;
    private HBox hboxNombre;

    private Label txtApellido;
    private Label boxApellido;
    private HBox hboxApellido;

    private Label txtBus;
    private Label boxBus;
    private HBox hboxBus;

    private Label txtTelefono;
    private Label boxTelefono;
    private HBox hboxTelefono;

    private Label txtCelular;
    private Label boxCelular;
    private HBox hboxCelular;

    private Label txtCheck;

    private Button btnSalir;
    private Button btnModificar;
    private Button btnEliminar;
    private HBox hboxBtn;
    Connection con = DBConexion.conectarMySQL();

    private String chofer;
    private String datos[];

    private String linea;

    public ChoferMostrar(String chofer) {
        this.chofer = chofer;

        try {
            String Query = "SELECT C.DNI,C.NOMBRE,C.APELLIDO,C.TELEFONO,C.CELULAR,B.PLACA FROM CHOFER AS C INNER JOIN BUS AS B ON C.DNI=B.DNI_CHOFER HAVING DNI='" + chofer + "'";
            Statement st = con.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);

            while (resultSet.next()) {

                linea = resultSet.getString("DNI")
                        + "--" + resultSet.getString("NOMBRE")
                        + "--" + resultSet.getString("APELLIDO")
                        + "--" + resultSet.getString("TELEFONO")
                        + "--" + resultSet.getString("CELULAR")
                        + "--" + resultSet.getString("PLACA");

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        datos = linea.split("--");

        sChofer = new Stage();

        rootChoferR = new StackPane();
        sceneChoferR = new Scene(rootChoferR, 440, 480);
        sChofer.setScene(sceneChoferR);
        sChofer.setTitle("Mostrar Chofer");

        imgFont = new Imagen("FontAzul3.jpeg", 440, 480);

        txtChoferR = new Label("Mostrar Chofer");

        txtCedula = new Label("Cedula:");
        boxCedula = new Label(chofer);
        hboxCedula = new HBox();
        hboxCedula.setSpacing(20);
        hboxCedula.getChildren().addAll(txtCedula, boxCedula);

        txtNombre = new Label("Nombre:");
        boxNombre = new Label(datos[1]);
        hboxNombre = new HBox();
        hboxNombre.setSpacing(20);
        hboxNombre.getChildren().addAll(txtNombre, boxNombre);

        txtApellido = new Label("Apellido:");
        boxApellido = new Label(datos[2]);
        hboxApellido = new HBox();
        hboxApellido.setSpacing(20);
        hboxApellido.getChildren().addAll(txtApellido, boxApellido);

        txtBus = new Label("Bus:");
        boxBus = new Label(datos[5]);
        hboxBus = new HBox();
        hboxBus.setSpacing(20);
        hboxBus.getChildren().addAll(txtBus, boxBus);

        txtTelefono = new Label("Telefono:");
        boxTelefono = new Label(datos[3]);
        hboxTelefono = new HBox();
        hboxTelefono.setSpacing(20);
        hboxTelefono.getChildren().addAll(txtTelefono, boxTelefono);

        txtCelular = new Label("Celular:");
        boxCelular = new Label(datos[4]);
        hboxCelular = new HBox();
        hboxCelular.setSpacing(20);
        hboxCelular.getChildren().addAll(txtCelular, boxCelular);

        txtCheck = new Label();
        txtCheck.setTextFill(Color.RED);

        btnSalir = new Button("Salir");
        btnModificar = new Button("Modificar");
        btnEliminar = new Button("Eliminar");
        hboxBtn = new HBox();
        hboxBtn.setAlignment(Pos.CENTER);
        hboxBtn.setSpacing(50);
        hboxBtn.getChildren().addAll(btnSalir, btnModificar, btnEliminar);

        vboxMain = new VBox();
        vboxMain.getChildren().addAll(txtChoferR, hboxCedula, hboxNombre, hboxApellido, hboxBus, hboxTelefono, hboxCelular, txtCheck, hboxBtn);
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setSpacing(15);
        rootChoferR.getChildren().addAll(imgFont.getImagen(), vboxMain);
        Eventos();

    }

    public Stage getStageChoferM() {
        return sChofer;
    }

    private void Eventos() {
        btnSalir.setOnAction(e -> {
            sChofer.close();
        });

        btnModificar.setOnAction(e -> {
            ChoferModificar chofMod = new ChoferModificar(chofer, datos[1], datos[2], datos[3], datos[4], datos[5]);
            chofMod.getStageChoferMod().show();

        });

        btnEliminar.setOnAction(e -> {
            try {

                Statement st = con.createStatement();
                java.sql.ResultSet resultSet;

                String Query1 = "DELETE FROM CHOFER WHERE DNI='" + chofer + "'";
                int res1 = st.executeUpdate(Query1);

                //////////////////////
                sChofer.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        });
    }

}
