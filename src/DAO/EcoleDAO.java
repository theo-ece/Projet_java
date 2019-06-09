/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connexion.Connexion;
import Model.*;
import java.sql.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/** EcoleDAO : classe qui recupere les donnees de la table ecole de la BDD
 *
 * @author Flora
 */
public class EcoleDAO extends DAO<Ecole> {
    
    /** Construsteur surcharge avec un seul parametre connect
     * @param connect un objet de type Connexion */
    public EcoleDAO(Connexion connect) {
        
        //Appel du constructeur par défaut de la classe mère
        super(connect);
    }

    
    /** modifier : methode permettant de modifier un attribut d un objet de la table
     * @param obj un objet de typ Anneescolaire
     * @param champ un objet de type String 
     * @param element un objet de type element
     * @return vrai si la modification a eu lieu et non sinon */
    @Override
    public boolean modifier(Ecole obj, String champ, String element) {

        //Déclaration d'un String
        String rqt;
        
        //Ajout de guillement si element est un Varchar dans la BDD
        if("Nom".equals(champ) || "Region".equals(champ)){
            element = "\'" + element + "\'";
        }

        //Récupération de l'ordre de la requete
        rqt = "update ecole set " + champ + " = " + element + " where Id = " + obj.getID() + ";";

        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(EcoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }
 
    }

    
    /** supprimer : methode permettant de supprimer un objet de la table
     * @param obj de type Ecole */
    @Override
    public void supprimer(Ecole obj) {
        
        //Déclaration d'un String
        String rqt;
        
        //Récupération de l'ordre de la requete
        rqt = "delete from ecole where Id = " + obj.getID() + ";";
        
        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
        } catch (SQLException ex) {
            Logger.getLogger(EcoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /** ajouter : methode permettant d ajouter un nouvel objet dans la table
     * @param obj de type Ecole
     * @return l ID de l objet ajouter dans la bdd */
    @Override
    public int ajouter(Ecole obj) {
        
        //Création d'un objet ResultSet
        ResultSet rset;
        
        try {
            //Récupération de l'ordre de la requete
            rset = connect.getStatement().executeQuery("select * from ecole");
            
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
            String rqt = "insert into ecole (" + champs + ") values (\'" + obj.getNom()+ "\', \'" + obj.getRegion() + "\');";
            
            //Ajout de l'élément dans la table
            connect.getStatement().executeUpdate(rqt);
            
            //Recherche l'id de l'élément ajouter
            ResultSet rset1 = connect.getConnexion().createStatement().executeQuery("select LAST_INSERT_ID() as last_id from ecole"); 
            if(rset1.next())
                return rset1.getInt("last_id");
            
            return 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(EcoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return 0;
        }  
    }

    
    /** trouver_et_charge : methode permettant de trouver et charger dans les donnees un objet de la table via son id
     * @param id l id de l objet qu il faut trouver dans la bdd
     * @return l objet Ecole trouve */
    @Override
    public Ecole trouver_et_charge(int id) {
        
        //Création d'un objet Ecole
        Ecole ecole = new Ecole();
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset = connect.getStatement().executeQuery("select * from ecole where id = " + id); 
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création du nouvel objet Ecole
                ecole = new Ecole(id, rset.getString("Nom"), rset.getString("Region"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EcoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset2 = connect.getConnexion().createStatement().executeQuery("select distinct Id_niveau from classe where Id_ecole =" + id);   

            //Si on a un résultat, on se positionne sur cette ligne
            if (rset2.first()){

                //Création d'un objet Niveau
                NiveauDAO niveau_dao = new NiveauDAO(connect);

                //Ajout des niveaux dans l'Ecole
                ecole.addNiveaux(niveau_dao.trouver_et_charge(rset2.getInt("Id_niveau")));  

                //Tant qu'il y a un résultat, on ajoute le niveau dans la liste de niveaux de notre objet Ecole
                while(rset2.next()) 
                    ecole.addNiveaux(niveau_dao.trouver_et_charge(rset2.getInt("Id_niveau")));     
            }

        } catch (SQLException ex) {
            Logger.getLogger(EcoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        try {
            
            //Récupération de l'ordre de la requete
            ResultSet rset1 = connect.getConnexion().createStatement().executeQuery("select * from classe where Id_ecole  = " + id);
            
            //Si on a un résultat, on se positionne sur cette ligne
            while(rset1.next()){
                    
                //Récupération de l'ordre de la requete
                ResultSet rset2 = connect.getConnexion().createStatement().executeQuery("select * from enseignement where Id_classe  = " + rset1.getInt("Id"));   

                //Si on a un résultat, on se positionne sur cette ligne
                if (rset2.first()){

                    //Création d'un objet Personne
                    PersonneDAO enseignant_dao = new PersonneDAO(connect);

                    //Ajout des enseignants dans l'ecole
                    ecole.addEnseignants((Enseignant) enseignant_dao.trouver_et_charge(rset2.getInt("Id_personne")));

                    //Tant qu'il y a un résultat, on ajoute l'enseignant dans la liste des enseignants de notre objet Ecole
                    while(rset2.next()) 
                        ecole.addEnseignants((Enseignant) enseignant_dao.trouver_et_charge(rset2.getInt("Id_personne")));     
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(EcoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            //Récupération de l'ordre de la requete
            ResultSet rset1 = connect.getConnexion().createStatement().executeQuery("select * from classe where Id_ecole  = " + id);
            
            //Si on a un résultat, on se positionne sur cette ligne
            while(rset1.next()){

                //Récupération de l'ordre de la requete
                ResultSet rset2 = connect.getConnexion().createStatement().executeQuery("select * from inscription where Id_classe = " + rset1.getInt("Id"));   

                //Si on a un résultat, on se positionne sur cette ligne
                if (rset2.first()){

                    //Création d'un objet Personne
                    PersonneDAO etudiant_dao = new PersonneDAO(connect);

                    //Ajout des etudiants dans l'ecole
                    ecole.addEtudiants((Etudiant) etudiant_dao.trouver_et_charge(rset2.getInt("Id_personne")));

                    //Tant qu'il y a un résultat, on ajoute l'etudiant dans la liste des etudiants de notre objet Ecole
                    while(rset2.next()) 
                        ecole.addEtudiants((Etudiant) etudiant_dao.trouver_et_charge(rset2.getInt("Id_personne")));     
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(EcoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
        //Retourne l'objet trouvé
        return ecole;
    
    }
    
    
    /** trouver : methode permettant de trouver dans les donnees un objet de la table via son id
     * @param id l id de l objet qu il faut trouver dans la bdd
     * @return l objet Ecole trouve */
    @Override
    public Ecole trouver(int id) {
        
        //Création d'un objet Ecole
        Ecole ecole = new Ecole();
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset = connect.getStatement().executeQuery("select * from ecole where id = " + id); 
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création du nouvel objet Ecole
                ecole = new Ecole(id, rset.getString("Nom"), rset.getString("Region"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EcoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Retourne l'objet trouvé
        return ecole;
    
    }
    
    
    public HashMap<Integer,Etudiant> charger_etudiants(Ecole ecole){
        
        //Création d'un objet HashMap<Integer,Etudiant>
        HashMap<Integer,Etudiant> etudiants = new HashMap<>();
        
        try {
            
            //Récupération de l'ordre de la requete
            ResultSet rset1 = connect.getConnexion().createStatement().executeQuery("select * from classe where Id_ecole  = " + ecole.getID());
            
            //Si on a un résultat, on se positionne sur cette ligne
            while(rset1.next()){

                //Récupération de l'ordre de la requete
                ResultSet rset2 = connect.getConnexion().createStatement().executeQuery("select * from inscription where Id_classe = " + rset1.getInt("Id"));   

                //Si on a un résultat, on se positionne sur cette ligne
                if (rset2.first()){

                    //Création d'un objet Personne
                    PersonneDAO etudiant_dao = new PersonneDAO(connect);

                    //Ajout des etudiants dans l'ecole
                    etudiants.put(rset2.getInt("Id_personne"),(Etudiant) etudiant_dao.trouver_et_charge(rset2.getInt("Id_personne")));

                    //Tant qu'il y a un résultat, on ajoute l'etudiant dans la liste des etudiants de notre objet Ecole
                    while(rset2.next()) 
                        etudiants.put(rset2.getInt("Id_personne"),(Etudiant) etudiant_dao.trouver_et_charge(rset2.getInt("Id_personne")));
                }
            }
            
            return etudiants;

        } catch (SQLException ex) {
            Logger.getLogger(EcoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
