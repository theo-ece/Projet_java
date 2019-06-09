/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connexion.Connexion;
import Model.Evaluation;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/** EvaluationDAO : classe qui recupere les donnees de la table evaluation de la BDD
 *
 * @author Flora
 */
public class EvaluationDAO extends DAO<Evaluation> {
    
    /** Construsteur surcharge avec un seul parametre connect
     * @param connect un objet de type Connexion */
    public EvaluationDAO(Connexion connect) {
        
        //Appel du constructeur par défaut de la classe mère
        super(connect);
    }

    
    /** modifier : methode permettant de modifier un attribut d un objet de la table
     * @param obj un objet de typ Anneescolaire
     * @param champ un objet de type String 
     * @param element un objet de type element
     * @return vrai si la modification a eu lieu et non sinon */
    @Override
    public boolean modifier(Evaluation obj, String champ, String element) {

        //Déclaration d'un String
        String rqt;
        
        //Ajout de guillement si element est un Varchar dans la BDD
        if("Appreciation".equals(champ)){
            element = "\'" + element + "\'";
        }

        //Récupération de l'ordre de la requete
        rqt = "update evaluation set " + champ + " = " + element + " where Id = " + obj.getID() + ";";

        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
            //Retourne vrai
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(EvaluationDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return false;
        }
 
    }

    
    /** supprimer : methode permettant de supprimer un objet de la table
     * @param obj un objet de type Evaluation */
    @Override
    public void supprimer(Evaluation obj) {
        
        //Déclaration d'un String
        String rqt;
        
        //Récupération de l'ordre de la requete
        rqt = "delete from evaluation where Id = " + obj.getID() + ";";
        
        try {
            //Suppression de l'élément de la table
            connect.getStatement().executeUpdate(rqt);
            
        } catch (SQLException ex) {
            Logger.getLogger(EvaluationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /** ajouter : methode permettant d ajouter un nouvel objet dans la table
     * @param obj de type Evaluation
     * @return l ID de l objet ajouter dans la bdd */
    @Override
    public int ajouter(Evaluation obj) {
        
        //Création d'un objet ResultSet
        ResultSet rset;
        
        try {
            //Récupération de l'ordre de la requete
            rset = connect.getStatement().executeQuery("select * from evaluation");
            
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
            String rqt = "insert into evaluation (" + champs + ") values (" + obj.getDetailsB().getID() + ", " + obj.getNote() + ", \'" + obj.getAppreciation() + "\');";
            
            //Ajout de l'élément dans la table
            connect.getStatement().executeUpdate(rqt);
            
            //Recherche l'id de l'élément ajouter
            ResultSet rset1 = connect.getConnexion().createStatement().executeQuery("select LAST_INSERT_ID() as last_id from evaluation"); 
            if(rset1.next())
                return rset1.getInt("last_id");
            
            return 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(EvaluationDAO.class.getName()).log(Level.SEVERE, null, ex);
            //Retourne faux
            return 0;
        }  
    }

    
    /** trouver_et_charge : methode permettant de trouver et charger dans les donnees un objet de la table via son id
     * @param id l id de l objet qu il faut trouver dans la bdd
     * @return l objet Evaluation trouve */
    @Override
    public Evaluation trouver_et_charge(int id) {
        
        //Création d'un objet Evaluation
        Evaluation evaluation = new Evaluation();
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset = connect.getStatement().executeQuery("select * from evaluation where id = " + id);  
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création du nouvel objet Evaluation
                evaluation = new Evaluation(id, rset.getDouble("Note"), rset.getString("Appreciation"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvaluationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        //Retourne l'objet trouvé
        return evaluation;
    
    }
    
    
    /** trouver : methode permettant de trouver dans les donnees un objet de la table via son id
     * @param id l id de l objet qu il faut trouver dans la bdd
     * @return l objet Evaluation trouve */
    @Override
    public Evaluation trouver(int id) {
        
        //Création d'un objet Evaluation
        Evaluation evaluation = new Evaluation();
        
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset = connect.getStatement().executeQuery("select * from evaluation where id = " + id);  
            
            //Si on a un résultat, on se positionne sur cette ligne
            if (rset.first()){
                
                //Création du nouvel objet Evaluation
                evaluation = new Evaluation(id, rset.getDouble("Note"), rset.getString("Appreciation"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvaluationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        //Retourne l'objet trouvé
        return evaluation;
    
    }
    public double[] loadData() {        
        //Création d'un objet Evaluation
        double[] DATA;
        ArrayList<Evaluation> eval = new ArrayList<>();       
        try {
            //Récupération de l'ordre de la requete
            ResultSet rset = connect.getStatement().executeQuery("select * from evaluation");
            while(rset.next()){         
                eval.add(new Evaluation(0, rset.getDouble("Note"), rset.getString("Appreciation")));
            }
                        
        } catch (SQLException ex) {
            Logger.getLogger(EvaluationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }    
        DATA = new double[eval.size()];
        for(int i=0;i<eval.size();i++){
            DATA[i]=eval.get(i).getNote();
        }
        //Retourne l'objet trouvé
        return DATA;    
    }
}
