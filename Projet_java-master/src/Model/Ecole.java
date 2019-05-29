/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Connexion.Connexion;
import DAO.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thebo
 */
public class Ecole {
    
    private final String path;
    
    private int ID;
    private String nom;
    private String region;
    
    private Connexion connect;
    
    protected HashMap<Integer, Etudiant> etudiants;
    protected HashMap<Integer, Enseignant> enseignants;
    protected ArrayList<Niveau> niveaux;
    
    public Ecole(){
        path = "";
        connect = null;
        ID = 0;
        nom = "";
        region = "";
    }
    
    public Ecole(int ID, String nom, String region){
        path = "";
        this.ID = ID;
        this.nom = nom;
        this.region = region;
        connect = null;
    }
    
    public int getID(){
        return ID;
    }
    
    public String getNom(){
        return nom;
    }
    
    public String getRegion(){
        return region;
    }
    
    public ArrayList<Niveau> getNiveaux(){
        return niveaux;
    }
    
    public void addNiveaux(Niveau n){
        if(niveaux == null)
            niveaux = new ArrayList<>();
        
        niveaux.add(n);
    }
    
    public void removeAllNiveaux(){
        niveaux.clear();
    }
    
    public HashMap<Integer, Etudiant> getEtudiants(){
        return etudiants;
    }
    
    public void addEtudiants(Etudiant e){
        if(etudiants == null)
            etudiants = new HashMap<>();
        
        etudiants.put(e.getID(), e);
    }
    
    public void removeAllEtudiants(){
        etudiants.clear();
    }
    
    public HashMap<Integer, Enseignant> getEnseignants(){
        return enseignants;
    }
    
    public void addEnseignants(Enseignant e){
        if(enseignants == null)
            enseignants = new HashMap<>();
        
        enseignants.put(e.getID(), e);
    }
    
