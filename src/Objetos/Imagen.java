/*
Esta clase inicia la imagen deseada y sus dimenciones
 */
package Objetos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author dennys
 */
public class Imagen {

    private String nombre;
    private FileInputStream fileimg;
    private Image imag;
    private ImageView imagen;

    public Imagen(String nombr, int ancho, int alto) {
        this.nombre = nombr;
        this.fileimg = null;
        try {
            this.fileimg = new FileInputStream("src/Recursos/" + nombre);
        } catch (FileNotFoundException e) {
            System.out.println("Img no encontrada");
        }
        this.imag = new Image(fileimg);
        this.imagen = new ImageView(imag);
        this.imagen.setFitWidth(ancho);
        this.imagen.setFitHeight(alto);
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagenNombre(String nombre) {
        this.nombre = nombre;
    }

    public Image getImage() {
        return imag;
    }

    public String getNombre() {
        return nombre;
    }
}
