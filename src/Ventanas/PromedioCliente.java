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
public class PromedioCliente {

    private Stage s;

    private StackPane rootCompra;
    private Scene sceneCompra;

    private Imagen imgFont;
    private VBox vboxMain;

    private Label txtNull;
    private Label txtCompra;

    private Label txtConsulta;
    private ChoiceBox listConsulta;
    private TextField boxConsulta;
    private HBox hboxConsulta;

    private Label txtOrdenar;
    private ChoiceBox listOrdenar;
    private Button btnAsc;
    private Button btnDesc;
    private HBox hboxOrdenar;

    private ListView listCompra;

    private Button btnRegresar;
    private Button btnSalir;
    private HBox hboxSalir;

    private Label txtNull2;
    Connection con = DBConexion.conectarMySQL();

    public PromedioCliente(Stage s) {
        this.s = s;
        rootCompra = new StackPane();
        sceneCompra = new Scene(rootCompra, 840, 480);

        imgFont = new Imagen("FontAzul5.jpeg", 840, 480);

        txtNull = new Label();
        txtCompra = new Label("Promedio Cliente Reporte");

        txtConsulta = new Label("Consultar por:");
        listConsulta = new ChoiceBox();
        listConsulta.setValue("Cliente");
        listConsulta.getItems().addAll("Nombre", "Apellido", "Valor");
        listConsulta.setValue("Nombre");
        boxConsulta = new TextField();
        hboxConsulta = new HBox();
        hboxConsulta.setAlignment(Pos.CENTER);
        hboxConsulta.setSpacing(60);
        hboxConsulta.getChildren().addAll(txtConsulta, listConsulta, boxConsulta);

        txtOrdenar = new Label("Ordenar por:");
        listOrdenar = new ChoiceBox();
        listOrdenar.getItems().addAll("Nombre", "Apellido", "Valor");
        listOrdenar.setValue("Nombre");
        btnAsc = new Button("Ascendente");
        btnDesc = new Button("Descendente");
        hboxOrdenar = new HBox();
        hboxOrdenar.setAlignment(Pos.CENTER);
        hboxOrdenar.setSpacing(60);
        hboxOrdenar.getChildren().addAll(txtOrdenar, listOrdenar, btnAsc, btnDesc);

        listCompra = new ListView();

        try {
            String Query = "SELECT * FROM PROMEDIO_CLIENTE";
            Statement st = con.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            listCompra.getItems().clear();
            listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

            while (resultSet.next()) {

                String linea;
                linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                        + "--" + resultSet.getString("APELLIDO")
                        + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                        + "--" + resultSet.getString("PROMEDIO_PRECIO");
                listCompra.getItems().add(linea);

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

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
        vboxMain.getChildren().addAll(txtNull, txtCompra, hboxConsulta, hboxOrdenar, listCompra, hboxSalir, txtNull2);
        rootCompra.getChildren().addAll(imgFont.getImagen(), vboxMain);
        Eventos();
    }

    public Scene getSceneCompra() {
        return sceneCompra;
    }

    private void Eventos() {
        btnSalir.setOnAction(e -> {
            s.close();
        });
        btnRegresar.setOnAction(q -> {
            Opcion.InicializarAll(s);
            s.setScene(Reportes.getSceneOpcion());
        });

        ///////////////////////////////////////////////////////////////////////
        boxConsulta.setOnKeyReleased(value -> {
            if (listConsulta.getValue().equals("Valor")) {
                try {
                    String Query = "SELECT * FROM PROMEDIO_CLIENTE WHERE PROMEDIO_PRECIO=" + boxConsulta.getText();
                    Statement st = con.createStatement();
                    java.sql.ResultSet resultSet;
                    resultSet = st.executeQuery(Query);
                    listCompra.getItems().clear();
                    listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                    while (resultSet.next()) {

                        String linea;
                        linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                + "--" + resultSet.getString("APELLIDO")
                                + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                + "--" + resultSet.getString("PROMEDIO_PRECIO");
                        listCompra.getItems().add(linea);

                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            } else if (listConsulta.getValue().equals("Nombre")) {
                try {
                    String Query = "SELECT * FROM PROMEDIO_CLIENTE WHERE NOMBRE LIKE" + "'" + boxConsulta.getText() + "%'";
                    Statement st = con.createStatement();
                    java.sql.ResultSet resultSet;
                    resultSet = st.executeQuery(Query);
                    listCompra.getItems().clear();
                    listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                    while (resultSet.next()) {

                        String linea;
                        linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                + "--" + resultSet.getString("APELLIDO")
                                + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                + "--" + resultSet.getString("PROMEDIO_PRECIO");
                        listCompra.getItems().add(linea);

                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            } else if (listConsulta.getValue().equals("Apellido")) {
                try {
                    String Query = "SELECT * FROM PROMEDIO_CLIENTE WHERE APELLIDO LIKE" + "'" + boxConsulta.getText() + "%'";
                    Statement st = con.createStatement();
                    java.sql.ResultSet resultSet;
                    resultSet = st.executeQuery(Query);
                    listCompra.getItems().clear();
                    listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                    while (resultSet.next()) {

                        String linea;
                        linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                + "--" + resultSet.getString("APELLIDO")
                                + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                + "--" + resultSet.getString("PROMEDIO_PRECIO");
                        listCompra.getItems().add(linea);

                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }

        });

        btnDesc.setOnAction(des -> {
            
            
            if (listOrdenar.getValue().equals("Valor")) {

                if (listConsulta.getValue().equals("Valor")) {
                    try {
                        String Query = "SELECT * FROM PROMEDIO_CLIENTE WHERE PROMEDIO_PRECIO=" + boxConsulta.getText() + " ORDER BY PROMEDIO_PRECIO DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCompra.getItems().clear();
                        listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO")
                                    + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                    + "--" + resultSet.getString("PROMEDIO_PRECIO");
                            listCompra.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Nombre")) {
                    try {
                        String Query = "SELECT * FROM PROMEDIO_CLIENTE WHERE NOMBRE LIKE" + "'" + boxConsulta.getText() + "%' ORDER BY PROMEDIO_PRECIO DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCompra.getItems().clear();
                        listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO")
                                    + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                    + "--" + resultSet.getString("PROMEDIO_PRECIO");
                            listCompra.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Apellido")) {
                    try {
                        String Query = "SELECT * FROM PROMEDIO_CLIENTE WHERE APELLIDO LIKE" + "'" + boxConsulta.getText() + "%' ORDER BY PROMEDIO_PRECIO DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCompra.getItems().clear();
                        listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO")
                                    + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                    + "--" + resultSet.getString("PROMEDIO_PRECIO");
                            listCompra.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }

            } else if (listOrdenar.getValue().equals("Nombre")) {

                if (listConsulta.getValue().equals("Valor")) {
                    try {
                        String Query = "SELECT * FROM PROMEDIO_CLIENTE WHERE PROMEDIO_PRECIO=" + boxConsulta.getText() + " ORDER BY NOMBRE DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCompra.getItems().clear();
                        listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO")
                                    + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                    + "--" + resultSet.getString("PROMEDIO_PRECIO");
                            listCompra.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Nombre")) {
                    try {
                        String Query = "SELECT * FROM PROMEDIO_CLIENTE WHERE NOMBRE LIKE" + "'" + boxConsulta.getText() + "%' ORDER BY NOMBRE DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCompra.getItems().clear();
                        listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO")
                                    + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                    + "--" + resultSet.getString("PROMEDIO_PRECIO");
                            listCompra.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Apellido")) {
                    try {
                        String Query = "SELECT * FROM PROMEDIO_CLIENTE WHERE APELLIDO LIKE" + "'" + boxConsulta.getText() + "%' ORDER BY NOMBRE DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCompra.getItems().clear();
                        listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO")
                                    + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                    + "--" + resultSet.getString("PROMEDIO_PRECIO");
                            listCompra.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }

            } else if (listOrdenar.getValue().equals("Apellido")) {

                if (listConsulta.getValue().equals("Valor")) {
                    try {
                        String Query = "SELECT * FROM PROMEDIO_CLIENTE WHERE PROMEDIO_PRECIO=" + boxConsulta.getText() + " ORDER BY APELLIDO DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCompra.getItems().clear();
                        listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO")
                                    + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                    + "--" + resultSet.getString("PROMEDIO_PRECIO");
                            listCompra.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Nombre")) {
                    try {
                        String Query = "SELECT * FROM PROMEDIO_CLIENTEE WHERE NOMBRE LIKE" + "'" + boxConsulta.getText() + "%' ORDER BY APELLIDO DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCompra.getItems().clear();
                        listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO")
                                    + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                    + "--" + resultSet.getString("PROMEDIO_PRECIO");
                            listCompra.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Apellido")) {
                    try {
                        String Query = "SELECT * FROM PROMEDIO_CLIENTE WHERE APELLIDO LIKE" + "'" + boxConsulta.getText() + "%' ORDER BY APELLIDO DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCompra.getItems().clear();
                        listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO")
                                    + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                    + "--" + resultSet.getString("PROMEDIO_PRECIO");
                            listCompra.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }

            }

          
        });

        btnAsc.setOnAction(asc -> {

            if (listOrdenar.getValue().equals("Valor")) {

                if (listConsulta.getValue().equals("Valor")) {
                    try {
                        String Query = "SELECT * FROM PROMEDIO_CLIENTE WHERE PROMEDIO_PRECIO=" + boxConsulta.getText() + " ORDER BY PROMEDIO_PRECIO ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCompra.getItems().clear();
                        listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO")
                                    + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                    + "--" + resultSet.getString("PROMEDIO_PRECIO");
                            listCompra.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Nombre")) {
                    try {
                        String Query = "SELECT * FROM PROMEDIO_CLIENTE WHERE NOMBRE LIKE" + "'" + boxConsulta.getText() + "%' ORDER BY PROMEDIO_PRECIO ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCompra.getItems().clear();
                        listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO")
                                    + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                    + "--" + resultSet.getString("PROMEDIO_PRECIO");
                            listCompra.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Apellido")) {
                    try {
                        String Query = "SELECT * FROM PROMEDIO_CLIENTE WHERE APELLIDO LIKE" + "'" + boxConsulta.getText() + "%' ORDER BY PROMEDIO_PRECIO ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCompra.getItems().clear();
                        listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO")
                                    + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                    + "--" + resultSet.getString("PROMEDIO_PRECIO");
                            listCompra.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }

            } else if (listOrdenar.getValue().equals("Nombre")) {

                if (listConsulta.getValue().equals("Valor")) {
                    try {
                        String Query = "SELECT * FROM PROMEDIO_CLIENTE WHERE PROMEDIO_TARGETAS=" + boxConsulta.getText() + " ORDER BY NOMBRE ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCompra.getItems().clear();
                        listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO")
                                    + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                    + "--" + resultSet.getString("PROMEDIO_PRECIO");
                            listCompra.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Nombre")) {
                    try {
                        String Query = "SELECT * FROM PROMEDIO_CLIENTE WHERE NOMBRE LIKE" + "'" + boxConsulta.getText() + "%' ORDER BY NOMBRE ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCompra.getItems().clear();
                        listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO")
                                    + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                    + "--" + resultSet.getString("PROMEDIO_PRECIO");
                            listCompra.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Apellido")) {
                    try {
                        String Query = "SELECT * FROM PROMEDIO_CLIENTE WHERE APELLIDO LIKE" + "'" + boxConsulta.getText() + "%' ORDER BY NOMBRE ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCompra.getItems().clear();
                        listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO")
                                    + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                    + "--" + resultSet.getString("PROMEDIO_PRECIO");
                            listCompra.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }

            } else if (listOrdenar.getValue().equals("Apellido")) {

                if (listConsulta.getValue().equals("Valor")) {
                    try {
                        String Query = "SELECT * FROM PROMEDIO_CLIENTE WHERE PROMEDIO_PRECIO=" + boxConsulta.getText() + " ORDER BY APELLIDO ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCompra.getItems().clear();
                        listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO")
                                    + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                    + "--" + resultSet.getString("PROMEDIO_PRECIO");
                            listCompra.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Nombre")) {
                    try {
                        String Query = "SELECT * FROM PROMEDIO_CLIENTEE WHERE NOMBRE LIKE" + "'" + boxConsulta.getText() + "%' ORDER BY APELLIDO ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCompra.getItems().clear();
                        listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO")
                                    + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                    + "--" + resultSet.getString("PROMEDIO_PRECIO");
                            listCompra.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Apellido")) {
                    try {
                        String Query = "SELECT * FROM PROMEDIO_CLIENTE WHERE APELLIDO LIKE" + "'" + boxConsulta.getText() + "%' ORDER BY APELLIDO ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listCompra.getItems().clear();
                        listCompra.getItems().add("DNI--NOMBRE--APELLIDO--PROMEDIO_TARGETAS--PROMEDIO_PRECIO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO")
                                    + "--" + resultSet.getString("PROMEDIO_TARGETAS")
                                    + "--" + resultSet.getString("PROMEDIO_PRECIO");
                            listCompra.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }

            }

        });

    }

}
