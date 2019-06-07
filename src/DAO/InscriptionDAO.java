/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connexion.Connexion;
import Model.Etudiant;
import Model.Inscription;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Flora
 */
public class InscriptionDAO extends DAO<Inscription> {
    
    
    /** Construsteur surcharge avec un seul parametre connect
     * @param connect */
    public InscriptionDAO(Connexion connect) {
        
        //Appel du constructeur par défaut de la classe mère
        super(connect);
    }

    
    /** creer : methode permettant de modifier un attribut d un objet de la table
     * @param obj
     * @param champ
     * @param element
     * @return  */
    @Override
    public boolean modifier(Inscription obj, String champ, String element) {

        //Déclaration d'un String
        String rqt;

        //Récupération de l'ordre de la requete
        rqt = "update inscription set " + champ + " = " + element + " where Id = " + obj.getID() + ";";

        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }
 
    }

    
    /** supprimer : methode permettant de supprimer un objet de la table
     * @param obj  */
    @Override
    public void supprimer(Inscription obj) {
        
        //Déclaration d'un String
        String rqt;
        
        //Récupération de l'ordre de la requete
        rqt = "delete from inscription where Id = " + obj.getID() + ";";
        
        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /** ajouter : methode permettant d ajouter un nouvel objet dans la table
     * @return  */
    @Override
    public int ajouter(Inscription obj) {
        
        //Création d'un objet ResultSet
        ResultSet rset;
        
        try {
            //Récupération de l'ordre de la requete
            rset = connect.getStatement().executeQuery("select * from inscription");
            
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
            String rqt = "insert into inscription (" + champs + ") values (" + obj.getClasse().getID() + ", " + obj.getEtudiant().getID() + ");";
            
            //Ajout de l'élément dans la table
            connect.getStatement().executeUpdate(rqt);
            
            //Recherche l'id de l'élément ajouter
            ResultSet rset1 = connect.getConnexion().createStatement().executeQuery("select LAST_INSERT_ID() as last_id from inscription"); 
            if(rset1.next())
                return rset1.getInt("last_id");
            
            return 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return 0;
        }  
    }

    
    /** trouver : methode permettant de trouver un objet de la table via son id
     * @return  */
    @Override
    public Inscription trouver(int id) {
        return null;
    }
    
    /** trouver_et_charge : methode permettant de trouver et de charge dans les donnees un objet de la table via son id
     * @return  */
    @Override
    public Inscription trouver_et_charge(int id) {
        
        //Création d'un objet Inscription
        Inscription inscription = new Inscription();
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset = connect.getStatement().executeQuery("select * from inscription where id = " + id);   
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création du nouvel objet AnneeScolaire
                inscription = new Inscription(id);
                
                //Recherche dans enseignement l' Id_personne correspondant à notre objet Enseignant
                ResultSet rset2 = connect.getConnexion().createStatement().executeQuery("select * from inscription where id = " + id);

                //Si on a un résultat, on se positionne sur cette ligne
                if (rset2.first()){

                    //Création d'un objet DisciplineDAO
                    PersonneDAO personne_dao = new PersonneDAO(connect);

                    //Modification de la discipline
                    inscription.setEtudiant((Etudiant) personne_dao.trouver_et_charge(rset2.getInt("Id_personne")));
                
                    //Création d'un objet DisciplineDAO
                    ClasseDAO classe_dao = new ClasseDAO(connect);

                    //Modification de la discipline
                    inscription.setClasse(classe_dao.trouver_et_charge(rset2.getInt("Id_classe")));
                }
            }
                  
        } catch (SQLException ex) {
            Logger.getLogger(AnneeScolaireDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Retourne l'objet trouvé
        return inscription;
    }
}
