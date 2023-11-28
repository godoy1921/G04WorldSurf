package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domain.Evento;
import domain.Surfista;



public class cargaEvento {
	
	public static List<Evento> cargarEventos() {
        String csvFile = "resources\\Eventos.csv";
        String line = "";
        String cvsSplitBy = ",";

        List<Evento> eventos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // Ignorar la primera línea (encabezados)

            while ((line = br.readLine()) != null) {
                String[] datos = line.split(cvsSplitBy);

                // Verificar si hay suficientes datos antes de intentar procesar la línea
                if (datos.length >= 4) {
                    int idEvento = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    String fechaInicio = datos[2];
                    String fechaFin = datos[3];
                    
                    List<Surfista> surfistasEvento = new ArrayList<Surfista>();
                    
                    
                    	
                  //Se crea un surfista por cada id
        			for (int i=4; i<datos.length; i++) {
        				//Sólo se da valor al atributo id del surfista
        				
        				List<Surfista> surfistas = cargaSurfista.cargarSurfistas();
        				for(Surfista surfista: surfistas) {
                        	if(surfista.getIdSurfista() == Integer.parseInt(datos[i])) {
                        		surfistasEvento.add(surfista);
        			}
        				}
        			}

                    // Crear objeto Evento y agregarlo a la lista
                    Evento evento = new Evento(idEvento, nombre, fechaInicio, fechaFin, surfistasEvento);
                    eventos.add(evento);
                } else {
                    System.out.println("Error en línea: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        // Imprimir la lista de eventos creados
        //for (Evento evento : eventos) {
            //System.out.println(evento);
        //}
        
        return eventos;
    }

}
