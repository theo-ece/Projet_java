/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connexion.Connexion;
import Model.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Flora
 */
public class EcoleDAO extends DAO<Ecole> {
    
    /** Construsteur surcharge avec un seul parametre connect
     * @param connect */
    public EcoleDAO(Connexion connect) {
        
        //Appel du constructeur par défaut de la classe mère
        super(connect);
    }

    
    /** creer : methode permettant de modifier un attribut d un objet de la table
     * @param obj
     * @param champ
     * @param element
     * @return  */
    @Override
    public boolean modifier(Ecole obj, String champ, String element) {

        //Déclaration d'un String
        String rqt;
        
        //Ajout de guillement si element est un Varchar dans la BDD
        if("Nom".equals(champ) || "Region".equals(champ)){
            element = "\'" + element + "\'";
        }

        //Récupération de l'ordre de la requete
        rqt = "update ecole set " + champ + " = " + element + " where Id = " + obj.getID() + ";";

        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(EcoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }
 
    }

    
    /** supprimer : methode permettant de supprimer un objet de la table
     * @param obj
     * @return  */
    @Override
    public boolean supprimer(Ecole obj) {
        
        //Déclaration d'un String
        String rqt;
        
        //Récupération de l'ordre de la requete
        rqt = "delete from ecole where Id = " + obj.getID() + ";";
        
        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(EcoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }
    }

    
    /** ajouter : methode permettant d ajouter un nouvel objet dans la table
     * @return  */
    @Override
    public boolean ajouter(Ecole obj) {
        
        //Création d'un objet ResultSet
        ResultSet rset;
        
        try {
            //Récupération de l'ordre de la requete
            rset = connect.getStatement().executeQuery("select * from ecole");
            
            //Récupération du résultat de l'ordre
            ResultSetMetaData rsetMeta = rset.getMetaData();
            
            //Calcul du nombre de colonnes du résultat
            int nbColonne = rsetMeta.getColumnCount();
            
            //Déclaration d'un String
            String champs = "";
            
            //Pour tous les champs de la tables
            for(int i=1; i<nbColonne; i++){
                
                //Ajout des champs dans le String champs
                if("".equals(champs)){
                    champs = rsetMeta.getColumnLabel(i+1);
                } else {
                    champs += ", " + rsetMeta.getColumnLabel(i+1);
                }
            }
            
            //Récupération de l'ordre de la requete
            String rqt = "insert into ecole (" + champs + ") values (\'" + obj.getNom()+ "\', \'" + obj.getRegion() + "\');";
            
            //Ajout de l'élément dans la table
            connect.getStatement().executeUpdate(rqt);
            
            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(EcoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }  
    }

    
    /** trouver : methode permettant de trouver un objet de la table via son id
     * @return  */
    @Override
    public Ecole trouver(int id) {
        
        //Création d'un objet Ecole
        Ecole ecole = new Ecole();
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset = connect.getStatement().executeQuery("select * from ecole where id = " + id); 
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création du nouvel objet Ecole
                ecole = new Ecole(id, rset.getString("Nom"), rset.getString("Region"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EcoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset2 = connect.getConnexion().createStatement().executeQuery("select * from niveau where Id = (select Id_niveau from classe where Id_ecole =" + id + ")");   

            //Si on a un résultat, on se positionne sur cette ligne
            if (rset2.first()){

                //Création d'un objet Niveau
                NiveauDAO niveau_dao = new NiveauDAO(connect);

                //Ajout des niveaux dans l'Ecole
                ecole.addNiveaux(niveau_dao.trouver(rset2.getInt("Id")));  

                //Tant qu'il y a un résultat, on ajoute le niveau dans la liste de niveaux de notre objet Ecole
                while(rset2.next()) 
                    ecole.addNiveaux(niveau_dao.trouver(rset2.getInt("Id")));     
            }

        } catch (SQLException ex) {
            Logger.getLogger(EcoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset2 = connect.getConnexion().createStatement().executeQuery("select * from etudiant where Id_ecole = " + id + ")");   

            //Si on a un résultat, on se positionne sur cette ligne
            if (rset2.first()){

                //Création d'un objet Personne
                PersonneDAO etudiant_dao = new PersonneDAO(connect);

                //Ajout des etudiants dans l'ecole
                ecole.addEtudiants((Etudiant) etudiant_dao.trouver(rset2.getInt("Id")));

                //Tant qu'il y a un résultat, on ajoute l'etudiant dans la liste des etudiants de notre objet Ecole
                while(rset2.next()) 
                    ecole.addEtudiants((Etudiant) etudiant_dao.trouver(rset2.getInt("Id")));     
            }

        } catch (SQLException ex) {
            Logger.getLogger(EcoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset2 = connect.getConnexion().createStatement().executeQuery("select * from enseignant where Id_ecole = " + id + ")");   

            //Si on a un résultat, on se positionne sur cette ligne
            if (rset2.first()){

                //Création d'un objet Personne
                PersonneDAO enseignant_dao = new PersonneDAO(connect);

                //Ajout des enseignant dans l'ecole
                ecole.addEnseignants((Enseignant) enseignant_dao.trouver(rset2.getInt("Id")));

                //Tant qu'il y a un résultat, on ajoute l'enseignant dans la liste des etudiants de notre objet Ecole
                while(rset2.next()) 
                    ecole.addEtudiants((Etudiant) enseignant_dao.trouver(rset2.getInt("Id")));     
            }

        } catch (SQLException ex) {
            Logger.getLogger(EcoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        //Retourne l'objet trouvé
        return ecole;
    
    }
    
}
