package main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import data.cargaEvento;
import data.cargaResultadoEvento;
import data.cargaSurfista;
import domain.Evento;
import domain.ResultadoEvento;
import domain.Surfista;
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
                
                ArrayList<Surfista> surfistas = cargaSurfista.cargarSurfistas();
                for(Surfista surfista: surfistas) {
                	System.out.println(surfista.getNombre());
                }
                
                List<ResultadoEvento> resultadoEventos = cargaResultadoEvento.cargarResultadoEventos();
                for(ResultadoEvento resultadoEvento : resultadoEventos) {
                	System.out.println(resultadoEvento);
                }
                
            }
            
		});

	}

}
