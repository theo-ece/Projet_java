/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.HashMap;

/**
 *
 * @author thebo
 */
public class Bulletin {
    protected int ID;
    protected String appreciation;
    protected HashMap<Integer, Evaluation> eval;
    public Bulletin(int iD){
        ID=iD;
        /*
            Sortir de la bdd les note du gars et son appreciation
        */
    }
}
