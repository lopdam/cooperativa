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
public class BusMostrar {

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
    private Label boxCdDuenio;
    private HBox hboxCdDuenio;

    private Label txtCdChofer;
    private Label boxCdChofer;
    private HBox hboxCdChofer;

    private Label txtCooperativa;
    private Label boxCooperativa;
    private HBox hboxCooperativa;

    private Label txtCheck;

    private Button btnSalir;
    private Button btnModificar;
    private Button btnEliminar;
    private HBox hboxBtn;
    private String bus;

    Connection con = DBConexion.conectarMySQL();
    
    private String linea;
    private String datos[];

    public BusMostrar(String bus) {
        this.bus = bus;
        
        try {
                    String Query = "SELECT * FROM BUS WHERE PLACA='" +bus + "'";
                    Statement st = con.createStatement();
                    java.sql.ResultSet resultSet;
                    resultSet = st.executeQuery(Query);
                    

                    while (resultSet.next()) {

                        linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                + resultSet.getString("COOPERATIVA");

                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
        
        datos=linea.split("--");
        sBus = new Stage();

        rootBusR = new StackPane();
        sceneBusR = new Scene(rootBusR, 440, 480);
        sBus.setScene(sceneBusR);
        sBus.setTitle("Mostrar Bus");

        imgFont = new Imagen("FontAzul3.jpeg", 440, 480);

        txtBusR = new Label("Mostrar Bus");

        txtPlaca = new Label("Placa:");
        boxPlaca = new Label(bus);
        hboxPlaca = new HBox();
        hboxPlaca.setSpacing(20);
        hboxPlaca.getChildren().addAll(txtPlaca, boxPlaca);

        txtCdDuenio = new Label("Cedula Duenio:");
        boxCdDuenio = new Label(datos[1]);
        hboxCdDuenio = new HBox();
        hboxCdDuenio.setSpacing(20);
        hboxCdDuenio.getChildren().addAll(txtCdDuenio, boxCdDuenio);

        txtCdChofer = new Label("Cedula Chofer:");
        boxCdChofer = new Label(datos[2]);
        hboxCdChofer = new HBox();
        hboxCdChofer.setSpacing(20);
        hboxCdChofer.getChildren().addAll(txtCdChofer, boxCdChofer);

        txtCooperativa = new Label("Cooperativa:");
        boxCooperativa = new Label(datos[3]);
        hboxCooperativa = new HBox();
        hboxCooperativa.setSpacing(20);
        hboxCooperativa.getChildren().addAll(txtCooperativa, boxCooperativa);

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
        vboxMain.getChildren().addAll(txtBusR, hboxPlaca, hboxCdDuenio, hboxCdChofer, hboxCooperativa, txtCheck, hboxBtn);
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setSpacing(15);
        rootBusR.getChildren().addAll(imgFont.getImagen(), vboxMain);
        Eventos();

    }

    public Stage getStageBusM() {
        return sBus;
    }

    private void Eventos() {
        btnSalir.setOnAction(e -> {
            sBus.close();
        });

        btnModificar.setOnAction(e -> {
            BusModificar busMod = new BusModificar(bus,datos[1],datos[2],datos[3]);
            busMod.getStageBusMod().show();

        });

        btnEliminar.setOnAction(e -> {

            //////////////////////
            try {

                Statement st = con.createStatement();
                java.sql.ResultSet resultSet;

                String Query1 = "DELETE FROM BUS WHERE PLACA='" + bus + "'";
                int res1 = st.executeUpdate(Query1);

                //////////////////////
                sBus.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        });
    }

}
