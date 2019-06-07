/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.EnseignementDAO;

/**
 *
 * @author Flora
 */
public class Enseignement {
   
    /** Attribut prive de la classe : classe, discipline et enseignant */
    private int ID;
    
    //Attributs pour l ajout
    private Classe classe = null;
    private Discipline discipline = null;
    private Enseignant enseignant = null;
    
    
    /** Constructeur par defaut */
    public Enseignement(){
        ID = 0;
    }
    
    /** Constructeur surcharge avec un parametre ID
     * @param ID */
    public Enseignement(int ID){
        this.ID = ID;
    }
    
    /** Constructeur surcharge avec trois parametres classe, discipline et enseignant
     * @param classe
     * @param discipline
     * @param enseignant */
    public Enseignement(Classe classe, Discipline discipline, Enseignant enseignant){
        this.classe = classe;
        this.discipline = discipline;
        this.enseignant = enseignant;
    }
    
    /** getID : permet d acceder a l attribut ID
     * @return  */
    public int getID(){
        return ID;
    }
    
    /** setID : permettant de modifier l attribut ID
     * @param id */
    public void setID(int id){
        ID = id;
    }

    /** getClasse : permettant d acceder a l attribut classe
     * @return  */
    public Classe getClasse(){
        return classe;
    }
    
    /** setClasse : permettant de modifier l attribut classe
     * @param classe */
    public void setClasse(Classe classe){
        this.classe = classe;
    }
    
    /** getEnseignant : permettant d acceder a l attribut enseignant
     * @return  */
    public Enseignant getEnseignant(){
        return enseignant;
    }
    
    /** setEnseignant : permettant de modifier l attribut enseignant
     * @param enseignant */
    public void setEnseignant(Enseignant enseignant){
        this.enseignant = enseignant;
    }
    
    /** getDiscipline : permettant d acceder a l attribut discipline
     * @return  */
    public Discipline getDiscipline(){
        return discipline;
    }
    
    /** setDiscipline : permettant de modifier l attribut discipline
     * @param discipline */
    public void setDiscipline(Discipline discipline){
        this.discipline = discipline;
    }
    
    /* enseignement : methode permettant d enseigner une discipline a une classe */
    public int enseignement(Connexion connect){ 
        
        //Création d'un objet EnseignementDAO
        EnseignementDAO enseignement_dao = new EnseignementDAO(connect);
        return enseignement_dao.ajouter(this);
    }
    
    /* desenseignement : methode permettant de desenseigner une discipline a une classe */
    public void desenseignement(Connexion connect){ 
        
        //Création d'un objet EnseignementDAO
        EnseignementDAO enseignement_dao = new EnseignementDAO(connect);
        enseignement_dao.supprimer(this);
    }
}
