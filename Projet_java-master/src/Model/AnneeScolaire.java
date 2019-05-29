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
public class AnneeScolaire {
    
    private final int ID;
    private final String date;
    private ArrayList<Trimestre> trimestres;
    private ArrayList<Classe> classes;
    
    public AnneeScolaire(){
        ID = 0;
        date = null;
        trimestres = null;
        classes = null;
    }
    
    public AnneeScolaire(int ID, String date){
        this.ID = ID;
        this.date = date;
        trimestres = null;
        classes = null;
    }
    
    public int getID(){
        return ID;
    }
    
    public String getDate(){
        return date;
    }
    
    public ArrayList<Trimestre> getTrimestres(){
        return trimestres;
    }
    
    public void addTrimestres(Trimestre t){
        if(trimestres == null)
            trimestres = new ArrayList<>();
        
        trimestres.add(t);
    }
    
    public void removeAllTrimestres(){
        trimestres.clear();
    }
    
    public ArrayList<Classe> getClasses(){
        return classes;
    }
    
    public void addClasses(Classe c){
        if(classes == null)
            classes = new ArrayList<>();
        
        classes.add(c);
    }
    
    public void removeAllClasses(){
        classes.clear();
    }
}

