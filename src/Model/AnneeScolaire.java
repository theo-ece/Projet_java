/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.AnneeScolaireDAO;
import java.util.ArrayList;

/** AnneeScolaire : classe contenant les donnees liees a la table anneescolaire de la bdd
 *
 * @author thebo
 */
public class AnneeScolaire {
    
    /** Attributs prives de la classe ID, date, trimestres et classes */
    private int ID;
    private String date;
    private ArrayList<Trimestre> trimestres;
    private ArrayList<Classe> classes;
    
    
    /** Constructeur par defaut */
    public AnneeScolaire(){
        ID = 0;
        date = null;
        trimestres = null;
        classes = null;
    }
    
    /** Constructeur surcharge avec deux parametres ID et date
     * @param ID de type int
     * @param date de type String */
    public AnneeScolaire(int ID, String date){
        this.ID = ID;
        this.date = date;
        trimestres = null;
        classes = null;
    }
    
    /** Constructeur surcharge avec un parametre date
     * @param date de type String */
    public AnneeScolaire(String date){
        ID = 0;
        this.date = date;
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
    
    /** getDate : permettant d acceder a l attribut Date
     * @return l attribut date */
    public String getDate(){
        return date;
    }
    
    /** getTrimestres : permettant d acceder a l attribut trimestres
     * @return l attribut trimestres */
    public ArrayList<Trimestre> getTrimestres(){
        return trimestres;
    }
    
    /** addTrimestre : permettant d ajouter un trimestre a l attribut trimestres
     * @param t un objet Trimestre */
    public void addTrimestres(Trimestre t){
        if(trimestres == null)
            trimestres = new ArrayList<>();
        
        trimestres.add(t);
    }
    
    /** removeAllTrimestres : permettant de supprimer tous les trimestres de l attribut trimestres */
    public void removeAllTrimestres(){
        trimestres.clear();
    }
    
    /** getClasses : permettant d acceder a l attribut classes
     * @return l attribut classe */
    public ArrayList<Classe> getClasses(){
        return classes;
    }
    
    /** addClasses : permettant d ajouter une classes a l attribut classes
     * @param c un objet Classe */
    public void addClasses(Classe c){
        if(classes == null)
            classes = new ArrayList<>();
        
        classes.add(c);
    }
    
    /** removeAllClasses : permettant de supprimer toutes les classes de l attribut classes */
    public void removeAllClasses(){
        classes.clear();
    }
    
    
    /** ajoutAnneeScolaire : methode permettant d ajouter une AnneeScolaire
     * @param connect un objet Connexion */
    public void ajoutAnneeScolaire(Connexion connect){
        
        //Création d'un objet AnneeScolaireDAO
        AnneeScolaireDAO anneescolaire_dao = new AnneeScolaireDAO(connect);
        
        //Appel de la fonction d'ajout
        anneescolaire_dao.ajouter(this);
    }
}

