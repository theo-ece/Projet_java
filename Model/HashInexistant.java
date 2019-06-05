/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author thebo
 */
public class HashInexistant extends Exception {

    /**
     * Creates a new instance of <code>HashInexistant</code> without detail
     * message.
     */
    public HashInexistant() {
    }

    /**
     * Constructs an instance of <code>HashInexistant</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public HashInexistant(String msg) {
        super(msg);
    }
}
