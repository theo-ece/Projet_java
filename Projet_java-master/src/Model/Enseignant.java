/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.PersonneDAO;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author thebo
 */
public class Enseignant extends Personne{
    
    
    /** Attributs prives de la classe discipline, classe et etudiants */
    private Discipline discipline;
    private Classe classe;
    private HashMap<Integer, Etudiant> etudiants;
    
    /** Constructeur par defaut */
    public Enseignant(){
        super(0,"","",0);
        discipline = null;
        classe = null;
    }
    
    // Etudiant déja existant -> a completer
   /* public Enseignant(int id){
        super(id,"","",0);
        liste_disciplines = null;
        liste_classes = null;
        // sortir toutes les info de la bdd
    }*/
    
    /** Constructeur surcharge avec quatre parametres ID, Nom, Prenom et type
     * @param ID
     * @param Nom
     * @param Prenom
     * @param type */
    public Enseignant(int ID, String Nom, String Prenom, int type){
        super(ID, Nom, Prenom, type);
        discipline = null;
        classe = null;
    }
    
    /** Constructeur surcharge avec trois parametres Nom, Prenom et type
     * @param Nom
     * @param Prenom
     * @param type */
    public Enseignant(String Nom, String Prenom, int type){
        super(0, Nom, Prenom, type);
        discipline = null;
        classe = null;
    }
    
    /** getDiscipline : permettant d acceder a l attribut discipline
     * @return */
    public Discipline getDiscipline(){
        return discipline;
    }
    
    /** setDiscipline : permettant de modifier l attribut discipline
     * @param discipline */
    public void setDiscipline(Discipline discipline){
        this.discipline = discipline;
    }
    
    /** getClasse : permettant d acceder a l attribut classe
     * @return  */
    public Classe getClasse(){
        return classe;
    }
    
    /** setClasse : permettant de modifier l attribut classe
     * @param classe */
    public void setClasse(Classe classe){
        this.classe = classe;
    }
    
    /** getEtudiants : permettant d acceder a l attribut etudiants
     * @return  */
    public HashMap<Integer, Etudiant> getEtudiants(){
        return etudiants;
    }
    
    /** addEtudiants : permettant d ajouter un etudiant a l attribut etudiants
     * @param e */
    public void addEtudiants(Etudiant e){
        if(etudiants == null)
            etudiants = new HashMap<>();
        
        etudiants.put(e.getID(), e);
    }
    
    /** removeAllEtudiants : permettant de supprimer tous les etudiants de l attribut etudiants */
    public void removeAllEtudiants(){
        etudiants.clear();
    }
    
    /** ajoutEnseignant : methode permettant d ajouter un enseignant
     * @param connect */
    public void ajoutEnseignant(Connexion connect){
        
        //Création d'un objet PersonneDAO
        PersonneDAO enseignant_dao = new PersonneDAO(connect);
        
        //Appel de la fonction d'ajout
        enseignant_dao.ajouter(this);
    }

    
    /*
    public void test(){}
    
    // Main de Enseignant -> pas a mettre dans le graphe #useless
    public void run(String Path, int id){
        //chargementClasse();
        this.path = Path + " -> Etudiant";
        String str="";
        do{
            str="";
            System.out.println("Entrer votre choix : ");
            System.out.println("    etudiants");
            System.out.println("    classes");
            System.out.println("Pour sortir : exit");            
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
            if(null != str)switch (str) {
                case "classes":
                    showClasses(this.path);
                    break;
                case "etudiants":
                    //showEtudiants(this.path);
                    break;
                default:
                    break;
            }
        }while(!"exit".equals(str));
    }
    
    
    public void ajout_discipline(){
        
    }*/
    
    /* enseignement : methode permettant d ajouter un enseignement a un enseignant */
    public void enseignement(){//correspond à enseignement dans la BDD au lieu de faire une classe "Enseignement"
    
    
    } 
    
}
