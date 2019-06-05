/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;
import Connexion.Connexion;
import Connexion.DataIncorrecte;
import DAO.*;
import Model.*;
import Graphisme.*;
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
        
        //Ouverture de la page de connexion
        Connexion_gr test= new Connexion_gr();
        test.setVisible(true);
        
        //Déclaration de variables
        String mdp;
        int localhost;
        
        //Création d'objets Scanner
        Scanner sc = new Scanner(System.in);
        Scanner cl = new Scanner(System.in);
                    
        //Saisie par l'utilisateur du mot de passe
        System.out.println("*** CONNEXION A LA BDD JAVA ***");
        System.out.print("Localhost: ");
        localhost = sc.nextInt();
        
        //Saisie par l'utilisateur du mot de passe
        System.out.print("Mot de passe: ");
        mdp = cl.nextLine();

        //Création d'un objet Connexion
        Connexion connect;
        
        try {
            try {
                //Création d'un objet Connexion
                connect = new Connexion(mdp,localhost);
                
                //Création d'un objet Chargement
                Chargement charge = new Chargement(connect);
                charge.chargementTrimestres();
                charge.chargementEvaluations();
                charge.chargementDetailsBulletins();
                charge.chargementBulletins();
                charge.chargementDisciplines();
                charge.chargementAnneeScolaires();
                charge.chargementPersonnes();
                charge.chargementClasses();
                charge.chargementNiveaux();
                charge.chargementEnseignement();
                charge.chargementInscription();
                charge.chargementEcoles();

                charge.run();
                
            } catch (DataIncorrecte di) {
                System.out.println(di.getMsg());
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Projet.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
    
}
