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
public class Trimestre {
    protected int ID;
    protected int numero;
    protected String debut;
    protected String fin;
    public Trimestre(int iD, int n, String d, String f){
        ID=iD;
        numero = n;
        debut = d;
        fin = f;
    }
}
