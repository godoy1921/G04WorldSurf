package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import data.cargaEvento;
import domain.Evento;
import domain.Surfista;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Copias2 {
	private static DefaultTableModel calendarModel;
    private static DefaultTableModel eventsModel;
    private static JTable calendarTable;
    private static JTable eventsTable;
    private static LocalDate selectedDate;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calendario y Eventos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel calendarPanel = new JPanel(new BorderLayout());
        JPanel eventsPanel = new JPanel(new BorderLayout());

        calendarModel = new DefaultTableModel(6, 7);
        String[] daysOfWeek = {"Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"};
        calendarModel.setColumnIdentifiers(daysOfWeek);

        calendarTable = new JTable(calendarModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                comp.setBackground(Color.WHITE);
                Object value = getModel().getValueAt(row, column);
                if (value != null && value.equals(selectedDate.getDayOfMonth())) {
                    comp.setBackground(Color.CYAN);
                }
                return comp;
            }
        };
        calendarTable.setRowHeight(40);
        calendarTable.setIntercellSpacing(new Dimension(5, 5));
        calendarTable.setDefaultEditor(Object.class, null);

        JScrollPane calendarScrollPane = new JScrollPane(calendarTable);
        calendarPanel.add(createNavigationPanel(), BorderLayout.NORTH);
        calendarPanel.add(calendarScrollPane, BorderLayout.CENTER);

        eventsModel = new DefaultTableModel(new String[]{"Evento", "Surfistas"}, 0);
        eventsTable = new JTable(eventsModel);
        JScrollPane eventsScrollPane = new JScrollPane(eventsTable);
        eventsPanel.add(eventsScrollPane, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, calendarPanel, eventsPanel);
        splitPane.setDividerLocation(350); // Ajusta la posición del divisor
        frame.add(splitPane);

        calendarTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = calendarTable.rowAtPoint(e.getPoint());
                int col = calendarTable.columnAtPoint(e.getPoint());

                Object selectedValue = calendarModel.getValueAt(row, col);
                if (selectedValue != null && selectedValue instanceof Integer) {
                    selectedDate = selectedDate.withDayOfMonth((Integer) selectedValue);
                    cargarInformacionEventos(selectedDate);
                    calendarTable.clearSelection();
                    calendarTable.repaint();
                }
            }
        });

        frame.setVisible(true);
        LocalDate now = LocalDate.now();
        selectedDate = now;
        updateCalendar(now.getMonthValue(), now.getYear());
        cargarInformacionEventos(now);
    }

    private static JPanel createNavigationPanel() {
        JPanel panel = new JPanel();

        String[] monthNames = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        JComboBox<String> monthComboBox = new JComboBox<>(monthNames);

        JComboBox<String> yearComboBox = new JComboBox<>();
        int year = LocalDate.now().getYear();
        for (int i = year - 5; i <= year + 5; i++) {
            yearComboBox.addItem(String.valueOf(i));
        }

        panel.add(monthComboBox);
        panel.add(yearComboBox);
        
        monthComboBox.setSelectedIndex(LocalDate.now().getMonthValue() - 1);
        yearComboBox.setSelectedItem(String.valueOf(LocalDate.now().getYear()));

        monthComboBox.addActionListener(e -> {
            int selectedMonth = monthComboBox.getSelectedIndex() + 1;
            int selectedYear = Integer.parseInt((String) yearComboBox.getSelectedItem());
            updateCalendar(selectedMonth, selectedYear);
        });

        yearComboBox.addActionListener(e -> {
            int selectedMonth = monthComboBox.getSelectedIndex() + 1;
            int selectedYear = Integer.parseInt((String) yearComboBox.getSelectedItem());
            updateCalendar(selectedMonth, selectedYear);
        });

        return panel;
    }

    private static void cargarInformacionEventos(LocalDate selectedDate) {
        // Obtener eventos del archivo de datos o de donde sea que los obtengas
        List<Evento> eventos = cargaEvento.cargarEventos();

        // Limpiar la tabla de eventos
        eventsModel.setRowCount(0);

        // Iterar sobre los eventos y agregar los correspondientes a la fecha seleccionada
        for (Evento evento : eventos) {
            // Convertir las fechas de String a LocalDate (usando métodos parseados que tengas)
            LocalDate fechaInicio = parsearFecha(evento.getFechaInicio());
            LocalDate fechaFin = parsearFecha(evento.getFechaFin());

            // Verificar si el evento cae en el día seleccionado
            if ((selectedDate.isEqual(fechaInicio) || selectedDate.isAfter(fechaInicio)) &&
                (selectedDate.isEqual(fechaFin) || selectedDate.isBefore(fechaFin))) {
                // Obtener los surfistas participantes y agregarlos a la tabla de eventos
                StringBuilder surfistas = new StringBuilder();
                for (Surfista surfista : evento.getParticipantes()) {
                    surfistas.append(surfista.getNombre()).append(", ");
                }
                eventsModel.addRow(new Object[]{evento.getNombre(), surfistas.toString()});
            }
        }
    }

    // Método para convertir String a LocalDate (aquí necesitas implementar tu lógica de conversión)
    private static LocalDate parsearFecha(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        return LocalDate.parse(fecha, formatter);
    }


    private static void updateCalendar(int selectedMonth, int selectedYear) {
        calendarModel.setRowCount(0);

        selectedDate = LocalDate.of(selectedYear, selectedMonth, 1);
        YearMonth yearMonth = YearMonth.of(selectedYear, selectedMonth);

        int firstDayOfWeek = selectedDate.getDayOfWeek().getValue();
        int daysInMonth = yearMonth.lengthOfMonth();
        int row = 0;
        int day = 1;

        for (int i = 0; i < 6; i++) {
            calendarModel.addRow(new Object[7]);
        }

        for (int i = 1; i < firstDayOfWeek; i++) {
            calendarModel.setValueAt("", 0, i - 1);
        }

        for (int i = firstDayOfWeek; i < 8; i++) {
            calendarModel.setValueAt(day++, 0, i - 1);
        }

        for (int i = 1; i < 6; i++) {
            for (int j = 0; j < 7 && day <= daysInMonth; j++) {
                calendarModel.setValueAt(day++, i, j);
            }
        }

        calendarTable.repaint();
    }
}
