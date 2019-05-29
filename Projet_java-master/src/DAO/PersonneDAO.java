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
public class PersonneDAO extends DAO<Personne> {
    
    /** Construsteur surcharge avec un seul parametre connect
     * @param connect */
    public PersonneDAO(Connexion connect) {
        
        //Appel du constructeur par défaut de la classe mère
        super(connect);
    }

    
    /** creer : methode permettant de modifier un attribut d un objet de la table
     * @param obj
     * @param champ
     * @param element
     * @return  */
    @Override
    public boolean modifier(Personne obj, String champ, String element) {

        //Déclaration d'un String
        String rqt;
        
        //Ajout de guillement si element est un Varchar dans la BDD
        if("Nom".equals(champ) || "Prenom".equals(champ)){
            element = "\'" + element + "\'";
        }

        //Récupération de l'ordre de la requete
        rqt = "update personne set " + champ + " = " + element + " where Id = " + obj.getID() + ";";

        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }
 
    }

    
    /** supprimer : methode permettant de supprimer un objet de la table
     * @param obj
     * @return  */
    @Override
    public boolean supprimer(Personne obj) {
        
        //Déclaration d'un String
        String rqt;
        
        //Récupération de l'ordre de la requete
        rqt = "delete from personne where Id = " + obj.getID() + ";";
        
        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }
    }

    
    /** ajouter : methode permettant d ajouter un nouvel objet dans la table
     * @return  */
    @Override
    public boolean ajouter(Personne obj) {
        
        //Création d'un objet ResultSet
        ResultSet rset;
        
        try {
            //Récupération de l'ordre de la requete
            rset = connect.getStatement().executeQuery("select * from personne");
            
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
            String rqt = "insert into personne (" + champs + ") values (\'" + obj.getNom() + "\', \'" + obj.getPrenom() + "\', " + obj.getType() + ");";
            
            //Ajout de l'élément dans la table
            connect.getStatement().executeUpdate(rqt);
            
            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }  
    }

    
    /** trouver : methode permettant de trouver un objet de la table via son id
     * @return  */
    @Override
    public Personne trouver(int id) {
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset = connect.getStatement().executeQuery("select * from personne where id = " + id);    
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Si c'est un enseignant
                if(rset.getInt("Type") == 1){
                    
                    //Création d'un objet Enseignant
                    Enseignant enseignant = new Enseignant(id, rset.getString("Nom"), rset.getString("Prenom"), rset.getInt("Type"));
                    
                    try {

                        //Recherche dans enseignement l' Id_enseignant correspondant à notre objet Enseignant
                        ResultSet rset2 = connect.getConnexion().createStatement().executeQuery("select * from enseignement where Id_enseignant = " + id);

                        //Si on a un résultat, on se positionne sur cette ligne
                        if (rset2.first()){

                            //Création d'un objet DisciplineDAO
                            DisciplineDAO discipline_dao = new DisciplineDAO(connect);

                            //Modification de la disiciplne
                            enseignant.setDiscipline(discipline_dao.trouver(rset.getInt("Id_discipline")));            
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    try {

                        //Recherche dans enseignement l' Id_enseignant correspondant à notre objet Enseignant
                        ResultSet rset2 = connect.getConnexion().createStatement().executeQuery("select * from enseignement where Id_enseignant = " + id);

                        //Si on a un résultat, on se positionne sur cette ligne
                        if (rset2.first()){

                            //Création d'un objet ClasseDAO
                            ClasseDAO classe_dao = new ClasseDAO(connect);

                            //Modification de la Classe
                            enseignant.setClasse(classe_dao.trouver(rset.getInt("Id_classe")));            
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    //Retourne l'objet trouvé
                    return enseignant;
                
                //Sinon si c'est un etudiant
                } else {
                     
                    //Création d'un objet Etudiant
                    Etudiant etudiant = new Etudiant(id, rset.getString("Nom"), rset.getString("Prenom"), rset.getInt("Type"));
                                        
                    try {

                        //Recherche dans classe les Id_classe correspondant à notre objet Etudiant
                        ResultSet rset2 = connect.getConnexion().createStatement().executeQuery("select * from classe where Id = (select Id_classe from etudiant where Id = " + id + ")");

                        //Si on a un résultat, on se positionne sur cette ligne
                        if (rset.first()){

                            //Création d'un objet ClasseDAO
                            ClasseDAO classe_DAO = new ClasseDAO(connect);

                            //Modification de la Classe
                            etudiant.setClasse(classe_DAO.trouver(rset.getInt("Id")));         
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    try {

                        //Recherche dans classe les Id_niveau correspondant à notre objet Etudiant
                        ResultSet rset2 = connect.getConnexion().createStatement().executeQuery("select * from niveau where Id = (select Id_niveau from etudiant where Id =" + id + ")");

                        //Si on a un résultat, on se positionne sur cette ligne
                        if (rset.first()){

                            //Création d'un objet NiveauDAO
                            NiveauDAO niveau_DAO = new NiveauDAO(connect);

                            //Modification du Niveau
                            etudiant.setNiveau(niveau_DAO.trouver(rset.getInt("Id")));         
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    //Retourne l'objet trouvé
                    return etudiant;
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
