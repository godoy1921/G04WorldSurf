package main;

import java.net.URL;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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

public class Copias {
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
        String[] daysOfWeek = {"Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"};
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
        
        IconRenderer iconRenderer = new IconRenderer();
        calendarTable.setDefaultRenderer(Object.class, iconRenderer);


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
        for (int i = year - 1; i <= year + 1; i++) {
            yearComboBox.addItem(String.valueOf(i));
        }

        panel.add(monthComboBox);
        panel.add(yearComboBox);
        
        monthComboBox.setSelectedIndex(LocalDate.now().getMonthValue() - 1);
        yearComboBox.setSelectedItem(String.valueOf(LocalDate.now().getYear()));

        /*monthComboBox.addActionListener(e -> {
            int selectedMonth = monthComboBox.getSelectedIndex() + 1;
            int selectedYear = Integer.parseInt((String) yearComboBox.getSelectedItem());
            updateCalendar(selectedMonth, selectedYear);
        });

        yearComboBox.addActionListener(e -> {
            int selectedMonth = monthComboBox.getSelectedIndex() + 1;
            int selectedYear = Integer.parseInt((String) yearComboBox.getSelectedItem());
            updateCalendar(selectedMonth, selectedYear);
        });
        */
        
     // En el ActionListener de los ComboBox de mes y año
        monthComboBox.addActionListener(e -> {
            int selectedMonth = monthComboBox.getSelectedIndex() + 1;
            int selectedYear = Integer.parseInt((String) yearComboBox.getSelectedItem());
            updateCalendar(selectedMonth, selectedYear);
            LocalDate selectedDate = LocalDate.of(selectedYear, selectedMonth, 1);
            cargarInformacionEventos(selectedDate);
        });

        yearComboBox.addActionListener(e -> {
            int selectedMonth = monthComboBox.getSelectedIndex() + 1;
            int selectedYear = Integer.parseInt((String) yearComboBox.getSelectedItem());
            updateCalendar(selectedMonth, selectedYear);
            LocalDate selectedDate = LocalDate.of(selectedYear, selectedMonth, 1);
            cargarInformacionEventos(selectedDate);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
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
        
        List<Evento> eventos = cargaEvento.cargarEventos();
        
     // Obtener el día de la semana en que comienza el mes y calcular el offset
        int firstDayOfWeekX = selectedDate.withDayOfMonth(1).getDayOfWeek().getValue();
        int offset = (firstDayOfWeekX + 5) % 7; // Calcula el desplazamiento para empezar desde el domingo

        // Iterar sobre los eventos y colocar los íconos en las celdas correspondientes
        for (Evento evento : eventos) {
            LocalDate fechaInicio = parsearFecha(evento.getFechaInicio());
            LocalDate fechaFin = parsearFecha(evento.getFechaFin());

            // Iterar sobre el rango de fechas del evento
            for (LocalDate date = fechaInicio; date.isBefore(fechaFin.plusDays(1)); date = date.plusDays(1)) {
                if (date.getMonthValue() == selectedMonth && date.getYear() == selectedYear) {
                    int dayOfMonth = date.getDayOfMonth();

                    // Calcular la posición de la celda para colocar el ícono
                    int rowX = (dayOfMonth + offset) / 7;
                    int column = (dayOfMonth + offset) % 7;

                    // Asegurarse de que el ícono se coloque en la celda del día 1 del mes
                    if (dayOfMonth == 1) {
                        rowX = 0;
                        column = (firstDayOfWeekX + 6) % 7; // Ajustar columna para el día 1
                        
                    }

                    ImageIcon iconoEvento = obtenerIconoEvento(evento); 
                    calendarTable.setValueAt(iconoEvento, rowX, column);
                }
            }
        }

        calendarTable.repaint();
    }
    
    private static ImageIcon obtenerIconoEvento(Evento evento) {
        String nombreEvento = evento.getNombre();
        String nombreImagen = "";

        switch (nombreEvento) {
            case "World Surf League":
                nombreImagen = "WorldSurfLeague.png";
                break;
            case "Billabong Pipe Masters":
                nombreImagen = "Billabong.png";
                break;
            case "Jbay Open":
                nombreImagen = "JbayOpen.jpg";
                break;
            case "Margaret River Pro":
                nombreImagen = "MargaretRiver.jpg";
                break;
            case "Quick Silver Pro":
                nombreImagen = "QuickSilver.jpg";
                break;
            case "Tahiti Pro":
                nombreImagen = "TahitiPro.jpg";
                break;
            default:
                // Nombre de imagen predeterminado o manejo de error si no se encuentra el evento
                nombreImagen = "default.png";
                break;
        }
        
      

        // Suponiendo que los logos están en una carpeta llamada "logos" dentro del proyecto
        String rutaImagen = "surfworld/img/" + nombreImagen;
        ImageIcon icono = new ImageIcon(rutaImagen);
       

        return icono;
    }
    
    static class IconRenderer extends DefaultTableCellRenderer {
        private int iconWidth = 30; // Ancho deseado de la imagen
        private int iconHeight = 30; // Alto deseado de la imagen

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setIcon(null); // Limpia el icono

            if (value instanceof ImageIcon) {
                ImageIcon originalIcon = (ImageIcon) value;
                Image img = originalIcon.getImage();
                Image newImg = img.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(newImg);
                label.setIcon(scaledIcon);
                label.setText(""); // Borra el texto
            } 
            return label;
        }
    }
    




    }





	/* Codigo calendario que abajo pone el campeonato y sus surfistas
	
	
 */



	
	

