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
public class HashExistant extends Exception {

    /**
     * Creates a new instance of <code>HashExistant</code> without detail
     * message.
     */
    public HashExistant() {
    }

    /**
     * Constructs an instance of <code>HashExistant</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public HashExistant(String msg) {
        super(msg);
    }
}
