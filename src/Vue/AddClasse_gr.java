/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controler.Controleur;

/** AddClasse_gr : classe graphique
 *
 * @author Flora
 */
public class AddClasse_gr extends javax.swing.JFrame {

    /** Attribut prive de la classe : a */
    private Controleur a;
    
    /**
     * Creates new form AddClasse_gr
     * @param control de type Controleur
     */
    public AddClasse_gr(Controleur control) {
        initComponents();
        a = control;
        
        //Affichage des niveaux
        for(int i=0; i<a.retourneniveau().length; i++)
            Niveaux.addItem(a.retourneniveau()[i]);
        
        //Affichage des années
        for(int i=0; i<a.returnannee().length; i++)
            Annees.addItem(a.returnannee()[i]);
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
        Modif = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        Nom = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Niveaux = new javax.swing.JComboBox<>();
        annee = new javax.swing.JLabel();
        Annees = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel60.setBackground(new java.awt.Color(255, 255, 255));

        jPanel59.setBackground(new java.awt.Color(153, 0, 40));

        jLabel34.setBackground(new java.awt.Color(0, 240, 240));
        jLabel34.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Classe");

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel59Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(367, 367, 367))
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel34)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        Retour.setBackground(new java.awt.Color(153, 0, 0));
        Retour.setForeground(new java.awt.Color(255, 255, 255));
        Retour.setText("Retour");
        Retour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RetourActionPerformed(evt);
            }
        });

        Modif.setBackground(new java.awt.Color(153, 0, 0));
        Modif.setForeground(new java.awt.Color(255, 255, 255));
        Modif.setText("Ajouter Classe");
        Modif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifActionPerformed(evt);
            }
        });

        jLabel35.setText("Nom :");

        Nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NomActionPerformed(evt);
            }
        });

        jLabel6.setText("Niveau :");

        Niveaux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NiveauxActionPerformed(evt);
            }
        });

        annee.setText("Année : ");

        Annees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnneesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Retour)
                .addGap(88, 88, 88))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jLabel6)
                .addGap(26, 26, 26)
                .addComponent(Niveaux, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addComponent(annee)
                .addGap(18, 18, 18)
                .addComponent(Annees, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(jLabel35)
                .addGap(26, 26, 26)
                .addComponent(Nom, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Modif)
                .addGap(402, 402, 402))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel6)
                    .addComponent(Niveaux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(annee)
                    .addComponent(Annees, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addComponent(Modif)
                .addGap(104, 104, 104)
                .addComponent(Retour)
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RetourActionPerformed
        this.setVisible(false);
        Classe_gr test= new Classe_gr(a);
        test.setVisible(true);
        test.setLocationRelativeTo(null);
    }//GEN-LAST:event_RetourActionPerformed

    private void ModifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifActionPerformed
        a.ajouter_classe(Nom.getText(), Niveaux.getItemAt(Niveaux.getSelectedIndex()), Annees.getItemAt(Annees.getSelectedIndex()));
        this.setVisible(false);
        Classe_gr test= new Classe_gr(a);
        test.setVisible(true);
        test.setLocationRelativeTo(null);
    }//GEN-LAST:event_ModifActionPerformed

    private void NomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NomActionPerformed

    private void NiveauxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NiveauxActionPerformed

    }//GEN-LAST:event_NiveauxActionPerformed

    private void AnneesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnneesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AnneesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Annees;
    private javax.swing.JButton Modif;
    private javax.swing.JComboBox<String> Niveaux;
    private javax.swing.JTextField Nom;
    private javax.swing.JButton Retour;
    private javax.swing.JLabel annee;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    // End of variables declaration//GEN-END:variables
}
