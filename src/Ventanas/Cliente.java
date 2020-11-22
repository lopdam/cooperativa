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
public class Cliente implements Opciones {

    private Stage s;

    private StackPane rootCliente;
    private Scene sceneCliente;

    private Imagen imgFont;
    private VBox vboxMain;

    private Label txtNull;
    private Label txtCliente;

    private Label txtConsulta;
    private ChoiceBox listConsulta;
    private TextField boxConsulta;
    private HBox hboxConsulta;

    private Label txtOrdenar;
    private ChoiceBox listOrdenar;
    private Button btnAsc;
    private Button btnDesc;
    private HBox hboxOrdenar;

    private ListView listCliente;

    private Button btnRegistrar;

    private Button btnRegresar;
    private Button btnSalir;
    private HBox hboxSalir;

    private Label txtNull2;
    Connection con = DBConexion.conectarMySQL();

    public Cliente(Stage s) {
        this.s = s;
        rootCliente = new StackPane();
        sceneCliente = new Scene(rootCliente, 840, 480);

        imgFont = new Imagen("FontAzul5.jpeg", 840, 480);

        txtNull = new Label();
        txtCliente = new Label("Cliente");

        txtConsulta = new Label("Consultar por:");
        listConsulta = new ChoiceBox();
        listConsulta.getItems().addAll("Nombre", "Apellido");
        listConsulta.setValue("Nombre");
        boxConsulta = new TextField();
        hboxConsulta = new HBox();
        hboxConsulta.setAlignment(Pos.CENTER);
        hboxConsulta.setSpacing(60);
        hboxConsulta.getChildren().addAll(txtConsulta, listConsulta, boxConsulta);

        txtOrdenar = new Label("Ordenar por:");
        listOrdenar = new ChoiceBox();
        listOrdenar.getItems().addAll("Nombre", "Apellido");
        btnAsc = new Button("Ascendente");
        btnDesc = new Button("Descendente");
        hboxOrdenar = new HBox();
        hboxOrdenar.setAlignment(Pos.CENTER);
        hboxOrdenar.setSpacing(60);
        hboxOrdenar.getChildren().addAll(txtOrdenar, listOrdenar, btnAsc, btnDesc);

        listCliente = new ListView();
        try {
            String Query = "SELECT * FROM CLIENTE";
            Statement st = con.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            listCliente.getItems().clear();
            listCliente.getItems().add("DNI--NOMBRE--APELLIDO");

            while (resultSet.next()) {

                String linea;
                linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                        + "--" + resultSet.getString("APELLIDO");
                listCliente.getItems().add(linea);

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        btnRegistrar = new Button("Registrar");

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
        vboxMain.getChildren().addAll(txtNull, txtCliente, hboxConsulta, hboxOrdenar, listCliente, btnRegistrar, hboxSalir, txtNull2);
        rootCliente.getChildren().addAll(imgFont.getImagen(), vboxMain);
        Eventos();

    }

    public Scene getSceneCliente() {
        return sceneCliente;
    }
  private void Eventos() {
        salir();
        regresar();
        registrar();
        consultar();
        filtroAscendente();
        filtroDescendente();
        lista();
  
    }
  
    @Override
    public void salir() {
       btnSalir.setOnAction(e -> {
            s.close();
        }); 
    }
    @Override
    public void regresar(){
        btnRegresar.setOnAction(q -> {
            Opcion.InicializarAll(s);
            s.setScene(Opcion.getSceneOpcion());
        });
    }
    @Override
    public void registrar() {
        btnRegistrar.setOnAction(q -> {
            ClienteRegistrar clr = new ClienteRegistrar();
            clr.getStageClienteR().show();
        });
    }
    @Override
    public void consultar(){
        boxConsulta.setOnKeyReleased(value -> {
            if (listConsulta.getValue().equals("Apellido")) {
                try {
                    String Query = "SELECT * FROM CLIENTE WHERE APELLIDO LIKE " + "'" + boxConsulta.getText() + "%'";
                    Statement st = con.createStatement();
                    java.sql.ResultSet resultSet;
                    resultSet = st.executeQuery(Query);
                    listCliente.getItems().clear();
                    listCliente.getItems().add("DNI--NOMBRE--APELLIDO");

                    while (resultSet.next()) {

                        String linea;
                        linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                + "--" + resultSet.getString("APELLIDO");
                        listCliente.getItems().add(linea);

                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            } else {

                try {
                    String Query = "SELECT * FROM CLIENTE WHERE NOMBRE LIKE " + "'" + boxConsulta.getText() + "%'";
                    Statement st = con.createStatement();
                    java.sql.ResultSet resultSet;
                    resultSet = st.executeQuery(Query);
                    listCliente.getItems().clear();
                    listCliente.getItems().add("DNI--NOMBRE--APELLIDO");

                    while (resultSet.next()) {

                        String linea;
                        linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                + "--" + resultSet.getString("APELLIDO");
                        listCliente.getItems().add(linea);

                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }

        });
    }
    @Override
    public void filtroAscendente(){
        btnAsc.setOnAction(value -> {
            if (listOrdenar.getValue().equals("Apellido")) {
                if (listConsulta.getValue().equals("Apellido")) {
                    try {
                        String Query = "SELECT * FROM CLIENTE WHERE APELLIDO LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY APELLIDO ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCliente.getItems().clear();
                        listCliente.getItems().add("DNI--NOMBRE--APELLIDO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO");
                            listCliente.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }

                } else if (listConsulta.getValue().equals("Nombre")) {
                    try {
                        String Query = "SELECT * FROM CLIENTE WHERE NOMBRE LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY APELLIDO ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCliente.getItems().clear();
                        listCliente.getItems().add("DNI--NOMBRE--APELLIDO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO");
                            listCliente.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }

            } else if (listOrdenar.getValue().equals("Nombre")) {
                if (listConsulta.getValue().equals("Apellido")) {
                    try {
                        String Query = "SELECT * FROM CLIENTE WHERE APELLIDO LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY NOMBRE ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCliente.getItems().clear();
                        listCliente.getItems().add("DNI--NOMBRE--APELLIDO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO");
                            listCliente.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }

                } else if (listConsulta.getValue().equals("Nombre")) {
                    try {
                        String Query = "SELECT * FROM CLIENTE WHERE NOMBRE LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY NOMBRE ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCliente.getItems().clear();
                        listCliente.getItems().add("DNI--NOMBRE--APELLIDO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO");
                            listCliente.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }

            }
        });
    }
    @Override
    public void filtroDescendente(){
        btnDesc.setOnAction(eve -> {
            if (listOrdenar.getValue().equals("Apellido")) {
                if (listConsulta.getValue().equals("Apellido")) {
                    try {
                        String Query = "SELECT * FROM CLIENTE WHERE APELLIDO LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY APELLIDO DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCliente.getItems().clear();
                        listCliente.getItems().add("DNI--NOMBRE--APELLIDO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO");
                            listCliente.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }

                } else if (listConsulta.getValue().equals("Nombre")) {
                    try {
                        String Query = "SELECT * FROM CLIENTE WHERE NOMBRE LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY APELLIDO DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCliente.getItems().clear();
                        listCliente.getItems().add("DNI--NOMBRE--APELLIDO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO");
                            listCliente.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }

            } else if (listOrdenar.getValue().equals("Nombre")) {
                if (listConsulta.getValue().equals("Apellido")) {
                    try {
                        String Query = "SELECT * FROM CLIENTE WHERE APELLIDO LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY NOMBRE DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCliente.getItems().clear();
                        listCliente.getItems().add("DNI--NOMBRE--APELLIDO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO");
                            listCliente.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }

                } else if (listConsulta.getValue().equals("Nombre")) {
                    try {
                        String Query = "SELECT * FROM CLIENTE WHERE NOMBRE LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY NOMBRE DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCliente.getItems().clear();
                        listCliente.getItems().add("DNI--NOMBRE--APELLIDO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO");
                            listCliente.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }

            }

        });
    }
    @Override
    public void lista(){
        listCliente.setOnMouseClicked(clic->{
            String line[]=listCliente.getSelectionModel().getSelectedItem().toString().split("--");
        ClienteMostrar cliente=new ClienteMostrar(line[0]);
        cliente.getStageClienteM().show();
                
        });
    }
    
}
