/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author thebo
 */
public class Ecole {
    private final String path;
    private int ID;
    private String nom;
    protected HashMap<Integer, Etudiant> etudiants;
    protected HashMap<Integer, Enseignant> enseignants;
    protected HashMap<Integer, Niveau> niveaux;
    
    public Ecole(){
        path="Ecole";
        showEcole();
        chargementEtudiants();
        chargementEnseignants();
        chargementNiveaux();
    }  
    // Main de Ecole
    public void run(){
        String str="";
        do{
            str="";
            System.out.println("Entrer votre choix : ");
            System.out.println("    etudiants");
            System.out.println("    enseignants");
            System.out.println("    niveaux");
            System.out.println("Pour sortir : exit");            
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
            if(null != str)switch (str) {
                case "etudiants":
                    showEtudiants();
                    break;
                case "enseignants":
                    showEnseignants();
                    break;
                case "niveaux":
                    showNiveaux();
                    break;
                default:
                    break;
            }
        }while(!"exit".equals(str));
    }
    
    // Fonctions d'appel du choix (done)
    public void showEcole(){                    // A faire -> choix de l'ecole lié a la BDD
        int id=0;
        String Name="";
        /*
            Le code
        */
        _setNom(Name);
        _setID(id);        
    }   // A faire
    public void showEtudiants(){
        String str="";
        etudiants.keySet().forEach((key) -> {
            System.out.println(" Etudiants -> ["+ key + "] : " + etudiants.get(key));
        });
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
    public void showEnseignants(){
        enseignants.keySet().forEach((key) -> {
            System.out.println(" Enseignants -> ["+ key + "] : " + enseignants.get(key));
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
    public void showNiveaux(){
        niveaux.keySet().forEach((key) -> {
            System.out.println(" Niveaux -> ["+ key + "] : " + niveaux.get(key));
        });
        String str="";
        do{
            str="";
            System.out.println("exit pour sortir");                        
            Scanner sc = new Scanner(System.in);            
            str = sc.nextLine(); 
            try{
                recherche_niveau(str);
            }catch(HashInexistant e){   
            
            }
            catch(HashExistant e){
                niveaux.get(Integer.valueOf(str)).run(path, Integer.valueOf(str));
            }
        }while(!"exit".equals(str));
    }  
    
    // Fonctions de recherche dans la map (done)
    public void recherche_etudiant(String key) throws HashInexistant, HashExistant{
        try{
            etudiants.get(Integer.valueOf(key)).test();
            throw new HashExistant();
        }
        catch(NullPointerException e){
            throw new HashInexistant("Etudiant " + key + " non existant");
        }
    }
    public void recherche_enseignant(String key) throws  HashInexistant, HashExistant{
        try{
            enseignants.get(Integer.valueOf(key)).test();
            throw new HashExistant();
        }
        catch(NullPointerException e){
            throw new HashInexistant("Enseignant " + key + " non existant");
        }
    }
    public void recherche_niveau(String key) throws  HashInexistant, HashExistant{  
        try{
            enseignants.get(Integer.valueOf(key)).test();
            throw new HashExistant();
        }
        catch(NullPointerException e){
            throw new HashInexistant("Niveau " + key + " non existant");
        }
    }

    // Fonctions de Chargement (a remplir)
    private void chargementEtudiants(){
        
    }
    private void chargementEnseignants(){
        
    }
    private void chargementNiveaux(){
        
    }    
    
    // Setters
    private void _setID(int a){
        ID = a;
    }
    private void _setNom(String a){
        nom = a;
    }
}
