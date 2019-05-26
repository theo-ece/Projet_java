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
    private Niveau annee;
    private AnneeScolaire promotion;
    // Etudiant déja existant
    public Etudiant(int id){
        super(id,0);
        // chercher dans bdd la promotion et l'annee
    }
    // Creation nouvelle personne
    public Etudiant(int id, String Nom, String Prenom, Niveau P, AnneeScolaire A){
        super(id,Nom,Prenom,0);
        annee = P;
        promotion = A;
    }
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
    
}
