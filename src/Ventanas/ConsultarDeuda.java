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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author anaklusmos
 */
public class ConsultarDeuda {

    private Stage s;

    private StackPane rootCliente;
    private Scene sceneCliente;

    private Imagen imgFont;
    private VBox vboxMain;

    private Label txtNull;
    private Label txtCliente;

    private Label txtConsulta;
    private TextField boxConsulta;
    private Button btnConsultar;

    private HBox hboxConsulta;

    private Label txtOrdenar;
    private HBox hboxOrdenar;

    private Button btnRegresar;
    private Button btnSalir;
    private HBox hboxSalir;

    private String linea;

    private Label txtNull2;
    Connection con = DBConexion.conectarMySQL();

    public ConsultarDeuda(Stage s) {
        this.s = s;
        rootCliente = new StackPane();
        sceneCliente = new Scene(rootCliente, 840, 480);

        imgFont = new Imagen("FontAzul5.jpeg", 840, 480);

        txtNull = new Label();
        txtCliente = new Label("Consultar Deuda");

        txtConsulta = new Label("Cedula del Cliente:");
        btnConsultar = new Button("Consultar");

        boxConsulta = new TextField();
        hboxConsulta = new HBox();
        hboxConsulta.setAlignment(Pos.CENTER);
        hboxConsulta.setSpacing(60);
        hboxConsulta.getChildren().addAll(txtConsulta, boxConsulta, btnConsultar);

        txtOrdenar = new Label("La Deuda del Cliente es de : 00.00 $");

        hboxOrdenar = new HBox();
        hboxOrdenar.setAlignment(Pos.CENTER);
        hboxOrdenar.setSpacing(60);
        hboxOrdenar.getChildren().addAll(txtOrdenar);

        btnRegresar = new Button("Regresar");
        btnSalir = new Button("Salir");
        hboxSalir = new HBox();
        hboxSalir.setAlignment(Pos.CENTER);
        hboxSalir.setSpacing(250);
        hboxSalir.getChildren().addAll(btnRegresar, btnSalir);

        txtNull2 = new Label();

        vboxMain = new VBox();
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setSpacing(20);
        vboxMain.getChildren().addAll(txtNull, txtCliente, hboxConsulta, hboxOrdenar, hboxSalir, txtNull2);
        rootCliente.getChildren().addAll(imgFont.getImagen(), vboxMain);
        Eventos();

    }

    public Scene getSceneCliente() {
        return sceneCliente;
    }

    private void Eventos() {
        btnSalir.setOnAction(e -> {
            s.close();
        });
        btnRegresar.setOnAction(q -> {
            Opcion.InicializarAll(s);
            s.setScene(Opcion.getSceneOpcion());
        });

        boxConsulta.setOnKeyReleased(value -> {

        });
        btnConsultar.setOnAction(consul -> {

            try {
                String Query = "call Deuda('" + boxConsulta.getText() + "',@val)";
                String Query2 = "select @val;";
                Statement st = con.createStatement();
                java.sql.ResultSet resultSet;
                resultSet = st.executeQuery(Query);
                resultSet = st.executeQuery(Query2);

                while (resultSet.next()) {

                    linea = resultSet.getString(1);

                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }

            txtOrdenar.setText("La Deuda del Cliente es de : " + linea + " $");
        });

    }

}
