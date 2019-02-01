/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Ventanas.Opcion;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author anaklusmos
 */

public class cooperativa extends Application {
    
    @Override
    public void start(Stage s) {
        
        
 
        Opcion.InicializarAll(s);
        
        s.setTitle("Cooperativa - 10 de Agosto");
        s.setScene(Opcion.getSceneOpcion());
        s.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
