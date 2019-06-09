package Connexion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/** DataIncorrecte : exception sur les login, password, serveur et nom de la base lors de la connexion
 *
 * @author Flora
 */
public class DataIncorrecte extends Exception {
    
    /** Attribut prive msg */
    private final String msg;
    
    
    /** Constructeur surcharge avec un seul parametre msg
     * @param msg un message d erreur */
    public DataIncorrecte(String msg){
        this.msg = msg;
    }
    
    /** getMsg : permettant d acceder a l attribut msg 
     * @return l attribut msg */
    public String getMsg(){
        return msg;
    }
}
