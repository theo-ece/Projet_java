/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphisme;
import  java.sql.*;


/**
 *
 * @author lro
 */
public class Graphisme extends javax.swing.JFrame{

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        Connexion_gr test= new Connexion_gr();
        // TODO code application logic here
         
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Connexion_gr().setVisible(true);
                new Ecole_gr().setVisible(true);
                new Menu_gr().setVisible(true);
                new Etudiant_gr().setVisible(true);
                
                
            }
        });
   
    }
    
}
