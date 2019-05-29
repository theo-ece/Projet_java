/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;
import Connexion.Connexion;
import Model.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author thebo
 */
public class Projet {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        
        //Déclaration de variables
        String mdp;
        int localhost;
        
        //Création d'objets Scanner
        Scanner sc = new Scanner(System.in);
                    
        //Saisie par l'utilisateur du mot de passe
        System.out.print("Mot de passe: ");
        mdp = sc.nextLine();
        
        //Saisie par l'utilisateur du mot de passe
        System.out.print("Localhost: ");
        localhost = sc.nextInt();
        
        //Création d'un objet Connexion
        Connexion connect;
        
        try {
            //Création d'un objet Connexion
            connect = new Connexion(mdp,localhost);
            
            // TODO code application logic here
            Ecole ECE = new Ecole("str", connect);
            ECE.run();
 
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Projet.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
    
}
