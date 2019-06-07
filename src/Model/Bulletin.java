/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.BulletinDAO;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author thebo
 */
public class Bulletin {
    
    /** Attributs prives de la classe : ID, appreciation, bulletin_global */
    private int ID;
    private String appreciation;
    private ArrayList<DetailBulletin> bulletins_detail;
    private Inscription inscription;
    private Trimestre trimestre;
    private Etudiant etudiant;
    
    //protected HashMap<Integer, Evaluation> eval;
    
    //attribut utile pour l'ajout uniquement
    
    
    
    
    /** Constructeur par defaut */
    public Bulletin(){
        ID = 0;
        appreciation = "";
        bulletins_detail = null;
        inscription = null;
        trimestre = null;
        etudiant = null;
             
    }
    
    /** Constructeur surcharge avec deux parametres ID et appreciation
     * @param ID
     * @param appreciation */
    public Bulletin(int ID, String appreciation){
        this.ID = ID;
        this.appreciation = appreciation;
        bulletins_detail = null;
        inscription = null;
        trimestre = null;
        etudiant = null;
    }
    
    /** Constructeur surcharge avec un parametre appreciation
     * @param appreciation */
    public Bulletin(String appreciation){
        ID = 0;
        this.appreciation = appreciation;
        bulletins_detail = null;
        inscription = null;
        trimestre = null;
        etudiant = null;
    }

    /** getID : permet d acceder  a l attribut ID
     * @return  */
    public int getID(){
        return ID;
    }
    
    /** getDetailBulletin : permet d acceder a l attribut bulletins_detail
     * @return  */
    public ArrayList<DetailBulletin> getDetailBulletin(){
        return bulletins_detail;
    }
    
    /** getAppreciation : permet d acceder a l attribut appreciation
     * @return  */
    public String getAppreciation(){
        return appreciation;
    }
    
    /** addDetailsB : permet d ajouter un DetailBulletin  a l attribut bulletins_detail
     * @param d */
    public void addDetailsB(DetailBulletin d){
        if(bulletins_detail == null)
            bulletins_detail = new ArrayList<>();

        bulletins_detail.add(d);
    }
    
    /** removeAllDetailsB : permet de supprimer tous les DetailBulletin de bulletin_details */
    public void removeAllDetailsB(){
        bulletins_detail.clear();
    }
    
    /** setTrimestre : permet de modifier l attribut trimestre
     * @param trimestre */
    public void setTrimestre(Trimestre trimestre){
        this.trimestre = trimestre;
    }
    
    /** setEtudiant : permet de modifier l attribut etudiant
     * @param etudiant */
    public void setEtudiant(Etudiant etudiant){
        this.etudiant = etudiant;
    }
    
    /** getTrimestre : permet d acceder a l attribut trimestre
     * @return  */
    public Trimestre getTrimestre(){
        return trimestre;
    }
    
    /** getEtudiant : permet d acceder a l attribut etudiant
     * @return  */
    public Etudiant getEtudiant(){
        return etudiant;
    }
    
    /** getInscription : permet d acceder a l attribut inscription
     * @return  */
    public Inscription getInscription(){
        return inscription;
    }
    
    /** setInscription : permet de modifierl attribut etudiant
     * @param inscription  */
    public void setInscription(Inscription inscription){
        this.inscription = inscription;
    }
    
    /** ajoutBulletin : methode permettant d ajouter un bulletin
     * @param connect */
    public void ajoutBulletin(Connexion connect){
        
        //Création d'un objet BulletinDAO
        BulletinDAO bulletin_dao = new BulletinDAO(connect);
        
        //Appel de la fonction d'ajout
        bulletin_dao.ajouter(this);
    }
    
    
    public Bulletin(int iD){
        ID = iD;
        /*
            Sortir de la bdd les note du gars et son appreciation
        */
    }
    
}
