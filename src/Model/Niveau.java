/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.NiveauDAO;
import java.util.ArrayList;

/** Niveau : classe contenant les donnees liees a la table niveau de la bdd
 *
 * @author thebo
 */
public class Niveau {
    
    
    /** Attributs prives de la classe : ID, nom et classes */
    private int ID;
    private String nom;
    protected ArrayList<Classe> classes;
    
    
    /** Constructeur par defaut */
    public Niveau(){
        ID = 0;
        nom = "";
    }
    
    /** Constructeur surcharge avec deux parametres : ID et nom
     * @param ID de type int
     * @param nom de type String */
    public Niveau(int ID, String nom){
        this.ID = ID;
        this.nom = nom;
    }

    /** Constructeur surcharge avec un parametre nom
     * @param nom de type String */
    public Niveau(String nom){
        ID = 0;
        this.nom = nom;
    }
    
    /** getID : permettant d acceder a l attribut ID
     * @return l attribut ID */
    public int getID(){
        return ID;
    }
    
    /** getNom : permettant d acceder a l attribut nom
     * @return l attribut nom */
    public String getNom(){
        return nom;
    }
    
    /** getClasses : permet d acceder a l attribut classes
     * @return l attribut classes */
    public ArrayList<Classe> getClasses(){
        return classes;
    }
    
    /** addClasses : permet d ajouter une classe dans l arraylist de classe
     * @param c de type Classe */
    public void addClasses(Classe c){
        if(classes == null)
            classes = new ArrayList<>();
        
        classes.add(c);
    }
    
    /** removeAllClasses : permettant de supprimer toutes les classes de l attribut classes */
    public void removeAllClasses(){
        classes.clear();
    }
    
    /** ajoutNiveau : methode permettant d ajouter un niveau
     * @param connect de type Connexion */
    public void ajoutNiveau(Connexion connect){
        
        //Création d'un objet NiveauDAO
        NiveauDAO niveau_dao = new NiveauDAO(connect);
        
        //Appel de la fonction d'ajout
        niveau_dao.ajouter(this);
    }
    
}
