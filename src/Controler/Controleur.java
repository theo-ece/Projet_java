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
    
    /** Attribut prive de la classe : charge et connect */
    Chargement charge;
    Connexion connect;
    
    Ecole ecole = null;
    
    /** Constructeur par defaut */
    public Controleur(){
        charge = null;
        connect = null;
    }
    
    /** Constructeur surcharge avec un seul parametre : ecole
     * @param charge
     * @param connect */
    public Controleur(Chargement charge, Connexion connect){
        this.charge = charge;
        this.connect = connect;
    }
    
    /** setEcole : permet de modifier l attribut ecole
     * @param ecole */
    public void setEcole(Ecole ecole){
        this.ecole = ecole;
    }
    
    /** getEcole : permet d afficher l attribut ecole
     * @return  */
    public Ecole getEcole(){
        return ecole;
    }

    
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
    
    
    public Controleur returncontrol(){
        return this;
    }
    
    /** returnecole :
     * @return
     * @throws java.sql.SQLException  */
    public String[] returnecole() throws SQLException
    {
        
        String[] e = new String[charge.getEcoles().size()];
        
        for(int i=0; i<e.length; i++){
            e[i] = charge.getEcoles().get(i).getNom();
        }
        
        return e;
                
    }
    
    
    public void choix_ecole(String ecole){
        for(Ecole e : charge.getEcoles())
            if(e.getNom().equals(ecole))
                this.setEcole(e);
    }
    
    
    public void ajouter_ecole(String nom, String region){

        //Déclaration de variables
        int id, id_ins;
        
        //Ajout d'une école dans la BDD
        Ecole ecole = new Ecole(nom,region);
        EcoleDAO ecole_dao = new EcoleDAO(connect);
        id = ecole_dao.ajouter(ecole);
        
        //Modification de l'array list de Ecole
        ecole.setID(id);
        
        //ajout dans array list
        charge.addEcoles(ecole);
        
       
        //Vérification de l'ajout
        //Déclaration d'une variable boolean
        boolean test = false;
        
        //Vérification si le nouvel étudiant est dans l'ArrayList
        for(Ecole e : charge.getEcoles())
            if(e.getID() == id){
                test = true;
            } 
        
        //Affichage du résultat
        if(test) System.out.println("Ajout réussi.");
        else System.out.println("Ajout impossible.");
    }
    
    /**
     * @return  */
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
                for(Inscription i : charge.getInscription())
                    if(i.getEtudiant().getID() == map.getValue().getID())
                        for(Niveau niv : ecole.getNiveaux())
                            for(Classe cl : niv.getClasses())
                                if(cl.getID() == i.getClasse().getID())
                                    model.addRow(new Object[]{map.getValue().getID(),map.getValue().getPrenom(), map.getValue().getNom(),i.getClasse().getNom(), niv.getNom()});
        }
        
        return model;
    }
    
    /**
     * @return  */
    public DefaultTableModel returnenseignant(){
        
        //Création d'un objet DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();
        
        //Déclaration de String
        String Classe ="", Discipline="", Niveau="";
        
        //Ajout des colonnes sur le tableau
        model.addColumn("Id");
        model.addColumn("Prenom");
        model.addColumn("Nom");
        model.addColumn("Classe");
        model.addColumn("Niveau");
        model.addColumn("Discipline");
        
        
        //Recherche de tous les enseignants
        for(Map.Entry<Integer,Enseignant> map : ecole.getEnseignants().entrySet()){
            for(Enseignement e : charge.getEnseignement()){
                if(e.getEnseignant().getID() == map.getValue().getID()){
                    for(Niveau niv : ecole.getNiveaux()){ 
                        for(Classe cl : niv.getClasses()){
                            if(cl.getID() == e.getClasse().getID()){
                                model.addRow(new Object[]{map.getValue().getID(),map.getValue().getPrenom(), map.getValue().getNom(),cl.getNom(), niv.getNom(), e.getDiscipline().getNom()});            
                            }
                        }
                    }
                }
            }                                           
        }
        
        return model;
    }
    
    

    
    /** choix_ecole : permet de mettre a jour l ecole par celle choisie par l utilisateur
     * @param id
     */
    public void choix_ecole(int id){
        
        //Recherche de l'école parmi celle de l'arraylist de charge et modification de l'attribut ecole
        for(Ecole e : charge.getEcoles())
            if(e.getID() == id)
                setEcole(e);
        
    }
    
   
    
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
        for(Niveau niv : ecole.getNiveaux())
            if(niv.getNom().equals(niveau))
                for(Classe cl : niv.getClasses()){
                    
                    //Ajout dans la BDD
                    Inscription inscription = new Inscription(cl,etudiant);
                    id_ins = inscription.inscription(connect);
                    
                    //Ajout dans l'arrylist d'inscription
                    inscription.setID(id_ins);
                    charge.addInscriptions(inscription);
                }

        //Vérification de l'ajout
        //Déclaration d'une variable boolean
        boolean test = false;
        
        //Vérification si le nouvel étudiant est dans l'ArrayList
        for(Map.Entry<Integer,Etudiant> map : ecole.getEtudiants().entrySet())
            if(map.getValue().getID() == id){
                test = true;
            } 
        
        //Affichage du résultat
        if(test) System.out.println("Ajout réussi.");
        else System.out.println("Ajout impossible.");
    }
    
    
    public DefaultTableModel rechercher_etudiant(int id) throws HashExistant, HashInexistant{
        
        //Création d'un objet DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();
        
        //Ajout des colonnes sur le tableau
        model.addColumn("Id");
        model.addColumn("Prenom");
        model.addColumn("Nom");
        model.addColumn("Classe");
        model.addColumn("Niveau");
            
        //Recherche dans l'ArrayList d'etudiants
            for(Map.Entry<Integer,Etudiant> map : ecole.getEtudiants().entrySet())
                if(map.getKey() == id)
                    for(Inscription i : charge.getInscription())
                        if(i.getEtudiant().getID() == map.getValue().getID())
                            for(Niveau niv : ecole.getNiveaux())
                                for(Classe cl : niv.getClasses())
                                    if(cl.getID() == i.getClasse().getID())
                                        model.addRow(new Object[]{map.getValue().getID(),map.getValue().getPrenom(), map.getValue().getNom(),i.getClasse().getNom(), niv.getNom()});
                
            
        return model;
        
    }
    
    
    public void modifier_etudiant(int id, String champ, String element){
        
        //Recherche de l'Etudiant
        for(Map.Entry<Integer,Etudiant> map : ecole.getEtudiants().entrySet())
            if(map.getKey() == id){
                
                //Modification dans la BDD
                PersonneDAO etudiant_dao = new PersonneDAO(connect);
                etudiant_dao.modifier(map.getValue(), champ, element);
                
                //Modification dans Etudiant
                if("Id".equals(champ)) map.getValue().setID(Integer.valueOf(element));
                else if("Nom".equals(champ)) map.getValue().setNom(element);
                else if("Prenom".equals(champ)) map.getValue().setPrenom(element);
            }
    }
    
        
    public void supprimer_etudiant(int id){
        
        //Suppression dans la BDD
        PersonneDAO personne_dao = new PersonneDAO(connect);
        personne_dao.supprimer(personne_dao.trouver(id));
        
        //Supprimer dans l'arraylist Etudiants
        ecole.getEtudiants().remove(id);
        
    }
    
    
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
        
        for(Niveau n : ecole.getNiveaux())
            for(Classe c : n.getClasses())
                System.out.println(c.getNom());
        
        //Ajout d'un enseignement dans la BDD
        for(Niveau niv : ecole.getNiveaux()){
            if(niv.getNom().equals(niveau)){
                System.out.println("Niveau: " + niv.getNom());
                for(Classe cl : niv.getClasses()){   
                    System.out.println("Cl:" + cl.getNom());
                    if(cl.getNom().equals(classe)){
                        System.out.println("Classe: " + cl.getNom());
                        for(Map.Entry<Integer,Etudiant> map : cl.getEtudiants().entrySet()){
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
                

        //Vérification de l'ajout
        //Déclaration d'une variable boolean
        boolean test = false;
        
        //Vérification si le nouvel étudiant est dans l'ArrayList
        for(Map.Entry<Integer,Enseignant> map : ecole.getEnseignants().entrySet())
            if(map.getValue().getID() == id){
                test = true;
            } 
        
        //Affichage du résultat
        if(test) System.out.println("Ajout réussi.");
        else System.out.println("Ajout impossible.");
    }
    
  
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
            for(Map.Entry<Integer,Enseignant> map : ecole.getEnseignants().entrySet())
                if(map.getKey() == id)
                    for(Enseignement e : charge.getEnseignement())
                        if(e.getEnseignant().getID() == map.getValue().getID())
                            for(Niveau niv : ecole.getNiveaux())
                                for(Classe cl : niv.getClasses())
                                    if(cl.getID() == e.getClasse().getID())
                                        model.addRow(new Object[]{map.getValue().getID(),map.getValue().getPrenom(), map.getValue().getNom(),cl.getNom(), niv.getNom(), e.getDiscipline().getNom()});            
                                   
        return model;
    }
    
    
    public void modifier_enseignant(int id, String champ, String element){
        
        //Recherche de l'Enseignant
        for(Map.Entry<Integer,Enseignant> map : ecole.getEnseignants().entrySet())
            if(map.getKey() == id){
                
                //Modification dans la BDD
                PersonneDAO enseignant_dao = new PersonneDAO(connect);
                enseignant_dao.modifier(map.getValue(), champ, element);
                
                //Modification dans Enseignant
                if("Id".equals(champ)) map.getValue().setID(Integer.valueOf(element));
                else if("Nom".equals(champ)) map.getValue().setNom(element);
                else if("Prenom".equals(champ)) map.getValue().setPrenom(element);
            }
    }
       
    
    public void supprimer_enseignant(int id){
        
        //Suppression dans la BDD
        PersonneDAO personne_dao = new PersonneDAO(connect);
        personne_dao.supprimer(personne_dao.trouver(id));
        
        //Supprimer dans l'arraylist Enseignants
        ecole.getEnseignants().remove(id);
        
    }
    
    
    public void ajouter_classe(String nom, String niveau, String annee){

        //Déclaration d'un objet int
        int id;
        
        //Ajout d'un enseignant dans la BDD
        Classe classe = new Classe(nom);
        ClasseDAO classe_dao = new ClasseDAO(connect);
        id = classe_dao.ajouter(classe);
        
        //Modification de l'ID
        classe.setID(id);
        
        //Modification de l'année
        for(AnneeScolaire an : charge.getAnnees())
            if(an.getDate().equals(annee))
                classe.setAnnee(an);
        
        //Modification de l'école
        classe.setEcole(ecole);
                        
        //Modification du niveau et ajout de la classe dans le niveau
        for(Niveau niv : ecole.getNiveaux())
            if(niv.getNom().equals(niveau)){
                classe.setNiveau(niv);
                niv.addClasses(classe);
            }
                

        //Vérification de l'ajout
        //Déclaration d'une variable boolean
        boolean test = false;
        
        //Vérification si le nouvel étudiant est dans l'ArrayList
        for(Niveau n : ecole.getNiveaux())
            for(Classe c : n.getClasses())
                if(c.getID() == id){
                    test = true;
                } 
        
        //Affichage du résultat
        if(test) System.out.println("Ajout réussi.");
        else System.out.println("Ajout impossible.");
    }
    
    
    public Classe rechercher_classe(int id) throws HashExistant, HashInexistant{
        try{
            //Recherche dans l'ArrayList d'etudiants
            for(Niveau niveau : ecole.getNiveaux())
                for(Classe classe : niveau.getClasses())
                    if(classe.getID() == id) return classe;
           
            throw new HashExistant();
        }
        catch(NullPointerException e){
            throw new HashInexistant("Classe " + id + " non existante");
        }
    }
    
    
    public String[] returnannee(){
        
        String[] a = new String[charge.getAnnees().size()];
        
        for(int i=0; i<a.length; i++){
            a[i] = charge.getAnnees().get(i).getDate();
        }
        
        return a;
    }
    
    
    public String[] returntrimestre(int id_annee){
        
        String s;
        String[] a = null;
        
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
    
    
    public DefaultTableModel returnbulletins(int Id_etudiant, int Id_trimestre){
        
        //Création d'un objet DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();
  
        //Ajout des colonnes sur le tableau
        model.addColumn("Id");
        model.addColumn("Discipline");
        model.addColumn("Professeur");
        model.addColumn("Note");
        model.addColumn("Appréciation");
                    
        for(AnneeScolaire a : charge.getAnnees())
            if(a.getTrimestres() != null)
                for(Trimestre t : a.getTrimestres())
                    if(t.getID() == Id_trimestre)
                        if(t.getBulletins() != null)
                            for(Bulletin b : t.getBulletins())
                                if(b.getEtudiant().getID() == Id_etudiant)
                                    if(b.getDetailBulletin() != null)
                                        for(DetailBulletin d : b.getDetailBulletin())
                                            for(Evaluation e : d.getEvaluations())
                                                model.addRow(new Object[]{e.getID(),d.getDiscipline().getNom(), d.getEnseignant().getNom(), e.getNote(), e.getAppreciation()});

        
        return model;
    }

    
    public String returnappreciation(int Id_etudiant, int Id_trimestre){
        
        //Recherche du bulletin
        for(AnneeScolaire a : charge.getAnnees())
            if(a.getTrimestres() != null)
                for(Trimestre t : a.getTrimestres())
                    if(t.getID() == Id_trimestre)
                        if(t.getBulletins() != null)
                            for(Bulletin b : t.getBulletins())
                                return b.getAppreciation();
        
        
        return null;
    } 
    
    
    public int recherchetrimestres(String annee, String trimestre){
        
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
    
    
    public double moyenne(int Id_etudiant, int Id_trimestre){
        
        //Déclaration de variables
        double moyenne = 0;
        int nb=0;
        
        //On recherche les notes
        for(AnneeScolaire a : charge.getAnnees())
            if(a.getTrimestres() != null)
                for(Trimestre t : a.getTrimestres())
                    if(t.getID() == Id_trimestre)
                        if(t.getBulletins() != null)
                            for(Bulletin b : t.getBulletins())
                                if(b.getEtudiant().getID() == Id_etudiant)
                                    if(b.getDetailBulletin() != null)
                                        for(DetailBulletin d : b.getDetailBulletin())
                                            for(Evaluation e : d.getEvaluations()){
                                                nb++;
                                                moyenne += e.getNote();
                                            }                                            
        moyenne = moyenne/nb;
        return moyenne;                                
    }
    

    
    public void supprimer_evaluation(int id){
        //Suppression dans la BDD
        EvaluationDAO evaluation_dao = new EvaluationDAO(connect);
        evaluation_dao.supprimer(evaluation_dao.trouver(id));
        
        //Suppression dans l'arraylist Evaluation
        for(AnneeScolaire annee : charge.getAnnees())
            if(annee.getTrimestres() != null)
                for(Trimestre trimestre : annee.getTrimestres())
                    if(trimestre.getBulletins() != null)
                        for(Bulletin bulletin : trimestre.getBulletins())
                            if(bulletin.getDetailBulletin() != null)
                                for(DetailBulletin detail : bulletin.getDetailBulletin())
                                    if(detail.getEvaluations() != null)
                                        for(int i=0; i<detail.getEvaluations().size(); i++)
                                            if(detail.getEvaluations().get(i).getID() == id)
                                                detail.getEvaluations().remove(i);
    }
    
    
    public void modifier_evaluation(int id_etudiant, int id_trimestre, int id, int id_annee, String champ, String element){
        
        //Recherche de l'Evaluation
        for(AnneeScolaire annee : charge.getAnnees())
            if(annee.getID() == id_annee)
                if(annee.getTrimestres() != null)
                    for(Trimestre trimestre : annee.getTrimestres())
                        if(trimestre.getID() == id_trimestre)
                            if(trimestre.getBulletins() != null)
                                for(Bulletin bulletin : trimestre.getBulletins())
                                    for(Inscription inscription : charge.getInscription())
                                        if(inscription.getEtudiant() != null)
                                            if(inscription.getEtudiant().getID() == id_etudiant){
                                                bulletin.setEtudiant(inscription.getEtudiant());
                                                for(Enseignement ens : charge.getEnseignement())
                                                    if(bulletin.getDetailBulletin() != null)
                                                        for(DetailBulletin detail : bulletin.getDetailBulletin())
                                                            for(Evaluation eval : detail.getEvaluations())
                                                                if(eval.getID() == id){

                                                                    //Modification dans la BDD
                                                                    EvaluationDAO evaluation_dao = new EvaluationDAO(connect);
                                                                    evaluation_dao.modifier(eval, champ, element);

                                                                    //Modifier dans l'arrayList Evalutations
                                                                    if("Note".equals(champ)) eval.setNote(Double.valueOf(element));
                                                                    else if("Appreciation".equals(champ)) eval.setAppreciation(element);
                                                                }

                                                }

                                
    }
    
    
    public void ajouter_evaluation(int id_etudiant, String annee, String discipline, String trimestre, String prof, double note, String appreciation){
        
        //Déclaration de variables
        int id;
        
        //Création d'un objet discipline
        Discipline disc;
        
        //Déclaration d'un objet Evaluation
        Evaluation evaluation = new Evaluation(note, appreciation);
        
        for(AnneeScolaire a : charge.getAnnees())
            if(a.getDate().equals(annee))
                if(a.getTrimestres() != null)
                    for(Trimestre t : a.getTrimestres()){
                        String s = t.getDebut() + " / " + t.getFin();
                        if(s.equals(trimestre))
                            if(t.getBulletins() != null)
                                for(Bulletin b : t.getBulletins())
                                    if(b.getDetailBulletin() != null)
                                        for(DetailBulletin db : b.getDetailBulletin())
                                            for(Enseignement e : charge.getEnseignement())
                                                if(e.getDiscipline() != null)
                                                    if(e.getDiscipline().getNom().equals(discipline))
                                                        evaluation.setDetailsB(db);
                                        
                    }
        
        //Ajout evaluation dans la BDD
        EvaluationDAO evaluation_dao = new EvaluationDAO(connect);
        id = evaluation_dao.ajouter(evaluation);
        
        //Ajout dans l'arraylist
        for(AnneeScolaire a : charge.getAnnees())
            if(a.getDate().equals(annee))
                if(a.getTrimestres() != null)
                    for(Trimestre t : a.getTrimestres()){
                        String s = t.getDebut() + " / " + t.getFin();
                        if(s.equals(trimestre))
                            if(t.getBulletins() != null)
                                for(Bulletin b : t.getBulletins())
                                    if(b.getInscription().getEtudiant().getID() == id_etudiant)
                                        if(b.getDetailBulletin() != null)
                                            for(DetailBulletin db : b.getDetailBulletin()){
                                                evaluation.setID(id);
                                                db.getEvaluations().add(evaluation);
                                                
                                            }
                                                           
                    }
        
    }
    
    
    public String[] return_disciplines(int Id_etudiant){
        
        ArrayList<String> a = new ArrayList<>();
                
        for(Inscription ins : charge.getInscription())
            if(ins.getEtudiant() != null)
                if(ins.getEtudiant().getID() == Id_etudiant)
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
    
    
    public String return_prof(String discipline, int Id_etudiant){
                
        for(Inscription ins : charge.getInscription())
            if(ins.getEtudiant() != null)
                if(ins.getEtudiant().getID() == Id_etudiant)
                    for(Enseignement ens : charge.getEnseignement())
                        if(ens.getDiscipline() != null)
                            if(ens.getDiscipline().getNom().equals(discipline))
                                if(ens.getClasse().getID() == ins.getClasse().getID())
                                    return ens.getEnseignant().getNom();          
        
        return null;
                            
    }
       
}
