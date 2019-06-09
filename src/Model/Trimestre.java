/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.TrimestreDAO;
import java.util.ArrayList;
import java.util.Date;

/** Trimestre : classe contenant les donnees liees a la table trimestre de la bdd
 *
 * @author thebo
 */
public class Trimestre {
    
    /** Attributs prives de la classe : ID, numero, debut, fin et bulletins */
    private int ID;
    private int numero;
    private String debut; 
    private String fin;   
    private ArrayList<Bulletin> bulletins;
    
    
    /** Constructeur par defaut */
    public Trimestre(){
        ID = 0;
        numero = 0;
        debut = null;
        fin = null;
        bulletins = null;
    }
    
    /** Constructeur surcharge avec quatre parametres : iD, n, d et f
     * @param iD de type int
     * @param n de type int 
     * @param d de type String
     * @param f de type String */
    public Trimestre(int iD, int n, String d, String f){
        ID = iD;
        numero = n;
        debut = d;
        fin = f;
        bulletins = null;
    }
    
    /** Constructeur surcharge avec trois parametres : n , d et f
     * @param n de type int
     * @param d de type String
     * @param f de type String */
    public Trimestre(int n, String d, String f){
        ID = 0;
        numero = n;
        debut = d;
        fin = f;
        bulletins = null;
    }
    
    /** getID : permettant d acceder a l attribut ID
     * @return l attribut ID */
    public int getID(){
        return ID;
    }
    
    /** getNumero : permettant d acceder a l attribut numero
     * @return l attribut numero */
    public int getNumero(){
        return numero;
    }
    
    /** getDebut : permettant d acceder a l attribut debut
     * @return l attribut debut */
    public String getDebut(){
        return debut;
    }
    
    
    /** getFin : permettant d acceder a l attribut fin
     * @return l attribut fin */
    public String getFin(){
        return fin;
    }
    
    
    /** getBulletins : permettant d acceder a l attribut bulletins
     * @return l attribut bulletins */
    public ArrayList<Bulletin> getBulletins(){
        return bulletins;
    }
    
    /** addBulletins : permettant d ajouter un bulletin a l attribut bulletins
     * @param b de type Bulletin */
    public void addBulletins(Bulletin b){
        if(bulletins == null)
            bulletins = new ArrayList<>();
        
        bulletins.add(b);
    }
    
    /** removeAllBulletins : permettant de supprimer tous les bulletins de l attribut bulletins */
    public void removeAllBulletins(){
        bulletins.clear();
    }
    
    /** ajoutTrimestre : methode permettant d ajouter un trimestre
     * @param connect de type Connexion */
    public void ajoutTrimestre(Connexion connect){
        
        //Création d'un objet TrimestreDAO
        TrimestreDAO trimestre_dao = new TrimestreDAO(connect);
        
        //Appel de la fonction d'ajout
        trimestre_dao.ajouter(this);
    }
}
