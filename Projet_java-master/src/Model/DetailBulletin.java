/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.DetailBulletinDAO;
import java.util.ArrayList;

/**
 *
 * @author thebo
 */
public class DetailBulletin {
    
    /** Attribus prives de la classe ID, appreciation et evaluations */
    private int ID;
    private String appreciation;
    private ArrayList<Evaluation> evaluations;
    
    //Attribut pour l'ajout 
    private Bulletin bulletin = null;
    private Enseignant enseignant = null;
    
    
    /** Constructeur par defaut */
    public DetailBulletin(){
        ID = 0;
        appreciation = "";
        evaluations = null;
    }
    
    /** Constructeur surcharge avec deux parametres ID et appreciation
     * @param ID
     * @param appreciation */
    public DetailBulletin(int ID, String appreciation){
        this.ID = ID;
        this.appreciation = appreciation;
        evaluations = null;
    }
    
    /** Constructeur surcharge avec un parametre appreciation
     * @param appreciation */
    public DetailBulletin(String appreciation){
        ID = 0;
        this.appreciation = appreciation;
        evaluations = null;
    }
    
    /** getID : permet d acceder a l attribut ID
     * @return  */
    public int getID(){
        return ID;
    }
    
    /** getAppreciation : permet d acceder a l attribut appreciation
     * @return  */
    public String getAppreciation(){
        return appreciation;
    }
    
    /** getEvaluations : permet d acceder a l attribut evaluations
     * @return  */
    public ArrayList<Evaluation> getEvaluations(){
        return evaluations;
    }
    
    /** addEvaluations : permet d ajouter a l attribut evaluations une evaluation
     * @param e */
    public void addEvaluations(Evaluation e){
        if(evaluations == null)
            evaluations = new ArrayList<>();

        evaluations.add(e);
    }
    
    /** removeAllEvaluations : permet de supprimer toutes les evaluations de evaluations  */
    public void removeAllEvaluations(){
        evaluations.clear();
    }
    
    /** getBulletin : permet d acceder a l attribut bulletin
     * @return  */
    public Bulletin getBulletin(){
        return bulletin;
    }
    
    /** getEnseignant : permet d acceder a l attribut enseignant
     * @return  */
    public Enseignant getEnseignant(){
        return enseignant;
    }
    
    /** setBulletin : permet de modifier l attribut bulletin
     * @param bulletin */
    public void setBulletin(Bulletin bulletin){
        this.bulletin = bulletin;
    }
    
    /** setEnseignant : permet de modifier l attribut enseignant
     * @param enseignant */
    public void setEnseignement(Enseignant enseignant){
        this.enseignant = enseignant;
    }
    
    /** ajoutDetailsB : methode permettant d ajouter un DetailBulletin
     * @param connect */
    public void ajoutDetailsB(Connexion connect){
        
        //Création d'un objet DetailBulletinDAO
        DetailBulletinDAO detailbulletin_dao = new DetailBulletinDAO(connect);
        
        //Appel de la fonction d'ajout
        detailbulletin_dao.ajouter(this);
    }
}
