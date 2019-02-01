/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Objetos.Imagen;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author anaklusmos
 */
public class AcercaDe {

    private static StackPane rootAcercaDe;
    private static Scene sceneAcercaDe;
    private static Imagen imgFont;

    
    private static Imagen imgAcercaDe;
    
    private static VBox vboxMain;
    private static Button btnAceptar;

    private static void Inicializar() {
        rootAcercaDe = new StackPane();
        sceneAcercaDe = new Scene(rootAcercaDe, 840, 480);
        imgFont = new Imagen("FontColor.jpeg", 840, 480);
        
       
        imgAcercaDe=new Imagen("AcercaDe.png", 840, 400);

        btnAceptar = new Button("Aceptar");
        vboxMain = new VBox();
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setSpacing(20);
        
        vboxMain.getChildren().addAll(imgAcercaDe.getImagen(),btnAceptar);
        
        rootAcercaDe.getChildren().addAll(imgFont.getImagen(),vboxMain);

    }

    private static void Eventos(Stage s) {
        btnAceptar.setOnAction(value -> {
            s.setScene(Opcion.getSceneOpcion());
        });

    }

    public static void InicializarAll(Stage s) {
        Inicializar();
        Eventos(s);
    }

    public static Scene getSceneAcercaDe() {
        return sceneAcercaDe;
    }

}
