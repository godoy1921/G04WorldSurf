package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import data.cargaEvento;
import data.cargaSurfista;
import domain.Evento;
import domain.Surfista;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class VentanaInicio extends JPanel {
    private JComboBox<String> comboBoxBuscarPor;
    private JComboBox<String> comboBoxElementos;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JTable tablaSurfista;
    private JTable tablaEvento;

    public VentanaInicio() {
        setName("Ventana de Inicio");
        setSize(800, 600);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color lightBlue = new Color(135, 206, 250);
        setBackground(lightBlue);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        

        topPanel = new JPanel();
        Color darkBlue = new Color(60, 100, 170);
        topPanel.setBackground(darkBlue);
        JLabel labelBuscarPor = new JLabel("Buscar por:");
        comboBoxBuscarPor = new JComboBox<>(new String[]{"Surfista", "Evento"});
        topPanel.add(labelBuscarPor);
        topPanel.add(comboBoxBuscarPor);

        JPanel middlePanel = new JPanel();
        middlePanel.setBackground(darkBlue);
        JLabel labelElementos = new JLabel("Seleccione:");
        comboBoxElementos = new JComboBox<>();
        middlePanel.add(labelElementos);
        middlePanel.add(comboBoxElementos);

        bottomPanel = new JPanel(new GridLayout(1, 2));
        //bottomPanel.setBackground(darkBlue);
        String titulo = "       SURFISTAS                                                                                                                               EVENTOS";
        bottomPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(titulo));
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
                    ArrayList<Surfista> surfistas = cargaSurfista.cargarSurfistas();
                    List<Evento> eventos = cargaEvento.cargarEventos();
                    comboBoxElementos.removeAllItems(); // Limpiar combobox antes de agregar nuevos elementos
                    
                    //Ordena los surfistas recursivamente dependiendo del puesto de ranking para desplegarlos enel combobox
                    quicksort(surfistas, 0, surfistas.size() - 1);
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
    
    
    //Metodo recursivo para ordenar los surfistas por puestoRanking
    public void quicksort(List<Surfista> surfistas, int inicio, int fin) {
        if (inicio < fin) {
            // Obtiene el índice de partición
            int indiceParticion = particion(surfistas, inicio, fin);

            // Recursivamente ordena las dos sublistas
            quicksort(surfistas, inicio, indiceParticion - 1);
            quicksort(surfistas, indiceParticion + 1, fin);
        }
    }

    // Método de partición para quicksort
    private int particion(List<Surfista> surfistas, int inicio, int fin) {
        int pivote = surfistas.get(fin).getPuestoRanking();
        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {
            if (surfistas.get(j).getPuestoRanking() <= pivote) {
                i++;
                intercambiar(surfistas, i, j);
            }
        }

        intercambiar(surfistas, i + 1, fin);
        return i + 1;
    }

    // Método para intercambiar dos elementos en la lista
    private void intercambiar(List<Surfista> surfistas, int i, int j) {
        Surfista temp = surfistas.get(i);
        surfistas.set(i, surfistas.get(j));
        surfistas.set(j, temp);
    }

   

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaInicio();
            }
        });
    }
}

