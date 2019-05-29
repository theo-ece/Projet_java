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
    
    private Discipline discipline;
    private Classe classe;
    private HashMap<Integer, Etudiant> etudiants;
    
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
    
    public Enseignant(int ID, String Nom, String Prenom, int type){
        super(ID, Nom, Prenom, type);
        discipline = null;
        classe = null;
    }
    
    public Discipline getDiscipline(){
        return discipline;
    }
    
    public void setDiscipline(Discipline discipline){
        this.discipline = discipline;
    }
    
    public Classe getClasse(){
        return classe;
    }
    
    public void setClasse(Classe classe){
        this.classe = classe;
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
    
    public void enseignement(){//correspond à enseignement dans la BDD au lieu de faire une classe "Enseignement"
    
    
    } 
    
}
