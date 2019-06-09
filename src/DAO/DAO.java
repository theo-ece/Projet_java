/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Connexion.Connexion;

/** DAO : classe abstraite qui permettra a ses classes filles de recuperer les donnees de la table anneescolaire de la BDD
 *
 * @author Flora
 * source : OpenClassRoom : Pattern DAO
 * https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java/26830-liez-vos-tables-avec-des-objets-java-le-pattern-dao
 * @param <T> de type Objet
 */
public abstract class DAO<T> {
    
    /** Attribut prive de la classe : connect */
    protected Connexion connect = null;
    
    /** Constructeur surcharge avec un seul parametre connect
     * @param connect un objet de type Connexion */
    public DAO(Connexion connect){
        this.connect = connect;
    }
    
    /** modifier : methode permettant de modifier un attribut d un objet de la table
     * @param obj un objet de typ Anneescolaire
     * @param champ un objet de type String 
     * @param element un objet de type element
     * @return vrai si la modification a eu lieu et non sinon */
    public abstract boolean modifier(T obj, String champ, String element);

    /** supprimer : methode permettant de supprimer un objet de la table
     * @param obj un objet de type T */
    public abstract void supprimer(T obj);

    /** ajouter : methode permettant d ajouter un nouvel objet dans la table
     * @param obj de type T
     * @return l ID de l objet ajouter dans la bdd */
    public abstract int ajouter(T obj);

   /** trouver_et_charge : methode permettant de trouver et charger dans les donnees un objet de la table via son id
     * @param id l id de l objet qu il faut trouver dans la bdd
     * @return l objet T trouve */
    public abstract T trouver_et_charge(int id);
    
    /** trouver : methode permettant de trouver dans les donnees un objet de la table via son id
     * @param id l id de l objet qu il faut trouver dans la bdd
     * @return l objet T trouve */
    public abstract T trouver(int id);
    
}
