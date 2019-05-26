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
    protected Discipline matiere;
    // Etudiant déja existant -> a completer
    public Enseignant(int id){
        super(id,0);
        // sortir toutes les info de la bdd
    }
    public Enseignant(int id, String Nom, String Prenom, Discipline M){
        super(id,Nom,Prenom,1);
        matiere = M;
    }
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
    
}
