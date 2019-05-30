/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.PersonneDAO;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author thebo
 */
public class Etudiant extends Personne{
    
    /** Attribut prive de la classe : disciplines */
    private ArrayList<Discipline> disciplines;
    
    
    /** Constructeur par defaut */
    public Etudiant(){
        super(0, "", "", 0);
        disciplines = null;
    }
    
    /** Constructeur surcharge avec quatre parametres : ID, Nom, Prenom et type
     * @param ID
     * @param Nom
     * @param Prenom
     * @param type */
    public Etudiant(int ID, String Nom, String Prenom, int type){
        super(ID, Nom, Prenom, type);
    }
    
    /** Constructeur surcharge avec trois parametres : Nom, Prenom et type
     * @param Nom
     * @param Prenom
     * @param type */
    public Etudiant(String Nom, String Prenom, int type){
        super(0, Nom, Prenom, type);
    }
    
    
    /** getDisciplines : permettant d acceder a l attribut discipplines
     * @return  */
    public ArrayList<Discipline> getDisciplines(){
        return disciplines;
    }
    
    /** addDisciplines : permettant d ajouter une discipline a l attribut disciplines
     * @param d */
    public void addDisciplines(Discipline d){
        if(disciplines == null)
            disciplines = new ArrayList<>();
        
        disciplines.add(d);
    }
    
    /** removeAllDisciplines : permettant de supprimer toutes les disciplines de l attribut disciplines */
    public void removeAllDisciplines(){
        disciplines.clear();
    }
    
    /** ajoutEtudiant : methode permettant d ajouter un etudiant
     * @param connect */
    public void ajoutEtudiant(Connexion connect){
        
        //Création d'un objet PersonneDAO
        PersonneDAO etudiant_dao = new PersonneDAO(connect);
        
        //Appel de la fonction d'ajout
        etudiant_dao.ajouter(this);
    }
    
    /*
    public void test(){}
    
    // Main de Etudiant / useless
    public void run(String Path, int id){
        //chargementEnseignant();
        //chargementClasse();
        this.path = Path + " -> Etudiant";
        String str="";
        do{
            str="";
            System.out.println("Entrer votre choix : ");
            System.out.println("    enseignants");
            System.out.println("    classes");
            System.out.println("Pour sortir : exit");            
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
            if(null != str)switch (str) {
                case "classes":
                    showClasses(this.path);
                    break;
                case "enseignants":
                    //showEnseignants(this.path);
                    break;
                default:
                    break;
            }
        }while(!"exit".equals(str));
    }
    */
    
}
