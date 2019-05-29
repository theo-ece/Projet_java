package Connexion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Flora
 */
public class DataIncorrecte extends Exception {
    
    /** Attribut prive msg */
    private final String msg;
    
    /** Constructeur surcharge avec un seul parametre msg
     * @param msg */
    public DataIncorrecte(String msg){
        this.msg = msg;
    }
    
    /**
     * @return  */
    public String getMsg(){
        return msg;
    }
}
