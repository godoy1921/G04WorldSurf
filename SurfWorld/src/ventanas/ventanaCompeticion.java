package ventanas;

import javax.swing.*;

public class ventanaCompeticion {
    public ventanaCompeticion() {
        JFrame frame = new JFrame("Ventana de Competición");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Simulación de Competición de Surf");
        JPanel panel = new JPanel();
        panel.add(label);
        frame.add(panel);

        frame.setVisible(true);
    }
}

