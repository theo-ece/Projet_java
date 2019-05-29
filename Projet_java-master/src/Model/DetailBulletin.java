/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author thebo
 */
public class DetailBulletin {
    
    private int ID;
    private String appreciation;
    private ArrayList<Evaluation> evaluations;
    
    public DetailBulletin(){
        ID = 0;
        appreciation = "";
        evaluations = null;
    }
    
    public DetailBulletin(int ID, String appreciation){
        this.ID = ID;
        this.appreciation = appreciation;
        evaluations = null;
    }
    
    public int getID(){
        return ID;
    }
    
    public String getAppreciation(){
        return appreciation;
    }
    
    public ArrayList<Evaluation> getEvaluations(){
        return evaluations;
    }
    
    public void addEvaluations(Evaluation e){
        if(evaluations == null)
            evaluations = new ArrayList<>();

        evaluations.add(e);
    }
    
    public void removeAllEvaluations(){
        evaluations.clear();
    }
    
}
