/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Connexion.Connexion;
import Connexion.DataIncorrecte;
import DAO.*;
import Model.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Flora
 */
public class Controleur {
    
    /** Attribut prive de la classe : charge, connect et ecole */
    Chargement charge;
    Connexion connect;
    Ecole ecole = null;
    
    /** Constructeur par defaut */
    public Controleur(){
        charge = null;
        connect = null;
    }
    
    /** Constructeur surcharge avec un seul parametre : ecole
     * @param charge de type Chargement
     * @param connect de type Connexion */
    public Controleur(Chargement charge, Connexion connect){
        this.charge = charge;
        this.connect = connect;
    }
    
    /** setEcole : permet de modifier l attribut ecole
     * @param ecole de type Ecole */
    public void setEcole(Ecole ecole){
        this.ecole = ecole;
    }
    
    /** getEcole : permet d afficher l attribut ecole
     * @return l attribut ecole */
    public Ecole getEcole(){
        return ecole;
    }

    /** connexion : permet de se connecter  a la bdd
     * @param a de type String
     * @param b de type String
     * @param c de type String
     * @param d de type String
     * @return un boolean selon si la connexion est bonne ou nn
     * @throws java.sql.SQLException sql exception
     */
    public boolean connexion(String a, String b , String c, String d) throws SQLException
    {
        boolean louis = false;
         try {
            try {
             
                //Création d'un objet Connexion
                connect = new Connexion(d,c,b,a);
                
                //Création d'un objet Chargement
                charge = new Chargement(connect);
                charge.initialisation();
                Controleur controleur = new Controleur(charge, connect);
                louis = true;
                
            } catch (DataIncorrecte di) {
                System.out.println(di.getMsg());
                
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
         return louis;
    }
    
    
    /** retourne le controleur reference
     * @return le controleur reference */
    public Controleur returncontrol(){
        return this;
    }
    
    /** returnecole : retourne toutes les ecoles
     * @return le tableau de String contenant les ecoles
     * @throws java.sql.SQLException sql exeption */
    public String[] returnecole() throws SQLException
    {      
        String[] e = new String[charge.getEcoles().size()];
        if(charge.getEcoles() != null)
            for(int i=0; i<e.length; i++){
                e[i] = charge.getEcoles().get(i).getNom();
            }
        return e;      
    }
    
    /** choix_ecole : retourne l ecole choisie
     * @param ecole de type String */
    public void choix_ecole(String ecole){
        if(charge.getEcoles() != null)
            for(Ecole e : charge.getEcoles())
                if(e.getNom().equals(ecole))
                    this.setEcole(e);
    }
    
    /** choix_ecole : permet de mettre a jour l ecole par celle choisie par l utilisateur
     * @param id de type int
     */
    public void choix_ecole(int id){
        //Recherche de l'école parmi celle de l'arraylist de charge et modification de l'attribut ecole
        if(charge.getEcoles() != null)
            for(Ecole e : charge.getEcoles())
                if(e.getID() == id)
                    setEcole(e);
    }
    
    /** ajouter_ecole : ajout une ecole
     * @param nom de type String 
     * @param region de type String
     */
    public void ajouter_ecole(String nom, String region){

        //Déclaration de variables
        int id;
        
        //Ajout d'une école dans la BDD
        Ecole ecole = new Ecole(nom,region);
        EcoleDAO ecole_dao = new EcoleDAO(connect);
        id = ecole_dao.ajouter(ecole);
        
        //Modification de l'array list de Ecole
        ecole.setID(id);
        
        //ajout dans array list
        charge.addEcoles(ecole);
    }
    
    /** ajouter_annee : ajout une annee
     * @param nom de type String 
     */
    public void ajouter_annee(String nom){

        //Déclaration de variables
        int id;

        //Ajout d'une école dans la BDD
        AnneeScolaire a = new AnneeScolaire(nom);
        AnneeScolaireDAO annee_dao = new AnneeScolaireDAO(connect);
        id = annee_dao.ajouter(a);
        
        //Modification de l'array list d'années
        a.setID(id);
        
        //ajout dans array list
        charge.addAnnees(a);
    }
    
    /** ajouter_trimestre : ajout un trimestre
     * @param debut de type String 
     * @param fin de type String
     * @param annee de type String
     */
    public void ajouter_trimestre(String debut, String fin, String annee){

        //Déclaration de variables
        int id;
        
        //Ajout d'un trimestre dans la BDD
        Trimestre tri = new Trimestre(1,debut,fin);
        TrimestreDAO tri_dao = new TrimestreDAO(connect);
        id = tri_dao.ajouter(tri);
        
        //Modification de l'array list de trimestre
        /*etudiant.setID(id);
        ecole.addEtudiants(etudiant);
        
        //Ajout d'une inscription dans la BDD
        if(ecole.getNiveaux() != null)
            for(Niveau niv : ecole.getNiveaux())
                if(niv.getNom().equals(niveau))
                    for(Classe cl : niv.getClasses()){
                        if(cl.getNom() == classe){

                            //Ajout dans la BDD
                            Inscription inscription = new Inscription(cl,etudiant);
                            id_ins = inscription.inscription(connect);

                            //Ajout dans l'arrylist d'inscription
                            inscription.setID(id_ins);
                            charge.addInscriptions(inscription);
                        }
                    }*/
    }
    
    
    /** returnetudiants : retourne les etudiants de l ecole
     * @return les etudiant de l ecole dans un DefaultTableModel */
    public DefaultTableModel returnetudiants(){
        
        //Création d'un objet DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();

        //Ajout des colonnes sur le tableau
        model.addColumn("Id");
        model.addColumn("Prenom");
        model.addColumn("Nom");
        model.addColumn("Classe");
        model.addColumn("Niveau");
        
        //Recherche de tous les étudiants
        if(ecole.getEtudiants() != null)
            for(Map.Entry<Integer,Etudiant> map : ecole.getEtudiants().entrySet()){
                //Déclaration de variables
                boolean ok = false;
                String classe="", niveau="";
                if(charge.getInscription() != null){
                    for(Inscription i : charge.getInscription())
                        if(i.getEtudiant().getID() == map.getValue().getID())
                            if(ecole.getNiveaux() != null)
                                for(Niveau niv : ecole.getNiveaux())
                                    for(Classe cl : niv.getClasses()){
                                        if(cl.getID() == i.getClasse().getID()){
                                            ok = true;
                                            classe = cl.getNom();
                                            niveau = niv.getNom();
                                        }
                                }
                }
                if(ok == true) model.addRow(new Object[]{map.getValue().getID(),map.getValue().getPrenom(), map.getValue().getNom(),classe, niveau});
            }
        return model;
    }
    
    /** returnenseignant : retourne les enseignants de l ecole
     * @return les enseignants de l ecole dans un DefaultTableModel */
    public DefaultTableModel returnenseignant(){
        
        //Création d'un objet DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();
        
        //Ajout des colonnes sur le tableau
        model.addColumn("Id");
        model.addColumn("Prenom");
        model.addColumn("Nom");
        model.addColumn("Classe");
        model.addColumn("Niveau");
        model.addColumn("Discipline");
        
        //Recherche de tous les enseignants
        if(ecole.getEnseignants() != null)
            for(Map.Entry<Integer,Enseignant> map : ecole.getEnseignants().entrySet()){
                //Déclaration de variables
                boolean ok = false;
                String discipline = "", niveau = "", classe = "";
                if(charge.getEnseignement() != null)
                    for(Enseignement e : charge.getEnseignement()){
                        if(e.getEnseignant().getID() == map.getValue().getID()){
                            if(ecole.getNiveaux() != null)
                                for(Niveau niv : ecole.getNiveaux()){ 
                                    for(Classe cl : niv.getClasses()){
                                        if(cl.getID() == e.getClasse().getID()){
                                            ok = true;
                                            classe = cl.getNom();
                                            niveau = niv.getNom();
                                            discipline = e.getDiscipline().getNom();
                                        }
                                    }
                                }
                        }
                }  
                if(ok == true) model.addRow(new Object[]{map.getValue().getID(),map.getValue().getPrenom(), map.getValue().getNom(), classe, niveau, discipline});            
        }
        return model;
    }
    
    /** returnclasse : retourne les classes de l ecole
     * @return les classes de l ecole dans un DefaultTableModel */
    public DefaultTableModel returnclasse(){
        
        //Création d'un objet DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();
        
        //Ajout des colonnes sur le tableau
        model.addColumn("Id");
        model.addColumn("Niveau");
        model.addColumn("Nom");
        
        //Recherche de toutes les classes
        if(ecole.getNiveaux()!= null)
            for(Niveau n : ecole.getNiveaux())
                if(n.getClasses() != null)
                    for(Classe c : n.getClasses())
                        model.addRow(new Object[]{c.getID(),n.getNom(), c.getNom()});         
        return model;
    }

      
    /** ajouter_etudiant : ajout d etudiant
     * 
     * @param nom de type String
     * @param prenom de type String
     * @param niveau de type String
     * @param classe de type String
     */
    public void ajouter_etudiant(String nom, String prenom, String niveau, String classe){

        //Déclaration de variables
        int id, id_ins;
        
        //Ajout d'un étudiant dans la BDD
        Etudiant etudiant = new Etudiant(nom,prenom,0);
        PersonneDAO etudiant_dao = new PersonneDAO(connect);
        id = etudiant_dao.ajouter(etudiant);
        
        //Modification de l'array list de Etudiant
        etudiant.setID(id);
        ecole.addEtudiants(etudiant);
        
        //Ajout d'une inscription dans la BDD
        if(ecole.getNiveaux() != null)
            for(Niveau niv : ecole.getNiveaux())
                if(niv.getNom().equals(niveau))
                    for(Classe cl : niv.getClasses()){
                        if(cl.getNom() == classe){

                            //Ajout dans la BDD
                            Inscription inscription = new Inscription(cl,etudiant);
                            id_ins = inscription.inscription(connect);

                            //Ajout dans l'arrylist d'inscription
                            inscription.setID(id_ins);
                            charge.addInscriptions(inscription);
                        }
                    }
    }
    
    
    /** rechercher_etudiant : recherche un etudiant avec son id
     * 
     * @param id de type int
     * @return l etudiant dans un DefaultTableModel
     * @throws HashExistant exception de recherche d un id
     * @throws HashInexistant exception de recherche d un id
     */
    public DefaultTableModel rechercher_etudiant(int id) throws HashExistant, HashInexistant{
        
        //Création d'un objet DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();
        
        //Ajout des colonnes sur le tableau
        model.addColumn("Id");
        model.addColumn("Prenom");
        model.addColumn("Nom");
        model.addColumn("Classe");
        model.addColumn("Niveau");
            
        //Recherche de tous les étudiants
        if(ecole.getEtudiants() != null)
            for(Map.Entry<Integer,Etudiant> map : ecole.getEtudiants().entrySet()){
                //Déclaration de variables
                boolean ok = false;
                String classe="", niveau="";
                if(map.getKey() == id)
                    if(charge.getInscription() != null){
                        for(Inscription i : charge.getInscription())
                            if(i.getEtudiant().getID() == map.getValue().getID())
                                if(ecole.getNiveaux() != null)
                                    for(Niveau niv : ecole.getNiveaux())
                                        for(Classe cl : niv.getClasses()){
                                            if(cl.getID() == i.getClasse().getID()){
                                                ok = true;
                                                classe = cl.getNom();
                                                niveau = niv.getNom();
                                            }

                                    }
                }
                if(ok == true) model.addRow(new Object[]{map.getValue().getID(),map.getValue().getPrenom(), map.getValue().getNom(),classe, niveau});
            }
        return model;
        
    }
    
    
    /** modifier_etudiant : modifie un etudiant 
     * 
     * @param id de type int
     * @param nom de type String
     * @param prenom de type String
     * @param niveau de type String
     * @param classe de type String
     */
    public void modifier_etudiant(int id, String nom, String prenom, String niveau, String classe){
        
        //Création d'un objet PersonneDAO
        PersonneDAO etudiant_dao = new PersonneDAO(connect);
        InscriptionDAO inscription_dao = new InscriptionDAO(connect);
                    
        //Recherche de l'Etudiant
        if(ecole.getEtudiants() != null)
            for(Map.Entry<Integer,Etudiant> map : ecole.getEtudiants().entrySet())
                if(map.getKey() == id){
                    if(!map.getValue().getNom().equals(nom)){
                        etudiant_dao.modifier(map.getValue(), "Nom", nom);
                        map.getValue().setNom(nom);
                    }
                    if(!map.getValue().getPrenom().equals(prenom)){
                        etudiant_dao.modifier(map.getValue(), "Prenom", prenom);
                        map.getValue().setPrenom(prenom);
                    }
                    if(charge.getInscription() != null)
                        for(Inscription i : charge.getInscription())
                            if(i.getEtudiant().getID() == map.getKey())
                                if(!i.getClasse().getNom().equals(classe))
                                    for(Niveau niv : ecole.getNiveaux())
                                        if(niv.getNom().equals(niveau))
                                            for(Classe c : niv.getClasses())
                                                if(c.getNom().equals(classe)){       
                                                    String s = ""+c.getID();
                                                    inscription_dao.modifier(i, "Id_classe", s);
                                                    i.setClasse(c);
                                                }
                }
    }
    
    /** supprimer_etudiant : supprime un etudiant
     * 
     * @param id de type int
     */
    public void supprimer_etudiant(int id){
        
        //Suppression dans la BDD
        PersonneDAO personne_dao = new PersonneDAO(connect);
        personne_dao.supprimer(personne_dao.trouver(id));
        
        //Supprimer dans l'arraylist Etudiants
        ecole.getEtudiants().remove(id);
        
        //Suppression dans classe
        if(ecole.getNiveaux() != null)
            for(Niveau n : ecole.getNiveaux())
                if(n.getClasses() != null)
                    for(Classe c : n.getClasses())
                        if(c.getEtudiants() != null)
                            for(Map.Entry<Integer,Etudiant> map : c.getEtudiants().entrySet())
                                if(map.getValue().getID() == id)
                                    c.getEtudiants().remove(id);
        int index = 0;
        
        if(charge.getInscription() != null){
            for(int i=0; i<charge.getInscription().size(); i++)
                if(charge.getInscription().get(i).getEtudiant().getID() == id){
                    index = i;
                    charge.getInscription().get(i).desinscription(connect);
                }
                    
            charge.getInscription().remove(index);
        }
    }
    
    
    /** ajouter_enseignant : ajout un enseignant 
     * 
     * @param nom de type String
     * @param prenom de type String
     * @param niveau de type String
     * @param classe de type String
     * @param matiere de type String
     */
    public void ajouter_enseignant(String nom, String prenom, String niveau, String classe, String matiere){

        //Déclaration de variables
        int id, id_ens;
        boolean stop = false;

        //Ajout d'un enseignant dans la BDD
        Enseignant enseignant = new Enseignant(nom,prenom,1);
        PersonneDAO enseignant_dao = new PersonneDAO(connect);
        id = enseignant_dao.ajouter(enseignant);
        
        //Ajout dans l'array list de Enseignant
        enseignant.setID(id);
        ecole.addEnseignants(enseignant);
        
        //Ajout d'un enseignement dans la BDD
        if(ecole.getNiveaux() != null)
            for(Niveau niv : ecole.getNiveaux()){
                if(niv.getNom().equals(niveau)){
                    for(Classe cl : niv.getClasses()){   
                        if(cl.getNom().equals(classe)){
                            for(Map.Entry<Integer,Etudiant> map : ecole.getEtudiants().entrySet()){
                                for(Discipline discipline : map.getValue().getDisciplines())
                                    if((discipline.getNom().equals(matiere)) && (stop==false)){

                                        //Ajout dans la BDD
                                        Enseignement enseignement = new Enseignement(cl, discipline, enseignant);
                                        id_ens = enseignement.enseignement(connect);

                                        //Ajout dans l'arraylist Enseignement
                                        enseignement.setID(id_ens);
                                        charge.addEnseignement(enseignement);

                                        //MAJ du booleen
                                        stop=true;
                                    }
                            }
                        }
                    }
                }
        }
    }
    
  
    /** rechercher_enseignant : recherche un enseignant avec id recu en parametre
     * 
     * @param id de type int
     * @return enseignant trouve
     * @throws HashExistant exception pour la recherche d un id
     * @throws HashInexistant exception pour la recherche d un id
     */
    public DefaultTableModel rechercher_enseignant(int id) throws HashExistant, HashInexistant{
        
        //Création d'un objet DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();
        
        //Ajout des colonnes sur le tableau
        model.addColumn("Id");
        model.addColumn("Prenom");
        model.addColumn("Nom");
        model.addColumn("Classe");
        model.addColumn("Niveau");
            
        //Recherche dans l'ArrayList d'enseignant          
        if(ecole.getEnseignants() != null)
            for(Map.Entry<Integer,Enseignant> map : ecole.getEnseignants().entrySet()){
                //Déclaration de variables
                boolean ok = false;
                String discipline = "", niveau = "", classe = "";
                if(map.getKey() == id)
                    if(charge.getEnseignement() != null)
                        for(Enseignement e : charge.getEnseignement()){
                            if(e.getEnseignant().getID() == map.getValue().getID()){
                                if(ecole.getNiveaux() != null)
                                    for(Niveau niv : ecole.getNiveaux()){ 
                                        for(Classe cl : niv.getClasses()){
                                            if(cl.getID() == e.getClasse().getID()){
                                                ok = true;
                                                classe = cl.getNom();
                                                niveau = niv.getNom();
                                                discipline = e.getDiscipline().getNom();
                                            }
                                        }
                                    }
                            }
                }  
                if(ok == true) model.addRow(new Object[]{map.getValue().getID(),map.getValue().getPrenom(), map.getValue().getNom(), classe, niveau, discipline});            
        }
        return model;
    }
    
    /** rechercher_classe : recherche une classe avec id recu en parametre
     * 
     * @param id de type iint
     * @return la classe trouve
     * @throws HashExistant exception pour la recherche d un id
     * @throws HashInexistant exception pour la recherche d un id
     */
    public DefaultTableModel rechercher_classe(int id) throws HashExistant, HashInexistant{
        
        //Création d'un objet DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();
        
        //Ajout des colonnes sur le tableau
        model.addColumn("Id");
        model.addColumn("Niveau");
        model.addColumn("Nom");
        
        
        //Recherche de toutes les classes
        if(ecole.getNiveaux()!= null)
            for(Niveau n : ecole.getNiveaux())
                if(n.getClasses() != null)
                    for(Classe c : n.getClasses())
                        if(c.getID() == id)
                            model.addRow(new Object[]{c.getID(),n.getNom(), c.getNom()});
                    
        return model;
    }
    
    /** modifier_enseignant : modifier un enseignant
     * 
     * @param id de type int
     * @param nom de type String
     * @param prenom de type String
     * @param matiere de type String
     * @param classe de type String
     * @param niveau de type String
     */
    public void modifier_enseignant(int id, String nom, String prenom, String matiere, String classe, String niveau){
        
        //Création d'un objet PersonneDAO
        PersonneDAO enseignant_dao = new PersonneDAO(connect);
        EnseignementDAO inscription_dao = new EnseignementDAO(connect);
        ClasseDAO classe_dao = new ClasseDAO(connect);
                    
        //Recherche de l'Enseignant
        if(ecole.getEnseignants() != null)
            for(Map.Entry<Integer,Enseignant> map : ecole.getEnseignants().entrySet())
                if(map.getKey() == id){
                    if(!map.getValue().getNom().equals(nom)){
                        enseignant_dao.modifier(map.getValue(), "Nom", nom);
                        map.getValue().setNom(nom);
                    }
                    if(!map.getValue().getPrenom().equals(prenom)){
                        enseignant_dao.modifier(map.getValue(), "Prenom", prenom);
                        map.getValue().setPrenom(prenom);
                    }
                    if(charge.getEnseignement() != null)
                        for(Enseignement i : charge.getEnseignement())
                            if(i.getEnseignant().getID() == map.getKey())
                                if(!i.getClasse().getNom().equals(classe))
                                    for(Niveau niv : ecole.getNiveaux()){
                                        if(niv.getNom().equals(niveau))
                                            for(Classe c : niv.getClasses())
                                                if(c.getNom().equals(classe)){       
                                                    String s = ""+c.getID();
                                                    inscription_dao.modifier(i, "Id_classe", s);                                                   
                                                    i.setClasse(c);                                                    
                                                }                                       
                                    }
                }
    }
       
    
    /** supprimer_enseignant : supprimer un enseignant
     * 
     * @param id de type int
     */
    public void supprimer_enseignant(int id){
        
        //Suppression dans la BDD
        PersonneDAO personne_dao = new PersonneDAO(connect);
        personne_dao.supprimer(personne_dao.trouver(id));
        
        //Supprimer dans l'arraylist Enseignants
        ecole.getEnseignants().remove(id);
        
    }
    
    /** ajouter_classe : ajout d un classe
     * 
     * @param nom de type String
     * @param niveau de type String
     * @param annee de type String
     */
    public void ajouter_classe(String nom, String niveau, String annee){

        //Déclaration d'un objet int
        int id;
        
        //Ajout d'un enseignant dans la BDD
        Classe classe = new Classe(nom);
        ClasseDAO classe_dao = new ClasseDAO(connect);
        
        //Modification de l'année
        if(charge.getAnnees() != null)
            for(AnneeScolaire an : charge.getAnnees())
                if(an.getDate().equals(annee))
                    classe.setAnnee(an);
        
        //Modification de l'école
        classe.setEcole(ecole);
                        
        //Modification du niveau 
        if(ecole.getNiveaux() != null)
            for(Niveau niv : ecole.getNiveaux())
                if(niv.getNom().equals(niveau))
                    classe.setNiveau(niv);
                
        //Ajout dans la BDD
        id = classe_dao.ajouter(classe);
        
        //Modification de l'ID
        classe.setID(id);
        
        //Ajout de la classe dans le niveau
        if(ecole.getNiveaux() != null)
            for(Niveau niv : ecole.getNiveaux())
                if(niv.getNom().equals(niveau))
                    niv.addClasses(classe);
                
    }
    
    /** supprimer_classe : supprime une classe
     * 
     * @param id de type int
     */
    public void supprimer_classe(int id){
        //Suppression dans la BDD
        ClasseDAO classe_dao = new ClasseDAO(connect);
        classe_dao.supprimer(classe_dao.trouver(id));
        
        //Suppression dans l'arraylist Evaluation
        if(ecole.getNiveaux() != null)
            for(Niveau niv : ecole.getNiveaux())
                if(niv.getClasses() != null)
                    for(int i=0; i<niv.getClasses().size(); i++)
                        if(niv.getClasses().get(i).getID() == id)
                            niv.getClasses().remove(niv.getClasses().get(i));
                            
    }
    
    /** returnannee : retourne les annees scolaires
     * 
     * @return les annees scolaires
     */
    public String[] returnannee(){
        
        String[] a = new String[charge.getAnnees().size()];
        
        if(charge.getAnnees() != null)
            for(int i=0; i<a.length; i++){
                a[i] = charge.getAnnees().get(i).getDate();
            }
        
        return a;
    }
    
    
    /** returtrimestre : retourne les trimestre dependants de l annee recue en parametre
     * 
     * @param id_annee de type int
     * @return les trimestres de cette annnee
     */
    public String[] returntrimestre(int id_annee){
        
        String s;
        String[] a = null;
        
        if(charge.getAnnees() != null)
            for(AnneeScolaire annee : charge.getAnnees())
                if(annee.getID() == id_annee){
                    if(annee.getTrimestres() != null){
                        a = new String[annee.getTrimestres().size()];
                        for(int i=0; i<a.length; i++){
                            s = annee.getTrimestres().get(i).getDebut() + " / " + annee.getTrimestres().get(i).getFin();
                            a[i] = s;
                        }
                    }
                }
        
        return a; 
    }
    
    /** returnbulletins : retourne les bulletins pour un eleve et trimestre donnes
     * 
     * @param Id_etudiant de type int
     * @param Id_trimestre de type int
     * @return le bulletin sous forme de DefaultTableModel
     */
    public DefaultTableModel returnbulletins(int Id_etudiant, int Id_trimestre){
        
        //Création d'un objet DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();
  
        //Ajout des colonnes sur le tableau
        model.addColumn("Id");
        model.addColumn("Discipline");
        model.addColumn("Professeur");
        model.addColumn("Note");
        model.addColumn("Appréciation");
                    
        if(charge.getAnnees() != null)
            for(AnneeScolaire a : charge.getAnnees())
                if(a.getTrimestres() != null)
                    for(Trimestre t : a.getTrimestres())
                        if(t.getID() == Id_trimestre)
                            if(t.getBulletins() != null)
                                for(Bulletin b : t.getBulletins())
                                    if(b.getEtudiant() != null)
                                        if(b.getEtudiant().getID() == Id_etudiant)
                                            if(b.getDetailBulletin() != null)
                                                for(DetailBulletin d : b.getDetailBulletin())
                                                    if(d.getEvaluations() != null)
                                                        for(Evaluation e : d.getEvaluations())
                                                            model.addRow(new Object[]{e.getID(),d.getDiscipline().getNom(), d.getEnseignant().getNom(), e.getNote(), e.getAppreciation()});

        return model;
    }

    /** returnappreciation : retourne l appreciation du trimestre
     * 
     * @param Id_etudiant de type int
     * @param Id_trimestre de type int
     * @return l appreciation
     */
    public String returnappreciation(int Id_etudiant, int Id_trimestre){
        
        //Recherche du bulletin
        if(charge.getAnnees() != null)
            for(AnneeScolaire a : charge.getAnnees())
                if(a.getTrimestres() != null)
                    for(Trimestre t : a.getTrimestres())
                        if(t.getID() == Id_trimestre)
                            if(t.getBulletins() != null)
                                for(Bulletin b : t.getBulletins())
                                    return b.getAppreciation();
        
        
        return null;
    } 
    
    
    /** recherche_trimestres : retourne un id d un trimestre 
     * 
     * @param annee de type String
     * @param trimestre de type String
     * @return id du trimestre trouve
     */
    public int recherchetrimestres(String annee, String trimestre){
        if(charge.getAnnees() != null)
            for(AnneeScolaire a : charge.getAnnees())
                if(a.getDate().equals(annee))
                    for(Trimestre t : a.getTrimestres()){
                        String date = t.getDebut() + " / " + t.getFin();
                        if(date.equals(trimestre)){
                            return t.getID();
                        }
                    }
    
        return 0;
    }
    
    /** moyenne: retourne la moyenne d'un étudiant sur un trimestre
     *
     * @param Id_etudiant type int :  ID de l'étudiant demandé
     * @param Id_trimestre type int :  ID du trimestre demandé
     * @return Double -> moyenne de l'élève sur un trimestre
     */
    public double moyenne(int Id_etudiant, int Id_trimestre){
        
        //Déclaration de variables
        double moyenne = 0;
        int nb=0;
        
        //On recherche les notes
        if(charge.getAnnees() != null)
            for(AnneeScolaire a : charge.getAnnees())
                if(a.getTrimestres() != null)
                    for(Trimestre t : a.getTrimestres())
                        if(t.getID() == Id_trimestre)
                            if(t.getBulletins() != null)
                                for(Bulletin b : t.getBulletins())
                                    if(b.getEtudiant().getID() == Id_etudiant)
                                        if(b.getDetailBulletin() != null)
                                            for(DetailBulletin d : b.getDetailBulletin())
                                                if(d.getEvaluations() != null)
                                                    for(Evaluation e : d.getEvaluations()){
                                                        nb++;
                                                        moyenne += e.getNote();
                                                    }  
        //Calcul de la moyenne
        moyenne = moyenne/nb;
        
        //Le programme retourne la moyenne
        return moyenne;                                
    }
    
    /** supprimer_evaluation: supprime une evaluation dans la BDD grâce a son ID
     *
     * @param id type int : ID de l'évaluation demandé
     */
    public void supprimer_evaluation(int id){
        //Suppression dans la BDD
        EvaluationDAO evaluation_dao = new EvaluationDAO(connect);
        evaluation_dao.supprimer(evaluation_dao.trouver(id));
        
        //Suppression du Detail bulletin
        DetailBulletinDAO detailbulletin_dao = new DetailBulletinDAO(connect);
        
        //Déclaration d'une variable
        int index = 0;
        boolean stop = false;
        
        //Suppression dans l'arraylist Evaluation
        if(charge.getAnnees() != null)
            for(AnneeScolaire annee : charge.getAnnees())
                if(annee.getTrimestres() != null)
                    for(Trimestre trimestre : annee.getTrimestres())
                        if(trimestre.getBulletins() != null)
                            for(Bulletin bulletin : trimestre.getBulletins())
                                if(bulletin.getDetailBulletin() != null){
                                    int taille0 = bulletin.getDetailBulletin().size();
                                    for(int j=0; j<taille0; j++)
                                        if(bulletin.getDetailBulletin().get(j).getEvaluations() != null){
                                            int taille = bulletin.getDetailBulletin().get(j).getEvaluations().size();
                                            for(int i=0; i<taille; i++)
                                                if(bulletin.getDetailBulletin().get(j).getEvaluations().get(i).getID() == id){
                                                    bulletin.getDetailBulletin().get(j).getEvaluations().remove(i);
                                                    detailbulletin_dao.supprimer(bulletin.getDetailBulletin().get(j));
                                                    bulletin.getDetailBulletin().remove(j);
                                                    index = j;
                                                    i = taille;
                                                    j = taille0;
                                                }
                                        }
                                }
    }
    
    /**modifier_evaluation : modifie une évaluation de la BDD grace a son ID
     *
     * @param id type int :  ID de l'évaluation demandé
     * @param note type double : note modifiée
     * @param appreciation type String : appréciation modifiée
     */
    public void modifier_evaluation(int id, double note, String appreciation){
        
        //Création d'un objet EvaluationDAO
        EvaluationDAO evaluation_dao = new EvaluationDAO(connect);
                                                
        //Recherche de la modification
        if(charge.getAnnees() != null)
            for(AnneeScolaire annee : charge.getAnnees())
                if(annee.getTrimestres() != null)
                    for(Trimestre trimestre : annee.getTrimestres())
                        if(trimestre.getBulletins() != null)
                            for(Bulletin bulletin : trimestre.getBulletins())
                                if(bulletin.getDetailBulletin() != null)
                                    for(DetailBulletin detail : bulletin.getDetailBulletin())
                                        for(Evaluation eval : detail.getEvaluations())
                                            if(eval.getID() == id){
                                                if(eval.getNote() != note){
                                                   String s = ""+note;
                                                   evaluation_dao.modifier(eval, "Note", s);
                                                   eval.setNote(note);
                                                }
                                                if(!eval.getAppreciation().equals(appreciation)){
                                                    evaluation_dao.modifier(eval, "Appreciation", appreciation);
                                                    eval.setAppreciation(appreciation);
                                                }
                                            }
                          
    }
    
    /**ajouter_evaluation : ajoute une évaluation dans la BDD
     *
     * @param id_etudiant  type int : ID de l'étudiant demandé
     * @param annee type String : année de l'étudiant
     * @param discipline type String : discipline de l'évaluation
     * @param trimestre type String : trimestre de l'évaluation
     * @param prof type String : correcteur
     * @param note type Double : résultat de la note
     * @param appreciation type String : String de l'appréciation
     */
    public void ajouter_evaluation(int id_etudiant, String annee, String discipline, String trimestre, String prof, double note, String appreciation){
        
        //Déclaration de variables
        int id;
        boolean stop = false;
        
        //Déclaration d'un objet Evaluation
        Evaluation evaluation = new Evaluation(note, appreciation);
        
        //Déclaration d'un objet BulletinDAO
        BulletinDAO bulletin_dao = new BulletinDAO(connect);
        
        //Déclaration d'un objet DetailBulletinDAO
        DetailBulletinDAO detailbulletin_dao = new DetailBulletinDAO(connect);
        
        //Déclaration d'un objet DAO
        EvaluationDAO evaluation_dao = new EvaluationDAO(connect);
        
       
        if(charge.getAnnees() != null)
            for(AnneeScolaire ann : charge.getAnnees())
                if(ann.getDate().equals(annee))
                    if(ann.getTrimestres() != null)
                        for(Trimestre trii : ann.getTrimestres()){
                            String stt = trii.getDebut() + " / " + trii.getFin();
                            if(stt.equals(trimestre))
                                if(trii.getBulletins() != null){
                                    for(Bulletin bb : trii.getBulletins()){
                                        if(bb.getEtudiant().getID() == id_etudiant){
                                            DetailBulletin newdb = new DetailBulletin(appreciation);
                                            newdb.setBulletin(bb);
                                            if(charge.getEnseignement() != null)
                                                for(Enseignement ee : charge.getEnseignement())
                                                    if(ee.getDiscipline().getNom().equals(discipline)){
                                                        if(stop == false){
                                                            newdb.setEnseignant(ee.getEnseignant());
                                                            id = detailbulletin_dao.ajouter(newdb);
                                                            newdb.setID(id);
                                                            evaluation.setDetailsB(newdb);
                                                            id = evaluation_dao.ajouter(evaluation);
                                                            evaluation.setID(id);
                                                            newdb.addEvaluations(evaluation);
                                                            stop = true;
                                                            bb.addDetailsB(newdb);
                                                        }
                                                    }
                                        }
                                    }
                               
                                }
                        }
                                    
    }
    
    /**return_disciplines : retourne un tableau de string contenant les disciplines
     *
     * @param Id_etudiant type int : ID de l'étudiant demandé
     * @return Tableaux des disciplines de l'élève
     */
    public String[] return_disciplines(int Id_etudiant){
        
        ArrayList<String> a = new ArrayList<>();
             
        if(charge.getInscription()!= null)
            for(Inscription ins : charge.getInscription())
                if(ins.getEtudiant() != null)
                    if(ins.getEtudiant().getID() == Id_etudiant)
                        if(charge.getEnseignement() != null)
                            for(Enseignement ens : charge.getEnseignement())
                                if(ens.getClass() != null)
                                    if(ens.getClasse().getID() == ins.getClasse().getID())
                                        if(ens.getDiscipline() != null)
                                            a.add(ens.getDiscipline().getNom());

              
        String[] s = new String[a.size()];
        
        for(int i=0; i<a.size(); i++)
            s[i] = a.get(i);
        
        return s; 
    }
    
    /**return_disciplinesP : retourne les disciplines d'un enseignant sous forme de tableau de string
     *
     * @param Id_prof type int : ID de l'enseignant demandé
     * @return Tableau de String des disciplines d'un professeur
     */
    public String[] return_disciplinesP(int Id_prof){
        
        ArrayList<String> a = new ArrayList<>();
             
        if(charge.getInscription()!= null)
            for(Inscription ins : charge.getInscription())
                if(ins.getEtudiant() != null)
                    if(ins.getEtudiant().getID() == Id_prof)
                        if(charge.getEnseignement() != null)
                            for(Enseignement ens : charge.getEnseignement())
                                if(ens.getClass() != null)
                                    if(ens.getClasse().getID() == ins.getClasse().getID())
                                        if(ens.getDiscipline() != null)
                                            a.add(ens.getDiscipline().getNom());

              
        String[] s = new String[a.size()];
        
        for(int i=0; i<a.size(); i++)
            s[i] = a.get(i);
        
        return s; 
    }
    
    /**return_prof : retourne le nom d'un enseignant 
     *
     * @param discipline type String : nom de la discipline
     * @param Id_etudiant type int : ID de l'étudiant demandé
     * @return String du nom de son enseignant dans ne discipline donnée
     */
    public String return_prof(String discipline, int Id_etudiant){
                
        if(charge.getInscription()!= null)
            for(Inscription ins : charge.getInscription())
                if(ins.getEtudiant() != null)
                    if(ins.getEtudiant().getID() == Id_etudiant)
                        if(charge.getEnseignement() != null)
                            for(Enseignement ens : charge.getEnseignement())
                                if(ens.getDiscipline() != null)
                                    if(ens.getDiscipline().getNom().equals(discipline))
                                        if(ens.getClasse().getID() == ins.getClasse().getID())
                                            return ens.getEnseignant().getNom();          

        return null;
                            
    }
    
    /**returnNom : retourne le nom d'un étudiant en fonction de son id
     *
     * @param id_etudiant type int : ID de l'étudiant demandé
     * @return String de son nom
     */
    public String returnNom(int id_etudiant){
        if(ecole.getEtudiants() != null)
            for(Map.Entry<Integer,Etudiant> map : ecole.getEtudiants().entrySet())
                if(map.getValue().getID() == id_etudiant)
                    return map.getValue().getNom();
        return null;
    }
    
    /**returnPrenom : retourne le prénom d'un étudiant en fonction de son id
     *
     * @param id_etudiant type int : ID de l'étudiant demandé
     * @return String de son prénom
     */
    public String returnPrenom(int id_etudiant){
        if(ecole.getEtudiants() != null)
            for(Map.Entry<Integer,Etudiant> map : ecole.getEtudiants().entrySet())
                if(map.getValue().getID() == id_etudiant)
                    return map.getValue().getPrenom();
        return null;
    }
    
    /**returnNomP : retourne le nom d'un enseignant en fonction de son id
     *
     * @param id_prof type int : ID de l'enseignant demandé
     * @return String de son nom
     */
    public String returnNomP(int id_prof){
        if(ecole.getEnseignants() != null)
            for(Map.Entry<Integer,Enseignant> map : ecole.getEnseignants().entrySet())
                if(map.getValue().getID() == id_prof)
                    return map.getValue().getNom();
        return null;
    }
    
    /**returnPrenomP : retourne le prénom d'un enseignant en fonction de son id
     *
     * @param id_prof type int : ID de l'enseignant demandé
     * @return String de son prénom
     */
    public String returnPrenomP(int id_prof){
        if(ecole.getEnseignants() != null)
            for(Map.Entry<Integer,Enseignant> map : ecole.getEnseignants().entrySet())
                if(map.getValue().getID() == id_prof)
                    return map.getValue().getPrenom();
        return null;
    }
    
    /**returnNiveau : retourne le niveau (en string) d'un étudiant en foction de son id
     *
     * @param id_etudiant type int : ID de l'élève demandé
     * @return String du nom du niveau de l'étudiant
     */
    public String returnNiveau(int id_etudiant){
        
        if(charge.getInscription() != null)
            for(Inscription i : charge.getInscription())
                if(i.getEtudiant().getID() == id_etudiant)
                    if(ecole.getNiveaux() != null)
                        for(Niveau n : ecole.getNiveaux())
                            if(n.getClasses() != null)
                                for(Classe c : n.getClasses())
                                    if(c.getID() == i.getClasse().getID())
                                        return n.getNom();
        return null;
    }
    
    /**returnNiveauP : retourne le niveau (sous forme de string) d'un enseignant en fonction de son id
     *
     * @param id_prof type int : ID de l'enseignant demandé
     * @return String du niveau du professeur
     */
    public String returnNiveauP(int id_prof){
        
        if(charge.getEnseignement() != null)
            for(Enseignement i : charge.getEnseignement())
                if(i.getEnseignant().getID() == id_prof)
                    if(ecole.getNiveaux() != null)
                        for(Niveau n : ecole.getNiveaux())
                            if(n.getClasses() != null)
                                for(Classe c : n.getClasses())
                                    if(c.getID() == i.getClasse().getID())
                                        return n.getNom();
        return null;
    }
    
    /**returnClasse : retourne le nom de la classe d'un étudiant
     *
     * @param id_etudiant type int : ID de l'élève demandé
     * @return String du nom de la classe de l'étudiant
     */
    public String returnClasse(int id_etudiant){
        if(charge.getInscription() != null)
            for(Inscription i : charge.getInscription())
                if(i.getEtudiant().getID() == id_etudiant)
                    return i.getClasse().getNom();
                    
        return null;
    }
    
    /**returnClasseP : retourne le nom de la classe d'un enseignant 
     *
     * @param id_prof type int : ID de l'enseignant demandé
     * @return String du professeur recherché
     */
    public String returnClasseP(int id_prof){
        if(charge.getEnseignement() != null)
            for(Enseignement i : charge.getEnseignement())
                if(i.getEnseignant().getID() == id_prof)
                    return i.getClasse().getNom();
                    
        return null;
    }
    
    /**retourneniveau : retourne l'ensemble des noms des niveaux excistant dans la BDD
     *
     * @return Tableau de String de l'ensemble des niveaux
     */
    public String[] retourneniveau(){
        
        //Déclaration d'un tableau de String
        String[] a = null;
        
        //Recherche des niveaux
        if(ecole.getNiveaux() != null){
            a = new String[ecole.getNiveaux().size()];
            for(int i=0; i<a.length; i++){
                a[i] = ecole.getNiveaux().get(i).getNom();
            }
        }

        //Le programme retourne a
        return a;
    }
    
    /**retourneclasse : retourne l'ensemble des classes d'un niveau contenues dans la BDD
     *
     * @param Id_niv type int : ID du niveau demandé
     * @return Tableau de String des nom des classes d'un niveau
     */
    public String[] retourneclasse(String Id_niv){
        
        //Déclaration d'un tableau de String
        String[] a = null;
        
        //Recherche des niveaux
        if(ecole.getNiveaux() != null)
            for(Niveau n : ecole.getNiveaux())
                if(n.getNom().equals(Id_niv))
                    if(n.getClasses() != null){
                         a = new String[n.getClasses().size()];
                         for(int i=0; i<a.length; i++)
                            a[i] = n.getClasses().get(i).getNom();
      
            }

        //Le programme retourne a
        return a;
    }
    
    /**retournematiere : retourne les matières d'une classe en fonction de son niveau
     *
     * @param classe type String : nom de la classe
     * @param niveau type String : nom du nievau
     * @return Tableau de String des matières extraites
     */
    public String[] retournematiere(String classe, String niveau){
        
        String[] a=null;
        ArrayList<String> b = new ArrayList<>();
        
        if(ecole.getNiveaux() != null)
            for(Niveau n : ecole.getNiveaux())
                if(n.getNom().equals(niveau))
                    if(n.getClasses() != null)
                        for(Classe c : n.getClasses())
                            if(c.getNom().equals(classe)) 
                                if(charge.getEnseignement() != null)
                                    for(Enseignement e : charge.getEnseignement())
                                        if(e.getClasse().getNom().equals(c.getNom()))
                                            b.add(e.getDiscipline().getNom());
        
        a = new String[b.size()];
        
        for(int i=0; i<a.length; i++)
            a[i] = b.get(i);
                              
        //Le programme retourne a
        return a;
    }
    
    /**returnmatiereP : retourne la matière d'un prof
     *
     * @param Id_prof type int : ID de l'enseignant demandé
     * @return String de la matière du professeur
     */
    public String returnmatiereP(int Id_prof){
        
        String a=null;
        
        if(charge.getEnseignement() != null)
            for(Enseignement e : charge.getEnseignement())
                if(e.getEnseignant().getID() == Id_prof)
                    return e.getDiscipline().getNom();
                              
        //Le programme retourne a
        return a;
    }
    
    /**retourne_eleves : permet d'extraire un élève de la BDD et de l'ajouter dans DefaultTableModel (pour l'afficher dans l'application graphique)
     *
     * @param id type int : ID de l'élève demandé
     * @return DefaultTableModel de l'élève pour permettre l'affichage graphique dans le tableau
     */
    public DefaultTableModel retourne_eleves(int id){
        
        //Création d'un objet DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();
        
        //Ajout des colonnes sur le tableau
        model.addColumn("Id");
        model.addColumn("Prenom");
        model.addColumn("Nom");
        
        //Recherche de toutes les classes
        if(charge.getInscription() != null)
            for(Inscription i : charge.getInscription())
                if(i.getClasse().getID() == id)
                    model.addRow(new Object[]{i.getEtudiant().getID(),i.getEtudiant().getPrenom(), i.getEtudiant().getNom()});
                    
        return model;
    }
    
    /**return_nom_classe : retourne le nom d'une classe grace aà son ID
     *
     * @param id type int : ID de la classe demandée
     * @return String du nom de classe en String
     */
    public String return_nom_classe(int id){
        
        //Recherche de toutes les classes
        if(ecole.getNiveaux() != null)
            for(Niveau n : ecole.getNiveaux())
                if(n.getClasses() != null)
                    for(Classe c : n.getClasses())
                        if(c.getID() == id)
                            return c.getNom();
        
        return null;              
    }
    
    /**modif_classe : modifie le nom d'une classe
     *
     * @param nom type String : nouveau nom de la classe
     * @param Id_classe type int : ID de la classe demandée
     */
    public void modif_classe(String nom, int Id_classe){
        
        if(ecole.getNiveaux() != null)
            for(Niveau n : ecole.getNiveaux())
                if(n.getClasses() != null)
                    for(Classe c : n.getClasses())
                        if(c.getID() == Id_classe)
                            if(!c.getNom().equals(nom)){
                                //Création d'un objet ClasseDAO
                                ClasseDAO classe_dao = new ClasseDAO(connect);
                                classe_dao.modifier(c, "Nom", nom);
                                c.setNom(nom);
                            }   
    }
       
    /** returnEvalData : retourne le tableau de toutes les notes de l ecole
     * 
     *@return Tableau de double avec tout les notes 
     */
    public double[] returnEvalData(){
        EvaluationDAO tranzit = new EvaluationDAO(connect);
        return tranzit.loadData();
    }
    
    /**returnAppreciation : retourne l'appréciation d'une évaluation
     *
     * @param Id_eval type int : ID de l'évaluation demandée
     * @return String de l'appreciation
     */
    public String returnAppreciation(int Id_eval){
        if(charge.getAnnees() != null)
            for(AnneeScolaire a : charge.getAnnees())
                if(a.getTrimestres() != null)
                    for(Trimestre t : a.getTrimestres())
                        if(t.getBulletins() != null)
                            for(Bulletin b : t.getBulletins())
                                if(b.getDetailBulletin() != null)
                                    for(DetailBulletin db : b.getDetailBulletin())
                                        if(db.getEvaluations() != null)
                                            for(Evaluation e : db.getEvaluations())
                                                if(e.getID() == Id_eval)
                                                    return e.getAppreciation();
        return null;                             
    }
    
    /**returnNote : retourne la valeur d'une évaluation en fonction de son ID
     *
     * @param Id_eval type int : ID de l'évaluation demandée
     * @return double : note
     */
    public double returnNote(int Id_eval){
        if(charge.getAnnees() != null)
            for(AnneeScolaire a : charge.getAnnees())
                if(a.getTrimestres() != null)
                    for(Trimestre t : a.getTrimestres())
                        if(t.getBulletins() != null)
                            for(Bulletin b : t.getBulletins())
                                if(b.getDetailBulletin() != null)
                                    for(DetailBulletin db : b.getDetailBulletin())
                                        if(db.getEvaluations() != null)
                                            for(Evaluation e : db.getEvaluations())
                                                if(e.getID() == Id_eval)
                                                    return e.getNote();
        return 0;                             
    }
    
    /**returnMatiere : retourne la matiere d'une évaluation en fonction de son ID
     *
     * @param Id_eval type int : ID de l'évaluation demandée
     * @return String du nom de la matiere
     */
    public String returnMatiere(int Id_eval){
        if(charge.getAnnees() != null)
            for(AnneeScolaire a : charge.getAnnees())
                if(a.getTrimestres() != null)
                    for(Trimestre t : a.getTrimestres())
                        if(t.getBulletins() != null)
                            for(Bulletin b : t.getBulletins())
                                if(b.getDetailBulletin() != null)
                                    for(DetailBulletin db : b.getDetailBulletin())
                                        if(db.getEvaluations() != null)
                                            for(Evaluation e : db.getEvaluations())
                                                if(e.getID() == Id_eval)
                                                    return db.getDiscipline().getNom();
        return null;                             
    }
    
    
}
