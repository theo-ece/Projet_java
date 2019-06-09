/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Flora
 */
public class Controleur {
    
    public static void main(String[] args) {
        
        try {

            //Déclaration de variables
            String mdp, table, element, new_element;
            int choix, id;

            //Création d'objets Scanner
            Scanner clavier = new Scanner(System.in);
            Scanner s = new Scanner(System.in);
            Scanner cl = new Scanner(System.in);
            Scanner sc = new Scanner(System.in);

            //Création d'ArrayList
            ArrayList<String> liste;
            ArrayList<String> ajout = new ArrayList<>();
            
            //Saisie par l'utilisateur du mot de passe
            System.out.print("Mot de passe: ");
            mdp = sc.nextLine();
            
            //Création d'un objet Connexion
            Connexion conn = new Connexion(mdp);
            
            //Tant que le choix de l'utilisateur est mauvais
            do{
                //Menu
                System.out.println("\nQue souhaitez-vous faire ? ");
                System.out.println("1. Afficher une table");
                System.out.println("2. Ajouter un élément dans une table");
                System.out.println("3. Supprimer un élément d'une table");
                System.out.println("4. Modifier un élément d'une table");
                
                //Saisie du choix de l'utilisateur
                System.out.print("Votre choix: ");
                choix = sc.nextInt();
                
            }while(choix<1 || choix>4);
            
            //Traitement selon le choix de l'utilisateur
            switch(choix){
                
                //CAS AFFICHER UNE TABLE
                case 1:
                        //Saisie par l'utilisateur de la table qu'il souhaite voir
                        System.out.print("\nQuelle table souhaitez-vous voir ? ");
                        table = clavier.nextLine();

                        //Récupération des résultats de la table selectionnée
                        liste = conn.afficherTable(table);

                        //Affichage du nom de la table en question
                        System.out.println("\nTable \"" + table + "\" :\n");

                        //Affichage des champs de la table selectionnée 
                        for(int i=0; i<liste.size(); i++){
                            System.out.println(liste.get(i));
                        }
                    break;
                    
                //CAS AJOUTER UN ELEMENT DANS UNE TABLE
                case 2:
                        //Saisie par l'utilisateur de la table dans laquelle il souhaite ajouter un élément
                        System.out.print("\nDans quelle table souhaitez-vous ajouter un élément ? ");
                        table = clavier.nextLine();
                        
                        //Appel de la fonction qui permet de récupérer les champs de la table en question
                        liste = conn.recuperer_champs(table);
                        
                        //Informations pour l'utilisateur si l'attribut est un String
                        System.out.println("\nSi c'est un String, mettre entre ' '.");
                        
                        //Pour tous les champs de la tables
                        for(int i=0; i<liste.size(); i++){
                            
                            //L'utilisateur rentre une donnée correspondante au champ
                            System.out.print(liste.get(i) + ": ");
                            element = clavier.nextLine();
                            ajout.add(element);
                        }
                        
                        //Ajout de l'élément dans la table correspondante
                        conn.ajouter_element(table, liste, ajout);
                        
                        //Récupération des résultats de la table selectionnée
                        liste = conn.afficherTable(table);
                        
                        //Réaffichage de la table modifiée
                        System.out.println("\n");
                        for(int i=0; i<liste.size(); i++){
                            System.out.println(liste.get(i));
                        }
                        
                    break;
                
                //CAS SUPPRESSION D'UN ELEMENT D'UNE TABLE
                case 3: 
                        //Saisie par l'utilisateur de la table dans laquelle il souhaite ajouter un élément
                        System.out.print("\nDans quelle table souhaitez-vous supprimer un élément ? ");
                        table = clavier.nextLine();
                        
                        //Appel de la fonction qui permet de récupérer les champs de la table en question
                        liste = conn.afficherTable(table);

                        //Affichage du nom de la table en question
                        System.out.println("\nTable \"" + table + "\" :\n");

                        //Affichage des champs de la table selectionnée 
                        for(int i=0; i<liste.size(); i++){
                            System.out.println(liste.get(i));
                        }
                        
                        //Saisie de l'Id de l'élément à supprimer
                        System.out.print("Choisissez l'ID de l'élément à supprimer: ");
                        id = clavier.nextInt();
                        
                        //Ajout de l'élément dans la table correspondante
                        conn.supprimer_element(table, id);
                        
                        //Récupération des résultats de la table selectionnée
                        liste = conn.afficherTable(table);
                        
                        //Réaffichage de la table modifiée
                        System.out.println("\n");
                        for(int i=0; i<liste.size(); i++){
                            System.out.println(liste.get(i));
                        }
                    break;
                    
                //CAS MODIFICATION D'UN ELEMENT D'UNE TABLE
                case 4: 
                        //Saisie par l'utilisateur de la table dans laquelle il souhaite ajouter un élément
                        System.out.print("\nDans quelle table souhaitez-vous modifier un élément ? ");
                        table = clavier.nextLine();
                        
                        //Appel de la fonction qui permet de récupérer les champs de la table en question
                        liste = conn.afficherTable(table);

                        //Affichage du nom de la table en question
                        System.out.println("\nTable \"" + table + "\" :\n");

                        //Affichage des champs de la table selectionnée 
                        for(int i=0; i<liste.size(); i++){
                            System.out.println(liste.get(i));
                        }
                        
                        //Saisie de l'Id de l'élément à modifier
                        System.out.print("\nChoisissez l'ID de l'élément à modifier: ");
                        id = clavier.nextInt();
                        
                        //Appel de la fonction qui permet de récupérer les champs de la table en question
                        liste = conn.recuperer_champs(table);
                        
                        //Affichage des champs de la table
                        for(int i=0; i<liste.size(); i++){
                            System.out.println(liste.get(i));
                        }
                        
                        //Saisie du champ de l'élément à modifier
                        System.out.print("\nChoisissez le champ à modifier: ");
                        element = cl.nextLine();
                        
                        //Saisie de la nouvelle valeur du champ
                        System.out.print("Nouvelle valeur (entre ' ' si c'est un String): ");
                        new_element = s.nextLine();
                        
                        //Modification de l'élément dans la table correspondante
                        conn.modifier_element(table, id, element, new_element);
                        
                        //Récupération des résultats de la table selectionnée
                        liste = conn.afficherTable(table);
                        
                        //Réaffichage de la table modifiée
                        System.out.println("\n");
                        for(int i=0; i<liste.size(); i++){
                            System.out.println(liste.get(i));
                        }
                    break;
            }
            

        } catch (SQLException | ClassNotFoundException ex) {
            //Erreur
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
   
}
