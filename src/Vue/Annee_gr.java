/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controler.Controleur;

/** Annee_gr : classe graphique
 *
 * @author lro
 */
public class Annee_gr extends javax.swing.JFrame {

    /** Attribut prive de la classe a*/
    private Controleur a;
    
    /**
     * Creates new form Annee_gr
     * @param control de type Controleur
     */
    public Annee_gr(Controleur control) {
        initComponents();
        a = control;
        
        //affichage des années
        for(int i=0; i<a.returnannee().length; i++)
            annees_combo.addItem(a.returnannee()[i]);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel60 = new javax.swing.JPanel();
        jPanel59 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        Retour = new javax.swing.JButton();
        AddSemestre = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        Année = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        Debut = new javax.swing.JTextField();
        Fin = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        annees_combo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        AddAnnee = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel60.setBackground(new java.awt.Color(255, 255, 255));

        jPanel59.setBackground(new java.awt.Color(153, 0, 40));

        jLabel34.setBackground(new java.awt.Color(0, 240, 240));
        jLabel34.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Ajouter une année ou un trimestre");

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel59Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(311, 311, 311))
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel34)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Retour.setBackground(new java.awt.Color(153, 0, 0));
        Retour.setForeground(new java.awt.Color(255, 255, 255));
        Retour.setText("Retour");
        Retour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RetourActionPerformed(evt);
            }
        });

        AddSemestre.setBackground(new java.awt.Color(153, 0, 0));
        AddSemestre.setForeground(new java.awt.Color(255, 255, 255));
        AddSemestre.setText("Ajouter Trimestre");
        AddSemestre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddSemestreActionPerformed(evt);
            }
        });

        jLabel35.setText("Année scolaire :");

        Année.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnéeActionPerformed(evt);
            }
        });

        jLabel36.setText("Date de début :");

        Debut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DebutActionPerformed(evt);
            }
        });

        Fin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinActionPerformed(evt);
            }
        });

        jLabel6.setText("Date de fin :");

        annees_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annees_comboActionPerformed(evt);
            }
        });

        jLabel7.setText("Année scolaire :");

        AddAnnee.setBackground(new java.awt.Color(153, 0, 0));
        AddAnnee.setForeground(new java.awt.Color(255, 255, 255));
        AddAnnee.setText("Ajouter Annee");
        AddAnnee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddAnneeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addGap(560, 560, 560)
                .addComponent(AddSemestre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Retour)
                .addGap(65, 65, 65))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel60Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(AddAnnee)
                            .addGroup(jPanel60Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(42, 42, 42)
                                .addComponent(Année, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                        .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel36))))
                .addGap(80, 80, 80)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Debut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Fin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(annees_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(239, 239, 239))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Debut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addGap(76, 76, 76)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Année, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(Fin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(62, 62, 62)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(annees_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddSemestre)
                    .addComponent(Retour)
                    .addComponent(AddAnnee))
                .addGap(67, 67, 67))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RetourActionPerformed
        this.setVisible(false);
        Menu_gr test= new Menu_gr(a);
        test.setVisible(true);
        test.setLocationRelativeTo(null);
    }//GEN-LAST:event_RetourActionPerformed

    private void AddSemestreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddSemestreActionPerformed
        a.ajouter_trimestre(Debut.getText(), Fin.getText(), annees_combo.getItemAt(annees_combo.getSelectedIndex()));
        this.setVisible(false);
        Menu_gr test= new Menu_gr(a);
        test.setVisible(true);
        test.setLocationRelativeTo(null);
    }//GEN-LAST:event_AddSemestreActionPerformed

    private void AnnéeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnéeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AnnéeActionPerformed

    private void DebutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DebutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DebutActionPerformed

    private void FinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FinActionPerformed

    private void AddAnneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddAnneeActionPerformed
        a.ajouter_annee(Année.getText());
        this.setVisible(false);
        Menu_gr test= new Menu_gr(a);
        test.setVisible(true);
        test.setLocationRelativeTo(null);
    }//GEN-LAST:event_AddAnneeActionPerformed

    private void annees_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annees_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_annees_comboActionPerformed

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddAnnee;
    private javax.swing.JButton AddSemestre;
    private javax.swing.JTextField Année;
    private javax.swing.JTextField Debut;
    private javax.swing.JTextField Fin;
    private javax.swing.JButton Retour;
    private javax.swing.JComboBox<String> annees_combo;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel60;
    // End of variables declaration//GEN-END:variables
}