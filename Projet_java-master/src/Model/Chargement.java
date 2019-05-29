/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Flora
 */
public final class Chargement {
    
    /** Contructeur prive par defaut */
    private Chargement(){
        
    }
    
    /** charger : methode static permet de retourner un ArrayList contenant les Id de chaque objet d'une table
     * @param connect
     * @param table
     * @return 
     * @throws java.sql.SQLException */
    public static ArrayList<Integer> charger(Connexion connect, String table) throws SQLException{
        
        //Création d'un Objet ResultSet
        ResultSet rset = connect.getStatement().executeQuery("select * from " + table);
        
        //Création d'un objet ArrayList
        ArrayList<Integer> liste_Id = new ArrayList<>();
        
        if(rset.first()){
            
            //Ajout des Id dans l'ArrayList
            liste_Id.add(rset.getInt("Id"));
            
            //Ajout tant qu'il y a une ligne
            while(rset.next())
                liste_Id.add(rset.getInt("Id"));
        }
        
        //Le programme retourne l'ArrayList
        return liste_Id;
    }
}
