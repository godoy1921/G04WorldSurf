package gui;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import data.cargaEvento;
import domain.Evento;
import domain.Surfista;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ventanaEventos {
    private DefaultTableModel tableModel;

    public ventanaEventos() {
        JFrame frame = new JFrame("Calendario de Eventos");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JCalendar calendar = new JCalendar();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(calendar, BorderLayout.CENTER);

        // Crear el JTable para mostrar la información del evento y los surfistas
        tableModel = new DefaultTableModel(new String[]{"Evento", "Surfistas"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.SOUTH);

        // Agregar listener al JCalendar para detectar la selección de fecha
        calendar.getDayChooser().addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                if (e.getPropertyName().equals("day")) {
                    Date selectedDate = calendar.getDate(); // Obtener la fecha del calendario
                    cargarInformacionEventos(selectedDate);
                }
            }
        });


        frame.add(panel);
        frame.setVisible(true);
    }

    private void cargarInformacionEventos(java.util.Date selectedDate) {
        // Obtener eventos del archivo de datos (reemplaza esto por tu lógica para obtener eventos)
        List<Evento> eventos = cargaEvento.cargarEventos();

        // Limpiar la tabla
        tableModel.setRowCount(0);

        // Buscar eventos para la fecha seleccionada
        for (Evento evento : eventos) {
            // Convertir las fechas de String a Date
            java.util.Date fechaInicio = parsearFecha(evento.getFechaInicio());
            java.util.Date fechaFin = parsearFecha(evento.getFechaFin());

            if (selectedDate.after(fechaInicio) && selectedDate.before(fechaFin)) {
                // Agregar evento y surfistas a la tabla
            	StringBuilder surfistas = new StringBuilder();
                for (Surfista surfista : evento.getParticipantes()) {
                    surfistas.append(surfista.getNombre()).append(", ");
                }
                tableModel.addRow(new Object[]{evento.getNombre(), surfistas.toString()});
                
             
            }
        }
    }

    // Método para convertir String a Date
    private java.util.Date parsearFecha(String fecha) {
        // Implementa tu lógica para parsear la fecha
        // Aquí se asume un formato de fecha dd-MM-yyyy
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return formatter.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        // Crear la ventana del calendario
        SwingUtilities.invokeLater(() -> new ventanaEventos());
    }
}

