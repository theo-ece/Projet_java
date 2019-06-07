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
public class Personne {
    
    protected String path;
    
    /** Attributs prives de la classe : iD, nom, prenom et type */
    protected int iD;
    protected String nom;
    protected String prenom;
    protected int type;
    
    //protected HashMap<Integer, Classe> classes;
    
    /** Constructeur par defaut */
    public Personne(){
        iD = 0;
        nom = "";
        prenom = "";
        type = 0;
    }
    
    /** Constructeur surcharge avec quatre parametres: ID, nom, prenom et type
     * @param ID
     * @param nom
     * @param prenom
     * @param type */
    public Personne(int ID, String nom, String prenom, int type){
        iD = ID;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
    }
    
    /** Constructeur surcharge avec trois parametres: nom, prenom et type
     * @param nom
     * @param prenom
     * @param type */
    public Personne(String nom, String prenom, int type){
        iD = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
    }
    
    /** getID : permettant d acceder a l attribut ID
     * @return  */
    public int getID(){
        return iD;
    }
    
    /** getNom : permettant d acceder a l attribut nom
     * @return  */
    public String getNom(){
        return nom;
    }
    
    /** getPrenom : permettant d acceder a l attribut prenom
     * @return  */
    public String getPrenom(){
        return prenom;
    }
    
    /** getType : permettant d acceder a l attribut type
     * @return  */
    public int getType(){
        return type;
    }

    /** setID : permettant de modifier l attribut ID
     * @param id */
    public void setID(int id){
        iD = id;
    }
    
    /** setPrenom : permettant de modifier l attribut prenom
     * @param prenom  */
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    
    /** setNom : permettant de modifier l attribut nom
     * @param nom  */
    public void setNom(String nom){
        this.nom = nom;
    }
    /*
    //La personne existe
    public Personne(int ID,int Type){
        iD = ID;
        type = Type;
        classes = new HashMap<>();
        
            //chargementClasse(ID);
            //Extraire les données de la BDD
        
    }
    

    // Creation d'une personne
    public Personne(int ID, String Nom, String Prenom, int typ){
        iD=ID;
        nom= Nom;
        prenom = Prenom;
        type = typ;
        chargementClasse(ID);
    }
    */
    // A completer selon le type et l'ID
   /* public void chargementClasse(int id){
        classes = new HashMap<>();
        /*
            ... ton code
        */
    /*}
    public void showClasses(String src){
        classes.keySet().forEach((key) -> {
            System.out.println(src + " -> Classe -> ["+ key + "] : " + classes.get(key));
        });
        String str="";
        do{
            str="";
            System.out.println("exit pour sortir");                        
            Scanner sc = new Scanner(System.in);            
            str = sc.nextLine(); 
            try{
                recherche_classe(str);
            }catch(HashInexistant e){   
            
            }
            catch(HashExistant e){
                classes.get(Integer.valueOf(str)).run(path, Integer.valueOf(str));
            }
        }while(!"exit".equals(str));
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
    
    public void ajout_classe(){
        String key="";
        System.out.println("ID de la classe a ajouter (BDD) : ");
        Scanner sc = new Scanner(System.in);
        key = sc.nextLine();
        try{
            recherche_classe(key);
        }catch(HashInexistant e){
            classes.put(Integer.valueOf(key), import_classe(Integer.valueOf(key)));
        }catch(HashExistant e){
            
        }
    }   // + modif BDD à faire
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
    }   // + modif BDD à faire & graph
    public Classe import_classe(int key){return new Classe(key);}*/
    
    
}
