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

/**
 *
 * @author thebo
 */
public class Trimestre {
    
    /** Attributs prives de la classe : ID, numero, debut, fin et bulletins */
    private int ID;
    private int numero;
    private Date debut; 
    private Date fin;   
    private ArrayList<Bulletin> bulletins;
    
    
    /** Constructeur par defaut */
    public Trimestre(){
        ID = 0;
        numero = 0;
        debut = null;
        fin = null;
        bulletins = null;
    }
    
    /** Constructeur surcharge avec quatre parametres : iD, n , d et f
     * @param iD
     * @param n
     * @param d
     * @param f */
    public Trimestre(int iD, int n, Date d, Date f){
        ID = iD;
        numero = n;
        debut = d;
        fin = f;
        bulletins = null;
    }
    
    /** Constructeur surcharge avec trois parametres : n , d et f
     * @param n
     * @param d
     * @param f */
    public Trimestre(int n, Date d, Date f){
        ID = 0;
        numero = n;
        debut = d;
        fin = f;
        bulletins = null;
    }
    
    /** getID : permettant d acceder a l attribut ID
     * @return  */
    public int getID(){
        return ID;
    }
    
    /** getNumero : permettant d acceder a l attribut numero
     * @return  */
    public int getNumero(){
        return numero;
    }
    
    /** getDebut : permettant d acceder a l attribut debut
     * @return  */
    public Date getDebut(){
        return debut;
    }
    
    
    /** getFin : permettant d acceder a l attribut fin
     * @return  */
    public Date getFin(){
        return fin;
    }
    
    
    /** getBulletins : permettant d acceder a l attribut bulletins
     * @return  */
    public ArrayList<Bulletin> getBulletins(){
        return bulletins;
    }
    
    /** addClasses : permettant d ajouter un bulletin a l attribut bulletins
     * @param b */
    public void addClasses(Bulletin b){
        if(bulletins == null)
            bulletins = new ArrayList<>();
        
        bulletins.add(b);
    }
    
    /** removeAllBulletins : permettant de supprimer tous les bulletins de l attribut bulletins */
    public void removeAllBulletins(){
        bulletins.clear();
    }
    
    /** ajoutTrimestre : methode permettant d ajouter un trimestre
     * @param connect */
    public void ajoutTrimestre(Connexion connect){
        
        //Création d'un objet TrimestreDAO
        TrimestreDAO trimestre_dao = new TrimestreDAO(connect);
        
        //Appel de la fonction d'ajout
        trimestre_dao.ajouter(this);
    }
}
