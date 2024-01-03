package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazGrafica {
    private JFrame frame;
    private JPanel contentPane;
    private JPanel headerPanel;
    private CardLayout cardLayout;
    private VentanaInicio ventanaInicio;
    private VentanaEventos ventanaEventos;
    private VentanaCompeticion ventanaCompeticion;

    public InterfazGrafica() {
        frame = new JFrame("Aplicación de Surf");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        
        contentPane = new JPanel();  
        contentPane.setPreferredSize(new Dimension(1000, 600));
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);

        ventanaInicio = new VentanaInicio();
        ventanaEventos = new VentanaEventos();
        ventanaCompeticion = new VentanaCompeticion();

        // Crear un JLabel para el logo y cargar la imagen desde un archivo
        ImageIcon logo = new ImageIcon("surfworld\\img\\logo.png"); // Ruta a tu archivo de imagen
        Image logoImg = logo.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon logoScaled = new ImageIcon(logoImg);
        JLabel logoLabel = new JLabel(logoScaled);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir margen

                      
        // Agregar los JPanels al contentPane con un nombre asociado a cada uno
        contentPane.add(ventanaInicio, "Inicio");
        contentPane.add(ventanaEventos, "Eventos");
        contentPane.add(ventanaCompeticion, "Competición");

     // Crear los botones con texto HTML para el subrayado
        JButton inicioButton = new JButton("<html><u>INICIO</u></html>");
        JButton eventosButton = new JButton("<html><u>EVENTOS</u></html>");
        JButton competicionButton = new JButton("<html><u>COMPETICION</u></html>");

        // Establecer el tamaño preferido para asegurarse de que se muestra el texto completo
        inicioButton.setPreferredSize(new Dimension(80, 30));
        eventosButton.setPreferredSize(new Dimension(80, 30));
        competicionButton.setPreferredSize(new Dimension(80, 30));
        
     // Hacer que los botones no tengan un color de fondo visible
        inicioButton.setOpaque(false);
        inicioButton.setContentAreaFilled(false);
        inicioButton.setBorderPainted(false);

        eventosButton.setOpaque(false);
        eventosButton.setContentAreaFilled(false);
        eventosButton.setBorderPainted(false);

        competicionButton.setOpaque(false);
        competicionButton.setContentAreaFilled(false);
        competicionButton.setBorderPainted(false);

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

        headerPanel = new JPanel();
        Color darkBlue = new Color(60, 100, 170);
        headerPanel.setBackground(darkBlue);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.add(logoLabel);
        headerPanel.add(Box.createVerticalStrut(25)); 
        headerPanel.add(inicioButton);
        headerPanel.add(Box.createVerticalStrut(20)); 
        headerPanel.add(eventosButton);
        headerPanel.add(Box.createVerticalStrut(20)); 
        headerPanel.add(competicionButton);
        

        frame.add(headerPanel, BorderLayout.WEST);
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

