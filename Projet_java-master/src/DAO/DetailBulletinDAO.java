/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connexion.Connexion;
import Model.DetailBulletin;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Flora
 */
public class DetailBulletinDAO extends DAO<DetailBulletin> {
    
    /** Construsteur surcharge avec un seul parametre connect
     * @param connect */
    public DetailBulletinDAO(Connexion connect) {
        
        //Appel du constructeur par défaut de la classe mère
        super(connect);
    }

    
    /** creer : methode permettant de modifier un attribut d un objet de la table
     * @param obj
     * @param champ
     * @param element
     * @return  */
    @Override
    public boolean modifier(DetailBulletin obj, String champ, String element) {

        //Déclaration d'un String
        String rqt;
        
        //Ajout de guillement si element est un Varchar dans la BDD
        if("Appreciation".equals(champ)){
            element = "\'" + element + "\'";
        }

        //Récupération de l'ordre de la requete
        rqt = "update detail_bulletin set " + champ + " = " + element + " where Id = " + obj.getID() + ";";

        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DetailBulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }
 
    }

    
    /** supprimer : methode permettant de supprimer un objet de la table
     * @param obj
     * @return  */
    @Override
    public boolean supprimer(DetailBulletin obj) {
        
        //Déclaration d'un String
        String rqt;
        
        //Récupération de l'ordre de la requete
        rqt = "delete from detail_bulletin where Id = " + obj.getID() + ";";
        
        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DetailBulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }
    }

    
    /** ajouter : methode permettant d ajouter un nouvel objet dans la table
     * @return  */
    @Override
    public boolean ajouter(DetailBulletin obj) {
        
        //Création d'un objet ResultSet
        ResultSet rset;
        
        try {
            //Récupération de l'ordre de la requete
            rset = connect.getStatement().executeQuery("select * from detail_bulletin");
            
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
            String rqt = "insert into detail_bulletin (" + champs + ") values (\'" + obj.getAppreciation() + "\');";
            
            //Ajout de l'élément dans la table
            connect.getStatement().executeUpdate(rqt);
            
            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DetailBulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }  
    }

    
    /** trouver : methode permettant de trouver un objet de la table via son id
     * @return  */
    @Override
    public DetailBulletin trouver(int id) {
        
        //Création d'un objet DetailBulletin
        DetailBulletin detail_bulletin = new DetailBulletin();
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset = connect.getStatement().executeQuery("select * from detail_bulletin where id = " + id);  
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création du nouvel objet DetailBulletin
                detail_bulletin = new DetailBulletin(id, rset.getString("Appreciation"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DetailBulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {

            //Recherche dans evaluation les Id_db correspondant à notre objet DetailBulletin
            ResultSet rset = connect.getConnexion().createStatement().executeQuery("select * from evaluation where Id_db = " + id);
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création d'un objet EvaluationDAO
                EvaluationDAO evaluation_dao = new EvaluationDAO(connect);
                
                //Ajout d'une evaluation
                detail_bulletin.addEvaluations(evaluation_dao.trouver(rset.getInt("Id")));  

                //Tant qu'il y a un résultat, on ajoute l'evaluation dans la liste de evaluations de notre objet DetailBulletin
                while(rset.next()) 
                    detail_bulletin.addEvaluations(evaluation_dao.trouver(rset.getInt("Id")));          
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DetailBulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        //Retourne l'objet trouvé
        return detail_bulletin;
    
    }
    
}
