package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import domain.Surfista;



public class cargaSurfista {
    public static ArrayList<Surfista> cargarSurfistas() {
        String csvFile = "resources\\surfistas.csv";
        String line = "";
        String cvsSplitBy = ",";

        ArrayList<Surfista> surfistas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	
        	br.readLine();
        	
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(cvsSplitBy);
                
                //for (String dato : datos) {
                    //System.out.println("Dato: " + dato + datos[2]);
                //}
                //System.out.println("Dato: " + datos[1]);
                
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String pais = datos[2];
                int ranking = Integer.parseInt(datos[3]);

                // Crear objeto Surfista y agregarlo a la lista
                Surfista surfista = new Surfista(id, nombre, pais, ranking);
                surfistas.add(surfista);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Imprimir la lista de surfistas creados
        //for (Surfista surfista : surfistas) {
            //System.out.println(surfista);
        //}
        
        return surfistas;
    }
}
