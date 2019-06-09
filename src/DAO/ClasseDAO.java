/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connexion.Connexion;
import Model.Classe;
import Model.Etudiant;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/** ClasseDAO : classe qui recupere les donnees de la table classe de la BDD
 *
 * @author Flora
 */
public class ClasseDAO extends DAO<Classe> {
    
    /** Construsteur surcharge avec un seul parametre connect
     * @param connect un objet de type Connexion */
    public ClasseDAO(Connexion connect) {
        
        //Appel du constructeur par défaut de la classe mère
        super(connect);
    }

    
    /** modifier : methode permettant de modifier un attribut d un objet de la table
     * @param obj un objet de typ Anneescolaire
     * @param champ un objet de type String 
     * @param element un objet de type element
     * @return vrai si la modification a eu lieu et non sinon */
    @Override
    public boolean modifier(Classe obj, String champ, String element) {

        //Déclaration d'un String
        String rqt;
        
        //Ajout de guillement si element est un Varchar dans la BDD
        if("Nom".equals(champ)){
            element = "\'" + element + "\'";
        }

        //Récupération de l'ordre de la requete
        rqt = "update classe set " + champ + " = " + element + " where Id = " + obj.getID() + ";";

        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }
 
    }

    
    /** supprimer : methode permettant de supprimer un objet de la table
     * @param obj un objet de type Classe */
    @Override
    public void supprimer(Classe obj) {
        
        //Déclaration d'un String
        String rqt;
        
        //Récupération de l'ordre de la requete
        rqt = "delete from classe where Id = " + obj.getID() + ";";
        
        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /** ajouter : methode permettant d ajouter un nouvel objet dans la table
     * @param obj de type Classe
     * @return l ID de l objet ajouter dans la bdd */
    @Override
    public int ajouter(Classe obj) {
        
        //Création d'un objet ResultSet
        ResultSet rset;
        
        try {
            //Récupération de l'ordre de la requete
            rset = connect.getStatement().executeQuery("select * from classe");
            
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
            String rqt = "insert into classe (" + champs + ") values (\'" + obj.getNom() + "\'," + obj.getEcole().getID() + ", " + obj.getNiveau().getID() + ", " + obj.getAnnee().getID() + ");";
            
            //Ajout de l'élément dans la table
            connect.getStatement().executeUpdate(rqt);
            
            //Recherche l'id de l'élément ajouter
            ResultSet rset1 = connect.getConnexion().createStatement().executeQuery("select LAST_INSERT_ID() as last_id from classe"); 
            if(rset1.next())
                return rset1.getInt("last_id");
            
            return 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return 0;
        }  
    }

    
    /** trouver_et_charge : methode permettant de trouver et charger dans les donnees un objet de la table via son id
     * @param id l id de l objet qu il faut trouver dans la bdd
     * @return l objet Classe trouve */
    @Override
    public Classe trouver_et_charge(int id) {
        
        //Création d'un objet Classe
        Classe classe = new Classe();
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset = connect.getStatement().executeQuery("select * from classe where id = " + id);  
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création du nouvel objet Classe
                classe = new Classe(id, rset.getString("Nom"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {

            //Recherche dans etudiant les Id_classe correspondant à notre objet Classe
            ResultSet rset2 = connect.getConnexion().createStatement().executeQuery("select * from inscription where Id_classe = " + id);

            //Si on a un résultat, on se positionne sur cette ligne
            if (rset2.first()){

                //Création d'un objet PersonneDAO
                PersonneDAO etudiant_DAO = new PersonneDAO(connect);

                //Ajout d'un etudiant
                classe.addEtudiants((Etudiant) etudiant_DAO.trouver_et_charge(rset2.getInt("Id_personne")));

                //Tant qu'il y a un résultat, on ajoute un etudiant dans la liste des etudiants de notre objet Classe
                while(rset2.next()) 
                    classe.addEtudiants((Etudiant) etudiant_DAO.trouver_et_charge(rset2.getInt("Id_personne")));         
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Retourne l'objet trouvé
        return classe;
    
    }
    
    
    /** trouver : methode permettant de trouver dans les donnees un objet de la table via son id
     * @param id l id de l objet qu il faut trouver dans la bdd
     * @return l objet Classe trouve */
    @Override
    public Classe trouver(int id) {
        
        //Création d'un objet Classe
        Classe classe = new Classe();
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset = connect.getStatement().executeQuery("select * from classe where id = " + id);  
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création du nouvel objet Classe
                classe = new Classe(id, rset.getString("Nom"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Retourne l'objet trouvé
        return classe;
    
    }
    
}
