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
public class BusModificar {

    private Stage sBus;

    private StackPane rootBusR;
    private Scene sceneBusR;

    private Imagen imgFont;

    private VBox vboxMain;

    private Label txtBusR;

    private Label txtPlaca;
    private Label boxPlaca;
    private HBox hboxPlaca;

    private Label txtCdDuenio;
    private TextField boxCdDuenio;
    private HBox hboxCdDuenio;

    private Label txtCdChofer;
    private TextField boxCdChofer;
    private HBox hboxCdChofer;

    private Label txtCooperativa;
    private TextField boxCooperativa;
    private HBox hboxCooperativa;

    private Label txtCheck;

    private Button btnCancelar;
    private Button btnGuardar;
    private Button btnLimpiar;
    private HBox hboxBtn;

    private String bus;

    Connection con = DBConexion.conectarMySQL();

    public BusModificar(String bus, String duenio, String chofer, String cooperativa) {
        this.bus = bus;

        sBus = new Stage();

        rootBusR = new StackPane();
        sceneBusR = new Scene(rootBusR, 440, 480);
        sBus.setScene(sceneBusR);
        sBus.setTitle("Modificar Bus");

        imgFont = new Imagen("FontAzul3.jpeg", 440, 480);

        txtBusR = new Label("Modificar Bus");

        txtPlaca = new Label("Placa:");
        boxPlaca = new Label(bus);
        hboxPlaca = new HBox();
        hboxPlaca.setSpacing(20);
        hboxPlaca.getChildren().addAll(txtPlaca, boxPlaca);

        txtCdDuenio = new Label("Cedula Duenio:");
        boxCdDuenio = new TextField(duenio);
        hboxCdDuenio = new HBox();
        hboxCdDuenio.setSpacing(20);
        hboxCdDuenio.getChildren().addAll(txtCdDuenio, boxCdDuenio);

        txtCdChofer = new Label("Cedula Chofer:");
        boxCdChofer = new TextField(chofer);
        hboxCdChofer = new HBox();
        hboxCdChofer.setSpacing(20);
        hboxCdChofer.getChildren().addAll(txtCdChofer, boxCdChofer);

        txtCooperativa = new Label("Cooperativa:");
        boxCooperativa = new TextField(cooperativa);
        hboxCooperativa = new HBox();
        hboxCooperativa.setSpacing(20);
        hboxCooperativa.getChildren().addAll(txtCooperativa, boxCooperativa);

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
        vboxMain.getChildren().addAll(txtBusR, hboxPlaca, hboxCdDuenio, hboxCdChofer, hboxCooperativa, txtCheck, hboxBtn);
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setSpacing(15);
        rootBusR.getChildren().addAll(imgFont.getImagen(), vboxMain);
        Eventos();

    }

    public Stage getStageBusMod() {
        return sBus;
    }

    private void Eventos() {
        btnCancelar.setOnAction(e -> {
            sBus.close();
        });

        btnLimpiar.setOnAction(e -> {
            boxCdDuenio.setText("");
            boxCdChofer.setText("");
            boxCooperativa.setText("");
            txtCheck.setText("");

        });

        btnGuardar.setOnAction(e -> {
            try {

                Statement st = con.createStatement();
                java.sql.ResultSet resultSet;


                String Query2 = "UPDATE BUS SET DNI_CHOFER='" + boxCdChofer.getText()+"', DNI_CLIENTE='"+boxCdDuenio.getText() +"', COOPERATIVA='"+boxCooperativa.getText() + "' WHERE PLACA='" + bus + "'";
                int res2 = st.executeUpdate(Query2);

                //////////////////////
                txtCheck.setText("Cambios Guardados");

            } catch (SQLException ex) {
                System.out.println(ex);
            }


            //////////////////////
        });
    }

}
