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
public class CompraRegistrar {

    private Stage sCompra;

    private StackPane rootCompraR;
    private Scene sceneCompraR;

    private Imagen imgFont;

    private VBox vboxMain;

    private Label txtCompraR;

    private Label txtCedula;
    private TextField boxCedula;
    private HBox hboxCedula;

    private Label txtFecha;
    private DatePicker boxFecha;
    private HBox hboxFecha;

    private Label txtTargetas;
    private TextField boxTargetas;
    private HBox hboxTargetas;

    private Label txtCosto;
    private TextField boxCosto;
    private HBox hboxCosto;

    private Label txtEstado;
    private TextField boxEstado;
    private HBox hboxEstado;

    private Label txtCheck;

    private Button btnCancelar;
    private Button btnRegistrar;
    private Button btnLimpiar;
    private HBox hboxBtn;
    Connection con = DBConexion.conectarMySQL();

    public CompraRegistrar() {
        sCompra = new Stage();

        rootCompraR = new StackPane();
        sceneCompraR = new Scene(rootCompraR, 440, 480);
        sCompra.setScene(sceneCompraR);
        sCompra.setTitle("Registrar Compra");

        imgFont = new Imagen("FontAzul3.jpeg", 440, 480);

        txtCompraR = new Label("Registrar Compra");

        txtCedula = new Label("Cedula:");
        boxCedula = new TextField();
        hboxCedula = new HBox();
        hboxCedula.setSpacing(20);
        hboxCedula.getChildren().addAll(txtCedula, boxCedula);

        txtFecha = new Label("Fecha de Compra:");
        boxFecha = new DatePicker(LocalDate.now());
        hboxFecha = new HBox();
        hboxFecha.setSpacing(20);
        hboxFecha.getChildren().addAll(txtFecha, boxFecha);

        txtTargetas = new Label("Numero de Targetas:");
        boxTargetas = new TextField();
        hboxTargetas = new HBox();
        hboxTargetas.setSpacing(20);
        hboxTargetas.getChildren().addAll(txtTargetas, boxTargetas);

        txtCosto = new Label("Costo:");
        boxCosto = new TextField();
        hboxCosto = new HBox();
        hboxCosto.setSpacing(20);
        hboxCosto.getChildren().addAll(txtCosto, boxCosto);

        txtEstado = new Label("Estado:");
        boxEstado = new TextField();
        hboxEstado = new HBox();
        hboxEstado.setSpacing(20);
        hboxEstado.getChildren().addAll(txtEstado, boxEstado);

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
        vboxMain.getChildren().addAll(txtCompraR, hboxCedula, hboxFecha, hboxTargetas, hboxCosto, hboxEstado, txtCheck, hboxBtn);
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setSpacing(15);
        rootCompraR.getChildren().addAll(imgFont.getImagen(), vboxMain);
        Eventos();

    }

    public Stage getStageCompraR() {
        return sCompra;
    }

    private void Eventos() {
        btnCancelar.setOnAction(e -> {
            sCompra.close();
        });

        btnLimpiar.setOnAction(e -> {
            boxCedula.setText("");
            boxFecha.setValue(LocalDate.now());
            boxTargetas.setText("");
            boxCosto.setText("");
            boxEstado.setText("");
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
                String Query1 = "INSERT INTO COMPRA (DNI_CLIENTE,FECHA_EMISION,NUM_TARGETAS,PRECIO_TARGETA,ESTADO) VALUE ('"
                        + boxCedula.getText() + "','" + boxFecha.getValue() + "'," + boxTargetas.getText() + "," + boxCosto.getText() + ",'" + boxEstado.getText() + "')";
                Statement st = con.createStatement();
                java.sql.ResultSet resultSet;
                int res1 = st.executeUpdate(Query1);

                //////////////////////
            } catch (SQLException ex) {
                System.out.println(ex);
            }

            if (!boxCedula.getText().equals("")
                    && !boxTargetas.getText().equals("")
                    && !boxCosto.getText().equals("")
                    && !boxEstado.getText().equals("")) {
                try {
                    String Query = "commit";
                    Statement st = con.createStatement();
                    java.sql.ResultSet resultSet;
                    int res1 = st.executeUpdate(Query);
                    //////////////////////
                    boxCedula.setText("");
                    boxFecha.setValue(LocalDate.now());
                    boxTargetas.setText("");
                    boxCosto.setText("");
                    boxEstado.setText("");
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
