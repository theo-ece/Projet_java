/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connexion.Connexion;
import DAO.PersonneDAO;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 *
 * @author thebo
 */
public class Etudiant extends Personne{
    
    /** Attribut prive de la classe : disciplines */
    private ArrayList<Discipline> disciplines;
    
    
    /** Constructeur par defaut */
    public Etudiant(){
        super(0, "", "", 0);
        disciplines = null;
    }
    
    /** Constructeur surcharge avec quatre parametres : ID, Nom, Prenom et type
     * @param ID
     * @param Nom
     * @param Prenom
     * @param type */
    public Etudiant(int ID, String Nom, String Prenom, int type){
        super(ID, Nom, Prenom, type);
    }
    
    /** Constructeur surcharge avec trois parametres : Nom, Prenom et type
     * @param Nom
     * @param Prenom
     * @param type */
    public Etudiant(String Nom, String Prenom, int type){
        super(0, Nom, Prenom, type);
    }
    
    
    /** getDisciplines : permettant d acceder a l attribut discipplines
     * @return  */
    public ArrayList<Discipline> getDisciplines(){
        return disciplines;
    }
    
    /** addDisciplines : permettant d ajouter une discipline a l attribut disciplines
     * @param d */
    public void addDisciplines(Discipline d){
        if(disciplines == null)
            disciplines = new ArrayList<>();
        
        disciplines.add(d);
    }
    
    /** removeAllDisciplines : permettant de supprimer toutes les disciplines de l attribut disciplines */
    public void removeAllDisciplines(){
        disciplines.clear();
    }
    
    
    /** ajoutEtudiant : methode permettant d ajouter un etudiant
     * @param connect */
    public void ajoutEtudiant(Connexion connect){
        
        //Création d'un objet PersonneDAO
        PersonneDAO etudiant_dao = new PersonneDAO(connect);
        
        //Appel de la fonction d'ajout
        etudiant_dao.ajouter(this);
    }
    
    
        /** run : 
     * 
     * @param Path
     * @param id
     * @param niveaux 
     */
    public void run(String Path, int id, ArrayList<Niveau> niveaux){
        //chargementEnseignant();
        //chargementClasse();
        System.out.println("NOM: " + getNom());
        System.out.println("PRENOM: " + getPrenom());
            
        for(int j=0; j<niveaux.size(); j++){
            
            if(niveaux.get(j).getClasses() != null){
                
                for(int i=0; i<niveaux.get(j).getClasses().size(); i++){
                    
                    System.out.println("clas " + niveaux.get(j).getClasses().get(i).getNom());
                    
                    if(niveaux.get(j).getClasses().get(i).getEtudiants() != null){
                        for(Entry<Integer,Etudiant> map : niveaux.get(j).getClasses().get(i).getEtudiants().entrySet()){
                            System.out.println("clas " + map.getValue().getNom());
                            if(map.getValue().getID() == id){
                                System.out.println("NIVEAU: " + niveaux.get(j).getNom());
                                System.out.println("CLASSE: " + niveaux.get(j).getClasses().get(i).getNom());
                            }
                        }
                    }
                }
            }
        }
        
        this.path = Path + " -> Etudiant";
        String str="";
        do{
            str="";

            
            
        }while(!"exit".equals(str));
    }
   
    
}