    public void removeAllEnseignants(){
        enseignants.clear();
    }
    
    
    public Ecole(String path, Connexion connect){
        this.path=path;
        showEcole();
        chargementTrimestres(connect);
        chargementEvaluations(connect);
        chargementDetailsBulletins(connect);
        chargementBulletins(connect);
        chargementDisciplines(connect);
        chargementAnneeScolaires(connect);
        //chargementPersonnes(connect);
        chargementClasses(connect);
        chargementNiveaux(connect);
        chargementEcoles(connect);
    }  
    // Main de Ecole
    public void run(){
        String str="";
        do{
            str="";
            System.out.println("Entrer votre choix : ");
            System.out.println("    [1] Supprimer un élément");
            System.out.println("    [2] Ajouter un élément");
            System.out.println("    [3] Modifier un élément");
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
            System.out.println("    etudiants");
            System.out.println("    enseignants");
            System.out.println("    niveaux");
            System.out.println("    classes");
            System.out.println("    disciplines");
            System.out.println("Pour sortir : exit");            
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
            if(null != str)switch (str) {
                case "etudiants":
                    switch (choix) {
                        case 1:
                            erase_etudiant();
                            break;
                        case 2:
                            ajout_new_etudiant();
                            break;
                        case 3:
                            showEtudiants();
                            break;
                        default:
                            break;
                    }
                    break;
                case "enseignants":
                    switch (choix) {
                        case 1:
                            erase_enseignant();
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
                case "niveaux":
                    switch (choix) {
                        case 1:
                            erase_niveau();
                            break;
                        case 2:
                            ajout_new_niveau();
                            break;
                        case 3:
                            showNiveaux();
                            break;
                        default:
                            break;
                    }
                    break;
                case "classes":
                    showClasses();
                    switch (choix) {
                        case 1:
                            erase_classe();
                            break;
                        case 2:
                            ajout_new_classe();
                            break;
                        case 3:
                            showClasses();
                            break;
                        default:
                            break;
                    }
                    break;
                case "disciplines":
                    
                    switch (choix) {
                        case 1:
                            erase_discipline();
                            break;
                        case 2:
                            ajout_new_discipline();
                            break;
                        case 3:
                            showDisciplines();
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }while(!"exit".equals(str));
    }
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
    }   // A faire
    
    public void showEtudiants(){
        String str="";
        etudiants.keySet().forEach((key) -> {
            System.out.println(" Etudiants -> ["+ key + "] : " + etudiants.get(key));
        });
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
                //etudiants.get(Integer.valueOf(str)).run(path, Integer.valueOf(str));
            }
        }while(!"exit".equals(str));
    }    
    public void showEnseignants(){
        enseignants.keySet().forEach((key) -> {
            System.out.println(" Enseignants -> ["+ key + "] : " + enseignants.get(key));
        });
        String str="";
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
                //enseignants.get(Integer.valueOf(str)).run(path, Integer.valueOf(str));
            }
        }while(!"exit".equals(str));
    }
    public void showNiveaux(){
        /*niveaux.keySet().forEach((key) -> {
            System.out.println(" Niveaux -> ["+ key + "] : " + niveaux.get(key));
        });*/
        String str="";
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
                //niveaux.get(Integer.valueOf(str)).run(path, Integer.valueOf(str));
            }
        }while(!"exit".equals(str));
    }  
    public void showClasses(){
        /*classes.keySet().forEach((key) -> {
            System.out.println("Classe -> ["+ key + "] : " + classes.get(key));
        });*/
        String str="";
        do{
            str="";
            System.out.println("ID a modifier :");
            System.out.println("exit pour sortir");                        
            Scanner sc = new Scanner(System.in);            
            str = sc.nextLine(); 
            try{
                recherche_classe(str);
            }catch(HashInexistant e){   
            
            }
            catch(HashExistant e){
                //classes.get(Integer.valueOf(str)).run(path, Integer.valueOf(str));
            }//
        }while(!"exit".equals(str));
    } 
    public void showDisciplines(){
        /*for (Integer key : Disciplines.keySet()) {
            System.out.println(" Disciplines -> ["+ key + "] : " + Disciplines.get(key));
        }*/
        String str="";
        do{
            str="";
            System.out.println("ID a modifier :");
            System.out.println("Pour sortir : exit");            
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine(); 
            try{
                recherche_discipline(str);
            }catch(HashInexistant e){   
            
            }
            catch(HashExistant e){
                //Disciplines.get(Integer.valueOf(str)).run();
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
     */
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
            enseignants.get(Integer.valueOf(key)).test();
            throw new HashExistant();
        }
        catch(NullPointerException e){
            throw new HashInexistant("Niveau " + key + " non existant");
        }
    }
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
    }
    
    // Fonction d'ajout
    
    public void ajout_new_classe(){
        
    }
    public void ajout_new_etudiant(){
        
    }
    public void ajout_new_discipline(){
        
    }
    public void ajout_new_enseignant(){
        
    }
    public void ajout_new_niveau(){
        
    }
    
    // Fonction de suppression
    public void erase_classe(){
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
    }               // + modif BDD à faire & graph
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
            etudiants.remove(Integer.valueOf(key));
            // A supprimer dans BDD
        }
    }             // + modif BDD à faire / Graphique
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
    }           // + modif BDD à faire / Graphique    
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
            niveaux.remove(Integer.valueOf(key));
            // A supprimer dans BDD
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
            enseignants.remove(Integer.valueOf(key));
            // A supprimer dans BDD
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
    
    // Fonctions de Chargement
    /** chargementPersonnes : methode permettant de charger les elements Enseignant et Etudiant
     * @param connect */
    public void chargementPersonnes(Connexion connect){
        
        //Création d'un objet Objet DAO
        DAO<Personne> personne_dao = new PersonneDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = Chargement.charger(connect,"personne");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                Personne personne = personne_dao.trouver(liste_Id.get(i));
                
                //Si c'est un Enseignant, alors création d'un Enseignant
                if(personne.getType() == 1){
                    Enseignant enseignant = (Enseignant) personne;
                    
                } else { 
                    //Sinon si c'est un Etudiant, alors création d'un Etudiant
                    Etudiant etudiant = (Etudiant) personne;
                }
                
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ecole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** chargementNiveaux : methode permettant de charger les elements Niveaux
     * @param connect */
    public void chargementNiveaux(Connexion connect){
        
        //Création d'un objet Objet DAO
        DAO<Niveau> niveau_dao = new NiveauDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = Chargement.charger(connect,"niveau");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                Niveau niveau = niveau_dao.trouver(liste_Id.get(i));     
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ecole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    /** chargementAnneeScolaires : methode permettant de charger les elements AnneeScolaire
     * @param connect */
    public void chargementAnneeScolaires(Connexion connect){
        
        //Création d'un objet Objet DAO
        DAO<AnneeScolaire> annee_scolaire_dao = new AnneeScolaireDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = Chargement.charger(connect,"anneescolaire");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                AnneeScolaire annee_scolaire = annee_scolaire_dao.trouver(liste_Id.get(i));
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ecole.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    /** chargementClasses : methode permettant de charger les elements Classe
     * @param connect */
    public void chargementClasses(Connexion connect){
        
        //Création d'un objet Objet DAO
        DAO<Classe> classe_dao = new ClasseDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = Chargement.charger(connect,"classe");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                Classe classe = classe_dao.trouver(liste_Id.get(i));
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ecole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** chargementEvaluations : methode permettant de charger les elements Evaluations
     * @param connect */
    public void chargementEvaluations(Connexion connect){
        
        //Création d'un objet Objet DAO
        DAO<Evaluation> evaluation_dao = new EvaluationDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = Chargement.charger(connect,"evaluation");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                Evaluation evaluation = evaluation_dao.trouver(liste_Id.get(i));
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ecole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** chargementDisciplines : methode permettant de charger les elements Discipline
     * @param connect */
    public void chargementDisciplines(Connexion connect){
        
        //Création d'un objet Objet DAO
        DAO<Discipline> discipline_dao = new DisciplineDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = Chargement.charger(connect,"discipline");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                Discipline discipline = discipline_dao.trouver(liste_Id.get(i));
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ecole.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    /** chargementTrimestres : methode permettant de charger les elements Trimestre
     * @param connect */
    public void chargementTrimestres(Connexion connect){
        
        //Création d'un objet Objet DAO
        DAO<Trimestre> trimestre_dao = new TrimestreDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = Chargement.charger(connect,"trimestre");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                Trimestre trimestre = trimestre_dao.trouver(liste_Id.get(i));
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ecole.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    /** chargementBulletins : methode permettant de charger les elements Bulletin
     * @param connect */
    public void chargementBulletins(Connexion connect){
        
        //Création d'un objet Objet DAO
        DAO<Bulletin> bulletin_dao = new BulletinDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = Chargement.charger(connect,"bulletin");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                Bulletin bulletin = bulletin_dao.trouver(liste_Id.get(i));
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ecole.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    /** chargementDetailsBulletins : methode permettant de charger les elements DetailsBulletin
     * @param connect */
    public void chargementDetailsBulletins(Connexion connect){
        
        //Création d'un objet Objet DAO
        DAO<DetailBulletin> detail_bulletin_dao = new DetailBulletinDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = Chargement.charger(connect,"detail_bulletin");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                DetailBulletin detail_bulletin = detail_bulletin_dao.trouver(liste_Id.get(i));
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ecole.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    /** chargementEcole : methode permettant de charger les elements Ecole
     * @param connect */
    public void chargementEcoles(Connexion connect){
        
        //Création d'un objet Objet DAO
        DAO<Ecole> ecole_dao = new EcoleDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = Chargement.charger(connect,"ecole");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                Ecole ecole = ecole_dao.trouver(liste_Id.get(i));
                /*if(ecole.getNiveaux() != null)
                    for(Map.Entry map : ecole.getNiveaux().entrySet())
                        System.out.println("ID : " + ecole.getID() + " Niveau : " + map.getValue());*/
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ecole.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    // Setters
    private void _setID(int a){
        ID = a;
    }
    private void _setNom(String a){
        nom = a;
    }
}
