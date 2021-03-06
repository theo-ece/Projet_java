/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connexion.Connexion;
import Model.Bulletin;
import Model.Etudiant;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/** BulletinDAO : classe qui recupere les donnees de la table bulletin de la BDD
 *
 * @author Flora
 */
public class BulletinDAO extends DAO<Bulletin> {
    
    
    /** Construsteur surcharge avec un seul parametre connect
     * @param connect un objet de type Connexion */
    public BulletinDAO(Connexion connect) {
        
        //Appel du constructeur par défaut de la classe mère
        super(connect);
    }

    /** modifier : methode permettant de modifier un attribut d un objet de la table
     * @param obj un objet de typ Anneescolaire
     * @param champ un objet de type String 
     * @param element un objet de type element
     * @return vrai si la modification a eu lieu et non sinon */
    @Override
    public boolean modifier(Bulletin obj, String champ, String element) {

        //Déclaration d'un String
        String rqt;
        
        //Ajout de guillement si element est un Varchar dans la BDD
        if("Appreciation".equals(champ)){
            element = "\'" + element + "\'";
        }

        //Récupération de l'ordre de la requete
        rqt = "update bulletin set " + champ + " = " + element + " where Id = " + obj.getID() + ";";

        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(BulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }
 
    }

    
    /** supprimer : methode permettant de supprimer un objet de la table
     * @param obj un objet de type Bulletin */
    @Override
    public void supprimer(Bulletin obj) {
        
        //Déclaration d'un String
        String rqt;
        
        //Récupération de l'ordre de la requete
        rqt = "delete from bulletin where Id = " + obj.getID() + ";";
        
        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
        } catch (SQLException ex) {
            Logger.getLogger(BulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /** ajouter : methode permettant d ajouter un nouvel objet dans la table
     * @param obj de type Bulletin
     * @return l ID de l objet ajouter dans la bdd */
    @Override
    public int ajouter(Bulletin obj) {
        
        //Création d'un objet ResultSet
        ResultSet rset;
        
        try {
            //Récupération de l'ordre de la requete
            rset = connect.getStatement().executeQuery("select * from bulletin");
            
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
            //ResultSet rset1 = connect.getConnexion().createStatement().executeQuery("select * from inscription where id_personne = " + obj.getEtudiant().getID());

            //Récupération de l'ordre de la requete
            //if(rset1.first()){
                String rqt = "insert into bulletin (" + champs + ") values (" + obj.getTrimestre().getID() + ", " + obj.getInscription().getID() + ", \'" + obj.getAppreciation() + "\');";

                //Ajout de l'élément dans la table
                connect.getStatement().executeUpdate(rqt);
            
            //}
            
            //Recherche l'id de l'élément ajouter
            ResultSet rset2 = connect.getConnexion().createStatement().executeQuery("select LAST_INSERT_ID() as last_id from bulletin"); 
            if(rset2.next())
                return rset2.getInt("last_id");
            
            return 0;
 
        } catch (SQLException ex) {
            Logger.getLogger(BulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return 0;
        }
    }

    
    /** trouver_et_charge : methode permettant de trouver et charger dans les donnees un objet de la table via son id
     * @param id l id de l objet qu il faut trouver dans la bdd
     * @return l objet Bulletin trouve */
    @Override
    public Bulletin trouver_et_charge(int id) {
        
        //Création d'un objet Bulletin
        Bulletin bulletin = new Bulletin();
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset = connect.getStatement().executeQuery("select * from bulletin where id = " + id);
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création du nouvel objet Bulletin
                bulletin = new Bulletin(id, rset.getString("Appreciation"));
                
                //Création d'un objet InscriptionDAO
                InscriptionDAO inscription_dao = new InscriptionDAO(connect);
                
                //Ajout de l'inscription
                bulletin.setInscription(inscription_dao.trouver_et_charge(rset.getInt("Id_inscription")));
                
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {

            //Recherche dans trimestre les Id_bulletin correspondant à notre objet Bulletin
            ResultSet rset1 = connect.getConnexion().createStatement().executeQuery("select * from detail_bulletin where Id_bulletin = " + id);
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset1.first()){
                
                //Création d'un objet DatailBulletinDAO
                DetailBulletinDAO detailbulletin_dao = new DetailBulletinDAO(connect);
                
                //Ajout d'un detail bulletin
                bulletin.addDetailsB(detailbulletin_dao.trouver_et_charge(rset1.getInt("Id")));

                //Tant qu'il y a un résultat, on ajoute le detail bulletin dans la liste de detail bulletins de notre objet Bulletin
                while(rset1.next()) 
                    bulletin.addDetailsB(detailbulletin_dao.trouver_et_charge(rset1.getInt("Id")));          
            }
                        
        } catch (SQLException ex) {
            Logger.getLogger(BulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {

            //Recherche dans trimestre les Id_bulletin correspondant à notre objet Bulletin
            ResultSet rset2 = connect.getConnexion().createStatement().executeQuery("select * from inscription where Id = (select Id_inscription from bulletin where Id = " + id + ")");
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset2.first()){
                
                //Création d'un objet PersonneDAO
                PersonneDAO etudiant_dao = new PersonneDAO(connect);
                
                //Ajout d'un detail bulletin
                bulletin.setEtudiant((Etudiant) etudiant_dao.trouver_et_charge(rset2.getInt("Id_personne")));
   
            }
                        
        } catch (SQLException ex) {
            Logger.getLogger(BulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        //Retourne l'objet trouvé
        return bulletin;
    
    }
    
    
    /** trouver : methode permettant de trouver dans les donnees un objet de la table via son id
     * @param id l id de l objet qu il faut trouver dans la bdd
     * @return l objet Bulletin trouve */
    @Override
    public Bulletin trouver(int id) {
        
        //Création d'un objet Bulletin
        Bulletin bulletin = new Bulletin();
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset = connect.getStatement().executeQuery("select * from bulletin where id = " + id);
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création du nouvel objet Bulletin
                bulletin = new Bulletin(id, rset.getString("Appreciation"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        //Retourne l'objet trouvé
        return bulletin;
    
    }
    
}
