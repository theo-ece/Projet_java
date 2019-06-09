/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.PersonneDAO;
import java.util.HashMap;

/** Enseignant : classe qui herite de Personne
 *
 * @author thebo
 */
public class Enseignant extends Personne{
    
    
    /** Attributs prives de la classe discipline, classe et etudiants */
    private Discipline discipline;
    private Classe classe;
    private HashMap<Integer, Etudiant> etudiants;
    
    /** Constructeur par defaut */
    public Enseignant(){
        super(0,"","",0);
        discipline = null;
        classe = null;
    }
    
    /** Constructeur surcharge avec quatre parametres ID, Nom, Prenom et type
     * @param ID de type int
     * @param Nom de type String
     * @param Prenom de type String
     * @param type de type int */
    public Enseignant(int ID, String Nom, String Prenom, int type){
        super(ID, Nom, Prenom, type);
        discipline = null;
        classe = null;
    }
    
    /** Constructeur surcharge avec trois parametres Nom, Prenom et type
     * @param Nom de type String
     * @param Prenom de type String
     * @param type de type int */
    public Enseignant(String Nom, String Prenom, int type){
        super(0, Nom, Prenom, type);
        discipline = null;
        classe = null;
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
    
    /** getEtudiants : permettant d acceder a l attribut etudiants
     * @return l attribut etudiants */
    public HashMap<Integer, Etudiant> getEtudiants(){
        return etudiants;
    }
    
    /** addEtudiants : permettant d ajouter un etudiant a l attribut etudiants
     * @param e de type Etudiant */
    public void addEtudiants(Etudiant e){
        if(etudiants == null)
            etudiants = new HashMap<>();
        
        etudiants.put(e.getID(), e);
    }
    
    /** removeAllEtudiants : permettant de supprimer tous les etudiants de l attribut etudiants */
    public void removeAllEtudiants(){
        etudiants.clear();
    }
    
    /** ajoutEnseignant : methode permettant d ajouter un enseignant
     * @param connect de type Connexion */
    public void ajoutEnseignant(Connexion connect){
        
        //Création d'un objet PersonneDAO
        PersonneDAO enseignant_dao = new PersonneDAO(connect);
        
        //Appel de la fonction d'ajout
        enseignant_dao.ajouter(this);
    }
    
}
