/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.DisciplineDAO;

/** Discipline : classe contenant les donnees liees a la table discipline de la bdd
 *
 * @author thebo
 * 
 */
public class Discipline {
    
    /** Attributs prives de la classe Id et nom */
    private int ID;
    private String nom;
    
    
    /** Constructeur par defaut */
    public Discipline(){
        ID = 0;
        nom = "";
    }
    
    /** Constructeur surcharge surcharge avec deux parametres iD et N
     * @param iD de type int
     * @param N de type String */
    public Discipline(int iD, String N){
        ID=iD;
        nom=N;
    }
    
    /** Constructeur surcharge surcharge avec un parametre nom
     * @param nom de type String */
    public Discipline(String nom){
        this.nom = nom;
    }
    
    /** getID : permettant d'acceder a l attribut ID
     * @return l attribut ID */
    public int getID(){
        return ID;
    }
    
    /** getNom : permettant d'acceder a l attribut nom
     * @return l attribut nom */
    public String getNom(){
        return nom;
    }
    
    /** ajoutDiscipline : methode permettant d ajouter une Discipline
     * @param connect de type Connexion */
    public void ajoutDiscipline(Connexion connect){
        
        //Création d'un objet DisciplineDAO
        DisciplineDAO discipline_dao = new DisciplineDAO(connect);
        
        //Appel de la fonction d'ajout
        discipline_dao.ajouter(this);
    }
}
