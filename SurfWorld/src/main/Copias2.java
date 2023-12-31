package main;

import javax.swing.*;
import java.awt.*;

public class Copias extends JFrame {

    private static final int PARTICIPANTES = 16;
    private static final int NIVELES = 4;
    private static final int LADO = 50;

    public Copias() {
        setTitle("Torneo de Surf");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int[] posX = new int[PARTICIPANTES + 1];
                int nivelHeight = getHeight() / (NIVELES + 2);

                for (int nivel = 0; nivel <= NIVELES; nivel++) {
                    int nodeCount = (int) Math.pow(2, nivel);
                    int nivelWidth = getWidth() / (nodeCount + 1);

                    for (int i = 1; i <= nodeCount; i++) {
                        int x = i * nivelWidth;
                        int y = (nivel + 1) * nivelHeight;
                        g.drawRect(x - LADO / 2, y - LADO / 2, LADO, LADO);
                        posX[i] = x;
                        if (nivel > 0 && i <= nodeCount / 2) {
                            int childX1 = 2 * x - nivelWidth;
                            int childX2 = 2 * x + nivelWidth;
                            int childY = (nivel + 2) * nivelHeight;
                            g.drawLine(x, y - LADO / 2, childX1, childY - LADO / 2);
                            g.drawLine(x, y - LADO / 2, childX2, childY - LADO / 2);
                        }
                    }
                }
            }
        };

        panel.setPreferredSize(new Dimension(800, 550));
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Copias::new);
    }
}



