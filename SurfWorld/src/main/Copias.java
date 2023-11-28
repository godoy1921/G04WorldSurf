package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import data.cargaEvento;
import data.cargaSurfista;
import domain.Evento;
import domain.Surfista;

public class Copias {
	
	/*
	
	comboBoxBuscarPor.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String selected = (String) comboBoxBuscarPor.getSelectedItem();
            if (selected.equals("Surfista")) {
                // Llenar comboBox con surfistas
                ArrayList<Surfista> surfistas = cargaSurfista.cargarSurfistas();
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
                                
                                // Mostrar eventos relacionados con el surfista seleccionado en tablaEvento
                                // Utiliza surfista.getEventos() para obtener los eventos relacionados con el surfista
                                // y muestra estos eventos en tablaEvento
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
                                
                                // Mostrar surfistas relacionados con el evento seleccionado en tablaSurfista
                                // Utiliza evento.getParticipantes() para obtener los surfistas relacionados
                                // y muestra estos surfistas en tablaSurfista
                                break;
                            }
                        }
                    }
                });
            }
        }
    }); */

	
	
}
