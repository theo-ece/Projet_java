/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.InscriptionDAO;

/** Inscription : classe contenant les donnees liees a la table inscription de la bdd
 *
 * @author Flora
 */
public class Inscription {
    
    /** Attribut prive de la classe : ID */
    private int ID;
    
    //Attributs pour l' ajout
    private Classe classe = null;
    private Etudiant etudiant = null;
    
    /** Constructeur par defaut */
    public Inscription(){
        ID = 0;
    }
    
    /** Constructeur surcharge avec un parametre ID
     * @param ID de type int */
    public Inscription(int ID){
        this.ID = ID;
    }
    
    /** Constructeur surcharge avec deux parametres classe
     * @param classe de type Classe
     * @param etudiant de type Etudiant */
    public Inscription(Classe classe, Etudiant etudiant){
        this.classe = classe;
        this.etudiant = etudiant;
    }
    
    /** getID : permet d acceder a l attribut ID
     * @return l attribut ID */
    public int getID(){
        return ID;
    }
    
    /** setID : permettant de modifier l attribut ID
     * @param id de type int */
    public void setID(int id){
        ID = id;
    }
    
    /** getClasse : permettant d acceder a l attribut classe
     * @return l attribut classe */
    public Classe getClasse(){
        return classe;
    }
    
    /** setClasse : permettant de modifier l attribut classe
     * @param classe de type Classe */
    public void setClasse(Classe classe){
        this.classe = classe;
    }
    
    /** getEtudiant : permettant d acceder a l attribut etudiant
     * @return l attribut etudiant */
    public Etudiant getEtudiant(){
        return etudiant;
    }
    
    /** setEtudiant : permettant de modifier l attribut etudiant
     * @param etudiant de type Etudiant */
    public void setEtudiant(Etudiant etudiant){
        this.etudiant = etudiant;
    }
    
    /* inscription : methode permettant d inscrire un eleve dans une classe */
    public int inscription(Connexion connect){ 
        
        //Création d'un objet InscriptionDAO
        InscriptionDAO inscription_dao = new InscriptionDAO(connect);
        return inscription_dao.ajouter(this);
        
    }
    
    /* desinscription : methode permettant de desinscrire un eleve d une classe */
    public void desinscription(Connexion connect){ 
        
        //Création d'un objet InscriptionDAO
        InscriptionDAO inscription_dao = new InscriptionDAO(connect);
        inscription_dao.supprimer(this);
        
    }
}
