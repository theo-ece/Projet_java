/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Connexion.Connexion;
import DAO.*;
import java.util.*;

/** Ecole : classe contenant les donnees liees a la table ecole de la bdd
 *
 * @author thebo
 */
public class Ecole {
    
    
    private final String path;
    
    /** Attributs prives de la classe ID, nom, region, etudiants, enseignants et niveaux */
    private int ID;
    private String nom;
    private String region;
    protected HashMap<Integer, Etudiant> etudiants;
    protected HashMap<Integer, Enseignant> enseignants;
    protected ArrayList<Niveau> niveaux;
    
    // Attribut connect
    Connexion connect;
    
    //Attribut Chargement
    Chargement charge = new Chargement(connect);
    
    /** Constructeur par defaut */
    public Ecole(){
        path = "";
        connect = null;
        ID = 0;
        nom = "";
        region = "";
    }
    
    /** Constructeur surchage avec trois parametres ID, nom et region
     * @param ID de type int
     * @param nom de type String
     * @param region de type String */
    public Ecole(int ID, String nom, String region){
        path = "";
        this.ID = ID;
        this.nom = nom;
        this.region = region;
        connect = null;
    }
    
    /** Constructeur surchage avec deux parametres nom et region
     * @param nom de type String
     * @param region de type String */
    public Ecole(String nom, String region){
        path = "";
        ID = 0;
        this.nom = nom;
        this.region = region;
        connect = null;
    }
    
    /** getID : permettant d acceder a l attribut ID
     * @return l attribut ID */
    public int getID(){
        return ID;
    }
    
    /** setID : permettant de modifier l attribut ID
     * @param id de type int */
    public void setID(int id){
        ID = id;
    }
    
    /** getNom : permettant d acceder a l attribut nom
     * @return l attribut nom */
    public String getNom(){
        return nom;
    }
    
    /** getRegion : permettant d acceder a l attribut region
     * @return l attribut region */
    public String getRegion(){
        return region;
    }
    
    /** getNiveaux : permettant d acceder a l attribut niveaux
     * @return l attribut niveaux */
    public ArrayList<Niveau> getNiveaux(){
        return niveaux;
    }
    
    /** addNiveaux : permettant d ajouter un niveau a l attribut niveaux
     * @param n de type Niveau */
    public void addNiveaux(Niveau n){
        if(niveaux == null)
            niveaux = new ArrayList<>();
        
        niveaux.add(n);
    }
    
    /** removeAllNiveaux : permettant de supprimer toutes les niveaux de l attribut niveaux */
    public void removeAllNiveaux(){
        niveaux.clear();
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
    
    /** removeAllEtudiats : permettant de supprimer tous les etudiants de l attribut etudiants */
    public void removeAllEtudiants(){
        etudiants.clear();
    }
    
    /** setEtudiants : permettant de recharger la totalité des étudiants de l'école */
    public void setEtudiants(){
        etudiants.clear();
        EcoleDAO e = new EcoleDAO(connect);
        etudiants = e.charger_etudiants(this);
    }
    
    /** getEnseignants : permettant d acceder a l attribut enseignements
     * @return l attribut enseignants */
    public HashMap<Integer, Enseignant> getEnseignants(){
        return enseignants;
    }
    
    /** addEnseignants : permettant d ajouter un enseignant a l attribut enseignants
     * @param e de type Enseignant */
    public void addEnseignants(Enseignant e){
        if(enseignants == null)
            enseignants = new HashMap<>();
        
        enseignants.put(e.getID(), e);
    }
    
    /** removeAllEnseignants : permettant de supprimer tous les enseignements de l attribut enseignements */
    public void removeAllEnseignants(){
        enseignants.clear();
    }
    
    /** setConnexion : permettant de modifier l attribut connect
     * @param connect de type Connexion */
    public void setConnexion(Connexion connect){
        this.connect = connect;
    }
    
    /** ajoutEcole : methode permettant d ajouter une ecole
     * @param connect de type Connexion */
    public void ajoutEcole(Connexion connect){
        
        //Création d'un objet EcoleDAO
        EcoleDAO ecole_dao = new EcoleDAO(connect);
        
        //Appel de la fonction d'ajout
        ecole_dao.ajouter(this);
    }
    
}
