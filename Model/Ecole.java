/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Connexion.Connexion;
import DAO.*;
import Model.*;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thebo
 */
public class Ecole {
    
    
    private final String path;
    
    /** Attributs prives de la classe ID, nom, region, etudiants, enseignants et niveaux */
    private int ID;
    private String nom;
    private String region;
    protected HashMap<Integer, Etudiant> etudiants;
    protected HashMap<Integer, Enseignant> enseignants;
    protected ArrayList<Niveau> niveaux;
    
    // Attribut connect
    Connexion connect;
    
    
    /** Constructeur par defaut */
    public Ecole(){
        path = "";
        connect = null;
        ID = 0;
        nom = "";
        region = "";
    }
    
    /** Constructeur surchage avec trois parametres ID, nom et region
     * @param ID
     * @param nom
     * @param region */
    public Ecole(int ID, String nom, String region){
        path = "";
        this.ID = ID;
        this.nom = nom;
        this.region = region;
        connect = null;
    }
    
    /** Constructeur surchage avec deux parametres nom et region
     * @param nom
     * @param region */
    public Ecole(String nom, String region){
        path = "";
        ID = 0;
        this.nom = nom;
        this.region = region;
        connect = null;
    }
    
    /** getID : permettant d acceder a l attribut ID
     * @return  */
    public int getID(){
        return ID;
    }
    
    /** getNom : permettant d acceder a l attribut nom
     * @return  */
    public String getNom(){
        return nom;
    }
    
    /** getRegion : permettant d acceder a l attribut region
     * @return  */
    public String getRegion(){
        return region;
    }
    
    /** getNiveaux : permettant d acceder a l attribut niveaux
     * @return  */
    public ArrayList<Niveau> getNiveaux(){
        return niveaux;
    }
    
    /** addNiveaux : permettant d ajouter un niveau a l attribut niveaux
     * @param n */
    public void addNiveaux(Niveau n){
        if(niveaux == null)
            niveaux = new ArrayList<>();
        
        niveaux.add(n);
    }
    
    /** removeAllNiveaux : permettant de supprimer toutes les niveaux de l attribut niveaux */
    public void removeAllNiveaux(){
        niveaux.clear();
    }
    
    /** getEtudiants : permettant d acceder a l attribut etudiants
     * @return  */
    public HashMap<Integer, Etudiant> getEtudiants(){
        return etudiants;
    }
    
    /** addEtudiants : permettant d ajouter un etudiant a l attribut etudiants
     * @param e */
    public void addEtudiants(Etudiant e){
        if(etudiants == null)
            etudiants = new HashMap<>();
        
        etudiants.put(e.getID(), e);
    }
    
    /** removeAllEtudiats : permettant de supprimer tous les etudiants de l attribut etudiants */
    public void removeAllEtudiants(){
        etudiants.clear();
    }
    
    /** getEnseignants : permettant d acceder a l attribut enseignements
     * @return  */
    public HashMap<Integer, Enseignant> getEnseignants(){
        return enseignants;
    }
    
    /** addEnseignants : permettant d ajouter un enseignant a l attribut enseignants
     * @param e */
    public void addEnseignants(Enseignant e){
        if(enseignants == null)
            enseignants = new HashMap<>();
        
        enseignants.put(e.getID(), e);
    }
    
    /** removeAllEnseignants : permettant de supprimer tous les enseignements de l attribut enseignements */
    public void removeAllEnseignants(){
        enseignants.clear();
    }
    
    /** setConnexion : permettant de modifier l attribut connect
     * @param connect  */
    public void setConnexion(Connexion connect){
        this.connect = connect;
    }
    
    /** ajoutEcole : methode permettant d ajouter une ecole
     * @param connect */
    public void ajoutEcole(Connexion connect){
        
        //Création d'un objet EcoleDAO
        EcoleDAO ecole_dao = new EcoleDAO(connect);
        
        //Appel de la fonction d'ajout
        ecole_dao.ajouter(this);
    }
    
    
    public Ecole(String path){
        this.path=path;
        //showEcole(); 
    }  
    // Main de Ecole
    public void run(){
        String str="";
        do{
            str="";
            System.out.println("Entrer votre choix : ");
            System.out.println("    [1] Etudiants");
            System.out.println("    [2] Enseignants");
            System.out.println("    [3] Niveaux");
            System.out.println("Pour sortir : exit");            
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
            if("1".equals(str) || "2".equals(str) || "3".equals(str)){showRUN(Integer.valueOf(str));}
        }while(!"exit".equals(str));
    }
    
