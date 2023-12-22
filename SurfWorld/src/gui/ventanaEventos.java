package gui;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

import java.awt.*;

public class ventanaEventos {

    public ventanaEventos() {
        JFrame frame = new JFrame("Calendario de Eventos");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JCalendar calendar = new JCalendar();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(calendar, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Crear la ventana del calendario
        SwingUtilities.invokeLater(() -> new ventanaEventos());
    }
}

