package Connexion;

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
     * @param login
     * @param base
     * @param serveur
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws Connexion.DataIncorrecte */
    public Connexion(String password, String login, String base, String serveur) throws ClassNotFoundException, SQLException, DataIncorrecte {
        
        //password = root ou rien
        //login = root
        //base = java
        //serveur = jdbc:mysql
        
        //Déclaration d'un objet String
        int localhost;
        
        try{ 
            //Chargement du driver "com.mysql.jdbc.Driver"
            Class.forName("com.mysql.jdbc.Driver");  
            
        }catch(ClassNotFoundException cnfe){ 
            //Message d'erreur
            System.out.println("Le driver n'a pas été trouvé."); 
        }
        
        //Vérification du login
        if(!"root".equals(login)){
           throw new DataIncorrecte("Login incorrect.");
        }
        
        //Vérification du mot de passe
        if((!"".equals(password)) && (!"root".equals(password))){
           throw new DataIncorrecte("Mot de passe incorrect.");
        } 
        
        //Vérification du serveur de la base
        if(!"jdbc:mysql".equals(serveur)){
           throw new DataIncorrecte("Serveur de la base incorrect.");
        }
        //Vérification de la base
        if(!"java".equals(base)){
           throw new DataIncorrecte("Nom de la base incorrecte.");
        }
        
        //Initialisation du localhost
        if("".equals(password)) localhost = 3306;
        else localhost = 8889;
        
        
        //Création d'une connexion JDBC à la base 
        connect = DriverManager.getConnection(serveur + "://localhost:" + localhost + "/" + base, login, password);

        //Création d'un ordre SQL
        stmt = connect.createStatement();
    }
    
    /**
     * @return  */
    public Statement getStatement(){
        return stmt;
    }   
    
    /**
     * @return  */
    public Connection getConnexion(){
        return connect;
    }   
    
    /**
     * @return  */
    public ResultSet getResultset(){
        return rset;
    }  
    
    /**
     * @return  */
    public ResultSetMetaData getResultsetmetadata(){
        return rsetMeta;
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
    
}