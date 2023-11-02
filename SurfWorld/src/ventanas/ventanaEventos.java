package ventanas;

import javax.swing.*;

public class ventanaEventos {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana de Eventos");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Calendario de Eventos de Surf");
        JPanel panel = new JPanel();
        panel.add(label);
        frame.add(panel);

        frame.setVisible(true);
    }
}
