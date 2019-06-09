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

/** PersonneDAO : classe qui recupere les donnees de la table personne de la BDD
 *
 * @author Flora
 */
public class PersonneDAO extends DAO<Personne> {
    
    /** Construsteur surcharge avec un seul parametre connect
     * @param connect un objet de type Connexion */
    public PersonneDAO(Connexion connect) {
        
        //Appel du constructeur par défaut de la classe mère
        super(connect);
    }

    
    /** modifier : methode permettant de modifier un attribut d un objet de la table
     * @param obj un objet de typ Anneescolaire
     * @param champ un objet de type String 
     * @param element un objet de type element
     * @return vrai si la modification a eu lieu et non sinon */
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
     * @param obj un objet de type Personne */
    @Override
    public void supprimer(Personne obj) {
        
        //Déclaration d'un String
        String rqt;
        
        //Récupération de l'ordre de la requete
        rqt = "delete from personne where Id = " + obj.getID() + ";";
        
        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /** ajouter : methode permettant d ajouter un nouvel objet dans la table
     * @param obj de type Personne
     * @return l ID de l objet ajouter dans la bdd */
    @Override
    public int ajouter(Personne obj) {
        
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
            
            //Recherche l'id de l'élément ajouter
            ResultSet rset1 = connect.getConnexion().createStatement().executeQuery("select LAST_INSERT_ID() as last_id from personne"); 
            if(rset1.next())
                return rset1.getInt("last_id");
            
            return 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }  
    }

    
    /** trouver_et_charge : methode permettant de trouver et charger dans les donnees un objet de la table via son id
     * @param id l id de l objet qu il faut trouver dans la bdd
     * @return l objet Personne trouve */
    @Override
    public Personne trouver_et_charge(int id) {
        
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

                        //Recherche dans enseignement l' Id_personne correspondant à notre objet Enseignant
                        ResultSet rset2 = connect.getConnexion().createStatement().executeQuery("select * from enseignement where Id_personne = " + id);

                        //Si on a un résultat, on se positionne sur cette ligne
                        if (rset2.first()){

                            //Création d'un objet DisciplineDAO
                            DisciplineDAO discipline_dao = new DisciplineDAO(connect);

                            //Modification de la discipline
                            enseignant.setDiscipline(discipline_dao.trouver_et_charge(rset2.getInt("Id_discipline"))); 
                            
                            //Création d'un objet ClasseDAO
                            ClasseDAO classe_dao = new ClasseDAO(connect);

                            //Modification de la Classe
                            enseignant.setClasse(classe_dao.trouver_et_charge(rset2.getInt("Id_classe")));
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    try {

                        //Recherche dans etudiant les Id_classe correspondant à notre objet Etudiant
                        ResultSet rset2 = connect.getConnexion().createStatement().executeQuery("select * from inscription where Id_classe = (select Id_classe from enseignement where Id_personne =" + id + ")");

                        //Si on a un résultat, on se positionne sur cette ligne
                        if (rset2.first()){

                            //Création d'un objet PersonneDAO
                            PersonneDAO etudiant_dao = new PersonneDAO(connect);

                            //Ajout d'un étudiant
                            enseignant.addEtudiants((Etudiant) etudiant_dao.trouver_et_charge(rset2.getInt("Id_personne")));  

                            //Tant qu'il y a un résultat, on ajoute un l'étudiant dans la liste d'etudiants de notre objet Enseignant
                            while(rset2.next()) 
                                enseignant.addEtudiants((Etudiant) etudiant_dao.trouver_et_charge(rset2.getInt("Id_personne")));  
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

                        //Recherche dans enseignement les Id_classe correspondant à notre objet Etudiant
                        ResultSet rset2 = connect.getConnexion().createStatement().executeQuery("select * from enseignement where Id_classe = (select Id_classe from inscription where Id_personne = " + id + ")");

                        //Si on a un résultat, on se positionne sur cette ligne
                        if (rset2.first()){

                            //Création d'un objet DisciplineDAO
                            DisciplineDAO discipline_dao = new DisciplineDAO(connect);

                            //Ajout d'une discipline
                            etudiant.addDisciplines(discipline_dao.trouver_et_charge(rset2.getInt("Id_discipline")));  

                            //Tant qu'il y a un résultat, on ajoute la discipline dans la liste de disciplines de notre objet Etudiant
                            while(rset2.next()) 
                                etudiant.addDisciplines(discipline_dao.trouver_et_charge(rset2.getInt("Id_discipline"))); 
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
    
    
    /** trouver : methode permettant de trouver dans les donnees un objet de la table via son id
     * @param id l id de l objet qu il faut trouver dans la bdd
     * @return l objet Personne trouve */
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
                    
                    //Retourne l'objet trouvé
                    return enseignant;
                
                //Sinon si c'est un etudiant
                } else {
                     
                    //Création d'un objet Etudiant
                    Etudiant etudiant = new Etudiant(id, rset.getString("Nom"), rset.getString("Prenom"), rset.getInt("Type"));                       
                    
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
