/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

/**
 *
 * @author thebo
 */
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/** CEvoluation : graphique moyenne
 *
 * @author thebo
 */
public class CEvolution extends JFrame  {

    /** Attributs prives de la classe moyenneT et canvas */
    private ArrayList<Double> moyenneT = new ArrayList<>();
    private DrawCanvas canvas;

    /** Constructeur surcharge avec un parametre a
     * @param a de type ArrayList de double */
    public CEvolution(ArrayList<Double> a) {
        moyenneT=a;
        canvas = new DrawCanvas();
        canvas.setPreferredSize(new Dimension(1000, 400));
        Container cp = getContentPane();
        cp.add(canvas);
        pack();
        setTitle("Evolution sur l'année");
        setVisible(true);

    }

    private class DrawCanvas extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);
            int sizeM = moyenneT.size();
            int rayon = 20;
            int[] poseX = new int[sizeM];
            int[] poseY = new int[sizeM];
            // Affichage du graph des moyennes
            for(int i=0;i<sizeM;i++){
                poseX[i]= 40+ i*(800/sizeM);
                poseY[i]= (int) (300 - ((moyenneT.get(i)/20)*200));
            }
            g.setColor(Color.BLUE);
            for(int i=0;i<sizeM-1;i++){
                g.drawLine(poseX[i]+rayon/2, poseY[i]+rayon/2, poseX[i+1]+rayon/2, poseY[i+1]+rayon/2);
            }
            for(int i = 0;i<sizeM;i++){
                g.setColor(Color.RED);
                g.fillOval(poseX[i], poseY[i], rayon, rayon);
                g.setColor(Color.BLACK);
                g.drawString("Moyenne : " + moyenneT.get(i), poseX[i] + rayon + 15, poseY[i] + rayon + 15);                
            }
            g.setColor(Color.BLACK);
            g.fillRect(10, 300, 960, 10);
            
            // détail des statistiques
            g.drawString("Courbe d'évolution des moyennes trimestrielles", 20, 340);
        }
    }
}
