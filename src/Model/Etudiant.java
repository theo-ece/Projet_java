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
public class Etudiant extends Personne{
    protected HashMap<Integer, Enseignant> enseignants;
    protected HashMap<Integer, Classe> classes;
    public Etudiant(int id, String Nom, String Prenom){
        super(id,Nom,Prenom,0);
    }
    public void test(){}
    
    // Main de Etudiant
    public void run(String Path, int id){
        chargementEnseignant();
        chargementClasse();
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
                    showEnseignants(this.path);
                    break;
                default:
                    break;
            }
        }while(!"exit".equals(str));
    }
    
    // Fonctions d'appel du choix (done)
    public void showClasses(String src){
        classes.keySet().forEach((key) -> {
            System.out.println(src + " -> Classe -> ["+ key + "] : " + classes.get(key));
        });
        String str="";
        do{
            str="";
            System.out.println("exit pour sortir");                        
            Scanner sc = new Scanner(System.in);            
            str = sc.nextLine(); 
            try{
                recherche_classe(str);
            }catch(HashInexistant e){   
            
            }
            catch(HashExistant e){
                classes.get(Integer.valueOf(str)).run(path, Integer.valueOf(str));
            }
        }while(!"exit".equals(str));
    } 
    public void showEnseignants(String src){
        enseignants.keySet().forEach((key) -> {
            System.out.println( src + " -> Enseignants -> ["+ key + "] : " + enseignants.get(key));
        });
        String str="";
        do{
            str="";
            System.out.println("exit pour sortir");                        
            Scanner sc = new Scanner(System.in);            
            str = sc.nextLine(); 
            try{
                recherche_enseignant(str);
            }catch(HashInexistant e){   
            
            }
            catch(HashExistant e){
                enseignants.get(Integer.valueOf(str)).run(path, Integer.valueOf(str));
            }
        }while(!"exit".equals(str));
    }
    public void recherche_enseignant(String key) throws HashExistant, HashInexistant{    // question 1.3
        try{
            enseignants.get(Integer.valueOf(key)).test();
            throw new HashExistant();
        }
        catch(NullPointerException e){
            throw new HashInexistant("Enseignant " + key + " non existant");
        }
    }
    public void recherche_classe(String key) throws HashExistant, HashInexistant{    // question 1.3
        try{
            enseignants.get(Integer.valueOf(key)).test();
            throw new HashExistant();
        }
        catch(NullPointerException e){
            throw new HashInexistant("Niveau " + key + " non existant");
        }
    }
    
    // Fonctions de chargement
    public void chargement(int id){
        this.iD=id;
        this.nom=""; // a chercher
        this.prenom = ""; // a chercher
        this.type = 0;
    }
    public void chargementEnseignant(){
        
    }
    public void chargementClasse(){
        
    }
}
