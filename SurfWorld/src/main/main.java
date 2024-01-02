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
import gui.InterfazGrafica;
import persistence.GestorBD;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //new interfazGrafica();
                
                GestorBD gestorBD = new GestorBD();
                
                //Se crea la BBDD
                gestorBD.crearBBDD();
                
                //Se cargan los datos y se inicializa la BBDD
                gestorBD.initilizeFromCSV();
                
                //Se recupera la lista de Comics y Personajes
        		List<Surfista> surfistas = gestorBD.getSurfistas();		
        		List<Evento> eventos = gestorBD.getEventos();
        		
        		System.out.println(surfistas);
        		System.out.println(eventos);
        		//Lambda expression para abrir la ventana Principal
        		SwingUtilities.invokeLater(() -> new InterfazGrafica());
        		
        		//Se borran los datos
        		gestorBD.borrarDatos();
        				
        		//Se borra la BBDD
        		gestorBD.borrarBBDD();
                
                /*
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
                }*/
                
            }
            
		});

	}

}
