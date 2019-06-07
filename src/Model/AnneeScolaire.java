/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.AnneeScolaireDAO;
import java.util.ArrayList;

/**
 *
 * @author thebo
 */
public class AnneeScolaire {
    
    /** Attributs prives de la classe ID, date, trimestres et classes */
    private final int ID;
    private final String date;
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
     * @param ID
     * @param date */
    public AnneeScolaire(int ID, String date){
        this.ID = ID;
        this.date = date;
        trimestres = null;
        classes = null;
    }
    
    /** Constructeur surcharge avec un parametre date
     * @param date */
    public AnneeScolaire(String date){
        ID = 0;
        this.date = date;
    }
    
    /** getID : permettant d acceder a l attribut ID
     * @return  */
    public int getID(){
        return ID;
    }
    
    /** getDate : permettant d acceder a l attribut Date
     * @return  */
    public String getDate(){
        return date;
    }
    
    /** getTrimestres : permettant d acceder a l attribut trimestres
     * @return  */
    public ArrayList<Trimestre> getTrimestres(){
        return trimestres;
    }
    
    /** addTrimestre : permettant d ajouter un trimestre a l attribut trimestres
     * @param t */
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
     * @return  */
    public ArrayList<Classe> getClasses(){
        return classes;
    }
    
    /** addClasses : permettant d ajouter une classes a l attribut classes
     * @param c */
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
     * @param connect */
    public void ajoutAnneeScolaire(Connexion connect){
        
        //Création d'un objet AnneeScolaireDAO
        AnneeScolaireDAO anneescolaire_dao = new AnneeScolaireDAO(connect);
        
        //Appel de la fonction d'ajout
        anneescolaire_dao.ajouter(this);
    }
}

