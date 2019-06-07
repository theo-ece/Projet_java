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
public class AnneeScolaireDAO extends DAO<AnneeScolaire> {
 
    
    /** Construsteur surcharge avec un seul parametre connect
     * @param connect */
    public AnneeScolaireDAO(Connexion connect) {
        
        //Appel du constructeur par défaut de la classe mère
        super(connect);
    }

    
    /** creer : methode permettant de modifier un attribut d un objet de la table
     * @param obj
     * @param champ
     * @param element
     * @return  */
    @Override
    public boolean modifier(AnneeScolaire obj, String champ, String element) {

        //Déclaration d'un String
        String rqt;
        
        //Ajout de guillement si element est un Varchar dans la BDD
        if("date".equals(champ)){
            element = "\'" + element + "\'";
        }

        //Récupération de l'ordre de la requete
        rqt = "update anneescolaire set " + champ + " = " + element + " where Id = " + obj.getID() + ";";
        System.out.println(rqt);

        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);

            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(AnneeScolaireDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }
 
    }

    
    /** supprimer : methode permettant de supprimer un objet de la table
     * @param obj
     * @return  */
    @Override
    public void supprimer(AnneeScolaire obj) {
        
        //Déclaration d'un String
        String rqt;

        //Récupération de l'ordre de la requete
        rqt = "delete from anneescolaire where Id = " + obj.getID() + ";";
            
        try {
            
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
        } catch (SQLException ex) {
            Logger.getLogger(AnneeScolaireDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /** ajouter : methode permettant d ajouter un nouvel objet dans la table
     * @return  */
    @Override
    public int ajouter(AnneeScolaire obj) {
        
        //Création d'un objet ResultSet
        ResultSet rset;
        
        try {
            //Récupération de l'ordre de la requete
            rset = connect.getStatement().executeQuery("select * from anneescolaire");
            
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
            String rqt = "insert into anneescolaire (" + champs + ") values (\'" + obj.getDate() + "\');";
            
            //Ajout de l'élément dans la table
            connect.getStatement().executeUpdate(rqt);
            
            //Recherche l'id de l'élément ajouter
            ResultSet rset1 = connect.getConnexion().createStatement().executeQuery("select LAST_INSERT_ID() as last_id from anneescolaire"); 
            if(rset1.next())
                return rset1.getInt("last_id");
            
            return 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(AnneeScolaireDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return 0;
        }  
    }

    
    /** trouver_et_charge : methode permettant de trouver et charger dans les donnees un objet de la table via son id
     * @param id
     * @return  */
    @Override
    public AnneeScolaire trouver_et_charge(int id) {
        
        //Création d'un objet AnneeScolaire
        AnneeScolaire anneescolaire = new AnneeScolaire();
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset = connect.getStatement().executeQuery("select * from anneescolaire where id = " + id);   
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création du nouvel objet AnneeScolaire
                anneescolaire = new AnneeScolaire(id, rset.getString("date"));
            }
                  
        } catch (SQLException ex) {
            Logger.getLogger(AnneeScolaireDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            //Recherche dans trimestre les Id_annee correspondant à notre objet AnneeScolaire
            ResultSet rset = connect.getConnexion().createStatement().executeQuery("select * from trimestre where Id_annee = " + id);
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création d'un objet TrimestreDAO
                TrimestreDAO trimestre_dao = new TrimestreDAO(connect);
                
                //Ajout d'un trimestre
                anneescolaire.addTrimestres(trimestre_dao.trouver_et_charge(rset.getInt("Id")));

                //Tant qu'il y a un résultat, on ajoute le trimestre dans la liste de trimestres de notre objet AnneeScolaire
                while(rset.next()) 
                    anneescolaire.addTrimestres(trimestre_dao.trouver_et_charge(rset.getInt("Id")));          
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AnneeScolaireDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {

            //Recherche dans classe les Id_annee correspondant à notre objet AnneeScolaire
            ResultSet rset = connect.getConnexion().createStatement().executeQuery("select * from classe where Id_annee = " + id);
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création d'un objet ClasseDAO
                ClasseDAO classe_dao = new ClasseDAO(connect);
                
                //Ajout d'une classe
                anneescolaire.addClasses(classe_dao.trouver_et_charge(rset.getInt("Id")));  

                //Tant qu'il y a un résultat, on ajoute la classe dans la liste de classes de notre objet AnneeScolaire
                while(rset.next()) 
                    anneescolaire.addClasses(classe_dao.trouver_et_charge(rset.getInt("Id")));          
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AnneeScolaireDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        //Retourne l'objet trouvé
        return anneescolaire;
    
    }
    
    
    /** trouver : methode permettant de trouver un objet de la table via son id
     * @return  */
    @Override
    public AnneeScolaire trouver(int id) {
        
        //Création d'un objet AnneeScolaire
        AnneeScolaire anneescolaire = new AnneeScolaire();
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset = connect.getStatement().executeQuery("select * from anneescolaire where id = " + id);   
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création du nouvel objet AnneeScolaire
                anneescolaire = new AnneeScolaire(id, rset.getString("date"));
            }
                  
        } catch (SQLException ex) {
            Logger.getLogger(AnneeScolaireDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Retourne l'objet trouvé
        return anneescolaire;
    
    }
    
}
