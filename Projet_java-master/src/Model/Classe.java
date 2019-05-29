/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author thebo
 */
public class Classe {
    
    private int ID;
    private String nom;
    private HashMap<Integer, Etudiant> etudiants;
    
    public Classe(){
        ID = 0;
        nom = "";
        etudiants = null;
    }
    
    public Classe(int ID, String nom){
        this.ID = ID;
        this.nom = nom;
        etudiants = null;
    }
    
    public int getID(){
        return ID;
    }
    
    public String getNom(){
        return nom;
    }
    
    public HashMap<Integer, Etudiant> getEtudiants(){
        return etudiants;
    }
    
    public void addEtudiants(Etudiant e){
        if(etudiants == null)
            etudiants = new HashMap<>();
        
        etudiants.put(e.getID(), e);
    }
    
    public void removeAllEtudiants(){
        etudiants.clear();
    }

    /*

    //A completer
    public Classe(int iD){
        ID=iD;
        // Extraire nom , discipline , niveau , prof de la BDD
        chargement_etudiants(iD);
    }
    // Creation d'une nouvelle classe
    public Classe(int iD, String N, Niveau lvl, Enseignant Prof, ArrayList<Discipline> matiere){
        etudiants = new HashMap<>();
        ID=iD;
        nom=N;
        niveau=lvl;
        prof=Prof;
        disciplines=matiere;
    }
    
    
    public void test(){}
    public void run(String path, int id){
        
    }
    
    // Fonction de chargement -> a faire
    public void chargement_etudiants(int key){
        etudiants = new HashMap<>();
    }
    
    // Fonction de recherche
    public void recherche_etudiant(String key) throws HashExistant, HashInexistant{    // question 1.3
        try{
            etudiants.get(Integer.valueOf(key)).test();
            throw new HashExistant();
        }
        catch(NullPointerException e){
            throw new HashInexistant("Enseignant " + key + " non existant");
        }
    }
        
    // Fonctions d'ajout
    public void ajout_etudiant(int ID, Etudiant a){
        String key = ""+ID;
        try{
            recherche_etudiant(key);
        }catch(HashInexistant e){
            etudiants.put(ID, a);
        }catch(HashExistant e){
            
        }
    }   // + modification de la BDD à faire / Graphique
    
    // Fonction de suppression    
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
    }   // + modif BDD à faire / Graphique
    
    // Fonction d'import de la BDD    
    public Etudiant import_etudiant(int key){return new Etudiant(key);}
    */
}
