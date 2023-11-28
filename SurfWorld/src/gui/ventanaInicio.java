package gui;

import javax.swing.*;

//public class ventanaInicio extends JFrame {
    //public ventanaInicio() {
        //JFrame frame = new JFrame("Ventana de Inicio");
        //frame.setSize(600, 400);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //JLabel label = new JLabel("Bienvenido a la aplicación de surf");
        //JPanel panel = new JPanel();
        //panel.add(label);
        //frame.add(panel);

        //frame.setVisible(true);
    //}
//}


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ventanaInicio extends JFrame {
    private JComboBox<String> comboBoxBuscarPor;
    private JComboBox<String> comboBoxElementos;
    private JTable tablaSurfista;
    private JTable tablaEvento;

    public void VentanaInicio() {
        setTitle("Ventana de Inicio");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JLabel labelBuscarPor = new JLabel("Buscar por:");
        comboBoxBuscarPor = new JComboBox<>(new String[]{"Surfista", "Evento"});
        topPanel.add(labelBuscarPor);
        topPanel.add(comboBoxBuscarPor);

        JPanel middlePanel = new JPanel();
        JLabel labelElementos = new JLabel("Seleccione:");
        comboBoxElementos = new JComboBox<>();
        middlePanel.add(labelElementos);
        middlePanel.add(comboBoxElementos);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        tablaSurfista = new JTable();
        tablaEvento = new JTable();
        JScrollPane scrollPaneSurfista = new JScrollPane(tablaSurfista);
        JScrollPane scrollPaneEvento = new JScrollPane(tablaEvento);
        bottomPanel.add(scrollPaneSurfista);
        bottomPanel.add(scrollPaneEvento);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(middlePanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        add(panel);

        comboBoxBuscarPor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selected = (String) comboBoxBuscarPor.getSelectedItem();
                if (selected.equals("Surfista")) {
                    // Llenar comboBox con surfistas
                    // Mostrar detalles de surfista seleccionado en tablaSurfista y eventos relacionados en tablaEvento
                } else if (selected.equals("Evento")) {
                    // Llenar comboBox con eventos
                    // Mostrar detalles de evento seleccionado en tablaEvento y surfistas relacionados en tablaSurfista
                }
            }
        });

        setVisible(true);
    }

    // Métodos para cargar datos en JComboBox y mostrar detalles en JTable según la selección
    // Implementa lógica para cargar surfistas, eventos y su visualización en las tablas

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ventanaInicio();
            }
        });
    }
}

