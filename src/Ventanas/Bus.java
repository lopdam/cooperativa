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
public class Bus {

    private Stage s;

    private StackPane rootBus;
    private Scene sceneBus;

    private Imagen imgFont;
    private VBox vboxMain;

    private Label txtNull;
    private Label txtBus;

    private Label txtConsulta;
    private ChoiceBox listConsulta;
    private TextField boxConsulta;
    private HBox hboxConsulta;

    private Label txtOrdenar;
    private ChoiceBox listOrdenar;
    private Button btnAsc;
    private Button btnDesc;
    private HBox hboxOrdenar;

    private ListView listBus;

    private Button btnRegistrar;

    private Button btnRegresar;
    private Button btnSalir;
    private HBox hboxSalir;

    private Label txtNull2;
    Connection con = DBConexion.conectarMySQL();

    public Bus(Stage s) {

        this.s = s;
        rootBus = new StackPane();
        sceneBus = new Scene(rootBus, 840, 480);

        imgFont = new Imagen("FontAzul5.jpeg", 840, 480);

        txtNull = new Label();
        txtBus = new Label("Bus");

        txtConsulta = new Label("Consultar por:");
        listConsulta = new ChoiceBox();
        listConsulta.setValue("Duenio");
        listConsulta.getItems().addAll("Placa", "Duenio", "Chofer");
        boxConsulta = new TextField();
        hboxConsulta = new HBox();
        hboxConsulta.setAlignment(Pos.CENTER);
        hboxConsulta.setSpacing(60);
        hboxConsulta.getChildren().addAll(txtConsulta, listConsulta, boxConsulta);

        txtOrdenar = new Label("Ordenar por:");
        listOrdenar = new ChoiceBox();
        listOrdenar.getItems().addAll("Placa", "Duenio", "Chofer");
        btnAsc = new Button("Ascendente");
        btnDesc = new Button("Descendente");
        hboxOrdenar = new HBox();
        hboxOrdenar.setAlignment(Pos.CENTER);
        hboxOrdenar.setSpacing(60);
        hboxOrdenar.getChildren().addAll(txtOrdenar, listOrdenar, btnAsc, btnDesc);

        listBus = new ListView();
        try {
            String Query = "SELECT * FROM BUS";
            Statement st = con.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            listBus.getItems().clear();
            listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

            while (resultSet.next()) {

                String linea;
                linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                        + "--" + resultSet.getString("DNI_CHOFER") + "--"
                        + resultSet.getString("COOPERATIVA");
                listBus.getItems().add(linea);

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
        vboxMain.getChildren().addAll(txtNull, txtBus, hboxConsulta, hboxOrdenar, listBus, btnRegistrar, hboxSalir, txtNull2);
        rootBus.getChildren().addAll(imgFont.getImagen(), vboxMain);
        Eventos();
    }

    public Scene getSceneBus() {
        return sceneBus;
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
            BusRegistrar busr = new BusRegistrar();
            busr.getStageBusR().show();
        });

        boxConsulta.setOnKeyReleased(value -> {
            if (listConsulta.getValue().equals("Placa")) {
                try {
                    String Query = "SELECT * FROM BUS WHERE PLACA LIKE " + "'" + boxConsulta.getText() + "%'";
                    Statement st = con.createStatement();
                    java.sql.ResultSet resultSet;
                    resultSet = st.executeQuery(Query);
                    listBus.getItems().clear();
                    listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                    while (resultSet.next()) {

                        String linea;
                        linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                + resultSet.getString("COOPERATIVA");
                        listBus.getItems().add(linea);

                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            } else if (listConsulta.getValue().equals("Duenio")) {
                try {
                    String Query = "SELECT * FROM BUS WHERE DNI_CLIENTE LIKE " + "'" + boxConsulta.getText() + "%'";
                    Statement st = con.createStatement();
                    java.sql.ResultSet resultSet;
                    resultSet = st.executeQuery(Query);
                    listBus.getItems().clear();
                    listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                    while (resultSet.next()) {

                        String linea;
                        linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                + resultSet.getString("COOPERATIVA");
                        listBus.getItems().add(linea);

                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            } else if (listConsulta.getValue().equals("Chofer")) {
                try {
                    String Query = "SELECT * FROM BUS WHERE DNI_CHOFER LIKE " + "'" + boxConsulta.getText() + "%'";
                    Statement st = con.createStatement();
                    java.sql.ResultSet resultSet;
                    resultSet = st.executeQuery(Query);
                    listBus.getItems().clear();
                    listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                    while (resultSet.next()) {

                        String linea;
                        linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                + resultSet.getString("COOPERATIVA");
                        listBus.getItems().add(linea);

                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }

        });

        btnAsc.setOnAction(value -> {

            if (listOrdenar.getValue().equals("Placa")) {
                if (listConsulta.getValue().equals("Placa")) {
                    try {
                        String Query = "SELECT * FROM BUS WHERE PLACA LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY PLACA ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listBus.getItems().clear();
                        listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                    + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                    + resultSet.getString("COOPERATIVA");
                            listBus.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Duenio")) {
                    try {
                        String Query = "SELECT * FROM BUS WHERE DNI_CLIENTE LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY PLACA ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listBus.getItems().clear();
                        listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                    + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                    + resultSet.getString("COOPERATIVA");
                            listBus.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Chofer")) {
                    try {
                        String Query = "SELECT * FROM BUS WHERE DNI_CHOFER LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY PLACA ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listBus.getItems().clear();
                        listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                    + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                    + resultSet.getString("COOPERATIVA");
                            listBus.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            } else if (listOrdenar.getValue().equals("Duenio")) {

                if (listConsulta.getValue().equals("Placa")) {
                    try {
                        String Query = "SELECT * FROM BUS WHERE PLACA LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY DNI_CLIENTE ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listBus.getItems().clear();
                        listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                    + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                    + resultSet.getString("COOPERATIVA");
                            listBus.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Duenio")) {
                    try {
                        String Query = "SELECT * FROM BUS WHERE DNI_CLIENTE LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY DNI_CLIENTE ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listBus.getItems().clear();
                        listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                    + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                    + resultSet.getString("COOPERATIVA");
                            listBus.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Chofer")) {
                    try {
                        String Query = "SELECT * FROM BUS WHERE DNI_CHOFER LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY DNI_CLIENTE ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listBus.getItems().clear();
                        listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                    + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                    + resultSet.getString("COOPERATIVA");
                            listBus.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }

            } else if (listOrdenar.getValue().equals("Chofer")) {
                
                if (listConsulta.getValue().equals("Placa")) {
                    try {
                        String Query = "SELECT * FROM BUS WHERE PLACA LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY DNI_CHOFER ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listBus.getItems().clear();
                        listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                    + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                    + resultSet.getString("COOPERATIVA");
                            listBus.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Duenio")) {
                    try {
                        String Query = "SELECT * FROM BUS WHERE DNI_CLIENTE LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY DNI_CHOFER ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listBus.getItems().clear();
                        listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                    + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                    + resultSet.getString("COOPERATIVA");
                            listBus.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Chofer")) {
                    try {
                        String Query = "SELECT * FROM BUS WHERE DNI_CHOFER LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY DNI_CHOFER ASC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listBus.getItems().clear();
                        listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                    + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                    + resultSet.getString("COOPERATIVA");
                            listBus.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            }

        });
        
        btnDesc.setOnAction(value->{
        
            if (listOrdenar.getValue().equals("Placa")) {
                if (listConsulta.getValue().equals("Placa")) {
                    try {
                        String Query = "SELECT * FROM BUS WHERE PLACA LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY PLACA DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listBus.getItems().clear();
                        listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                    + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                    + resultSet.getString("COOPERATIVA");
                            listBus.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Duenio")) {
                    try {
                        String Query = "SELECT * FROM BUS WHERE DNI_CLIENTE LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY PLACA DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listBus.getItems().clear();
                        listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                    + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                    + resultSet.getString("COOPERATIVA");
                            listBus.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Chofer")) {
                    try {
                        String Query = "SELECT * FROM BUS WHERE DNI_CHOFER LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY PLACA DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listBus.getItems().clear();
                        listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                    + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                    + resultSet.getString("COOPERATIVA");
                            listBus.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            } else if (listOrdenar.getValue().equals("Duenio")) {

                if (listConsulta.getValue().equals("Placa")) {
                    try {
                        String Query = "SELECT * FROM BUS WHERE PLACA LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY DNI_CLIENTE DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listBus.getItems().clear();
                        listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                    + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                    + resultSet.getString("COOPERATIVA");
                            listBus.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Duenio")) {
                    try {
                        String Query = "SELECT * FROM BUS WHERE DNI_CLIENTE LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY DNI_CLIENTE DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listBus.getItems().clear();
                        listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                    + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                    + resultSet.getString("COOPERATIVA");
                            listBus.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Chofer")) {
                    try {
                        String Query = "SELECT * FROM BUS WHERE DNI_CHOFER LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY DNI_CLIENTE DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listBus.getItems().clear();
                        listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                    + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                    + resultSet.getString("COOPERATIVA");
                            listBus.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }

            } else if (listOrdenar.getValue().equals("Chofer")) {
                
                if (listConsulta.getValue().equals("Placa")) {
                    try {
                        String Query = "SELECT * FROM BUS WHERE PLACA LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY DNI_CHOFER DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listBus.getItems().clear();
                        listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                    + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                    + resultSet.getString("COOPERATIVA");
                            listBus.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Duenio")) {
                    try {
                        String Query = "SELECT * FROM BUS WHERE DNI_CLIENTE LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY DNI_CHOFER DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listBus.getItems().clear();
                        listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                    + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                    + resultSet.getString("COOPERATIVA");
                            listBus.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (listConsulta.getValue().equals("Chofer")) {
                    try {
                        String Query = "SELECT * FROM BUS WHERE DNI_CHOFER LIKE " + "'" + boxConsulta.getText() + "%' ORDER BY DNI_CHOFER DESC";
                        Statement st = con.createStatement();
                        java.sql.ResultSet resultSet;
                        resultSet = st.executeQuery(Query);
                        listBus.getItems().clear();
                        listBus.getItems().add("PLACA--DNI_CLIENTE--DNI_CHOFER--COOPERATIVA");

                        while (resultSet.next()) {

                            String linea;
                            linea = resultSet.getString("PLACA") + "--" + resultSet.getString("DNI_CLIENTE")
                                    + "--" + resultSet.getString("DNI_CHOFER") + "--"
                                    + resultSet.getString("COOPERATIVA");
                            listBus.getItems().add(linea);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            }
            
        });
        
        listBus.setOnMouseClicked(clic->{
            String line[]=listBus.getSelectionModel().getSelectedItem().toString().split("--");
        BusMostrar cliente=new BusMostrar(line[0]);
        cliente.getStageBusM().show();
                
        });

    }
    private void salir(){
        btnSalir.setOnAction(e -> {
            s.close();
        });
    }
    private void regresar(){
        
    }
}
