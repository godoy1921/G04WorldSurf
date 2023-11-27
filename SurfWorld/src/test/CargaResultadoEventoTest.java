package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import data.cargaResultadoEvento;
import domain.ResultadoEvento;

public class CargaResultadoEventoTest {

	@Test
    public void testCargarResultadoEventos() {
        List<ResultadoEvento> resultadoEventos = cargaResultadoEvento.cargarResultadoEventos();

        // Verificar si la lista no está vacía
        assertNotNull(resultadoEventos);

        // Verificar si se cargaron algunos resultados de eventos 
        assertTrue(resultadoEventos.size() > 0);

        // Realizar pruebas específicas para los resultados de eventos cargados
        // Por ejemplo, asegurarse de que el primer resultado de evento tenga el ID correcto, evento asociado, surfista asociado, etc.
        ResultadoEvento primerResultadoEvento = resultadoEventos.get(0);
        assertEquals(1, primerResultadoEvento.getIdResultado());
        assertNotNull(primerResultadoEvento.getEvento());
        assertNotNull(primerResultadoEvento.getSurfista());
        assertEquals(2, primerResultadoEvento.getPosicion());
        
        
        // Asegurar que todos los resultados de eventos tengan un evento y un surfista asociado
        for (ResultadoEvento resultadoEvento : resultadoEventos) {
            assertNotNull(resultadoEvento.getEvento());
            assertNotNull(resultadoEvento.getSurfista());
        }
    }

}
