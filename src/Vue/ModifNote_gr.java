/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controler.Controleur;

/** ModifNote_gr : classe graphique
 *
 * @author Flora
 */
public class ModifNote_gr extends javax.swing.JFrame {

    /** Attribut prive de la classe : id_etudiant , a et Id_eval */
    private Controleur a;
    private int id_etudiant;
    private int Id_eval;
    /**
     * Creates new form ModifNote_gr
     * @param control de type Controleur
     * @param ID de type int
     * @param Id_eval de type int
     */
    public ModifNote_gr(Controleur control, int ID, int Id_eval) {
        initComponents();
        a = control;
        id_etudiant = ID;
        this.Id_eval = Id_eval;
            
        Appreciation.setText(a.returnAppreciation(Id_eval));
        Note.setText(""+a.returnNote(Id_eval));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jPanel60 = new javax.swing.JPanel();
        jPanel59 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        Modif = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Retour1 = new javax.swing.JButton();
        Appreciation = new javax.swing.JTextField();
        Note = new javax.swing.JTextField();

        jLabel7.setText("Matière :");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel60.setBackground(new java.awt.Color(255, 255, 255));

        jPanel59.setBackground(new java.awt.Color(153, 0, 40));

        jLabel34.setBackground(new java.awt.Color(0, 240, 240));
        jLabel34.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Note");

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel59Layout.createSequentialGroup()
                .addContainerGap(446, Short.MAX_VALUE)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(349, 349, 349))
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel34)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        Modif.setBackground(new java.awt.Color(153, 0, 0));
        Modif.setForeground(new java.awt.Color(255, 255, 255));
        Modif.setText("Modifier");
        Modif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifActionPerformed(evt);
            }
        });

        jLabel8.setText("Note :");

        jLabel9.setText("Appréciation : ");

        Retour1.setBackground(new java.awt.Color(153, 0, 0));
        Retour1.setForeground(new java.awt.Color(255, 255, 255));
        Retour1.setText("Retour");
        Retour1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Retour1ActionPerformed(evt);
            }
        });

        Appreciation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AppreciationActionPerformed(evt);
            }
        });

        Note.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel8)
                        .addGap(57, 57, 57)
                        .addComponent(Note, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(Appreciation, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addGap(402, 402, 402)
                        .addComponent(Modif)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                    .addContainerGap(801, Short.MAX_VALUE)
                    .addComponent(Retour1)
                    .addGap(52, 52, 52)))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(Appreciation, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Note, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addComponent(Modif)
                .addGap(139, 139, 139))
            .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                    .addContainerGap(460, Short.MAX_VALUE)
                    .addComponent(Retour1)
                    .addGap(24, 24, 24)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel60, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ModifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifActionPerformed
        this.setVisible(false);
        a.modifier_evaluation(Id_eval, Double.valueOf(Note.getText()), Appreciation.getText());
        Bulletin_gr test = new Bulletin_gr(a, id_etudiant);
        test.setVisible(true);
        test.setLocationRelativeTo(null);
    }//GEN-LAST:event_ModifActionPerformed

    private void Retour1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Retour1ActionPerformed
        this.setVisible(false);
        Bulletin_gr test= new Bulletin_gr(a, id_etudiant);
        test.setVisible(true);
        test.setLocationRelativeTo(null);
    }//GEN-LAST:event_Retour1ActionPerformed

    private void AppreciationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AppreciationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AppreciationActionPerformed

    private void NoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Appreciation;
    private javax.swing.JButton Modif;
    private javax.swing.JTextField Note;
    private javax.swing.JButton Retour1;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel60;
    // End of variables declaration//GEN-END:variables
}
