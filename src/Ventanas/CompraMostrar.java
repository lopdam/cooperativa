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
public class CompraMostrar {

    private Stage sCompra;

    private StackPane rootCompraR;
    private Scene sceneCompraR;

    private Imagen imgFont;

    private VBox vboxMain;

    private Label txtCompraR;

    private Label txtCedula;
    private Label boxCedula;
    private HBox hboxCedula;

    private Label txtFecha;
    private Label boxFecha;
    private HBox hboxFecha;

    private Label txtTargetas;
    private Label boxTargetas;
    private HBox hboxTargetas;

    private Label txtCosto;
    private Label boxCosto;
    private HBox hboxCosto;

    private Label txtEstado;
    private Label boxEstado;
    private HBox hboxEstado;

    private Label txtCheck;

    private Button btnSalir;
    private Button btnModificar;
    private Button btnEliminar;
    private HBox hboxBtn;

    private String compra;
    private String linea;
    private String datos[];
    Connection con = DBConexion.conectarMySQL();

    public CompraMostrar(String compra) {
        this.compra = compra;

        try {
            String Query = "SELECT * FROM COMPRA WHERE ID='"+compra+"'";
            Statement st = con.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);

            while (resultSet.next()) {

                linea = resultSet.getString("ID") + "--" + resultSet.getString("DNI_CLIENTE")
                        + "--" + resultSet.getString("FECHA_EMISION")
                        + "--" + resultSet.getString("NUM_TARGETAS")
                        + "--" + resultSet.getString("PRECIO_TARGETA")
                        + "--" + resultSet.getString("ESTADO");

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        datos=linea.split("--");
        

        sCompra = new Stage();

        rootCompraR = new StackPane();
        sceneCompraR = new Scene(rootCompraR, 440, 480);
        sCompra.setScene(sceneCompraR);
        sCompra.setTitle("Mostrar Compra");

        imgFont = new Imagen("FontAzul3.jpeg", 440, 480);

        txtCompraR = new Label("Mostrar Compra");

        txtCedula = new Label("Cedula:");
        boxCedula = new Label(datos[1]);
        hboxCedula = new HBox();
        hboxCedula.setSpacing(20);
        hboxCedula.getChildren().addAll(txtCedula, boxCedula);

        txtFecha = new Label("Fecha de Compra:");
        boxFecha = new Label(datos[2]);
        hboxFecha = new HBox();
        hboxFecha.setSpacing(20);
        hboxFecha.getChildren().addAll(txtFecha, boxFecha);

        txtTargetas = new Label("Numero de Targetas:");
        boxTargetas = new Label(datos[3]);
        hboxTargetas = new HBox();
        hboxTargetas.setSpacing(20);
        hboxTargetas.getChildren().addAll(txtTargetas, boxTargetas);

        txtCosto = new Label("Costo:");
        boxCosto = new Label(datos[4]);
        hboxCosto = new HBox();
        hboxCosto.setSpacing(20);
        hboxCosto.getChildren().addAll(txtCosto, boxCosto);

        txtEstado = new Label("Estado:");
        boxEstado = new Label(datos[5]);
        hboxEstado = new HBox();
        hboxEstado.setSpacing(20);
        hboxEstado.getChildren().addAll(txtEstado, boxEstado);

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
        vboxMain.getChildren().addAll(txtCompraR, hboxCedula, hboxFecha, hboxTargetas, hboxCosto, hboxEstado, txtCheck, hboxBtn);
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setSpacing(15);
        rootCompraR.getChildren().addAll(imgFont.getImagen(), vboxMain);
        Eventos();

    }

    public Stage getStageCompraM() {
        return sCompra;
    }

    private void Eventos() {
        btnSalir.setOnAction(e -> {
            sCompra.close();
        });

        btnModificar.setOnAction(e -> {
            CompraModificar comMod = new CompraModificar(compra,datos[1],datos[2],datos[3],datos[4],datos[5]);
            comMod.getStageCompraMod().show();
        });

        btnEliminar.setOnAction(e -> {

        });
    }

}
