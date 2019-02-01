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
public class ClienteMostrar {

    private Stage sCliente;

    private StackPane rootClienteR;
    private Scene sceneClienteR;

    private Imagen imgFont;

    private VBox vboxMain;

    private Label txtClienteR;

    private Label txtCedula;
    private Label boxCedula;
    private HBox hboxCedula;

    private Label txtNombre;
    private Label boxNombre;
    private HBox hboxNombre;

    private Label txtApellido;
    private Label boxApellido;
    private HBox hboxApellido;

    private Label txtFecha;
    private Label boxFecha;
    private HBox hboxFecha;

    private Label txtTelefono;
    private Label boxTelefono;
    private HBox hboxTelefono;

    private Label txtCelular;
    private Label boxCelular;
    private HBox hboxCelular;

    private Label txtCorreo;
    private Label boxCorreo;
    private HBox hboxCorreo;

    private Label txtDirecc;
    private Label boxDirecc;
    private HBox hboxDirecc;

    private Label txtCheck;

    private Button btnSalir;
    private Button btnModificar;
    private Button btnEliminar;
    private HBox hboxBtn;

    private String cliente;
    private String linea;

    private String datos[];
    Connection con = DBConexion.conectarMySQL();

    public ClienteMostrar(String cliente) {

        this.cliente = cliente;
        try {
            String Query = "SELECT C.DNI,C.NOMBRE,C.APELLIDO,C.FECHA_ASOCIADO,CO.TELEFONO,CO.CELULAR,CO.CORREO,D.CIUDAD,D.MANZANA,D.VILLA,D.SECTOR FROM CLIENTE AS C INNER JOIN CONTACTO AS CO ON C.DNI=CO.DNI INNER JOIN DIRECCION AS D ON C.DNI=D.DNI HAVING DNI='" + cliente + "'";
            Statement st = con.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);

            while (resultSet.next()) {

                linea = resultSet.getString("DNI") + "--" + resultSet.getString("NOMBRE")
                        + "--" + resultSet.getString("APELLIDO") + "--" + resultSet.getString("FECHA_ASOCIADO") + "--" + resultSet.getString("TELEFONO") + "--" + resultSet.getString("CELULAR")
                        + "--" + resultSet.getString("CORREO") + "--" + resultSet.getString("CIUDAD") + "--" + resultSet.getString("MANZANA")
                        + "--" + resultSet.getString("VILLA") + "--" + resultSet.getString("SECTOR");

                System.out.println(linea);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        datos = linea.split("--");

        sCliente = new Stage();

        rootClienteR = new StackPane();
        sceneClienteR = new Scene(rootClienteR, 440, 480);
        sCliente.setScene(sceneClienteR);
        sCliente.setTitle("Mostrar Cliente");

        imgFont = new Imagen("FontAzul3.jpeg", 440, 480);

        txtClienteR = new Label("Mostrar Cliente");

        txtCedula = new Label(datos[0]);
        boxCedula = new Label(cliente);
        hboxCedula = new HBox();
        hboxCedula.setSpacing(20);
        hboxCedula.getChildren().addAll(txtCedula, boxCedula);

        txtNombre = new Label("Nombre:");
        boxNombre = new Label(datos[1]);
        hboxNombre = new HBox();
        hboxNombre.setSpacing(20);
        hboxNombre.getChildren().addAll(txtNombre, boxNombre);

        txtApellido = new Label("Apellido:");
        boxApellido = new Label(datos[2]);
        hboxApellido = new HBox();
        hboxApellido.setSpacing(20);
        hboxApellido.getChildren().addAll(txtApellido, boxApellido);

        txtFecha = new Label("Fecha de Asociacion:");
        boxFecha = new Label(datos[3]);
        hboxFecha = new HBox();
        hboxFecha.setSpacing(20);
        hboxFecha.getChildren().addAll(txtFecha, boxFecha);

        txtTelefono = new Label("Telefono:");
        boxTelefono = new Label(datos[4]);
        hboxTelefono = new HBox();
        hboxTelefono.setSpacing(20);
        hboxTelefono.getChildren().addAll(txtTelefono, boxTelefono);

        txtCelular = new Label("Celular:");
        boxCelular = new Label(datos[5]);
        hboxCelular = new HBox();
        hboxCelular.setSpacing(20);
        hboxCelular.getChildren().addAll(txtCelular, boxCelular);

        txtCorreo = new Label("Correo:");
        boxCorreo = new Label(datos[6]);
        hboxCorreo = new HBox();
        hboxCorreo.setSpacing(20);
        hboxCorreo.getChildren().addAll(txtCorreo, boxCorreo);

        txtDirecc = new Label("Direccion:");
        boxDirecc = new Label(datos[7] + "," + datos[8] + "," + datos[9] + "," + datos[10]);
        hboxDirecc = new HBox();
        hboxDirecc.setSpacing(20);
        hboxDirecc.getChildren().addAll(txtDirecc, boxDirecc);

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
        vboxMain.getChildren().addAll(txtClienteR, hboxCedula, hboxNombre, hboxApellido, hboxFecha, hboxTelefono, hboxCelular, hboxCorreo, hboxDirecc, txtCheck, hboxBtn);
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setSpacing(15);
        rootClienteR.getChildren().addAll(imgFont.getImagen(), vboxMain);
        Eventos();

    }

    public Stage getStageClienteM() {
        return sCliente;
    }

    private void Eventos() {
        btnSalir.setOnAction(e -> {
            sCliente.close();
        });

        btnModificar.setOnAction(e -> {
            ClienteModificar clientMod = new ClienteModificar(cliente, datos[1], datos[2], datos[3], datos[4], datos[5], datos[6], datos[7], datos[8], datos[9], datos[10]);
            clientMod.getStageClienteMod().show();

        });

        btnEliminar.setOnAction(e -> {
            try {

                Statement st = con.createStatement();
                java.sql.ResultSet resultSet;

                String Query2 = "DELETE FROM  CONTACTO WHERE DNI='" + cliente + "'";
                int res2 = st.executeUpdate(Query2);


                String Query3 = "DELETE FROM  DIRECCION WHERE DNI='" + cliente + "'";

                int res3 = st.executeUpdate(Query3);

                String Query1 = "DELETE FROM CLIENTE WHERE DNI='" + cliente + "'";
                int res1 = st.executeUpdate(Query1);

                //////////////////////
                sCliente.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        });
    }

}
