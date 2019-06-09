/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Flora
 */
public class Chargement {
    
    /** Attributs prives de la classe : connect, ecole, annees_scolarires */
    private final Connexion connect;
    private ArrayList<Ecole> ecoles;
    private ArrayList<AnneeScolaire> annees_scolaires;
    private ArrayList<Inscription> inscriptions;
    private ArrayList<Enseignement> enseignements;
    
    
    /** Contructeur prive surcharge avec un parametre connect
     * @param connect */
    public Chargement(Connexion connect){
        this.connect = connect;
        ecoles = null;
        annees_scolaires = null;
    }
    
    /** getEcoles : getter permettant d acceder a l ArrayList des ecoles
     * @return  */
    public ArrayList<Ecole> getEcoles(){
        return ecoles;
    }
    
    /** addEcoles : methode permettant d ajouter une ecole  a l ArrayList des ecoles
     * @param e */
    public void addEcoles(Ecole e){
        if(ecoles == null)
            ecoles = new ArrayList<>();
        
        ecoles.add(e);
    }
    
    /** getAnnees : getter permettant d acceder a l ArrayList des ecoles
     * @return  */
    public ArrayList<AnneeScolaire> getAnnees(){
        return annees_scolaires;
    }
    
    /** addAnnees : methode permettant d ajouter une ecole  a l ArrayList des ecoles
     * @param a */
    public void addAnnees(AnneeScolaire a){
        if(annees_scolaires == null)
            annees_scolaires = new ArrayList<>();
        
        annees_scolaires.add(a);
    }
    
    /** getInscription : getter permettant d acceder a l ArrayList des inscriptions
     * @return  */
    public ArrayList<Inscription> getInscription(){
        return inscriptions;
    }
    
    /** addInscriptions : methode permettant d ajouter une ecole  a l ArrayList des inscriptions
     * @param i */
    public void addInscriptions(Inscription i){
        if(inscriptions == null)
            inscriptions = new ArrayList<>();
        
        inscriptions.add(i);
    }
    
    /** getEnseignement : getter permettant d acceder a l ArrayList des enseignements
     * @return  */
    public ArrayList<Enseignement> getEnseignement(){
        return enseignements;
    }
    
    /** addEnseignement : methode permettant d ajouter une ecole  a l ArrayList des enseignements
     * @param e */
    public void addEnseignement(Enseignement e){
        if(enseignements == null)
            enseignements = new ArrayList<>();
        
        enseignements.add(e);
    }
    
