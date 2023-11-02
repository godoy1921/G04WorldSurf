package ventanas;

import javax.swing.*;

public class ventanaInicio extends JFrame {
    public ventanaInicio() {
        JFrame frame = new JFrame("Ventana de Inicio");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Bienvenido a la aplicación de surf");
        JPanel panel = new JPanel();
        panel.add(label);
        frame.add(panel);

        frame.setVisible(true);
    }
}
