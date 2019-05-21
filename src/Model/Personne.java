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
public class Personne {
    protected int iD;
    protected String nom;
    protected String prenom;
    protected int type;
    public Personne(int ID, String Nom, String Prenom, int typ){
        iD=ID;
        nom= Nom;
        prenom = Prenom;
        type = typ;
    }
}
