/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.util.*;

/**
 *
 * @author Flora
 */
public class Connexion {
    
    /** Attributs prives : connect, stmt, rset et rsetMeta */
    private final Connection connect;
    private final Statement stmt;
    private ResultSet rset;
    private ResultSetMetaData rsetMeta;
    
    
    /** Constructeur surcharge avec en parametre un String password
     * @param password
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException */
    public Connexion(String password) throws ClassNotFoundException, SQLException {
                
        try{ 
            //Chargement du driver "com.mysql.jdbc.Driver"
            Class.forName("com.mysql.jdbc.Driver");  
            
        }catch(ClassNotFoundException cnfe){ 
            //Message d'erreur
            System.out.println("Le driver n'a pas été trouvé."); 
        }

        //Création d'une connexion JDBC à la base 
        connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/java","root",password);

        //Création d'un ordre SQL
        stmt = connect.createStatement();
    }
    
    
    
    /** afficherTable : fonction permettant de recuperer l ensemble des elements de la table recue en parametres
     * @param table
     * @return 
     * @throws java.sql.SQLException */
    public ArrayList afficherTable(String table) throws SQLException {
        
        //Récupération de l'ordre de la requete
        rset = stmt.executeQuery("select * from " + table);

        //Récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        //Calcul du nombre de colonnes du résultat
        int nbColonne = rsetMeta.getColumnCount();

        //Création d'une ArrayList de String qui contiendra toutes les lignes de la requête
        ArrayList<String> liste = new ArrayList<>();
        
        //Création d'un String qui contiendra une ligne à la fois
        String champs = "";
        
        //Ajout de tous les champs du résultat dans l'ArrayList
        for (int i=0; i<nbColonne; i++) {
            if("".equals(champs)){
                champs = rsetMeta.getColumnLabel(i + 1);
            } else {
                champs = champs + " | " + rsetMeta.getColumnLabel(i + 1);
            }
        }

        //Ajout des champs de la ligne dans l'ArrayList
        liste.add(champs);
        
        //Tant qu'il reste une ligne 
        while (rset.next()) {
            
            //Ajout du premier champ
            champs = rset.getString(1); 
            
            //Concatenation des champs de la ligne
            for (int i=1; i<nbColonne; i++) {               
                champs = champs + " | " + rset.getString(i + 1);
            }

            //Ajout des champs de la ligne dans l'ArrayList
            liste.add(champs);
        }

        //Retourne l'ArrayList
        return liste;
    }  
    
    
    /** recuperer _champs : methode permettant de recuperer les champs d une table recue en parametres
     * @param table
     * @return 
     * @throws java.sql.SQLException */
    public ArrayList recuperer_champs(String table) throws SQLException {
        
        //Récupération de l'ordre de la requete
        rset = stmt.executeQuery("select * from " + table);

        //Récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        //Calcul du nombre de colonnes du résultat
        int nbColonne = rsetMeta.getColumnCount();

        //Création d'une ArrayList de String qui contiendra toutes les lignes de la requête
        ArrayList<String> champs = new ArrayList<>();       
        
        //Ajout de tous les champs du résultat dans l'ArrayList
        for (int i=0; i<nbColonne; i++)
            champs.add(rsetMeta.getColumnLabel(i + 1));
        
        //Retourne l'ArrayList
        return champs;
    }  
    
    
    /** ajouter_element : methode permettant d ajouter un element dans la table choisie par l utilisateur
     * Cette methode a en parametres la table en question, les champs de la tables et les nouvelles valeurs a ajouter
     * @param table
     * @param champs
     * @param ajout 
     * @throws java.sql.SQLException */
    public void ajouter_element(String table, ArrayList<String> champs, ArrayList<String> ajout) throws SQLException {
        
        //Déclarations de String
        String ch="", ch1="", rqt;
        
        //Récupération des champs
        for(int i=0; i<champs.size(); i++){
            if("".equals(ch)){
                ch = champs.get(i);
            } else {
                ch += ", " + champs.get(i);
            }
        }
        
        //Récupération des nouvelles valeurs
        for(int i=0; i<ajout.size(); i++){
            if("".equals(ch1)){
                ch1 = ajout.get(i);
            } else {
                ch1 += ", " + ajout.get(i);
            }
        }
        
        //Récupération de l'ordre de la requete
        rqt = "insert into " + table + "(" + ch + ") values(" + ch1 + ");";
        
        //Ajout de l'élément dans la table
        stmt.executeUpdate(rqt);

    }  
    
    
    /** supprimer_element : methode permettant de supprimer un element de la table choisie par l utilisateur
     * Cette methode a en parametres la table en question et l'Id de l element a supprimer
     * @param table
     * @param id 
     * @throws java.sql.SQLException */
    public void supprimer_element(String table, int id) throws SQLException {
        
        //Déclaration d'un String
        String rqt;
        
        //Récupération de l'ordre de la requete
        rqt = "delete from " + table + " where Id = " + id + ";";
        
        //Suppression de l'élément de la table
        stmt.executeUpdate(rqt);

    }
    
    
    /** modifier_element : methode permettant de modifier un element de la table choisie par l utilisateur
     * Cette methode a en parametres l'Id de l'élément à modifier, le champ a modifier et la nouvelle valeur du champ
     * @param table
     * @param id 
     * @param champ 
     * @param element 
     * @throws java.sql.SQLException */
    public void modifier_element(String table, int id, String champ, String element) throws SQLException {
        
        //Déclaration d'un String
        String rqt;
        
        //Récupération de l'ordre de la requete
        rqt = "update " + table + " set " + champ + " = "+ element + " where Id = " + id + ";";
        
        //Suppression de l'élément de la table
        stmt.executeUpdate(rqt);

    }

}
