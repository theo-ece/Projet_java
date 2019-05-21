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
    protected int ID;
    protected double note;
    protected String appreciation;
    public Evaluation(int iD, double n, String d){
        ID=iD;
        note = n;
        appreciation = d;
    }
}
