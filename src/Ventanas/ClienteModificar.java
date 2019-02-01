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
public class ClienteModificar {

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
    private TextField boxNombre;
    private HBox hboxNombre;

    private Label txtApellido;
    private TextField boxApellido;
    private HBox hboxApellido;

    private Label txtFecha;
    private DatePicker boxFecha;
    private HBox hboxFecha;

    private Label txtTelefono;
    private TextField boxTelefono;
    private HBox hboxTelefono;

    private Label txtCelular;
    private TextField boxCelular;
    private HBox hboxCelular;

    private Label txtCorreo;
    private TextField boxCorreo;
    private HBox hboxCorreo;

    private Label txtDirecc;
    private TextField boxDirecc;
    private HBox hboxDirecc;

    private Label txtCheck;

    private Button btnCancelar;
    private Button btnGuardar;
    private Button btnLimpiar;
    private HBox hboxBtn;

    private String cliente;
    Connection con = DBConexion.conectarMySQL();

    public ClienteModificar(String cliente, String nombre, String apellido, String fecha, String telefono, String celular, String correo, String Ciudad, String manzana, String villa, String sector) {
        this.cliente = cliente;

        sCliente = new Stage();

        rootClienteR = new StackPane();
        sceneClienteR = new Scene(rootClienteR, 440, 480);
        sCliente.setScene(sceneClienteR);
        sCliente.setTitle("Modificar Cliente");

        imgFont = new Imagen("FontAzul3.jpeg", 440, 480);

        txtClienteR = new Label("Modificar Cliente");

        txtCedula = new Label("Cedula:");
        boxCedula = new Label(cliente);
        hboxCedula = new HBox();
        hboxCedula.setSpacing(20);
        hboxCedula.getChildren().addAll(txtCedula, boxCedula);

        txtNombre = new Label("Nombre:");
        boxNombre = new TextField(nombre);
        hboxNombre = new HBox();
        hboxNombre.setSpacing(20);
        hboxNombre.getChildren().addAll(txtNombre, boxNombre);

        txtApellido = new Label("Apellido:");
        boxApellido = new TextField(apellido);
        hboxApellido = new HBox();
        hboxApellido.setSpacing(20);
        hboxApellido.getChildren().addAll(txtApellido, boxApellido);

        txtFecha = new Label("Fecha de Asociacion:");
        boxFecha = new DatePicker(LocalDate.parse(fecha));
        hboxFecha = new HBox();
        hboxFecha.setSpacing(20);
        hboxFecha.getChildren().addAll(txtFecha, boxFecha);

        txtTelefono = new Label("Telefono:");
        boxTelefono = new TextField(telefono);
        hboxTelefono = new HBox();
        hboxTelefono.setSpacing(20);
        hboxTelefono.getChildren().addAll(txtTelefono, boxTelefono);

        txtCelular = new Label("Celular:");
        boxCelular = new TextField(celular);
        hboxCelular = new HBox();
        hboxCelular.setSpacing(20);
        hboxCelular.getChildren().addAll(txtCelular, boxCelular);

        txtCorreo = new Label("Correo:");
        boxCorreo = new TextField(correo);
        hboxCorreo = new HBox();
        hboxCorreo.setSpacing(20);
        hboxCorreo.getChildren().addAll(txtCorreo, boxCorreo);

        txtDirecc = new Label("Direccion:");
        boxDirecc = new TextField(Ciudad + "," + manzana + "," + villa + "," + sector);
        hboxDirecc = new HBox();
        hboxDirecc.setSpacing(20);
        hboxDirecc.getChildren().addAll(txtDirecc, boxDirecc);

        txtCheck = new Label();

        txtCheck.setTextFill(Color.RED);
        txtCheck.setText("Ciudad,Manzana,Villa,Sector");

        btnCancelar = new Button("Cancelar");
        btnGuardar = new Button("Guardar");
        btnLimpiar = new Button("Limpiar");
        hboxBtn = new HBox();
        hboxBtn.setAlignment(Pos.CENTER);
        hboxBtn.setSpacing(50);
        hboxBtn.getChildren().addAll(btnCancelar, btnGuardar, btnLimpiar);

        vboxMain = new VBox();
        vboxMain.getChildren().addAll(txtClienteR, hboxCedula, hboxNombre, hboxApellido, hboxFecha, hboxTelefono, hboxCelular, hboxCorreo, hboxDirecc, txtCheck, hboxBtn);
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setSpacing(15);
        rootClienteR.getChildren().addAll(imgFont.getImagen(), vboxMain);
        Eventos();

    }

    public Stage getStageClienteMod() {
        return sCliente;
    }

    private void Eventos() {
        btnCancelar.setOnAction(e -> {
            sCliente.close();
        });

        btnLimpiar.setOnAction(e -> {

            boxNombre.setText("");
            boxApellido.setText("");
            boxFecha.setValue(LocalDate.now());
            boxTelefono.setText("");
            boxCelular.setText("");
            boxCorreo.setText("");
            boxDirecc.setText("");
            txtCheck.setText("");
        });

        btnGuardar.setOnAction(e -> {

            try {

                String Query1 = "UPDATE CLIENTE SET NOMBRE='" + boxNombre.getText() + "', APELLIDO='" + boxApellido.getText() + "', FECHA_ASOCIADO='" + boxFecha.getValue() + "' WHERE DNI='" + cliente + "'";
                Statement st = con.createStatement();
                java.sql.ResultSet resultSet;
                int res1 = st.executeUpdate(Query1);

                String Query2 = "UPDATE CONTACTO SET TELEFONO='" + boxTelefono.getText() + "', CELULAR='" + boxCelular.getText() + "', CORREO='" + boxCorreo.getText() + "' WHERE DNI='" + cliente + "'";
                int res2 = st.executeUpdate(Query2);

                String lin[] = boxDirecc.getText().split(",");

                String Query3 = "UPDATE DIRECCION SET CIUDAD='" + lin[0] + "', MANZANA='" + lin[1] + "', VILLA='" + lin[2] + "', SECTOR='" + lin[3] + "' WHERE DNI='" + cliente + "'";

                int res3 = st.executeUpdate(Query3);

                //////////////////////
                txtCheck.setText("Cambios Guardados");

            } catch (SQLException ex) {
                System.out.println(ex);
            }

            //////////////////////
        });
    }

}