    // Fonctions d'appel du choix (done)
    public void showRUN(int choix){
        String str="";
        do{
            str="";
            System.out.println("Entrer votre choix : ");
            System.out.println("    [1] Afficher");
            System.out.println("    [2] Ajouter");
            System.out.println("    [3] Modifier");
            System.out.println("    [4] Supprimer");
            System.out.println("Pour sortir : exit");            
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
            if(null != str)switch (str) {
                case "1":
                    switch (choix) {
                        case 1:
                            showEtudiants();
                            break;
                        case 2:
                            showEnseignants();
                            break;
                        case 3:
                            showNiveaux();
                            break;
                        default:
                            break;
                    }
                    break;
                case "2":
                    switch (choix) {
                        case 1:
                            ajout_new_etudiant();
                            break;
                        case 2:
                            ajout_new_enseignant();
                            break;
                        case 3:
                            showEnseignants();
                            break;
                        default:
                            break;
                    }
                    break;
                case "3":
                    switch (choix) {
                        case 1:
                            
                            break;
                        case 2:
                            
                            break;
                        case 3:
                            
                            break;
                        default:
                            break;
                    }
                    break;
                case "4":
                    
                    switch (choix) {
                        case 1:
                          
                            break;
                        case 2:
                            
                            break;
                        case 3:
                            
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }while(!"exit".equals(str));
    }/*
    public void showADD(){
        
    }
    public void showERASE(){
        
    }
    
    public void showEcole(){                    // A faire -> choix de l'ecole lié a la BDD
        int id=0;
        String Name="";
        /*
            Le code
        */
        //_setNom(Name);
        //_setID(id);        
    //}   // A faire
    
    public void showEtudiants(){
        String str="";
        System.out.println("Etudiants: ");
        for(Entry<Integer,Etudiant> map : this.getEtudiants().entrySet())
            System.out.println(map.getValue().getID() + ". " + map.getValue().getNom() + " " + map.getValue().getPrenom());
        do{
            str="";
            System.out.println("ID a modifier :");
            System.out.println("exit pour sortir");                        
            Scanner sc = new Scanner(System.in);            
            str = sc.nextLine(); 
            try{
                recherche_etudiant(str);
            }catch(HashInexistant e){   
            
            }
            catch(HashExistant e){
                etudiants.get(Integer.valueOf(str)).run(path, Integer.valueOf(str),getNiveaux());
            }
        }while(!"exit".equals(str));
    }  
    
    
    public void showEnseignants(){
        String str="";
        System.out.println("Enseignants: ");
        for(Entry<Integer,Enseignant> map : this.getEnseignants().entrySet())
            System.out.println(map.getValue().getID() + ". " + map.getValue().getNom() + " " + map.getValue().getPrenom());
        do{
            str="";
            System.out.println("ID a modifier :");
            System.out.println("exit pour sortir");                        
            Scanner sc = new Scanner(System.in);            
            str = sc.nextLine(); 
            try{
                recherche_enseignant(str);
            }catch(HashInexistant e){   
            
            }
            catch(HashExistant e){
                enseignants.get(Integer.valueOf(str)).run(path, Integer.valueOf(str));
            }
        }while(!"exit".equals(str));
    }
    
    
    public void showNiveaux(){
        String str="";
        System.out.println("Niveaux: ");
        for(int i=0; i<niveaux.size(); i++)
            System.out.println("* " + niveaux.get(i).getNom());
        do{
            str="";
            System.out.println("ID a modifier :");
            System.out.println("exit pour sortir");                        
            Scanner sc = new Scanner(System.in);            
            str = sc.nextLine(); 
            try{
                recherche_niveau(str);
            }catch(HashInexistant e){   
            
            }
            catch(HashExistant e){
                niveaux.get(Integer.valueOf(str)).run(path, Integer.valueOf(str));
            }
        }while(!"exit".equals(str));
    } 
    
    // Fonctions de recherche dans la map (done)
    /*** UTILISER LES FONCTIONS DAO TROUVER
     * 
     * 
     * @param key
     * @throws HashInexistant
     * @throws HashExistant 
     *
     * */
    public void recherche_etudiant(String key) throws HashInexistant, HashExistant{
        try{
            //etudiants.get(Integer.valueOf(key)).test();
            throw new HashExistant();
        }
        catch(NullPointerException e){
            throw new HashInexistant("Etudiant " + key + " non existant");
        }
    }
    public void recherche_enseignant(String key) throws  HashInexistant, HashExistant{
        try{
            //enseignants.get(Integer.valueOf(key)).test();
            throw new HashExistant();
        }
        catch(NullPointerException e){
            throw new HashInexistant("Enseignant " + key + " non existant");
        }
    }
    public void recherche_niveau(String key) throws  HashInexistant, HashExistant{  
        try{
            //enseignants.get(Integer.valueOf(key)).test();
            throw new HashExistant();
        }
        catch(NullPointerException e){
            throw new HashInexistant("Niveau " + key + " non existant");
        }
    }/*
    public void recherche_classe(String key) throws HashExistant, HashInexistant{    // question 1.3
        try{
            classes.get(Integer.valueOf(key)).test();
            throw new HashExistant();
        }
        catch(NullPointerException e){
            throw new HashInexistant("Niveau " + key + " non existant");
        }
    }
    public void recherche_discipline(String key) throws HashExistant, HashInexistant{
        try{
            Disciplines.get(Integer.valueOf(key));
            throw new HashExistant();
        }
        catch(NullPointerException e){
            throw new HashInexistant("Niveau " + key + " non existant");
        }
    }*/
    
    // Fonction d'ajout
    
    public void ajout_new_classe(){ 
        
    }
    public void ajout_new_etudiant(){
        //BDD
        String nom, prenom, niveau, classe;
        
        System.out.print("Nom: ");
        Scanner sc1 = new Scanner(System.in);
        nom = sc1.nextLine();
        System.out.print("Prenom: ");
        Scanner sc2 = new Scanner(System.in);
        prenom = sc2.nextLine();
        //affiche classe
        for(int i=0; i<getNiveaux().size(); i++){
            System.out.println(getNiveaux().get(i).getID() + ". " + getNiveaux().get(i).getNom());
        }
        
        System.out.print("Niveau: ");
        Scanner sc3 = new Scanner(System.in);
        niveau = sc3.nextLine();
        
        for(int i=0; i<getNiveaux().size(); i++){
            if(getNiveaux().get(i).getNom().equals(niveau)){
                System.out.println("Niveau " + getNiveaux().get(i));
                for(int j=0; j<getNiveaux().get(i).getClasses().size(); j++)
                    
                    System.out.println("* " + getNiveaux().get(i).getClasses().get(j).getID() + ". " + getNiveaux().get(i).getClasses().get(j).getNom());
            }
        }
        
        System.out.print("Classe: ");
        Scanner sc4 = new Scanner(System.in);
        classe = sc4.nextLine();
        
        Etudiant etudiant = new Etudiant(nom,prenom,0);
        PersonneDAO etudiant_dao = new PersonneDAO(connect);
        etudiant_dao.ajouter(etudiant);
        etudiants.clear();
        enseignants.clear();
        Chargement charge = new Chargement(connect);
        charge.chargementPersonnes();
        
        for(int i=0; i<getNiveaux().size(); i++)
            for(int j=0; j<getNiveaux().get(i).getClasses().size(); j++)
                if(getNiveaux().get(i).getClasses().get(j).getNom() == classe){
                    Inscription inscription = new Inscription(getNiveaux().get(i).getClasses().get(j),etudiant);
                    InscriptionDAO inscription_dao = new InscriptionDAO(connect);
                    inscription_dao.ajouter(inscription);
                }
        boolean test=false;
        for(Entry<Integer,Etudiant> map : getEtudiants().entrySet())
            if(map.getValue().nom==nom && map.getValue().prenom==prenom){
                test = true;
            } 
        
        if(test) System.out.println("Ajout réussi.");
        else System.out.println("Ajout impossible.");
    }
    public void ajout_new_discipline(){
        
    }
    public void ajout_new_enseignant(){
        //BDD
        PersonneDAO enseignant_dao = new PersonneDAO(connect);
        //etudiant_dao.ajouter(/*Nouvel Enseignant*/);
    }
    public void ajout_new_niveau(){
        //BDD
        NiveauDAO niveau_dao = new NiveauDAO(connect);
        //niveau_dao.ajouter(/*Nouveau Niveau*/);
        
    }
    
    // Fonction de suppression
    /*public void erase_classe(){
        String key="";
        System.out.println("ID de la Classe a supprimer : ");
        Scanner sc = new Scanner(System.in);
        key = sc.nextLine();
        try{
            recherche_classe(key);
        }catch(HashInexistant e){
            System.out.println("La classe n'existe pas.");
        }catch(HashExistant e){
            classes.remove(Integer.valueOf(key));
        }
    }  */             // + modif BDD à faire & graph
    public void erase_etudiant(){
        String key="";
        System.out.println("ID de la Classe a supprimer : ");
        Scanner sc = new Scanner(System.in);
        key = sc.nextLine();
        try{
            recherche_etudiant(key);
        }catch(HashInexistant e){
            System.out.println("La classe n'existe pas.");
        }catch(HashExistant e){
            // A supprimer dans BDD
            PersonneDAO etudiant_dao = new PersonneDAO(connect);
            etudiant_dao.supprimer(etudiant_dao.trouver(Integer.valueOf(key)));
            
            etudiants.remove(Integer.valueOf(key));
            
        }
    }             // + modif BDD à faire / Graphique
    
    /*
    public void erase_discipline(){
        String key="";
        System.out.println("ID de la discipline a supprimer : ");
        Scanner sc = new Scanner(System.in);
        key = sc.nextLine();
        try{
            recherche_discipline(key);
        }catch(HashInexistant e){
            System.out.println("La discipline n'existe pas.");
        }catch(HashExistant e){
            Disciplines.remove(Integer.valueOf(key));
            // A supprimer dans BDD
        }
    }        */   // + modif BDD à faire / Graphique    
    public void erase_niveau(){
        String key="";
        System.out.println("ID du niveau a supprimer : ");
        Scanner sc = new Scanner(System.in);
        key = sc.nextLine();
        try{
            recherche_niveau(key);
        }catch(HashInexistant e){
            System.out.println("Le niveau n'existe pas.");
        }catch(HashExistant e){
            // A supprimer dans BDD
            NiveauDAO niveau_dao = new NiveauDAO(connect);
            niveau_dao.supprimer(niveau_dao.trouver(Integer.valueOf(key)));
            
            niveaux.remove(Integer.valueOf(key));
        }
    }               // + modif BDD à faire / Graphique 
    public void erase_enseignant(){
        String key="";
        System.out.println("ID du prof a supprimer : ");
        Scanner sc = new Scanner(System.in);
        key = sc.nextLine();
        try{
            recherche_enseignant(key);
        }catch(HashInexistant e){
            System.out.println("Le prof n'existe pas.");
        }catch(HashExistant e){
            // A supprimer dans BDD
            PersonneDAO enseignant_dao = new PersonneDAO(connect);
            enseignant_dao.supprimer(enseignant_dao.trouver(Integer.valueOf(key)));
            
            enseignants.remove(Integer.valueOf(key));
            
        }
    }           // + modif BDD à faire / Graphique 
    
    // Fonction d'import
    /*
    public Discipline import_discipline(int key, String Nom){return new Discipline(key,Nom);}
    public Classe import_classe(int key){return new Classe(key);}
    public Etudiant import_etudiant(int key){return new Etudiant(key);}
    public Enseignant import_enseignant(int key){return new Enseignant(key);}
    public Niveau import_Niveau(int key){return new Niveau(key);}
    
    */
    
    // Setters
    private void _setID(int a){
        ID = a;
    }
    private void _setNom(String a){
        nom = a;
    }
}
