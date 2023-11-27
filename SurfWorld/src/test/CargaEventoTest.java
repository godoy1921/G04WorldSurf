package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import data.cargaEvento;
import domain.Evento;

public class CargaEventoTest {

	@Test
	public void testCargarEventos() {
		List<Evento> eventos = cargaEvento.cargarEventos();

        // Verificar si la lista no está vacía
        assertNotNull(eventos);

        // Verificar si se cargaron algunos eventos 
        assertTrue(eventos.size() > 0);

        // Realizar pruebas específicas para los eventos cargados
        // Por ejemplo, asegurarse de que el primer evento tenga el ID correcto, nombre, etc.
        Evento primerEvento = eventos.get(0);
        assertEquals(111, primerEvento.getIdEvento());
        assertEquals("World Surf League", primerEvento.getNombre());
        assertEquals("5-05-2024", primerEvento.getFechaInicio());
        assertEquals("5-10-2024", primerEvento.getFechaFin());
        
        // Asegurar de que todos los eventos tengan un nombre no nulo
        for (Evento evento : eventos) {
            assertNotNull(evento.getNombre());
        }
	}

}
