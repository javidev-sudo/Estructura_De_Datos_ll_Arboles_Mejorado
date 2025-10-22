/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package excepciones;

/**
 *
 * @author javi24
 */
public class ExcepcionOrdenInvalido extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionOrdenInvalido</code> without
     * detail message.
     */
    public ExcepcionOrdenInvalido() {
        super("El orden es invalido");
    }

    public ExcepcionOrdenInvalido(String msg) {
        super(msg);
    }
}
