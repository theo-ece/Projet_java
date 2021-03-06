/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controler.Controleur;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/** Bulletin_gr : classe graphique
 *
 * @author lro
 */
public class Bulletin_gr extends javax.swing.JFrame {

    /** Attributs prives de la classe : a et Id_etudiant */
    private Controleur a;
    private int Id_etudiant;
    
    /** Construction surcharge avec un parametre control
     * Creates new form Bulletin
     * @param control de type Controleur
     * @param Id_etudiant de type int
     */
    public Bulletin_gr(Controleur control, int Id_etudiant) {
        initComponents();
        a = control;
        this.Id_etudiant = Id_etudiant;
        
        //Affichage des années
        for(int i=0; i<a.returnannee().length; i++)
            annee.addItem(a.returnannee()[i]);
        annee.setSelectedItem(null);
        trimestres.setSelectedItem(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Retour = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        bulletins = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        trimestres = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        Moyenne = new javax.swing.JTextField();
        Appreciation = new javax.swing.JTextField();
        Addnote = new javax.swing.JButton();
        Modifnote = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        annee = new javax.swing.JComboBox<>();
        supprimer_note = new javax.swing.JButton();
        Statistiques = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(153, 0, 40));

        jLabel1.setBackground(new java.awt.Color(0, 240, 240));
        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Bulletin");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(441, 441, 441)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Retour.setBackground(new java.awt.Color(153, 0, 0));
        Retour.setForeground(new java.awt.Color(255, 255, 255));
        Retour.setText("Retour");
        Retour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RetourActionPerformed(evt);
            }
        });

        bulletins.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Discipline", "Professeur", "Note", "Appréciation"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        bulletins.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bulletinsMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(bulletins);

        jLabel2.setText("Trimestre :");

        jLabel3.setText("Appréciation générale :");

        trimestres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trimestresActionPerformed(evt);
            }
        });

        jLabel4.setText("Moyenne :");

        Moyenne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MoyenneActionPerformed(evt);
            }
        });

        Appreciation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AppreciationActionPerformed(evt);
            }
        });

        Addnote.setBackground(new java.awt.Color(153, 0, 0));
        Addnote.setForeground(new java.awt.Color(255, 255, 255));
        Addnote.setText("Ajouter une note");
        Addnote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddnoteActionPerformed(evt);
            }
        });

        Modifnote.setBackground(new java.awt.Color(153, 0, 0));
        Modifnote.setForeground(new java.awt.Color(255, 255, 255));
        Modifnote.setText("Modifier une note");
        Modifnote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifnoteActionPerformed(evt);
            }
        });

        jLabel5.setText("Année scolaire :");

        annee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anneeActionPerformed(evt);
            }
        });

        supprimer_note.setBackground(new java.awt.Color(153, 0, 0));
        supprimer_note.setForeground(new java.awt.Color(255, 255, 255));
        supprimer_note.setText("Supprimer une note");
        supprimer_note.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimer_noteActionPerformed(evt);
            }
        });

        Statistiques.setBackground(new java.awt.Color(153, 0, 0));
        Statistiques.setForeground(new java.awt.Color(255, 255, 255));
        Statistiques.setText("Statistiques");
        Statistiques.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatistiquesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Moyenne, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(trimestres, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(annee, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(Appreciation, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(Statistiques)))
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Modifnote, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(Addnote)
                        .addGap(31, 31, 31)
                        .addComponent(supprimer_note)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Retour))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE))
                .addGap(38, 38, 38))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(annee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(trimestres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(Moyenne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Appreciation, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(Statistiques)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Retour)
                    .addComponent(Addnote)
                    .addComponent(Modifnote)
                    .addComponent(supprimer_note))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RetourActionPerformed
        this.setVisible(false);
        ModifEtudiant_gr test= new ModifEtudiant_gr(a, Id_etudiant);
        test.setVisible(true);
        test.setLocationRelativeTo(null);
    }//GEN-LAST:event_RetourActionPerformed

    private void bulletinsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bulletinsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bulletinsMouseClicked

    private void trimestresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trimestresActionPerformed
        if(a.returntrimestre(trimestres.getSelectedIndex()+1) != null){
            DefaultTableModel model = new DefaultTableModel();
            model.setRowCount(0);
            bulletins.setModel(model);
            Appreciation.setText("");
            Moyenne.setText("");
                
            if(a.returnbulletins(Id_etudiant, a.recherchetrimestres(annee.getItemAt(annee.getSelectedIndex()), trimestres.getItemAt(trimestres.getSelectedIndex()))) != null){
                //Affichage du bulletin
                bulletins.setModel(a.returnbulletins(Id_etudiant, a.recherchetrimestres(annee.getItemAt(annee.getSelectedIndex()), trimestres.getItemAt(trimestres.getSelectedIndex()))));
                Moyenne.setText(""+a.moyenne(Id_etudiant, a.recherchetrimestres(annee.getItemAt(annee.getSelectedIndex()), trimestres.getItemAt(trimestres.getSelectedIndex()))));
                Appreciation.setText(a.returnappreciation(Id_etudiant, a.recherchetrimestres(annee.getItemAt(annee.getSelectedIndex()), trimestres.getItemAt(trimestres.getSelectedIndex()))));
            }
        }
    }//GEN-LAST:event_trimestresActionPerformed

    private void MoyenneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MoyenneActionPerformed
        
    }//GEN-LAST:event_MoyenneActionPerformed

    private void AppreciationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AppreciationActionPerformed
        
    }//GEN-LAST:event_AppreciationActionPerformed

    private void AddnoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddnoteActionPerformed
        this.setVisible(false);
        Note_gr test= new Note_gr(a, Id_etudiant);
        test.setVisible(true);
        test.setLocationRelativeTo(null);
    }//GEN-LAST:event_AddnoteActionPerformed

    private void ModifnoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifnoteActionPerformed
        int ligne = bulletins.getSelectedRow();
        if(ligne != -1){
            int id = (int) bulletins.getValueAt(ligne, 0);
            this.setVisible(false);
            ModifNote_gr test= new ModifNote_gr(a, Id_etudiant, id);
            test.setVisible(true);
            test.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_ModifnoteActionPerformed

    private void anneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anneeActionPerformed
        if(a.returntrimestre(annee.getSelectedIndex()+1) != null){
            
            trimestres.removeAllItems();
            DefaultTableModel model = new DefaultTableModel();
            model.setRowCount(0);
            bulletins.setModel(model);
            Appreciation.setText("");
            Moyenne.setText("");
                
            for(int i=0; i<a.returntrimestre(annee.getSelectedIndex()+1).length; i++)
                trimestres.addItem(a.returntrimestre(annee.getSelectedIndex()+1)[i]);
            trimestres.setSelectedIndex(0);
            
            if(a.returnbulletins(Id_etudiant, a.recherchetrimestres(annee.getItemAt(annee.getSelectedIndex()), trimestres.getItemAt(trimestres.getSelectedIndex()))) != null){
                //Affichage du bulletin
                bulletins.setModel(a.returnbulletins(Id_etudiant, a.recherchetrimestres(annee.getItemAt(annee.getSelectedIndex()), trimestres.getItemAt(trimestres.getSelectedIndex()))));
                Moyenne.setText(""+a.moyenne(Id_etudiant, a.recherchetrimestres(annee.getItemAt(annee.getSelectedIndex()), trimestres.getItemAt(trimestres.getSelectedIndex()))));
                Appreciation.setText(a.returnappreciation(Id_etudiant, a.recherchetrimestres(annee.getItemAt(annee.getSelectedIndex()), trimestres.getItemAt(trimestres.getSelectedIndex()))));
                
            }
            else{
                Appreciation.setText("");
                Moyenne.setText("");
            }
 
        }
        else {
            trimestres.removeAllItems();
            DefaultTableModel model = new DefaultTableModel();
            model.setRowCount(0);
            bulletins.setModel(model);
            Appreciation.setText("");
            Moyenne.setText("");           
        }
    }//GEN-LAST:event_anneeActionPerformed

    private void supprimer_noteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimer_noteActionPerformed
        int ligne = bulletins.getSelectedRow();
        if(ligne != -1){
            int id = (int) bulletins.getValueAt(ligne, 0);
            a.supprimer_evaluation(Integer.valueOf(id));
            this.setVisible(false);
            Bulletin_gr test= new Bulletin_gr(a, Id_etudiant);
            test.setVisible(true);
            test.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_supprimer_noteActionPerformed

    private void StatistiquesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatistiquesActionPerformed
        this.setVisible(true);
        ArrayList<Double> data = new ArrayList<>();
        int it=0;
        while(trimestres.getItemAt(it)!=null){
            data.add(a.moyenne(Id_etudiant, a.recherchetrimestres(annee.getItemAt(annee.getSelectedIndex()), trimestres.getItemAt(it))));
            it++;
        }
        new CEvolution(data);        
    }//GEN-LAST:event_StatistiquesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Addnote;
    private javax.swing.JTextField Appreciation;
    private javax.swing.JButton Modifnote;
    private javax.swing.JTextField Moyenne;
    private javax.swing.JButton Retour;
    private javax.swing.JButton Statistiques;
    private javax.swing.JComboBox<String> annee;
    private javax.swing.JTable bulletins;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton supprimer_note;
    private javax.swing.JComboBox<String> trimestres;
    // End of variables declaration//GEN-END:variables
}
