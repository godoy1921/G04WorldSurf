package main;

import java.util.List;

import javax.swing.SwingUtilities;

import data.cargaEvento;
import data.cargaSurfista;
import domain.Evento;
import gui.interfazGrafica;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new interfazGrafica();
                List<Evento> eventos = cargaEvento.cargarEventos();
                for(Evento evento: eventos) {
                	System.out.println(evento.getNombre());
                }
                
            }
            
		});

	}

}
