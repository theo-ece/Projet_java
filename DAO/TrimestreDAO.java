/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connexion.Connexion;
import Model.Trimestre;
import java.sql.*;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Flora
 */
public class TrimestreDAO extends DAO<Trimestre> {
    
    /** Construsteur surcharge avec un seul parametre connect
     * @param connect */
    public TrimestreDAO(Connexion connect) {
        
        //Appel du constructeur par défaut de la classe mère
        super(connect);
    }

    
    /** creer : methode permettant de modifier un attribut d un objet de la table
     * @param obj
     * @param champ
     * @param element
     * @return  */
    @Override
    public boolean modifier(Trimestre obj, String champ, String element) {

        //Déclaration d'un String
        String rqt;
        
        //Ajout de guillement si element est une Date dans la BDD
        if("Debut".equals(champ) || "Fin".equals(champ)){
            element = "\'" + element + "\'";
        }

        //Récupération de l'ordre de la requete
        rqt = "update trimestre set " + champ + " = " + element + " where Id = " + obj.getID() + ";";

        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(TrimestreDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }
 
    }

    
    /** supprimer : methode permettant de supprimer un objet de la table
     * @param obj
     * @return  */
    @Override
    public boolean supprimer(Trimestre obj) {
        
        //Déclaration d'un String
        String rqt;
        
        //Récupération de l'ordre de la requete
        rqt = "delete from trimestre where Id = " + obj.getID() + ";";
        
        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(TrimestreDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }
    }

    
    /** ajouter : methode permettant d ajouter un nouvel objet dans la table
     * @return  */
    @Override
    public boolean ajouter(Trimestre obj) {
        
        //Création d'un objet ResultSet
        ResultSet rset;
        
        try {
            //Récupération de l'ordre de la requete
            rset = connect.getStatement().executeQuery("select * from trimestre");
            
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
            
            //Récupération des annees
            Calendar c = Calendar.getInstance();
            c.setTime(obj.getDebut());
            System.out.println(c);
            
            //Récupération de l'ordre de la requete
            ResultSet rset1 = connect.getConnexion().createStatement().executeQuery("select * from anneescolaire");
            
            //Récupération de l'ordre de la requete
            String rqt = "insert into trimestre (" + champs + ") values (" + obj.getNumero() + ", \'" + obj.getDebut() + "\', \'" + obj.getFin() + ");";           
            
            //Ajout de l'élément dans la table
            connect.getStatement().executeUpdate(rqt);
            
            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(TrimestreDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }  
    }

    
    /** trouver_et_charge : methode permettant de trouver et de charger dans les donnees un objet de la table via son id
     * @return  */
    @Override
    public Trimestre trouver_et_charge(int id) {
        
        //Création d'un objet Trimestre
        Trimestre trimestre = new Trimestre();
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset = connect.getStatement().executeQuery("select * from trimestre where id = " + id);
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création du nouvel objet Trimestre
                trimestre = new Trimestre(id, rset.getInt("Numero"), rset.getDate("Debut"), rset.getDate("Fin"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TrimestreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {

            //Recherche dans bulletin les Id_trimestre correspondant à notre objet Trimestre
            ResultSet rset = connect.getConnexion().createStatement().executeQuery("select * from bulletin where Id_trimestre = " + id);
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création d'un objet Bulletin
                BulletinDAO bulletin_dao = new BulletinDAO(connect);
                
                //Ajout d'un bulletin
                trimestre.addClasses(bulletin_dao.trouver(rset.getInt("Id")));  

                //Tant qu'il y a un résultat, on ajoute le bulletin dans la liste de bulletins de notre objet Trimestre
                while(rset.next()) 
                    trimestre.addClasses(bulletin_dao.trouver(rset.getInt("Id")));        
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TrimestreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        //Retourne l'objet trouvé
        return trimestre;
    
    }
    
    
     /** trouver : methode permettant de trouver un objet de la table via son id
     * @return  */
    @Override
    public Trimestre trouver(int id) {
        
        //Création d'un objet Trimestre
        Trimestre trimestre = new Trimestre();
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset = connect.getStatement().executeQuery("select * from trimestre where id = " + id);
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création du nouvel objet Trimestre
                trimestre = new Trimestre(id, rset.getInt("Numero"), rset.getDate("Debut"), rset.getDate("Fin"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TrimestreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        //Retourne l'objet trouvé
        return trimestre;
    
    }
}
