package gui;

import domain.Evento;
import com.toedter.calendar.JCalendar;

import data.cargaEvento;

import com.toedter.calendar.*;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ventanaEventos {
    private List<Evento> eventos;

    public ventanaEventos(List<Evento> eventos) {
        this.eventos = eventos;

        JFrame frame = new JFrame("Calendario de Eventos");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JCalendar calendar = new JCalendar();
        frame.add(calendar);

        highlightEventDays(calendar);

        frame.setVisible(true);
    }

    private void highlightEventDays(JCalendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        calendar.getDayChooser().addPropertyChangeListener(e -> {
            if (e.getPropertyName().equals("day")) {
                Date selectedDate = calendar.getDate();
                for (Evento evento : eventos) {
                    try {
                        Date fechaInicio = sdf.parse(evento.getFechaInicio());
                        Date fechaFin = sdf.parse(evento.getFechaFin());

                        if (selectedDate.after(fechaInicio) && selectedDate.before(fechaFin)) {
                            calendar.getDayChooser().setDayForeground(selectedDate, getColorForEvent(evento));
                        }
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    private Color getColorForEvent(Evento evento) {
        // Aquí puedes definir lógica para asignar colores diferentes según el evento
        // Por ejemplo, para dos eventos distintos, podrías devolver rojo y azul.
        // En este ejemplo, se devolverá un color aleatorio.
        return new Color((int) (Math.random() * 0x1000000));
    }

    public static void main(String[] args) {
        // Obtener eventos (reemplazar esto con tu lógica para obtener eventos)
        List<Evento> eventos = obtenerEventos();

        // Crear la ventana del calendario con los eventos
        new ventanaEvento(eventos);
    }

    private static List<Evento> obtenerEventos() {
        // Lógica para obtener eventos
        return null;
    }
}
