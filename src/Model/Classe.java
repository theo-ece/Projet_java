/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.ClasseDAO;
import java.util.ArrayList;
import java.util.HashMap;

/**
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
     * @param ID
     * @param nom */
    public Classe(int ID, String nom){
        this.ID = ID;
        this.nom = nom;
        etudiants = null;
    }
    
    /** Constructeur surchage avec un parametre nom
     * @param nom */
    public Classe(String nom){
        ID = 0;
        this.nom = nom;
        etudiants = null;
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
    
    /** getNom : permet d acceder a l attribut nom
     * @return  */
    public String getNom(){
        return nom;
    }
    
    /** getEtudiants : permet d acceder a l attribut etudiants
     * @return  */
    public HashMap<Integer, Etudiant> getEtudiants(){
        return etudiants;
    }
    
    /** addEtudiants : methode qui permet d ajouter un etudiant dans etudiants
     * @param e */
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
     * @return  */
    public Niveau getNiveau(){
        return niveau;
    }
    
    /** getEcole : permet d acceder a l attribut ecole
     * @return  */
    public Ecole getEcole(){
        return ecole;
    }
    
    /** getAnnee : permet d acceder a l attribut annee
     * @return  */
    public AnneeScolaire getAnnee(){
        return annee;
    }
    
    /** getNiveau : permet de modifier l attribut niveau
     * @param niveau  */
    public void setNiveau(Niveau niveau){
        this.niveau = niveau;
    }
    
    /** getEcole : permet de modifier l attribut ecole
     * @param ecole */
    public void setEcole(Ecole ecole){
        this.ecole = ecole; 
    }
    
    /** getAnnee : permet de modifier l attribut annee
     * @param annee */
    public void setAnnee(AnneeScolaire annee){
        this.annee = annee;
    }
    
    /** ajoutClasse : methode permettant d ajouter une Classe
     * @param connect */
    public void ajoutClasse(Connexion connect){
        
        //Création d'un objet ClasseDAO
        ClasseDAO classe_dao = new ClasseDAO(connect);
        
        //Appel de la fonction d'ajout
        classe_dao.ajouter(this);
    }

    /*

    //A completer
    public Classe(int iD){
        ID=iD;
        // Extraire nom , discipline , niveau , prof de la BDD
        chargement_etudiants(iD);
    }
    // Creation d'une nouvelle classe
    public Classe(int iD, String N, Niveau lvl, Enseignant Prof, ArrayList<Discipline> matiere){
        etudiants = new HashMap<>();
        ID=iD;
        nom=N;
        niveau=lvl;
        prof=Prof;
        disciplines=matiere;
    }
    
    
    public void test(){}
    public void run(String path, int id){
        
    }
    
    // Fonction de chargement -> a faire
    public void chargement_etudiants(int key){
        etudiants = new HashMap<>();
    }
    
    // Fonction de recherche
    public void recherche_etudiant(String key) throws HashExistant, HashInexistant{    // question 1.3
        try{
            etudiants.get(Integer.valueOf(key)).test();
            throw new HashExistant();
        }
        catch(NullPointerException e){
            throw new HashInexistant("Enseignant " + key + " non existant");
        }
    }
        
    // Fonctions d'ajout
    public void ajout_etudiant(int ID, Etudiant a){
        String key = ""+ID;
        try{
            recherche_etudiant(key);
        }catch(HashInexistant e){
            etudiants.put(ID, a);
        }catch(HashExistant e){
            
        }
    }   // + modification de la BDD à faire / Graphique
    
    // Fonction de suppression    
    public void erase_etudiant(){
        String key="";
        System.out.println("ID de la Classe a supprimer : ");
        Scanner sc = new Scanner(System.in);
        key = sc.nextLine();
        try{
            recherche_etudiant(key);
        }catch(HashInexistant e){
            System.out.println("La classe n'existe pas.");
        }catch(HashExistant e){
            etudiants.remove(Integer.valueOf(key));
            // A supprimer dans BDD
        }
    }   // + modif BDD à faire / Graphique
    
    // Fonction d'import de la BDD    
    public Etudiant import_etudiant(int key){return new Etudiant(key);}
    */
}
