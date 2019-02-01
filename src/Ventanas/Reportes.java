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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author anaklusmos
 */
public class Reportes {

    private static StackPane rootOpcion;
    private static Scene sceneReporte;

    private static Imagen imgFont;

    private static VBox vboxMain;

    ;
    private static Button btnCompraCliente;
    private static Button btnPromedioCliente;
    private static Button btntotalUnidades;
    
    private static Button btnClienteSeptiembre;
    private static Button btnClienteOctubre;
    private static Button btnClienteNoviembre;

    

    private static HBox hboxSalir;
    private static Button btnSalir;
    private static Button btnRegresar;
    
    private static Label txtNull;

    private static void Inicializar() {
        rootOpcion = new StackPane();
        sceneReporte = new Scene(rootOpcion, 840, 480);
        imgFont = new Imagen("FontAzul.jpeg", 840, 480);

        rootOpcion.setAlignment(Pos.CENTER);

        btnCompraCliente = new Button("Total Compra Cliente");
        btnPromedioCliente = new Button("Promedio Compra Cliente");
        btntotalUnidades =new Button("Total Unidades");
        
        btnClienteSeptiembre=new Button("Total Compra Cliente Septiembre 2018");
        btnClienteOctubre=new Button("Total Compra Cliente Octubre 2018");
        btnClienteNoviembre=new Button("Total Compra Cliente Noviembre 2018");
        
       
        

        vboxMain = new VBox();
        vboxMain.setAlignment(Pos.CENTER);
        vboxMain.setSpacing(20);

        hboxSalir = new HBox();
        hboxSalir.setAlignment(Pos.CENTER);
        hboxSalir.setSpacing(200);
        
        btnRegresar=new Button("Regresar");
        btnSalir = new Button("Salir");
        hboxSalir.getChildren().addAll(btnRegresar,btnSalir);
        
        txtNull=new Label();
        
        vboxMain.getChildren().addAll(btnCompraCliente, btnPromedioCliente,btntotalUnidades,btnClienteSeptiembre,btnClienteOctubre,btnClienteNoviembre, hboxSalir,txtNull);
        rootOpcion.getChildren().addAll(imgFont.getImagen(), vboxMain);

    }

    private static void Eventos(Stage s) {
      
        btnSalir.setOnAction(e->{
        s.close();
        });
   btnRegresar.setOnAction(value->{
   
   s.setScene(Opcion.getSceneOpcion());
   });
   
   btnCompraCliente.setOnAction(value->{
   TotalCliente tolcli=new TotalCliente(s);
   s.setScene(tolcli.getSceneCompra());
   });
   btnPromedioCliente.setOnAction(value->{
   
       PromedioCliente procli=new  PromedioCliente(s);
       s.setScene(procli.getSceneCompra());
   
   });

   btntotalUnidades.setOnAction(value->{
   TotalUnidades uni=new TotalUnidades(s);
   s.setScene(uni.getSceneCompra());
   
   });
   
   btnClienteSeptiembre.setOnAction(value->{
   TotalCliente1 tolcli=new TotalCliente1(s);
   s.setScene(tolcli.getSceneCompra());
   
   });
   
   btnClienteOctubre.setOnAction(value->{
   TotalCliente2 tolcli=new TotalCliente2(s);
   s.setScene(tolcli.getSceneCompra());
   
   });
   
   btnClienteNoviembre.setOnAction(value->{
   TotalCliente2 tolcli=new TotalCliente2(s);
   s.setScene(tolcli.getSceneCompra());
   
   });
    }
    

    public static void InicializarAll(Stage s) {
        Inicializar();
        Eventos(s);
    }

    public static Scene getSceneOpcion() {
        return sceneReporte;
    }

}