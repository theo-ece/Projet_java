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
public class Bulletin {
    
    protected int ID;
    protected String appreciation;
    private ArrayList<DetailBulletin> bulletins_detail;
    
    protected HashMap<Integer, Evaluation> eval;
    
    public Bulletin(){
        ID = 0;
        appreciation = "";
        bulletins_detail = null;
    }
    
    public Bulletin(int ID, String appreciation){
        this.ID = ID;
        this.appreciation = appreciation;
        bulletins_detail = null;
    }

    public Bulletin(int iD){
        ID = iD;
        /*
            Sortir de la bdd les note du gars et son appreciation
        */
    }
    
    public int getID(){
        return ID;
    }
    
    public ArrayList<DetailBulletin> getIDdetailB(){
        return bulletins_detail;
    }
    
    public String getAppreciation(){
        return appreciation;
    }
    
    public void addDetailsB(DetailBulletin d){
        if(bulletins_detail == null)
            bulletins_detail = new ArrayList<>();

        bulletins_detail.add(d);
    }
    
    public void removeAllDetailsB(){
        bulletins_detail.clear();
    }
    
}
