/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.DetailBulletinDAO;
import java.util.ArrayList;

/** DetailBulletin : classe contenant les donnees liees a la table detail_bulletin de la bdd
 *
 * @author thebo
 */
public class DetailBulletin {
    
    /** Attribus prives de la classe ID, appreciation, evaluations, discipline et enseignant */
    private int ID;
    private String appreciation;
    private ArrayList<Evaluation> evaluations;
    private Discipline discipline;
    private Enseignant enseignant;

    
    //Attributs privés pour l'ajout
    private Bulletin bulletin;
    
    
    /** Constructeur par defaut */
    public DetailBulletin(){
        ID = 0;
        appreciation = "";
        evaluations = null;
        bulletin= null;
        enseignant = null;
        discipline = null;
    }
    
    /** Constructeur surcharge avec deux parametres ID et appreciation
     * @param ID de type int
     * @param appreciation de type String */
    public DetailBulletin(int ID, String appreciation){
        this.ID = ID;
        this.appreciation = appreciation;
        evaluations = null;
        bulletin= null;
        enseignant = null;
        discipline = null;
    }
    
    /** Constructeur surcharge avec un parametre appreciation
     * @param appreciation de type String */
    public DetailBulletin(String appreciation){
        ID = 0;
        this.appreciation = appreciation;
        evaluations = null;
        bulletin= null;
        enseignant = null;
        discipline = null;
    }
    
    /** getID : permet d acceder a l attribut ID
     * @return l attribut ID */
    public int getID(){
        return ID;
    }
    
    /** setID : permet de modifier l attribut ID
     * @param ID de type int */
    public void setID(int ID){
        this.ID = ID;
    }
    
    /** getAppreciation : permet d acceder a l attribut appreciation
     * @return l attribut appreciation */
    public String getAppreciation(){
        return appreciation;
    }
    
    /** getEvaluations : permet d acceder a l attribut evaluations
     * @return l attribut evaluations */
    public ArrayList<Evaluation> getEvaluations(){
        return evaluations;
    }
    
    /** addEvaluations : permet d ajouter a l attribut evaluations une evaluation
     * @param e de type Evalaution */
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
     * @return l attribut bulletin */
    public Bulletin getBulletin(){
        return bulletin;
    }
    
    /** getEnseignant : permet d acceder a l attribut enseignant
     * @return l attribut enseignant */
    public Enseignant getEnseignant(){
        return enseignant;
    }
    
    /** setBulletin : permet de modifier l attribut bulletin
     * @param bulletin de type Bulletin */
    public void setBulletin(Bulletin bulletin){
        this.bulletin = bulletin;
    }
    
    /** setEnseignant : permet de modifier l attribut enseignant
     * @param enseignant de type Enseignant */
    public void setEnseignant(Enseignant enseignant){
        this.enseignant = enseignant;
    }
    
    /** getDiscipline : permettant d acceder a l attribut discipline
     * @return l attribut discipline */
    public Discipline getDiscipline(){
        return discipline;
    }
    
    /** setDiscipline : permettant de modifier l attribut discipline
     * @param discipline de type Discipline */
    public void setDiscipline(Discipline discipline){
        this.discipline = discipline;
    }
    
    /** ajoutDetailsB : methode permettant d ajouter un DetailBulletin
     * @param connect de type Connexion*/
    public void ajoutDetailsB(Connexion connect){
        
        //Création d'un objet DetailBulletinDAO
        DetailBulletinDAO detailbulletin_dao = new DetailBulletinDAO(connect);
        
        //Appel de la fonction d'ajout
        detailbulletin_dao.ajouter(this);
    }
}
