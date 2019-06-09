/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.EnseignementDAO;

/** Enseignement : classe contenant les donnees liees a la table enseignement de la bdd
 *
 * @author Flora
 */
public class Enseignement {
   
    /** Attribut prive de la classe : ID */
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
     * @param ID de type int */
    public Enseignement(int ID){
        this.ID = ID;
    }
    
    /** Constructeur surcharge avec trois parametres classe, discipline et enseignant
     * @param classe de type Classe
     * @param discipline de type Discipline
     * @param enseignant de type Enseignant */
    public Enseignement(Classe classe, Discipline discipline, Enseignant enseignant){
        this.classe = classe;
        this.discipline = discipline;
        this.enseignant = enseignant;
    }
    
    /** getID : permet d acceder a l attribut ID
     * @return l attribut ID */
    public int getID(){
        return ID;
    }
    
    /** setID : permettant de modifier l attribut ID
     * @param id de type int */
    public void setID(int id){
        ID = id;
    }

    /** getClasse : permettant d acceder a l attribut classe
     * @return l attribut classe */
    public Classe getClasse(){
        return classe;
    }
    
    /** setClasse : permettant de modifier l attribut classe
     * @param classe de type Classe */
    public void setClasse(Classe classe){
        this.classe = classe;
    }
    
    /** getEnseignant : permettant d acceder a l attribut enseignant
     * @return l attribut enseignant */
    public Enseignant getEnseignant(){
        return enseignant;
    }
    
    /** setEnseignant : permettant de modifier l attribut enseignant
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
