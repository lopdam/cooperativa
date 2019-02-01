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
public class BusRegistrar {

    private Stage sBus;

    private StackPane rootBusR;
    private Scene sceneBusR;

    private Imagen imgFont;

    private VBox vboxMain;

    private Label txtBusR;

    private Label txtPlaca;
    private TextField boxPlaca;
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
    private Button btnRegistrar;
    private Button btnLimpiar;
    private HBox hboxBtn;
    Connection con = DBConexion.conectarMySQL();

    public BusRegistrar() {
        sBus = new Stage();

        rootBusR = new StackPane();
        sceneBusR = new Scene(rootBusR, 440, 480);
        sBus.setScene(sceneBusR);
        sBus.setTitle("Registrar Bus");

        imgFont = new Imagen("FontAzul3.jpeg", 440, 480);

        txtBusR = new Label("Registrar Bus");

        txtPlaca = new Label("Placa:");
        boxPlaca = new TextField();
        hboxPlaca = new HBox();
        hboxPlaca.setSpacing(20);
        hboxPlaca.getChildren().addAll(txtPlaca, boxPlaca);

        txtCdDuenio = new Label("Cedula Duenio:");
        boxCdDuenio = new TextField();
        hboxCdDuenio = new HBox();
        hboxCdDuenio.setSpacing(20);
        hboxCdDuenio.getChildren().addAll(txtCdDuenio, boxCdDuenio);

        txtCdChofer = new Label("Cedula Chofer:");
        boxCdChofer = new TextField();
        hboxCdChofer = new HBox();
        hboxCdChofer.setSpacing(20);
        hboxCdChofer.getChildren().addAll(txtCdChofer, boxCdChofer);

        txtCooperativa = new Label("Cooperativa:");
        boxCooperativa = new TextField();
        hboxCooperativa = new HBox();
        hboxCooperativa.setSpacing(20);
        hboxCooperativa.getChildren().addAll(txtCooperativa, boxCooperativa);

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
        vboxMain.getChildren().addAll(txtBusR, hboxPlaca, hboxCdDuenio, hboxCdChofer, hboxCooperativa, txtCheck, hboxBtn);
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setSpacing(15);
        rootBusR.getChildren().addAll(imgFont.getImagen(), vboxMain);
        Eventos();

    }

    public Stage getStageBusR() {
        return sBus;
    }

    private void Eventos() {
        btnCancelar.setOnAction(e -> {
            sBus.close();
        });

        btnLimpiar.setOnAction(e -> {
            boxPlaca.setText("");
            boxCdDuenio.setText("");
            boxCdChofer.setText("");
            boxCooperativa.setText("");
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
                String Query1 = "INSERT INTO BUS VALUE ('"
                        + boxPlaca.getText() + "','" + boxCdDuenio.getText() + "', '" + boxCdChofer.getText() + "','" + boxCooperativa.getText() + "')";
                Statement st = con.createStatement();
                java.sql.ResultSet resultSet;
                int res1 = st.executeUpdate(Query1);

            } catch (SQLException ex) {
                System.out.println(ex);
            }

            if (!boxPlaca.getText().equals("")
                    && !boxCdDuenio.getText().equals("")
                    && !boxCdChofer.getText().equals("")
                    && !boxCooperativa.getText().equals("")) {

                try {
                    String Query = "commit";
                    Statement st = con.createStatement();
                    java.sql.ResultSet resultSet;
                    int res1 = st.executeUpdate(Query);
                    //////////////////////

                    //////////////////////
                    boxPlaca.setText("");
                    boxCdDuenio.setText("");
                    boxCdChofer.setText("");
                    boxCooperativa.setText("");

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
