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
public class CompraModificar {

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
    private Button btnGuardar;
    private Button btnLimpiar;
    private HBox hboxBtn;

    Connection con = DBConexion.conectarMySQL();
    
    private String compra;

    public CompraModificar(String compra, String cliente, String fecha, String numero, String precio, String estado) {
        sCompra = new Stage();
        this.compra=compra;

        rootCompraR = new StackPane();
        sceneCompraR = new Scene(rootCompraR, 440, 480);
        sCompra.setScene(sceneCompraR);
        sCompra.setTitle("Modificar Compra");

        imgFont = new Imagen("FontAzul3.jpeg", 440, 480);

        txtCompraR = new Label("Modificar Compra");

        txtCedula = new Label("Cedula:");
        boxCedula = new TextField(cliente);
        hboxCedula = new HBox();
        hboxCedula.setSpacing(20);
        hboxCedula.getChildren().addAll(txtCedula, boxCedula);

        txtFecha = new Label("Fecha de Compra:");
        boxFecha = new DatePicker(LocalDate.parse(fecha));
        hboxFecha = new HBox();
        hboxFecha.setSpacing(20);
        hboxFecha.getChildren().addAll(txtFecha, boxFecha);

        txtTargetas = new Label("Numero de Targetas:");
        boxTargetas = new TextField(numero);
        hboxTargetas = new HBox();
        hboxTargetas.setSpacing(20);
        hboxTargetas.getChildren().addAll(txtTargetas, boxTargetas);

        txtCosto = new Label("Costo:");
        boxCosto = new TextField(precio);
        hboxCosto = new HBox();
        hboxCosto.setSpacing(20);
        hboxCosto.getChildren().addAll(txtCosto, boxCosto);

        txtEstado = new Label("Estado:");
        boxEstado = new TextField(estado);
        hboxEstado = new HBox();
        hboxEstado.setSpacing(20);
        hboxEstado.getChildren().addAll(txtEstado, boxEstado);

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
        vboxMain.getChildren().addAll(txtCompraR, hboxCedula, hboxFecha, hboxTargetas, hboxCosto, hboxEstado, txtCheck, hboxBtn);
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setSpacing(15);
        rootCompraR.getChildren().addAll(imgFont.getImagen(), vboxMain);
        Eventos();

    }

    public Stage getStageCompraMod() {
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

        btnGuardar.setOnAction(e -> {
try {

                Statement st = con.createStatement();
                java.sql.ResultSet resultSet;


                String Query2 = "UPDATE COMPRA SET DNI_CLIENTE='" + boxCedula.getText()+"', FECHA_EMISION='"+boxFecha.getValue() +"', NUM_TARGETAS="+boxTargetas.getText() +", PRECIO_TARGETA="+boxFecha.getValue() +", ESTADO='"+boxEstado.getText() + "' WHERE ID='" + compra + "'";
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
