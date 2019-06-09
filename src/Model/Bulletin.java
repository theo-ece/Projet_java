/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.BulletinDAO;
import java.util.ArrayList;

/** Bulletin : classe contenant les donnees liees a la table bulletin de la bdd
 *
 * @author thebo
 */
public class Bulletin {
    
    /** Attributs prives de la classe : ID, appreciation, bulletin_global, inscription, trimestre, etudiant */
    private int ID;
    private String appreciation;
    private ArrayList<DetailBulletin> bulletins_detail;
    private Inscription inscription;
    private Trimestre trimestre;
    private Etudiant etudiant;
    
    
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
     * @param ID de type int
     * @param appreciation de type String */
    public Bulletin(int ID, String appreciation){
        this.ID = ID;
        this.appreciation = appreciation;
        bulletins_detail = null;
        inscription = null;
        trimestre = null;
        etudiant = null;
    }
    
    /** Constructeur surcharge avec un parametre appreciation
     * @param appreciation de type String */
    public Bulletin(String appreciation){
        ID = 0;
        this.appreciation = appreciation;
        bulletins_detail = null;
        inscription = null;
        trimestre = null;
        etudiant = null;
    }

    /** getID : permet d acceder  a l attribut ID
     * @return l attribut ID */
    public int getID(){
        return ID;
    }
    
    /** setID : permet d acceder  a l attribut ID
     * @param id de type int */
    public void setID(int id){
        ID = id;
    }
    
    /** getDetailBulletin : permet d acceder a l attribut bulletins_detail
     * @return l attribut bulletins_detail */
    public ArrayList<DetailBulletin> getDetailBulletin(){
        return bulletins_detail;
    }
    
    /** getAppreciation : permet d acceder a l attribut appreciation
     * @return l attribut appreciation */
    public String getAppreciation(){
        return appreciation;
    }
    
    /** addDetailsB : permet d ajouter un DetailBulletin  a l attribut bulletins_detail
     * @param d de type DetailBulletin */
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
     * @param trimestre  de type Trimestre */
    public void setTrimestre(Trimestre trimestre){
        this.trimestre = trimestre;
    }
    
    /** setEtudiant : permet de modifier l attribut etudiant
     * @param etudiant de type Etudiant */
    public void setEtudiant(Etudiant etudiant){
        this.etudiant = etudiant;
    }
    
    /** getTrimestre : permet d acceder a l attribut trimestre
     * @return l attribut trimestre */
    public Trimestre getTrimestre(){
        return trimestre;
    }
    
    /** getEtudiant : permet d acceder a l attribut etudiant
     * @return l attribut etudiant */
    public Etudiant getEtudiant(){
        return etudiant;
    }
    
    /** getInscription : permet d acceder a l attribut inscription
     * @return l attribut inscription */
    public Inscription getInscription(){
        return inscription;
    }
    
    /** setInscription : permet de modifier l attribut etudiant
     * @param inscription de type Inscription */
    public void setInscription(Inscription inscription){
        this.inscription = inscription;
    }
    
    /** ajoutBulletin : methode permettant d ajouter un bulletin
     * @param connect de type Connexion */
    public void ajoutBulletin(Connexion connect){
        
        //Création d'un objet BulletinDAO
        BulletinDAO bulletin_dao = new BulletinDAO(connect);
        
        //Appel de la fonction d'ajout
        bulletin_dao.ajouter(this);
    }
    
}
