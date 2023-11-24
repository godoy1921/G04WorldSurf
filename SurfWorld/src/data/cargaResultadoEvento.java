package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domain.Evento;
import domain.ResultadoEvento;
import domain.Surfista;


public class cargaResultadoEvento {
	
	public static void main(String[] args) {
        String csvFile = "resources\\resultadoEventos.csv";
        String line = "";
        String cvsSplitBy = ",";

        List<Evento> resultadoEventos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // Ignorar la primera línea (encabezados)

            while ((line = br.readLine()) != null) {
                String[] datos = line.split(cvsSplitBy);

                // Verificar si hay suficientes datos antes de intentar procesar la línea
                if (datos.length >= 4) {
                    int idResultadoEvento = Integer.parseInt(datos[0]);
                    String nombreEvento = datos[1];
                    String nombreSurfista = datos[2];
                    int resultado = Integer.parseInt(datos[3]);
                    
                    List<Evento> eventos = cargaEvento.cargarEventos();
                    for(Evento evento: eventos) {
                    	if(evento.getNombre().equals(nombreEvento)) {
                    		Evento eventoX = evento;
                    	}
                    }

                    // Crear objeto Evento y agregarlo a la lista
                    ResultadoEvento resultadoEvento = new ResultadoEvento(idResultadoEvento, eventoX, surfista, resultado, null);
                    eventos.add(evento);
                } else {
                    System.out.println("Error en línea: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        // Imprimir la lista de eventos creados
        for (Evento evento : eventos) {
            System.out.println(evento);
        }
    }

}
