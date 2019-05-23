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
public class Niveau {
    private int ID;
    private String nom;
    protected HashMap<Integer, Classe> Classes;
    protected HashMap<Integer, Discipline> Disciplines;
    public Niveau(int iD, String N){
        ID=iD;
        nom=N;
    }
    public void test(){}
    public void run(String Path, int id){
        
    }
    public void chargementClasses(){
        
    }
    public void chargementDisciplines(){
        
    }    
    public void showDisciplines(){
        for (Integer key : Disciplines.keySet()) {
            System.out.println(" Disciplines -> ["+ key + "] : " + Disciplines.get(key));
        }
    }
    public void showClasses(){
        for (Integer key : Classes.keySet()) {
            System.out.println(" Classes -> ["+ key + "] : " + Classes.get(key));
        }
    }
}
