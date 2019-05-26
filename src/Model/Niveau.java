/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author thebo
 */
public class Niveau {
    private int ID;
    private String nom;
    protected HashMap<Integer, Classe> Classes;
    public Niveau(int iD){
        ID=iD;
        // nom a chercher dans la BDD
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
    }
    
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
    public Classe import_classe(int key){return new Classe(key);}
    
}
