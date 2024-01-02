package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazGrafica {
    private JFrame frame;
    private JPanel contentPane;
    private CardLayout cardLayout;
    private VentanaInicio ventanaInicio;
    private VentanaEventos ventanaEventos;
    private VentanaCompeticion ventanaCompeticion;

    public InterfazGrafica() {
        frame = new JFrame("Aplicación de Surf");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        contentPane = new JPanel();
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);

        ventanaInicio = new VentanaInicio();
        ventanaEventos = new VentanaEventos();
        ventanaCompeticion = new VentanaCompeticion();

        // Integrar los JFrames en JPanels para usarlos con CardLayout
        //JPanel panelInicio = ventanaInicio.getPanel();
        //JPanel panelEventos = ventanaEventos.getPanel();
       

        // Agregar los JPanels al contentPane con un nombre asociado a cada uno
        contentPane.add(ventanaInicio, "Inicio");
        contentPane.add(ventanaEventos, "Eventos");
        contentPane.add(ventanaCompeticion, "Competición");

        JButton inicioButton = new JButton("Inicio");
        JButton eventosButton = new JButton("Eventos");
        JButton competicionButton = new JButton("Competición");

        inicioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPane, "Inicio");
            }
        });

        eventosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPane, "Eventos");
            }
        });

        competicionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPane, "Competición");
            }
        });

        JPanel headerPanel = new JPanel();
        headerPanel.add(inicioButton);
        headerPanel.add(eventosButton);
        headerPanel.add(competicionButton);

        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(contentPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InterfazGrafica();
            }
        });
    }
}

