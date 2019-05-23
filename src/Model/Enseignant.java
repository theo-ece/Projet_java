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
public class Enseignant extends Personne{
    protected HashMap<Integer, Etudiant> etudiants;
    protected HashMap<Integer, Classe> classes;
    public Enseignant(int id, String Nom, String Prenom){
        super(id,Nom,Prenom,1);
    }
    public void test(){}
    
    // Main de Enseignant
    public void run(String Path, int id){
        chargementClasse();
        chargementEtudiant();
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
                    showEtudiants(this.path);
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
    public void showEtudiants(String src){
        etudiants.keySet().forEach((key) -> {
            System.out.println( src + " -> Enseignants -> ["+ key + "] : " + etudiants.get(key));
        });
        String str="";
        do{
            str="";
            System.out.println("exit pour sortir");                        
            Scanner sc = new Scanner(System.in);            
            str = sc.nextLine(); 
            try{
                recherche_etudiant(str);
            }catch(HashInexistant e){   
            
            }
            catch(HashExistant e){
                etudiants.get(Integer.valueOf(str)).run(path, Integer.valueOf(str));
            }
        }while(!"exit".equals(str));
    }
    public void recherche_etudiant(String key) throws HashExistant, HashInexistant{    // question 1.3
        try{
            etudiants.get(Integer.valueOf(key)).test();
            throw new HashExistant();
        }
        catch(NullPointerException e){
            throw new HashInexistant("Enseignant " + key + " non existant");
        }
    }
    public void recherche_classe(String key) throws HashExistant, HashInexistant{    // question 1.3
        try{
            classes.get(Integer.valueOf(key)).test();
            throw new HashExistant();
        }
        catch(NullPointerException e){
            throw new HashInexistant("Niveau " + key + " non existant");
        }
    }
    
    // Fonctions de chargement
    public void chargementEtudiant(){
        
    }
    public void chargementClasse(){
        
    }
}