    /** charger : methode static permet de retourner un ArrayList contenant les Id de chaque objet d'une table
     * @param table
     * @return 
     * @throws java.sql.SQLException */
    public ArrayList<Integer> charger(String table) throws SQLException{
        
        //Création d'un Objet ResultSet
        ResultSet rset = connect.getConnexion().createStatement().executeQuery("select * from " + table);
        
        //Création d'un objet ArrayList
        ArrayList<Integer> liste_Id = new ArrayList<>();
        
        if(rset.first()){
            
            //Ajout des Id dans l'ArrayList
            liste_Id.add(rset.getInt("Id"));
            
            //Ajout tant qu'il y a une ligne
            while(rset.next())
                liste_Id.add(rset.getInt("Id"));
        }
        
        //Le programme retourne l'ArrayList
        return liste_Id;
    }
    
    
    // Fonctions de Chargement
    /** chargementPersonnes : methode permettant de charger les elements Enseignant et Etudiant */
    public void chargementPersonnes(){
        
        //Création d'un objet Objet DAO
        DAO<Personne> personne_dao = new PersonneDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = charger("personne");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                Personne personne = personne_dao.trouver_et_charge(liste_Id.get(i));
                
                //Si c'est un Enseignant, alors création d'un Enseignant
                if(personne.getType() == 1){
                    Enseignant enseignant = (Enseignant) personne;
                    /*System.out.println("\nEnseignant " + enseignant.getNom() + " " + enseignant.getPrenom());
                    System.out.println("* Classe : " + enseignant.getClasse().getNom() + "\n* Discipline: " + enseignant.getDiscipline().getNom());
                    System.out.println("* Eleves: ");
                    if(enseignant.getEtudiants() != null)
                        for(Map.Entry map : enseignant.getEtudiants().entrySet())
                            System.out.println(map.getKey() + ", " + map.getValue());*/
                    
                } else { 
                    //Sinon si c'est un Etudiant, alors création d'un Etudiant
                    Etudiant etudiant = (Etudiant) personne;
                    /*System.out.println("\nEtudiant " + etudiant.getNom() + " " + etudiant.getPrenom());
                    if(etudiant.getDisciplines() != null)
                        for(Discipline d : etudiant.getDisciplines())
                            System.out.println("* Disciplines : " + d.getNom());*/
                }
                
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ecole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /** chargementNiveaux : methode permettant de charger les elements Niveaux */
    public void chargementNiveaux(){
        
        //Création d'un objet Objet DAO
        DAO<Niveau> niveau_dao = new NiveauDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = charger("niveau");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                Niveau niveau = niveau_dao.trouver_et_charge(liste_Id.get(i));
                /*System.out.println("\nNiveau " + niveau.getNom());
                if(niveau.getClasses() != null)
                    for(Classe c : niveau.getClasses())
                        System.out.println("* Classe " + c.getNom());*/
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ecole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    /** chargementAnneeScolaires : methode permettant de charger les elements AnneeScolaire */
    public void chargementAnneeScolaires(){
        
        //Création d'un objet Objet DAO
        DAO<AnneeScolaire> annee_scolaire_dao = new AnneeScolaireDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = charger("anneescolaire");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                AnneeScolaire annee_scolaire = annee_scolaire_dao.trouver_et_charge(liste_Id.get(i));
                
                //Ajout de l'Ecole dans l'ArrayList de Chargement
                this.addAnnees(annee_scolaire);
                
                /*System.out.println("\nAnnee scolaire " + annee_scolaire.getDate());
                if(annee_scolaire.getTrimestres() != null)
                    for(Trimestre t : annee_scolaire.getTrimestres())
                        System.out.println("* Trimestre " + t.getNumero() + ", " + t.getDebut() + " - " + t.getFin());
                if(annee_scolaire.getClasses()!= null)
                    for(Classe c : annee_scolaire.getClasses())
                        System.out.println("* Classe " + c.getNom());*/
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ecole.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    
    /** chargementClasses : methode permettant de charger les elements Classe */
    public void chargementClasses(){
        
        //Création d'un objet Objet DAO
        DAO<Classe> classe_dao = new ClasseDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = charger("classe");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                Classe classe = classe_dao.trouver_et_charge(liste_Id.get(i));
                /*System.out.println("\nClasse " + classe.getNom());
                if(classe.getEtudiants() != null)
                    for(Map.Entry e : classe.getEtudiants().entrySet())
                        System.out.println("* Etudiant " + e.getKey());*/
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ecole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /** chargementEvaluations : methode permettant de charger les elements Evaluations */
    public void chargementEvaluations(){
        
        //Création d'un objet Objet DAO
        DAO<Evaluation> evaluation_dao = new EvaluationDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = charger("evaluation");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                Evaluation evaluation = evaluation_dao.trouver_et_charge(liste_Id.get(i));
                //System.out.println("Evaluation " + evaluation.getNote() + " - " + evaluation.getAppreciation());
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ecole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /** chargementDisciplines : methode permettant de charger les elements Discipline */
    public void chargementDisciplines(){
        
        //Création d'un objet Objet DAO
        DAO<Discipline> discipline_dao = new DisciplineDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = charger("discipline");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                Discipline discipline = discipline_dao.trouver_et_charge(liste_Id.get(i));
                //System.out.println("Discipline " + discipline.getNom());
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ecole.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    
    /** chargementTrimestres : methode permettant de charger les elements Trimestre */
    public void chargementTrimestres(){
        
        //Création d'un objet Objet DAO
        DAO<Trimestre> trimestre_dao = new TrimestreDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = charger("trimestre");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                Trimestre trimestre = trimestre_dao.trouver_et_charge(liste_Id.get(i));
                /*System.out.println("\nTrimestre " + trimestre.getNumero() + ", " + trimestre.getDebut() + " - " + trimestre.getFin());
                if(trimestre.getBulletins() != null)
                    for(Bulletin b : trimestre.getBulletins())
                        System.out.println("* Bulletin " + b.getAppreciation());*/
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ecole.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    
    /** chargementBulletins : methode permettant de charger les elements Bulletin */
    public void chargementBulletins(){
        
        //Création d'un objet Objet DAO
        DAO<Bulletin> bulletin_dao = new BulletinDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = charger("bulletin");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                Bulletin bulletin = bulletin_dao.trouver_et_charge(liste_Id.get(i));
                /*System.out.println("\nBulletin " + bulletin.getAppreciation());
                if(bulletin.getDetailBulletin() != null)
                    for(DetailBulletin d : bulletin.getDetailBulletin())
                        System.out.println("* Detail " + d.getAppreciation());*/
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ecole.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    
    /** chargementDetailsBulletins : methode permettant de charger les elements DetailsBulletin */
    public void chargementDetailsBulletins(){
        
        //Création d'un objet Objet DAO
        DAO<DetailBulletin> detail_bulletin_dao = new DetailBulletinDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = charger("detail_bulletin");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                DetailBulletin detail_bulletin = detail_bulletin_dao.trouver_et_charge(liste_Id.get(i));
                /*System.out.println("\nDetail bulletin " + detail_bulletin.getAppreciation());
                if(detail_bulletin.getEvaluations() != null)
                    for(Evaluation e : detail_bulletin.getEvaluations())
                        System.out.println("* Evaluation " + e.getNote() + ", " + e.getAppreciation());*/
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ecole.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    
    /** chargementInscription : methode permettant de charger les elements Inscription */
    public void chargementInscription(){
        
        //Création d'un objet Objet DAO
        DAO<Inscription> inscription_dao = new InscriptionDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = charger("inscription");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                Inscription inscription = inscription_dao.trouver_et_charge(liste_Id.get(i));
                
                //Ajout dans l'ArrayList inscriptions
                this.addInscriptions(inscription);
                
                //System.out.println("\nInscription " + inscription.getID());
                
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    
    /** chargementEnseignement : methode permettant de charger les elements Enseignement */
    public void chargementEnseignement(){
        
        //Création d'un objet Objet DAO
        DAO<Enseignement> enseignement_dao = new EnseignementDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = charger("enseignement");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                Enseignement enseignement = enseignement_dao.trouver_et_charge(liste_Id.get(i));
                
                //Ajout dans l'ArrayList enseignements
                this.addEnseignement(enseignement);
                
                //System.out.println("\nEnseignement " + enseignement.getID());
                
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
      
    
    /** chargementEcole : methode permettant de charger les elements Ecole */
    public void chargementEcoles(){
        
        //Création d'un objet Objet DAO
        DAO<Ecole> ecole_dao = new EcoleDAO(connect);
        
        //Création d'un ArrayList contenant tous les Id de la table
        ArrayList<Integer> liste_Id;
        
        try {
            //Chargement de la liste des ID dans l'ArrayList
            liste_Id = charger("ecole");
                    
            //Pour toute la liste
            for(int i=0; i<liste_Id.size(); i++){
                
                //Création d'un Objet
                Ecole ecole = ecole_dao.trouver_et_charge(liste_Id.get(i));
                ecole.setConnexion(connect);
                
                //Ajout de l'Ecole dans l'ArrayList de Chargement
                this.addEcoles(ecole);
                
                /*System.out.println("\nEcole " + ecole.getNom() + ", " + ecole.getRegion());
                if(ecole.getNiveaux() != null)
                    for(Niveau n : ecole.getNiveaux())
                        System.out.println("* Niveau " + n.getNom());
                if(ecole.getEnseignants() != null)
                    for(Map.Entry map : ecole.getEnseignants().entrySet())
                        System.out.println("* Enseignant " + map.getKey());
                if(ecole.getEtudiants() != null)
                    for(Map.Entry map1 : ecole.getEtudiants().entrySet())
                        System.out.println("* Etudiant " + map1.getKey());*/
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ecole.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    
    public void run(){
        String str="";
        do{
            str="";
            System.out.println("Liste des écoles:");
            for(int i=0; i<ecoles.size(); i++)
                System.out.println("* [" + i + "] " + ecoles.get(i).getNom() + " - " + ecoles.get(i).getRegion());
            System.out.println("Entrer votre choix : ");
            System.out.println("Pour sortir : exit");            
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
            if(!"exit".equals(str)){ecoles.get(Integer.valueOf(str)).run();}
        }while(!"exit".equals(str));
    }
    
    
}
