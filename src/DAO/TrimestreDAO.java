/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connexion.Connexion;
import Model.Trimestre;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/** TrimestreDAO : classe qui recupere les donnees de la table trimestre de la BDD
 *
 * @author Flora
 */
public class TrimestreDAO extends DAO<Trimestre> {
    
    /** Construsteur surcharge avec un seul parametre connect
     * @param connect un objet de type Connexion */
    public TrimestreDAO(Connexion connect) {
        
        //Appel du constructeur par défaut de la classe mère
        super(connect);
    }

    
    /** modifier : methode permettant de modifier un attribut d un objet de la table
     * @param obj un objet de typ Anneescolaire
     * @param champ un objet de type String 
     * @param element un objet de type element
     * @return vrai si la modification a eu lieu et non sinon */
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
     * @param obj un objet de type Trimestre */
    @Override
    public void supprimer(Trimestre obj) {
        
        //Déclaration d'un String
        String rqt;
        
        //Récupération de l'ordre de la requete
        rqt = "delete from trimestre where Id = " + obj.getID() + ";";
        
        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
        } catch (SQLException ex) {
            Logger.getLogger(TrimestreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /** ajouter : methode permettant d ajouter un nouvel objet dans la table
     * @param obj de type Trimestre
     * @return l ID de l objet ajouter dans la bdd */
    @Override
    public int ajouter(Trimestre obj) {
        
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
            
            //Récupération de l'ordre de la requete
            ResultSet rset1 = connect.getConnexion().createStatement().executeQuery("select * from anneescolaire");
            
            //Récupération de l'ordre de la requete
            String rqt = "insert into trimestre (" + champs + ") values (" + obj.getNumero() + ", \'" + obj.getDebut() + "\', \'" + obj.getFin() + ");";           
            
            //Ajout de l'élément dans la table
            connect.getStatement().executeUpdate(rqt);
            
            //Recherche l'id de l'élément ajouter
            ResultSet rset2 = connect.getConnexion().createStatement().executeQuery("select LAST_INSERT_ID() as last_id from trimestre"); 
            if(rset2.next())
                return rset2.getInt("last_id");
            
            return 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(TrimestreDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return 0;
        }  
    }

    
    /** trouver_et_charge : methode permettant de trouver et charger dans les donnees un objet de la table via son id
     * @param id l id de l objet qu il faut trouver dans la bdd
     * @return l objet Trimestre trouve */
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
                trimestre = new Trimestre(id, rset.getInt("Numero"), rset.getString("Debut"), rset.getString("Fin"));
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
                trimestre.addBulletins(bulletin_dao.trouver_et_charge(rset.getInt("Id")));  

                //Tant qu'il y a un résultat, on ajoute le bulletin dans la liste de bulletins de notre objet Trimestre
                while(rset.next()) 
                    trimestre.addBulletins(bulletin_dao.trouver_et_charge(rset.getInt("Id")));        
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TrimestreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        //Retourne l'objet trouvé
        return trimestre;
    
    }
    
    
    /** trouver : methode permettant de trouver dans les donnees un objet de la table via son id
     * @param id l id de l objet qu il faut trouver dans la bdd
     * @return l objet Trimestre trouve */
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
                trimestre = new Trimestre(id, rset.getInt("Numero"), rset.getString("Debut"), rset.getString("Fin"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TrimestreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        //Retourne l'objet trouvé
        return trimestre;
    
    }
}
