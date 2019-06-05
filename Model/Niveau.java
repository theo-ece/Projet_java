/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.NiveauDAO;
import java.util.ArrayList;

/**
 *
 * @author thebo
 */
public class Niveau {
    
    
    /** Attributs prives de la classe : ID, nom et classes */
    private int ID;
    private String nom;
    protected ArrayList<Classe> classes;
    
    
    /** Constructeur par defaut */
    public Niveau(){
        ID = 0;
        nom = "";
    }
    
    /** Constructeur surcharge avec deux parametres : ID et nom
     * @param ID
     * @param nom */
    public Niveau(int ID, String nom){
        this.ID = ID;
        this.nom = nom;
    }

    /** Constructeur surcharge avec un parametre nom
     * @param nom */
    public Niveau(String nom){
        ID = 0;
        this.nom = nom;
    }
    
    /** getID : permettant d acceder a l attribut ID
     * @return  */
    public int getID(){
        return ID;
    }
    
    /** getNom : permettant d acceder a l attribut nom
     * @return  */
    public String getNom(){
        return nom;
    }
    
    public ArrayList<Classe> getClasses(){
        return classes;
    }
    
    public void addClasses(Classe c){
        if(classes == null)
            classes = new ArrayList<>();
        
        classes.add(c);
    }
    
    /** removeAllClasses : permettant de supprimer toutes les classes de l attribut classes */
    public void removeAllClasses(){
        classes.clear();
    }
    
    /** ajoutNiveau : methode permettant d ajouter un niveau
     * @param connect */
    public void ajoutNiveau(Connexion connect){
        
        //Création d'un objet NiveauDAO
        NiveauDAO niveau_dao = new NiveauDAO(connect);
        
        //Appel de la fonction d'ajout
        niveau_dao.ajouter(this);
    }
    
    
    /*
    public Niveau(int iD){
        ID = iD;
    }
    
    
    public void test(){}
    public void run(String Path, int id){
        chargementClasses();
        //chargementDisciplines();
        
        String str="";
        do{
            str="";
            System.out.println("Ecole -> Niveau"); 
            System.out.println("Entrer votre choix : ");
            System.out.println("    disciplines");
            System.out.println("    classes");
            System.out.println("Pour sortir : exit");            
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
            if(null != str)switch (str) {
                case "classes":
                    showClasses();
                    break;
                case "disciplines":
                    //showDisciplines();
                    break;
                default:
                    break;
            }
        }while(!"exit".equals(str));
    }
   
    // Fonction de chargement du Niveau
    public void chargementClasses(){
        Classes = new HashMap<>();
        /*
            ...ton code
        */
    //}
    /*
    // Fonction de display
    public void showClasses(){
        for (Integer key : Classes.keySet()) {
            System.out.println(" Classes -> ["+ key + "] : " + Classes.get(key));
        }
        String str="";
        do{
            str="";
            System.out.println("Ecole -> Niveau -> Classe"); 
            System.out.println("Entrer votre choix : ");
            System.out.println("    ajout");
            System.out.println("    suppression");
            System.out.println("Pour sortir : exit");            
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
            if(null != str)switch (str) {
                case "ajout":
                    //ajout_classe();
                    break;
                case "suppression":
                    erase_classe();
                    break;
                default:
                    break;
            }
        }while(!"exit".equals(str));
    }    
    
    // Fonctions de recherche
    public void recherche_classe(String key) throws HashExistant, HashInexistant{
        try{
            Classes.get(Integer.valueOf(key)).test();
            throw new HashExistant();
        }
        catch(NullPointerException e){
            throw new HashInexistant("Niveau " + key + " non existant");
        }
    }
    
    // Fonctions d'ajout
    public void ajout_classe(int ID, Classe a){
        String key=""+ID;
        try{
            recherche_classe(key);
        }catch(HashInexistant e){
            Classes.put(ID, a);
        }catch(HashExistant e){
            
        }
    }   // + modif BDD à faire
    
    // Fonction de suppression
    public void erase_classe(){
        String key="";
        System.out.println("ID de la Classe a supprimer : ");
        Scanner sc = new Scanner(System.in);
        key = sc.nextLine();
        try{
            recherche_classe(key);
        }catch(HashInexistant e){
            System.out.println("La classe n'existe pas.");
        }catch(HashExistant e){
            Classes.remove(Integer.valueOf(key));
        }
    }   // + modif BDD à faire & graph
    
    // Fonctions d'import de la BDD
    public Classe import_classe(int key){return new Classe(key);}*/

    void run(String path, Integer valueOf) {
    }
    
}
