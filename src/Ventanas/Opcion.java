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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author root
 */
public class Opcion {

    private static StackPane rootOpcion;
    private static Scene sceneOpcion;

    private static Imagen imgFont;

    private static VBox vboxMain;

    private static Imagen imgLogo;
    private static Button btnCliente;
    private static Button btnChofer;
    private static Button btnBus;
    private static Button btnCompra;
    private static Button btnReportes;
    private static Button btnConsulta;
    private static Button btnAcercaDe;

    private static HBox hboxSalir;
    private static Button btnSalir;

    private static Label txtNull;

    private static void Inicializar() {
        rootOpcion = new StackPane();
        sceneOpcion = new Scene(rootOpcion, 840, 480);
        imgFont = new Imagen("FontAzul.jpeg", 840, 480);
        imgLogo = new Imagen("Logo.png", 250, 150);

        rootOpcion.setAlignment(Pos.CENTER);

        btnCliente = new Button("Cliente");
        btnChofer = new Button("Chofer");
        btnBus = new Button("Bus");
        btnCompra = new Button("Compra");
        btnReportes = new Button("Reportes");
        btnConsulta = new Button("Consultar Deuda");
        btnAcercaDe = new Button("Acerca De");

        vboxMain = new VBox();
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setSpacing(15);

        hboxSalir = new HBox();
        hboxSalir.setAlignment(Pos.CENTER);
        hboxSalir.setSpacing(200);

        btnSalir = new Button("Salir");
        hboxSalir.getChildren().addAll(btnSalir);

        txtNull = new Label();

        vboxMain.getChildren().addAll(imgLogo.getImagen(), btnCliente, btnChofer, btnBus, btnCompra, btnReportes, btnConsulta, btnAcercaDe, hboxSalir, txtNull);
        rootOpcion.getChildren().addAll(imgFont.getImagen(), vboxMain);

    }

    private static void Eventos(Stage s) {
        btnCliente.setOnAction(a -> {
            Cliente cl = new Cliente(s);
            s.setScene(cl.getSceneCliente());
        });
        btnChofer.setOnAction(b -> {
            Chofer cl = new Chofer(s);
            s.setScene(cl.getSceneChofer());
        });
        btnBus.setOnAction(c -> {
            Bus cl = new Bus(s);
            s.setScene(cl.getSceneBus());
        });
        btnCompra.setOnAction(d -> {
            Compra cl = new Compra(s);
            s.setScene(cl.getSceneCompra());
        });

        btnReportes.setOnAction(value -> {
            Reportes.InicializarAll(s);
            s.setScene(Reportes.getSceneOpcion());
        });
        btnSalir.setOnAction(e -> {
            s.close();
        });
        btnAcercaDe.setOnAction(e -> {
            AcercaDe.InicializarAll(s);
            s.setScene(AcercaDe.getSceneAcercaDe());
        });
        
        btnConsulta.setOnAction(value->{
        
        ConsultarDeuda deuda=new ConsultarDeuda(s);
        s.setScene(deuda.getSceneCliente());
        });

    }

    public static void InicializarAll(Stage s) {
        Inicializar();
        Eventos(s);
    }

    public static Scene getSceneOpcion() {
        return sceneOpcion;
    }

}
