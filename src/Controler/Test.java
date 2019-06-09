/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controler;
import Vue.Connexion_gr;
import Model.*;
import java.sql.SQLException;

/** Test : classe contenant le main
 *
 * @author thebo
 */
public class Test {

    /** Main du projet
     * @param args the command line arguments
     * @throws java.sql.SQLException sql exception
     */
    public static void main(String[] args) throws SQLException {
        
        Chargement charge = new Chargement();
        Controleur control = new Controleur();
                
        //Ouverture de la page de connexion
        Connexion_gr test= new Connexion_gr();
        test.setVisible(true);
        test.setLocationRelativeTo(null);
        
   
    }
    
}
