/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.EvaluationDAO;

/** Evaluation : classe contenant les donnees liees a la table evaluation de la bdd
 *
 * @author thebo
 */
public class Evaluation {
    
    /** Attributs prives de la classe ID, note, appreciaiton */
    private int ID;
    private double note;
    private String appreciation;
    
    //Attribut util pour l'ajout
    private DetailBulletin detail_bulletin = null;
    
    /** Constructeur par defaut */
    public Evaluation(){
        ID = 0;
        note = 0;
        appreciation = "";
    }
    
    /** Constructeur surcharge avec trois parametres ID, note et appreciation
     * @param ID de type int
     * @param note de type double
     * @param appreciation de type String */
    public Evaluation(int ID, double note, String appreciation){
        this.ID = ID;
        this.note = note;
        this.appreciation = appreciation;
    }
    
    /** Constructeur surcharge avec deux parametres note et appreciation
     * @param note de type double
     * @param appreciation de type String */
    public Evaluation(double note, String appreciation){
        ID = 0;
        this.note = note;
        this.appreciation = appreciation;
    }
    
    /** getID : permettant d acceder a l attribut ID
     * @return l attribut ID */
    public int getID(){
        return ID;
    }
    
    /** setID : permettant de modifier l attribut ID
     * @param ID de type int */
    public void setID(int ID){
        this.ID = ID;
    }
    
    /** getNote : permettant d acceder a l attribut note
     * @return l attribut note */
    public double getNote(){
        return note;
    }
    
    /** setNote : permettant de modifier l attribut note
     * @param note de type double */
    public void setNote(double note){
        this.note = note;
    }
    
    /** getAppreciation : permettant d acceder a l attribut appreciation
     * @return l attribut appreciation */
    public String getAppreciation(){
        return appreciation;
    }
    
    /** setAppreciation : permettant de modifier l attribut appreciation
     * @param appreciation de type String */
    public void setAppreciation(String appreciation){
        this.appreciation = appreciation;
    }
    
    /** getDetailsB : permettant d acceder a l attribut detail_bulletin
     * @return l attribut detail_bulletin */
    public DetailBulletin getDetailsB(){
        return detail_bulletin;
    }
    
    /** setDetailsb : permettant de modifier l attribut detail_bulletin
     * @param detail_bulletin de type DetailBulletin */
    public void setDetailsB(DetailBulletin detail_bulletin){
        this.detail_bulletin = detail_bulletin;
    }
    
    /** ajoutEvaluation : methode permettant d ajouter une evaluation
     * @param connect de type Connexion */
    public void ajoutEvaluation(Connexion connect){
        
        //Création d'un objet EvaluationDAO
        EvaluationDAO evaluation_dao = new EvaluationDAO(connect);
        
        //Appel de la fonction d'ajout
        evaluation_dao.ajouter(this);
    }

}
