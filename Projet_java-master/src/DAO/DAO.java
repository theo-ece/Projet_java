/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Connexion.Connexion;

/**
 *
 * @author Flora
 * source : OpenClassRoom : Pattern DAO
 * -> https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java/26830-liez-vos-tables-avec-des-objets-java-le-pattern-dao
 * @param <T>
 */
public abstract class DAO<T> {
    
    /** Attribut prive de la classe : connect */
    protected Connexion connect = null;
    
    /** Constructeur surcharge avec un seul parametre : connect
     * @param connect */
    public DAO(Connexion connect){
        this.connect = connect;
    }
    
    /** modifier : methode permettant de modifier une ligne de la table 
     * @param obj
     * @param champ
     * @param element
     * @return  */
    public abstract boolean modifier(T obj, String champ, String element);

    /** supprimer : methode permettant de modifier une ligne de la table
     * @param obj
     * @return  */
    public abstract boolean supprimer(T obj);

    /** ajouter : methode permettant d ajouter une ligne dans la table
     * @param obj
     * @return  */
    public abstract boolean ajouter(T obj);

    /** methode permettant de trouver et d ajouter dans les donnees une ligne de la table
     * @param id
     * @return  */
    public abstract T trouver_et_charge(int id);
    
    /** methode permettant de trouver une ligne de la table
     * @param id
     * @return  */
    public abstract T trouver(int id);
    
}
