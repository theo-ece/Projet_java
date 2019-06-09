/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

/** Histogramme : classe pour l histogramme de notes
 *
 * @author thebo
 */
import javax.swing.JFrame;
import java.awt.*;      
import javax.swing.*;    

public class Histogramme extends JFrame {

    /** Attributs prives de la classe data et canvas */
    private static double[] data;
    private DrawCanvas canvas;

    /** Contructeur surchage
     * @param d de type double[]*/
    public Histogramme(double[] d) {
        data = new double[d.length];
        System.arraycopy(d, 0, data, 0, d.length);
        canvas = new DrawCanvas();
        canvas.setPreferredSize(new Dimension(1000, 400));

        // Set the Drawing JPanel as the JFrame's content-pane
        Container cp = getContentPane();
        cp.add(canvas);

        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();              // Either pack() the components; or setSize()
        setTitle("Répartition des notes");
        setVisible(true);

    }

    private class DrawCanvas extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);
            int sizeD = data.length;
            int sizeX = 1000;
            int[] tsizeX = new int[4];
            int sizeY = 50;
            double somme=0;
            double var=0;
            double[] tab_size = new double[4];
            // Calcul de la somme
            for(int i=0;i<sizeD;i++){
                somme = somme + data[i];
            }
            // Calcul de la varience
            for(int i = 0;i<sizeD;i++){
                var = ( Math.pow(2, data[i] - somme/sizeD));
            }
            var = var/sizeD;
            for (int i = 0; i < sizeD; i++) {
                if (data[i] < 5 && data[i] >= 0) {
                    tab_size[0] = tab_size[0] + 1;
                } else if (data[i] < 10 && data[i] >= 5) {
                    tab_size[1] = tab_size[1] + 1;
                } else if (data[i] < 15 && data[i] >= 10) {
                    tab_size[2] = tab_size[2] + 1;
                } else if (data[i] >= 15) {
                    tab_size[3] = tab_size[3] + 1;
                }
            }
            for (int i = 0; i < 4; i++) {
                tsizeX[i] = (int) (sizeX * (tab_size[i] / sizeD));
            }
            // notes entre 0 & 5
            g.setColor(Color.BLACK);
            g.drawString("% Notes [0 ; 5]", 5, 25);
            g.drawString(100 * (tab_size[0] / sizeD) + " %", 150 + tsizeX[0], 25);
            g.setColor(Color.GRAY);
            g.fillRect(100, 0, tsizeX[0], sizeY);

            // notes entre 5 & 10
            g.setColor(Color.BLACK);
            g.drawString("% Notes [5 ; 10]", 5, 60 + 25);
            g.drawString(100 * (tab_size[1] / sizeD) + " %", 150 + tsizeX[1], 60 + 25);
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(100, 60, tsizeX[1], sizeY);

            // notes entre 10 & 15
            g.setColor(Color.BLACK);
            g.drawString("% Notes [10 ; 15]", 5, 120 + 25);
            g.drawString(100 * (tab_size[2] / sizeD) + " %", 150 + tsizeX[2], 120 + 25);
            g.setColor(Color.GRAY);
            g.fillRect(100, 120, tsizeX[2], sizeY);

            // notes entre 15 & 20
            g.setColor(Color.BLACK);
            g.drawString("% Notes [15 ; 20]", 5, 180 + 25);
            g.drawString(100 * (tab_size[3] / sizeD) + " %", 150 + tsizeX[3], 180 + 25);
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(100, 180, tsizeX[3], sizeY);
            g.setColor(Color.BLACK);
            g.fillRect(10, 240, 960, 10);
            
            // détail des statistiques
            g.drawString("Nombre de Note(s) :"+data.length, 20, 280);
            g.drawString("Variance :"+var, 20, 310);
        }
    }
}
