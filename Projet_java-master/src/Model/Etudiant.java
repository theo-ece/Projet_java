/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author thebo
 */
public class Etudiant extends Personne{
    
    private Niveau niveau;
    private Classe classe;
    private ArrayList<Discipline> disciplines;
    
    // Creation nouvelle personne
    public Etudiant(int ID, String Nom, String Prenom, int type){
        super(ID, Nom, Prenom, type);
        niveau = null;
        classe = null;
    }
    
    public Niveau getNiveau(){
        return niveau;
    }
    
    public Classe getClasse(){
        return classe;
    }
    
    public void setNiveau(Niveau niveau){
        this.niveau = niveau;
    }
    
    public void setClasse(Classe classe){
        this.classe = classe;
    }
    
    public ArrayList<Discipline> getDisciplines(){
        return disciplines;
    }
    
    public void addDisciplines(Discipline d){
        if(disciplines == null)
            disciplines = new ArrayList<>();
        
        disciplines.add(d);
    }
    
    public void removeAllDisciplines(){
        disciplines.clear();
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
    
    public void inscription(){ //Remplace la table INSCRIPTION
        
    }
    
}
