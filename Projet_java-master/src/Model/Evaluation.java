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
    
    public Evaluation(){
        ID = 0;
        note = 0;
        appreciation = "";
    }
    
    public Evaluation(int ID, double note, String appreciation){
        this.ID = ID;
        this.note = note;
        this.appreciation = appreciation;
    }
    
    // Si l'eval existe
    public Evaluation(int iD){
        ID = iD;
        /*
            sortir de la BDD la note, l'appreciation, ID -> l'etudiant et le correcteur        
        */
    }
    
    public int getID(){
        return ID;
    }
    
    public double getNote(){
        return note;
    }
    
    public String getAppreciation(){
        return appreciation;
    }
    /*
    // Graphique
    public void showEval(){
        
    }
    
    // getteurs
    public double _get(){return note;}
    public String _get_appreciation(){return appreciation;}*/
}
