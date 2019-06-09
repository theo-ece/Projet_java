/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/** Personne : classe contenant les donnees liees a la table personne de la bdd
 *
 * @author thebo
 */
public class Personne {
    
    protected String path;
    
    /** Attributs prives de la classe : iD, nom, prenom et type */
    protected int iD;
    protected String nom;
    protected String prenom;
    protected int type;

    
    /** Constructeur par defaut */
    public Personne(){
        iD = 0;
        nom = "";
        prenom = "";
        type = 0;
    }
    
    /** Constructeur surcharge avec quatre parametres: ID, nom, prenom et type
     * @param ID de type int
     * @param nom de type String
     * @param prenom de type String
     * @param type de type int */
    public Personne(int ID, String nom, String prenom, int type){
        iD = ID;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
    }
    
    /** Constructeur surcharge avec trois parametres: nom, prenom et type
     * @param nom de type String
     * @param prenom de type String
     * @param type de type int */
    public Personne(String nom, String prenom, int type){
        iD = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
    }
    
    /** getID : permettant d acceder a l attribut ID
     * @return l attribut iD */
    public int getID(){
        return iD;
    }
    
    /** getNom : permettant d acceder a l attribut nom
     * @return l attribut nom */
    public String getNom(){
        return nom;
    }
    
    /** getPrenom : permettant d acceder a l attribut prenom
     * @return l attribut prenom */
    public String getPrenom(){
        return prenom;
    }
    
    /** getType : permettant d acceder a l attribut type
     * @return l attribut type */
    public int getType(){
        return type;
    }

    /** setID : permettant de modifier l attribut ID
     * @param id de type int */
    public void setID(int id){
        iD = id;
    }
    
    /** setPrenom : permettant de modifier l attribut prenom
     * @param prenom de type String */
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    
    /** setNom : permettant de modifier l attribut nom
     * @param nom de type String */
    public void setNom(String nom){
        this.nom = nom;
    }
   
}
