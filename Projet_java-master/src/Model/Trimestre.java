/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author thebo
 */
public class Trimestre {
    
    private int ID;
    private int numero;
    private Date debut;
    private Date fin;
    private ArrayList<Bulletin> bulletins;
    
    public Trimestre(){
        ID = 0;
        numero = 0;
        debut = null;
        fin = null;
    }
    
    public Trimestre(int iD, int n, Date d, Date f){
        ID = iD;
        numero = n;
        debut = d;
        fin = f;
    }
    
    public int getID(){
        return ID;
    }
    
    public int getNumero(){
        return numero;
    }
    
    public Date getDebut(){
        return debut;
    }
    
    public Date getFin(){
        return fin;
    }
    
    public ArrayList<Bulletin> getBulletins(){
        return bulletins;
    }
    
    public void addClasses(Bulletin b){
        if(bulletins == null)
            bulletins = new ArrayList<>();
        
        bulletins.add(b);
    }
    
    public void removeAllBulletins(){
        bulletins.clear();
    }
}
