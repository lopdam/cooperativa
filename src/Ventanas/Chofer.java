/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

/**
 *
 * @author anaklusmos
 */
import Bases.DBConexion;
import Objetos.Imagen;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.StampedLock;
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
public class Chofer {

    private Stage s;

    private StackPane rootChofer;
    private Scene sceneChofer;

    private Imagen imgFont;
    private VBox vboxMain;

    private Label txtNull;
    private Label txtChofer;

    private Label txtConsulta;
    private ChoiceBox listConsulta;
    private TextField boxConsulta;
    private HBox hboxConsulta;

    private Label txtOrdenar;
    private ChoiceBox listOrdenar;
    private Button btnAsc;
    private Button btnDesc;
    private HBox hboxOrdenar;

    private ListView listChofer;

    private Button btnRegistrar;

    private Button btnRegresar;
    private Button btnSalir;
    private HBox hboxSalir;

    private Label txtNull2;
    Connection con = DBConexion.conectarMySQL();

    public Chofer(Stage s) {

        this.s = s;
        rootChofer = new StackPane();
        sceneChofer = new Scene(rootChofer, 840, 480);

        imgFont = new Imagen("FontAzul5.jpeg", 840, 480);

        txtNull = new Label();
        txtChofer = new Label("Chofer");

        txtConsulta = new Label("Consultar por:");
        listConsulta = new ChoiceBox();
        listConsulta.setValue("Nombre");
        listConsulta.getItems().addAll("Nombre", "Apellido");
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

        listChofer = new ListView();
        try {
            String Query = "SELECT * FROM CHOFER";
            Statement st = con.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            listChofer.getItems().clear();
            listChofer.getItems().add("DNI--NOMBRE--APELLIDO");

            while (resultSet.next()) {

                String linea;
                linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                        + "--" + resultSet.getString("APELLIDO");
                listChofer.getItems().add(linea);

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
        vboxMain.getChildren().addAll(txtNull, txtChofer, hboxConsulta, hboxOrdenar, listChofer, btnRegistrar, hboxSalir, txtNull2);
        rootChofer.getChildren().addAll(imgFont.getImagen(), vboxMain);
        Eventos();
    }

    public Scene getSceneChofer() {
        return sceneChofer;
    }

    private void Eventos() {
        btnSalir.setOnAction(e -> {
            s.close();
        });
        btnRegresar.setOnAction(q -> {
            Opcion.InicializarAll(s);
            s.setScene(Opcion.getSceneOpcion());
        });
        btnRegistrar.setOnAction(e -> {
            ChoferRegistrar chr = new ChoferRegistrar();
            chr.getStageChoferR().show();
        });
        /////////////////////////////////////////////////////////

        boxConsulta.setOnKeyReleased(value -> {
            if(listConsulta.getValue().equals("Nombre")){
            try {
            String Query = "SELECT * FROM CHOFER  WHERE NOMBRE LIKE " + "'" + boxConsulta.getText() + "%'";
            Statement st = con.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            listChofer.getItems().clear();
            listChofer.getItems().add("DNI--NOMBRE--APELLIDO");

            while (resultSet.next()) {

                String linea;
                linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                        + "--" + resultSet.getString("APELLIDO");
                listChofer.getItems().add(linea);

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
            }
            else if(listConsulta.getValue().equals("Apellido")){try {
            String Query = "SELECT * FROM CHOFER  WHERE APELLIDO LIKE " + "'" + boxConsulta.getText() + "%'";
            Statement st = con.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            listChofer.getItems().clear();
            listChofer.getItems().add("DNI--NOMBRE--APELLIDO");

            while (resultSet.next()) {

                String linea;
                linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                        + "--" + resultSet.getString("APELLIDO");
                listChofer.getItems().add(linea);

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
            }
        });

        btnDesc.setOnAction(value -> {
            
            if (listOrdenar.getValue().equals("Apellido")) {
                if (listConsulta.getValue().equals("Apellido")) {
                    try {
                        String Query = "SELECT * FROM CHOFER WHERE APELLIDO LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY APELLIDO DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listChofer.getItems().clear();
                        listChofer.getItems().add("DNI--NOMBRE--APELLIDO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO");
                            listChofer.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }

                } else if (listConsulta.getValue().equals("Nombre")) {
                    try {
                        String Query = "SELECT * FROM CHOFER WHERE NOMBRE LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY APELLIDO DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listChofer.getItems().clear();
                        listChofer.getItems().add("DNI--NOMBRE--APELLIDO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO");
                            listChofer.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }

            } else if (listOrdenar.getValue().equals("Nombre")) {
                if (listConsulta.getValue().equals("Apellido")) {
                    try {
                        String Query = "SELECT * FROM CHOFER WHERE APELLIDO LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY NOMBRE DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listChofer.getItems().clear();
                        listChofer.getItems().add("DNI--NOMBRE--APELLIDO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO");
                            listChofer.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }

                } else if (listConsulta.getValue().equals("Nombre")) {
                    try {
                        String Query = "SELECT * FROM CHOFER WHERE NOMBRE LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY NOMBRE DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listChofer.getItems().clear();
                        listChofer.getItems().add("DNI--NOMBRE--APELLIDO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO");
                            listChofer.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }

            }
        });

        btnAsc.setOnAction(value -> {
            if (listOrdenar.getValue().equals("Apellido")) {
                if (listConsulta.getValue().equals("Apellido")) {
                    try {
                        String Query = "SELECT * FROM CHOFER WHERE APELLIDO LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY APELLIDO ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listChofer.getItems().clear();
                        listChofer.getItems().add("DNI--NOMBRE--APELLIDO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO");
                            listChofer.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }

                } else if (listConsulta.getValue().equals("Nombre")) {
                    try {
                        String Query = "SELECT * FROM CHOFER WHERE NOMBRE LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY APELLIDO ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listChofer.getItems().clear();
                        listChofer.getItems().add("DNI--NOMBRE--APELLIDO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO");
                            listChofer.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }

            } else if (listOrdenar.getValue().equals("Nombre")) {
                if (listConsulta.getValue().equals("Apellido")) {
                    try {
                        String Query = "SELECT * FROM CHOFER WHERE APELLIDO LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY NOMBRE ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listChofer.getItems().clear();
                        listChofer.getItems().add("DNI--NOMBRE--APELLIDO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO");
                            listChofer.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }

                } else if (listConsulta.getValue().equals("Nombre")) {
                    try {
                        String Query = "SELECT * FROM CHOFER WHERE NOMBRE LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY NOMBRE ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listChofer.getItems().clear();
                        listChofer.getItems().add("DNI--NOMBRE--APELLIDO");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                                    + "--" + resultSet.getString("APELLIDO");
                            listChofer.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }

            }

        });
        
        listChofer.setOnMouseClicked(clic->{
            String line[]=listChofer.getSelectionModel().getSelectedItem().toString().split("--");
        ChoferMostrar cliente=new ChoferMostrar(line[0]);
        cliente.getStageChoferM().show();
                
        });

    }

}
