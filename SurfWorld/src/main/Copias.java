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

                int nivelHeight = getHeight() / (NIVELES + 1);
                int nivelWidth = getWidth() / (PARTICIPANTES / 2 + 1);

                for (int nivel = 0; nivel < NIVELES; nivel++) {
                    int y = (nivel + 1) * nivelHeight;

                    for (int i = 0; i < Math.pow(2, nivel) && i * 2 + 1 < PARTICIPANTES; i++) {
                        int x = (i + 1) * nivelWidth;

                        g.drawRect(x - LADO / 2, y - LADO / 2, LADO, LADO);

                        int childY = (nivel + 2) * nivelHeight;
                        int childX1 = (i * 2 + 1) * nivelWidth;
                        int childX2 = (i * 2 + 2) * nivelWidth;

                        g.drawLine(x, y, childX1, childY);
                        g.drawLine(x, y, childX2, childY);
                    }
                }
            }
        };

        panel.setPreferredSize(new Dimension(800, 500));
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Copias::new);
    }
}



