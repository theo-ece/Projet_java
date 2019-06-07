/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.EvaluationDAO;

/**
 *
 * @author thebo
 */
public class Evaluation {
    
    /** Attributs prives de la classe ID, note */
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
     * @param ID
     * @param note
     * @param appreciation */
    public Evaluation(int ID, double note, String appreciation){
        this.ID = ID;
        this.note = note;
        this.appreciation = appreciation;
    }
    
    /** Constructeur surcharge avec deux parametres note et appreciation
     * @param note
     * @param appreciation */
    public Evaluation(double note, String appreciation){
        ID = 0;
        this.note = note;
        this.appreciation = appreciation;
    }
    
    /** getID : permettant d acceder a l attribut ID
     * @return  */
    public int getID(){
        return ID;
    }
    
    /** setID : permettant de modifier l attribut ID
     * @param ID */
    public void setID(int ID){
        this.ID = ID;
    }
    
    /** getNote : permettant d acceder a l attribut note
     * @return  */
    public double getNote(){
        return note;
    }
    
    /** setNote : permettant de modifier l attribut note
     * @param note */
    public void setNote(double note){
        this.note = note;
    }
    
    /** getAppreciation : permettant d acceder a l attribut appreciation
     * @return  */
    public String getAppreciation(){
        return appreciation;
    }
    
    /** setAppreciation : permettant de modifier l attribut appreciation
     * @param appreciation  */
    public void setAppreciation(String appreciation){
        this.appreciation = appreciation;
    }
    
    /** getDetailsB : permettant d acceder a l attribut detail_bulletin
     * @return  */
    public DetailBulletin getDetailsB(){
        return detail_bulletin;
    }
    
    /** setDetailsb : permettant de modifier l attribut detail_bulletin
     * @param detail_bulletin */
    public void setDetailsB(DetailBulletin detail_bulletin){
        this.detail_bulletin = detail_bulletin;
    }
    
    /** ajoutEvaluation : methode permettant d ajouter une evaluation
     * @param connect */
    public void ajoutEvaluation(Connexion connect){
        
        //Création d'un objet EvaluationDAO
        EvaluationDAO evaluation_dao = new EvaluationDAO(connect);
        
        //Appel de la fonction d'ajout
        evaluation_dao.ajouter(this);
    }
    
    
    // Si l'eval existe
    public Evaluation(int iD){
        ID = iD;
        /*
            sortir de la BDD la note, l'appreciation, ID -> l'etudiant et le correcteur        
        */
    }
    
    /*
    // Graphique
    public void showEval(){
        
    }
    
    // getteurs
    public double _get(){return note;}
    public String _get_appreciation(){return appreciation;}*/
}
