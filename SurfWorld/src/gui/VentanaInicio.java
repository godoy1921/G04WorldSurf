package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import data.cargaEvento;
import data.cargaSurfista;
import domain.Evento;
import domain.Surfista;

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
import java.util.List;


public class VentanaInicio extends JPanel {
    private JComboBox<String> comboBoxBuscarPor;
    private JComboBox<String> comboBoxElementos;
    private JTable tablaSurfista;
    private JTable tablaEvento;

    public VentanaInicio() {
        setName("Ventana de Inicio");
        setSize(800, 600);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

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

        //comboBoxBuscarPor.addActionListener(new ActionListener() {
            //public void actionPerformed(ActionEvent e) {
                //String selected = (String) comboBoxBuscarPor.getSelectedItem();
                //if (selected.equals("Surfista")) {
                    // Llenar comboBox con surfistas
                    // Mostrar detalles de surfista seleccionado en tablaSurfista y eventos relacionados en tablaEvento
                //} else if (selected.equals("Evento")) {
                    // Llenar comboBox con eventos
                    // Mostrar detalles de evento seleccionado en tablaEvento y surfistas relacionados en tablaSurfista
                //}
            //}
        //});
        
        comboBoxBuscarPor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selected = (String) comboBoxBuscarPor.getSelectedItem();
                if (selected.equals("Surfista")) {
                    // Llenar comboBox con surfistas
                    ArrayList<Surfista> surfistas = cargaSurfista.cargarSurfistas();
                    List<Evento> eventos = cargaEvento.cargarEventos();
                    comboBoxElementos.removeAllItems(); // Limpiar combobox antes de agregar nuevos elementos
                    for (Surfista surfista : surfistas) {
                        comboBoxElementos.addItem(surfista.getNombre()); // Agregar nombres de surfistas al combobox
                    }
                    
                    comboBoxElementos.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String selectedSurfista = (String) comboBoxElementos.getSelectedItem();
                            for (Surfista surfista : surfistas) {
                                if (surfista.getNombre().equals(selectedSurfista)) {
                                    // Mostrar detalles del surfista seleccionado en tablaSurfista
                                    // Aquí debes implementar la lógica para mostrar los detalles del surfista en tablaSurfista
                                	String[] surfistaDetails = {
                                            String.valueOf(surfista.getIdSurfista()),
                                            surfista.getNombre(),
                                            surfista.getPaisOrigen(),
                                            String.valueOf(surfista.getPuestoRanking())
                                        };
                                	
                                	//Tabla de surfista
                                	DefaultTableModel modelSurfista = new DefaultTableModel();
                                    modelSurfista.setColumnIdentifiers(new String[]{"ID", "Nombre", "País Origen", "Puesto Ranking"});
                                    modelSurfista.addRow(surfistaDetails);
                                    tablaSurfista.setModel(modelSurfista);
                                    // Mostrar eventos relacionados con el surfista seleccionado en tablaEvento
                                    // Utiliza surfista.getEventos() para obtener los eventos relacionados con el surfista
                                    // y muestra estos eventos en tablaEvento
                                    
                                 // Mostrar eventos relacionados con el surfista seleccionado en tablaEvento
                                    ArrayList<Evento> eventosRelacionados = new ArrayList<>();
                                    for (Evento evento : eventos) {
                                        if (evento.getParticipantes().contains(surfista)) {
                                            eventosRelacionados.add(evento);
                                        }
                                    }
                                    DefaultTableModel modelEvento = new DefaultTableModel();
                                    modelEvento.setColumnIdentifiers(new String[]{"ID", "Nombre", "Fecha Inicio", "Fecha Fin"});
                                    for (Evento evento : eventosRelacionados) {
                                        modelEvento.addRow(new String[]{
                                                String.valueOf(evento.getIdEvento()),
                                                evento.getNombre(),
                                                evento.getFechaInicio(),
                                                evento.getFechaFin()
                                        });
                                    }
                                    tablaEvento.setModel(modelEvento);
                                    
                                    break;
                                }
                            }
                        }
                    });
                } else if (selected.equals("Evento")) {
                    // Llenar comboBox con eventos
                    List<Evento> eventos = cargaEvento.cargarEventos();
                    comboBoxElementos.removeAllItems(); // Limpiar combobox antes de agregar nuevos elementos
                    for (Evento evento : eventos) {
                        comboBoxElementos.addItem(evento.getNombre()); // Agregar nombres de eventos al combobox
                    }
                    
                    comboBoxElementos.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String selectedEvento = (String) comboBoxElementos.getSelectedItem();
                            for (Evento evento : eventos) {
                                if (evento.getNombre().equals(selectedEvento)) {
                                    // Mostrar detalles del evento seleccionado en tablaEvento
                                    // Aquí debes implementar la lógica para mostrar los detalles del evento en tablaEvento
                                	
                                	String[] eventoDetails = {
                                            String.valueOf(evento.getIdEvento()),
                                            evento.getNombre(),
                                            evento.getFechaInicio(),
                                            evento.getFechaFin()
                                        };
                                        DefaultTableModel modelEvento = new DefaultTableModel();
                                        modelEvento.setColumnIdentifiers(new String[]{"ID", "Nombre", "Fecha Inicio", "Fecha Fin"});
                                        modelEvento.addRow(eventoDetails);
                                        tablaEvento.setModel(modelEvento);
                                    
                                    // Mostrar surfistas relacionados con el evento seleccionado en tablaSurfista
                                    // Utiliza evento.getParticipantes() para obtener los surfistas relacionados
                                    // y muestra estos surfistas en tablaSurfista
                                        
                                     // Mostrar surfistas que participan en el evento seleccionado en tablaSurfista
                                        List<Surfista> participantes = evento.getParticipantes();
                                        DefaultTableModel modelSurfista = new DefaultTableModel();
                                        modelSurfista.setColumnIdentifiers(new String[]{"ID", "Nombre", "País Origen", "Puesto Ranking"});
                                        for (Surfista surfista : participantes) {
                                            modelSurfista.addRow(new String[]{
                                                    String.valueOf(surfista.getIdSurfista()),
                                                    surfista.getNombre(),
                                                    surfista.getPaisOrigen(),
                                                    String.valueOf(surfista.getPuestoRanking())
                                            });
                                        }
                                        tablaSurfista.setModel(modelSurfista);
                                        break;
                                }
                            }
                        }
                    });
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
                new VentanaInicio();
            }
        });
    }
}

