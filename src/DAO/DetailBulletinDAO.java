/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connexion.Connexion;
import Model.DetailBulletin;
import Model.Enseignant;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/** DetailBulletinDAO : classe qui recupere les donnees de la table detail_bulletin de la BDD
 *
 * @author Flora
 */
public class DetailBulletinDAO extends DAO<DetailBulletin> {
    
    /** Construsteur surcharge avec un seul parametre connect
     * @param connect un objet de type Connexion */
    public DetailBulletinDAO(Connexion connect) {
        
        //Appel du constructeur par défaut de la classe mère
        super(connect);
    }

    
    /** modifier : methode permettant de modifier un attribut d un objet de la table
     * @param obj un objet de typ Anneescolaire
     * @param champ un objet de type String 
     * @param element un objet de type element
     * @return vrai si la modification a eu lieu et non sinon */
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
     * @param obj un objet de type DetailBulletin */
    @Override
    public void supprimer(DetailBulletin obj) {
        
        //Déclaration d'un String
        String rqt;
        
        //Récupération de l'ordre de la requete
        rqt = "delete from detail_bulletin where Id = " + obj.getID() + ";";
        
        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
        } catch (SQLException ex) {
            Logger.getLogger(DetailBulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /** ajouter : methode permettant d ajouter un nouvel objet dans la table
     * @param obj de type DetailBulletin
     * @return l ID de l objet ajouter dans la bdd */
    @Override
    public int ajouter(DetailBulletin obj) {
        
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
            ResultSet rset1 = connect.getConnexion().createStatement().executeQuery("select * from enseignement where id_personne = " + obj.getEnseignant().getID());

                //Récupération de l'ordre de la requete
                if(rset1.first()){

                //Récupération de l'ordre de la requete
                String rqt = "insert into detail_bulletin (" + champs + ") values (" + obj.getBulletin().getID() + ", " + rset1.getInt("Id") + ", \'" + obj.getAppreciation() + "\');";

                //Ajout de l'élément dans la table
                connect.getStatement().executeUpdate(rqt);

            }
                
            //Recherche l'id de l'élément ajouter
            ResultSet rset2 = connect.getConnexion().createStatement().executeQuery("select LAST_INSERT_ID() as last_id from detail_bulletin"); 
            if(rset2.next())
                return rset2.getInt("last_id");
            
            return 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(DetailBulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return 0;
        }  
    }

    
    /** trouver_et_charge : methode permettant de trouver et charger dans les donnees un objet de la table via son id
     * @param id l id de l objet qu il faut trouver dans la bdd
     * @return l objet DetailBulletin trouve */
    @Override
    public DetailBulletin trouver_et_charge(int id) {
        
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
                detail_bulletin.addEvaluations(evaluation_dao.trouver_et_charge(rset.getInt("Id")));  

                //Tant qu'il y a un résultat, on ajoute l'evaluation dans la liste de evaluations de notre objet DetailBulletin
                while(rset.next()) 
                    detail_bulletin.addEvaluations(evaluation_dao.trouver_et_charge(rset.getInt("Id")));          
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DetailBulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {

            //Recherche dans evaluation les Id_db correspondant à notre objet DetailBulletin
            ResultSet rset1 = connect.getConnexion().createStatement().executeQuery("select * from enseignement where Id = (select Id_enseignement from detail_bulletin where id = " + id +")");
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset1.first()){
                
                //Création d'un nouvel DetailDAO
                PersonneDAO enseignant_dao = new PersonneDAO(connect);
                
                //Ajout d'un bulletin
                detail_bulletin.setEnseignant((Enseignant) enseignant_dao.trouver_et_charge(rset1.getInt("Id_personne")));
                
                //Création d'un nouvel DisciplineDAO
                DisciplineDAO discipline_dao = new DisciplineDAO(connect);
                
                //Ajout d'un bulletin
                detail_bulletin.setDiscipline(discipline_dao.trouver_et_charge(rset1.getInt("Id_discipline"))); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DetailBulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        //Retourne l'objet trouvé
        return detail_bulletin;
    
    }
    
    
    /** trouver : methode permettant de trouver dans les donnees un objet de la table via son id
     * @param id l id de l objet qu il faut trouver dans la bdd
     * @return l objet DetailBulletin trouve */
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
        
        //Retourne l'objet trouvé
        return detail_bulletin;
    
    }
    
}
