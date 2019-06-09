/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.ClasseDAO;
import java.util.HashMap;

/** Classe : classe contenant les donnees liees a la table classe de la bdd
 *
 * @author thebo
 */
public class Classe {
    
    /** Attributs prives de la classe : Id, nom, etudiants */
    private int ID;
    private String nom;
    private HashMap<Integer, Etudiant> etudiants;
    
    //attribut utile pour l'ajout uniquement
    private Ecole ecole = null;
    private AnneeScolaire annee = null;
    private Niveau niveau = null;
    
    
    /** Constructeur par defaut */
    public Classe(){
        ID = 0;
        nom = "";
        etudiants = null;
    }
    
    /** Constructeur surchage avec deux parametres ID et nom
     * @param ID de type int
     * @param nom de type String */
    public Classe(int ID, String nom){
        this.ID = ID;
        this.nom = nom;
        etudiants = null;
    }
    
    /** Constructeur surchage avec un parametre nom
     * @param nom de type String */
    public Classe(String nom){
        ID = 0;
        this.nom = nom;
        etudiants = null;
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
    
    /** getNom : permet d acceder a l attribut nom
     * @return l attribut nom */
    public String getNom(){
        return nom;
    }
    
    /** setNom : permet de modifier l attribut nom
     * @param nom de type String */
    public void setNom(String nom){
        this.nom = nom;
    }
    
    
    /** getEtudiants : permet d acceder a l attribut etudiants
     * @return l attribut etudiants */
    public HashMap<Integer, Etudiant> getEtudiants(){
        return etudiants;
    }
    
    /** addEtudiants : methode qui permet d ajouter un etudiant dans etudiants
     * @param e de type Etudiant */
    public void addEtudiants(Etudiant e){
        if(etudiants == null)
            etudiants = new HashMap<>();
        
        etudiants.put(e.getID(), e);
    }
    
    /** removeAllEtudiants : methode de supprimer tous les etudiants */
    public void removeAllEtudiants(){
        etudiants.clear();
    }
    
    /** getNiveau : permet d acceder a l attribut niveau
     * @return l attribut niveau */
    public Niveau getNiveau(){
        return niveau;
    }
    
    /** getEcole : permet d acceder a l attribut ecole
     * @return l attribut ecole */
    public Ecole getEcole(){
        return ecole;
    }
    
    /** getAnnee : permet d acceder a l attribut annee
     * @return l attribut annee */
    public AnneeScolaire getAnnee(){
        return annee;
    }
    
    /** getNiveau : permet de modifier l attribut niveau
     * @param niveau de type Niveau */
    public void setNiveau(Niveau niveau){
        this.niveau = niveau;
    }
    
    /** getEcole : permet de modifier l attribut ecole
     * @param ecole de type Ecole */
    public void setEcole(Ecole ecole){
        this.ecole = ecole; 
    }
    
    /** getAnnee : permet de modifier l attribut annee
     * @param annee de type AnneeScolaire */
    public void setAnnee(AnneeScolaire annee){
        this.annee = annee;
    }
    
    /** ajoutClasse : methode permettant d ajouter une Classe
     * @param connect de type Connexion */
    public void ajoutClasse(Connexion connect){
        
        //Création d'un objet ClasseDAO
        ClasseDAO classe_dao = new ClasseDAO(connect);
        
        //Appel de la fonction d'ajout
        classe_dao.ajouter(this);
    }
}
