package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.cargaSurfista;
import domain.Surfista;

public class CargaSurfistaTest {

	@Test
    public void testCargarSurfistas() {
        ArrayList<Surfista> surfistas = cargaSurfista.cargarSurfistas();

        // Verificar si la lista no está vacía
        assertNotNull(surfistas);

        // Verificar si se cargaron algunos surfistas 
        assertTrue(surfistas.size() > 0);

        // Pruebas especificas para los surfistas cargados
        Surfista primerSurfista = surfistas.get(0);
        assertEquals(1, primerSurfista.getIdSurfista());
        assertEquals("Gabriel Medina", primerSurfista.getNombre());
        assertEquals("Brasil", primerSurfista.getPaisOrigen());
        assertEquals(3, primerSurfista.getPuestoRanking());
        
        
        // Asegurar que el ranking de todos los surfistas sea mayor que cero
        for (Surfista surfista : surfistas) {
            assertTrue(surfista.getPuestoRanking() > 0);
        }
    }

}
