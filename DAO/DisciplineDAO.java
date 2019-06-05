/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connexion.Connexion;
import Model.Discipline;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Flora
 */
public class DisciplineDAO extends DAO<Discipline> {
    
    /** Construsteur surcharge avec un seul parametre connect
     * @param connect */
    public DisciplineDAO(Connexion connect) {
        
        //Appel du constructeur par défaut de la classe mère
        super(connect);
    }

    
    /** creer : methode permettant de modifier un attribut d un objet de la table
     * @param obj
     * @param champ
     * @param element
     * @return  */
    @Override
    public boolean modifier(Discipline obj, String champ, String element) {

        //Déclaration d'un String
        String rqt;
        
        //Ajout de guillement si element est un Varchar dans la BDD
        if("Nom".equals(champ)){
            element = "\'" + element + "\'";
        }
        
        //Récupération de l'ordre de la requete
        rqt = "update discipline set " + champ + " = \'" + element + "\' where Id = " + obj.getID() + ";";

        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }
 
    }

    
    /** supprimer : methode permettant de supprimer un objet de la table
     * @param obj
     * @return  */
    @Override
    public boolean supprimer(Discipline obj) {
        
        //Déclaration d'un String
        String rqt;
        
        //Récupération de l'ordre de la requete
        rqt = "delete from discipline where Id = " + obj.getID() + ";";
        
        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }
    }

    
    /** ajouter : methode permettant d ajouter un nouvel objet dans la table
     * @return  */
    @Override
    public boolean ajouter(Discipline obj) {
        
        //Création d'un objet ResultSet
        ResultSet rset;
        
        try {
            //Récupération de l'ordre de la requete
            rset = connect.getStatement().executeQuery("select * from discipline");
            
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
            String rqt = "insert into discipline (" + champs + ") values (\'" + obj.getNom() + "\');";
            
            //Ajout de l'élément dans la table
            connect.getStatement().executeUpdate(rqt);
            
            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }  
    }

    
    /** trouver_et_charge : methode permettant de trouver et de charger dans les donnees un objet de la table via son id
     * @return  */
    @Override
    public Discipline trouver_et_charge(int id) {
        
        //Création d'un objet Discipline
        Discipline discipline = new Discipline();
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset = connect.getStatement().executeQuery("select * from discipline where id = " + id); 
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création du nouvel objet Discipline
                discipline = new Discipline(id, rset.getString("Nom"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        //Retourne l'objet trouvé
        return discipline;
    
    }
    
    
    /** trouver : methode permettant de trouver un objet de la table via son id
     * @return  */
    @Override
    public Discipline trouver(int id) {
        
        //Création d'un objet Discipline
        Discipline discipline = new Discipline();
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset = connect.getStatement().executeQuery("select * from discipline where id = " + id); 
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création du nouvel objet Discipline
                discipline = new Discipline(id, rset.getString("Nom"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        //Retourne l'objet trouvé
        return discipline;
    
    }
    
}
