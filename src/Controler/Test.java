/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;
import Connexion.Connexion;
import Vue.Connexion_gr;
import Connexion.DataIncorrecte;
import Model.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author thebo
 */
public class Test {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        
        Chargement charge = new Chargement();
        Controleur control = new Controleur();
                
        //Ouverture de la page de connexion
        Connexion_gr test= new Connexion_gr();
        test.setVisible(true);
         test.setLocationRelativeTo(null);
        
        //Déclaration de variables
        /*String mdp, login, base, serveur;
        
        //Création d'objets Scanner
        Scanner sc = new Scanner(System.in);
        Scanner cl = new Scanner(System.in);
                    
        //Saisie par l'utilisateur du mot de passe
        System.out.println("*** CONNEXION A LA BDD JAVA ***");
        System.out.print("Login: ");
        login = sc.nextLine();
        
        //Saisie par l'utilisateur du mot de passe
        System.out.print("Mot de passe: ");
        mdp = cl.nextLine();
        
        //Saisie par l'utilisateur du serveur
        System.out.print("Serveur: ");
        serveur = cl.nextLine();
        
        //Saisie par l'utilisateur de la base
        System.out.print("Base: ");
        base = cl.nextLine();

        //Création d'un objet Connexion
        Connexion connect;
        
        try {
            try {
                //Création d'un objet Connexion
                connect = new Connexion(mdp,login,base,serveur);
                
                //Création d'un objet Chargement
                Chargement charge = new Chargement(connect);
                charge.initialisation();
                Controleur controleur = new Controleur(charge, connect);
                
                
            } catch (DataIncorrecte di) {
                System.out.println(di.getMsg());
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }*/
   
    }
    
}
