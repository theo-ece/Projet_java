/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author thebo
 */
public class Evaluation {
    private int ID;
    private double note;
    private String appreciation;
    protected int ID_etudiant;
    protected int ID_correcteur;
    // Si l'eval existe
    public Evaluation(int iD){
        ID=iD;
        /*
            sortir de la BDD la note, l'appreciation, ID -> l'etudiant et le correcteur        
        */
    }
    // Nouvelle eval
    public Evaluation(int iD, double grade, String N, int E, int C){    // Creation d'une nouvelle note
        ID = iD;
        note = grade;
        appreciation = N;
        ID_etudiant = E;
        ID_correcteur = C;
        /*
        associer dans la BDD la nouvelle note à l'enseignant et à l'eleve avec verification qu'ils existent.
        */
    }
    // Graphique
    public void showEval(){
        
    }
    
    // getteurs
    public double _get(){return note;}
    public String _get_appreciation(){return appreciation;}
}
