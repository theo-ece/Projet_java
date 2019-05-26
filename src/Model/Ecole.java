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
    protected HashMap<Integer, Classe> classes;
    protected HashMap<Integer, Discipline> Disciplines;
    
    public Ecole(){
        path="Ecole";
        showEcole();
        chargementEtudiants();
        chargementEnseignants();
        chargementNiveaux();
        chargementClasses();
        chargementDisciplines();
    }  
    // Main de Ecole
    public void run(){
        String str="";
        do{
            str="";
            System.out.println("Entrer votre choix : ");
            System.out.println("    [1] Supprimer un élément");
            System.out.println("    [2] Ajouter un élément");
            System.out.println("    [3] Modifier un élément");
            System.out.println("Pour sortir : exit");            
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
            if("1".equals(str) || "2".equals(str) || "3".equals(str)){showRUN(Integer.valueOf(str));}
        }while(!"exit".equals(str));
    }
    
    // Fonctions d'appel du choix (done)
    public void showRUN(int choix){
        String str="";
        do{
            str="";
            System.out.println("Entrer votre choix : ");
            System.out.println("    etudiants");
            System.out.println("    enseignants");
            System.out.println("    niveaux");
            System.out.println("    classes");
            System.out.println("    disciplines");
            System.out.println("Pour sortir : exit");            
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
            if(null != str)switch (str) {
                case "etudiants":
                    switch (choix) {
                        case 1:
                            erase_etudiant();
                            break;
                        case 2:
                            ajout_new_etudiant();
                            break;
                        case 3:
                            showEtudiants();
                            break;
                        default:
                            break;
                    }
                    break;
                case "enseignants":
                    switch (choix) {
                        case 1:
                            erase_enseignant();
                            break;
                        case 2:
                            ajout_new_enseignant();
                            break;
                        case 3:
                            showEnseignants();
                            break;
                        default:
                            break;
                    }
                    break;
                case "niveaux":
                    switch (choix) {
                        case 1:
                            erase_niveau();
                            break;
                        case 2:
                            ajout_new_niveau();
                            break;
                        case 3:
                            showNiveaux();
                            break;
                        default:
                            break;
                    }
                    break;
                case "classes":
                    showClasses();
                    switch (choix) {
                        case 1:
                            erase_classe();
                            break;
                        case 2:
                            ajout_new_classe();
                            break;
                        case 3:
                            showClasses();
                            break;
                        default:
                            break;
                    }
                    break;
                case "disciplines":
                    
                    switch (choix) {
                        case 1:
                            erase_discipline();
                            break;
                        case 2:
                            ajout_new_discipline();
                            break;
                        case 3:
                            showDisciplines();
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }while(!"exit".equals(str));
    }
    public void showADD(){
        
    }
    public void showERASE(){
        
    }
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
            System.out.println("ID a modifier :");
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
            System.out.println("ID a modifier :");
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
            System.out.println("ID a modifier :");
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
    public void showClasses(){
        classes.keySet().forEach((key) -> {
            System.out.println("Classe -> ["+ key + "] : " + classes.get(key));
        });
        String str="";
        do{
            str="";
            System.out.println("ID a modifier :");
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
    public void showDisciplines(){
        for (Integer key : Disciplines.keySet()) {
            System.out.println(" Disciplines -> ["+ key + "] : " + Disciplines.get(key));
        }
        String str="";
        do{
            str="";
            System.out.println("ID a modifier :");
            System.out.println("Pour sortir : exit");            
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine(); 
            try{
                recherche_discipline(str);
            }catch(HashInexistant e){   
            
            }
            catch(HashExistant e){
                Disciplines.get(Integer.valueOf(str)).run();
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
    public void recherche_classe(String key) throws HashExistant, HashInexistant{    // question 1.3
        try{
            classes.get(Integer.valueOf(key)).test();
            throw new HashExistant();
        }
        catch(NullPointerException e){
            throw new HashInexistant("Niveau " + key + " non existant");
        }
    }
    public void recherche_discipline(String key) throws HashExistant, HashInexistant{
        try{
            Disciplines.get(Integer.valueOf(key));
            throw new HashExistant();
        }
        catch(NullPointerException e){
            throw new HashInexistant("Niveau " + key + " non existant");
        }
    }
    
    // Fonction d'ajout
    public void ajout_new_classe(){
        
    }
    public void ajout_new_etudiant(){
        
    }
    public void ajout_new_discipline(){
        
    }
    public void ajout_new_enseignant(){
        
    }
    public void ajout_new_niveau(){
        
    }
    
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
            classes.remove(Integer.valueOf(key));
        }
    }               // + modif BDD à faire & graph
    public void erase_etudiant(){
        String key="";
        System.out.println("ID de la Classe a supprimer : ");
        Scanner sc = new Scanner(System.in);
        key = sc.nextLine();
        try{
            recherche_etudiant(key);
        }catch(HashInexistant e){
            System.out.println("La classe n'existe pas.");
        }catch(HashExistant e){
            etudiants.remove(Integer.valueOf(key));
            // A supprimer dans BDD
        }
    }             // + modif BDD à faire / Graphique
    public void erase_discipline(){
        String key="";
        System.out.println("ID de la discipline a supprimer : ");
        Scanner sc = new Scanner(System.in);
        key = sc.nextLine();
        try{
            recherche_discipline(key);
        }catch(HashInexistant e){
            System.out.println("La discipline n'existe pas.");
        }catch(HashExistant e){
            Disciplines.remove(Integer.valueOf(key));
            // A supprimer dans BDD
        }
    }           // + modif BDD à faire / Graphique    
    public void erase_niveau(){
        String key="";
        System.out.println("ID du niveau a supprimer : ");
        Scanner sc = new Scanner(System.in);
        key = sc.nextLine();
        try{
            recherche_niveau(key);
        }catch(HashInexistant e){
            System.out.println("Le niveau n'existe pas.");
        }catch(HashExistant e){
            niveaux.remove(Integer.valueOf(key));
            // A supprimer dans BDD
        }
    }               // + modif BDD à faire / Graphique 
    public void erase_enseignant(){
        String key="";
        System.out.println("ID du prof a supprimer : ");
        Scanner sc = new Scanner(System.in);
        key = sc.nextLine();
        try{
            recherche_enseignant(key);
        }catch(HashInexistant e){
            System.out.println("Le prof n'existe pas.");
        }catch(HashExistant e){
            enseignants.remove(Integer.valueOf(key));
            // A supprimer dans BDD
        }
    }           // + modif BDD à faire / Graphique 
    
    // Fonction d'import
    public Discipline import_discipline(int key, String Nom){return new Discipline(key,Nom);}
    public Classe import_classe(int key){return new Classe(key);}
    public Etudiant import_etudiant(int key){return new Etudiant(key);}
    public Enseignant import_enseignant(int key){return new Enseignant(key);}
    public Niveau import_Niveau(int key){return new Niveau(key);}

    // Fonctions de Chargement (a remplir) -> utiliser les fonction d'import
    private void chargementEtudiants(){
        
    }
    private void chargementEnseignants(){
        
    }
    private void chargementNiveaux(){
        
    }    
    private void chargementClasses(){
        
    }
    private void chargementDisciplines(){
        
    }
    
    // Setters
    private void _setID(int a){
        ID = a;
    }
    private void _setNom(String a){
        nom = a;
    }
}
