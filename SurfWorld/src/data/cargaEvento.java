package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domain.Evento;


public class cargaEvento {
	
	public static void main(String[] args) {
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

                    // Crear objeto Evento y agregarlo a la lista
                    Evento evento = new Evento(idEvento, nombre, fechaInicio, fechaFin, null);
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
