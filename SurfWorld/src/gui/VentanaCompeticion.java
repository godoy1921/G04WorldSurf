package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.*;
import data.cargaSurfista;
import domain.Surfista;



public class VentanaCompeticion extends JPanel{

private static final long serialVersionUID = 1L;
	
	
	private JLabel[] leftSurfers;
    private JLabel[] rightSurfers;
    private JLabel[] scoreBoxes;
    private ImageIcon blankImage;
    

    private int filledScoreBoxes = 0; // Contador de cuadros de puntuación llenados
    private List<String> selectedSurfers = new ArrayList<>();// Conjunto de surfistas seleccionados
    private List<Surfista> surfistasBD = cargaSurfista.cargarSurfistas();	
    
    
    
    public VentanaCompeticion() {
		setName("Simulador de Manga de Surf");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        Color lightBlue = new Color(135, 206, 250);
        setBackground(lightBlue);

        initializeSurfers();
        initializeScoreBoxes();

        setLayout(new GridLayout(1, 5));

        // Agrega los surfistas al panel
        add(createSurfersPanel(leftSurfers));
        add(createScorePanel());
        add(createSurfersPanel(rightSurfers));
        add(createControlPanel(), BorderLayout.EAST); 

        //setLocationRelativeTo(null);
        setVisible(true);
	    }

	    private void initializeSurfers() {
	        leftSurfers = new JLabel[8];
	        rightSurfers = new JLabel[8];

	        leftSurfers[0] = createSurferLabel("GabrielMedina");
	        leftSurfers[1] = createSurferLabel("CarissaMoore");
	        leftSurfers[2] = createSurferLabel("ItaloFerreira");
	        leftSurfers[3] = createSurferLabel("StephanieGilmore");
	        leftSurfers[4] = createSurferLabel("JordySmith");
	        leftSurfers[5] = createSurferLabel("SallyFitzgibbons");
	        leftSurfers[6] = createSurferLabel("KoloheAndino");
	        leftSurfers[7] = createSurferLabel("TatianaWeston");
	        rightSurfers[0] = createSurferLabel("JohnJohnFlorence");
	        rightSurfers[1] = createSurferLabel("ConnerCoffin");
	        rightSurfers[2] = createSurferLabel("TylerWright");
	        rightSurfers[3] = createSurferLabel("JulianWilson");
	        rightSurfers[4] = createSurferLabel("LakeyPeterson");
	        rightSurfers[5] = createSurferLabel("FilipeToledo");
	        rightSurfers[6] = createSurferLabel("JeremyFlores");
	        rightSurfers[7] = createSurferLabel("JackRobinson");
	    }
	    
	  

	    private JLabel createSurferLabel(String name) {
	    	JLabel surferLabel = new JLabel();
	    	
	    	if(name != "JordySmith" && name != "KoloheAndino") {
	    		ImageIcon imageIcon = new ImageIcon("surfworld\\img\\" + name + ".jpg");
	            Image image = imageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	            
	            surferLabel = new JLabel(new ImageIcon(image));
	            surferLabel.setText(name);
	            surferLabel.setHorizontalTextPosition(JLabel.CENTER);
	            surferLabel.setVerticalTextPosition(JLabel.BOTTOM);
	            
	            surferLabel.addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                    if (filledScoreBoxes < 3) {
	                    	if (selectedSurfers.contains(name)) {
	                            JOptionPane.showMessageDialog(null, "Ya has añadido a este surfista.", "Alerta",
	                                    JOptionPane.WARNING_MESSAGE);
	                    		}else {
	                        // Encuentra el próximo cuadro de puntuación vacío
			                        int nextScoreBoxIndex = getNextEmptyScoreBox();
			                        scoreBoxes[nextScoreBoxIndex].setIcon(new ImageIcon(image));
			                        filledScoreBoxes++;
			                        selectedSurfers.add(name);
	                    		}

	                        // Puedes almacenar información adicional sobre el surfista seleccionado
	                        // en algún lugar si es necesario
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Ya has llenado todos los cuadros.", "Alerta", JOptionPane.WARNING_MESSAGE);
	                    }
	                }
	            });
	    	}else {
	    		ImageIcon imageIcon = new ImageIcon("surfworld\\img\\" + name + ".jpeg");
	            Image image = imageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	            
	            surferLabel = new JLabel(new ImageIcon(image));
	            surferLabel.setText(name);
	            surferLabel.setHorizontalTextPosition(JLabel.CENTER);
	            surferLabel.setVerticalTextPosition(JLabel.BOTTOM);
	            
	            surferLabel.addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                    if (filledScoreBoxes < 3) {
	                    	if (selectedSurfers.contains(name)) {
	                            JOptionPane.showMessageDialog(null, "Ya has añadido a este surfista.", "Alerta",
	                                    JOptionPane.WARNING_MESSAGE);
	                    		}else {
	                        // Encuentra el próximo cuadro de puntuación vacío
			                        int nextScoreBoxIndex = getNextEmptyScoreBox();
			                        scoreBoxes[nextScoreBoxIndex].setIcon(new ImageIcon(image));
			                        filledScoreBoxes++;
			                        selectedSurfers.add(name);
	                    		}

	                        // Puedes almacenar información adicional sobre el surfista seleccionado
	                        // en algún lugar si es necesario
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Ya has llenado todos los cuadros.", "Alerta", JOptionPane.WARNING_MESSAGE);
	                    }
	                }
	            });
	    	}

	        

	        return surferLabel;
	    }
	    

	    private void initializeScoreBoxes() {
	        scoreBoxes = new JLabel[3];
	        blankImage = new ImageIcon("path_to_image_directory/blank.jpg");

	        for (int i = 0; i < 3; i++) {
	            scoreBoxes[i] = new JLabel(blankImage, JLabel.CENTER);
	            scoreBoxes[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        }
	    }
	    

	    private JPanel createSurfersPanel(JLabel[] surfers) {
	        JPanel panel = new JPanel(new GridLayout(4, 2));
	        for (JLabel surfer : surfers) {
	            panel.add(surfer);
	        }
	        return panel;
	    }
	    

	    private JPanel createScorePanel() {
	        JPanel panel = new JPanel(new GridLayout(3, 1, 8, 30));
	        for (JLabel scoreBox : scoreBoxes) {
	            scoreBox.setPreferredSize(new Dimension(60, 60));
	            panel.add(scoreBox);
	        }
	        return panel;
	    }
	    
	    
	    
	    private JPanel createControlPanel() {
	        JPanel controlPanel = new JPanel();
	        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

	        // Crea un panel para los botones y agrega el panel en el este
	        JPanel buttonsPanel = new JPanel();
	        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
	        JButton vaciarButton = new JButton("Vaciar");
	        JButton competirButton = new JButton("Competir");

	        vaciarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	        competirButton.setAlignmentX(Component.CENTER_ALIGNMENT);

	        vaciarButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Lógica para vaciar los scoreboxes
	            	for (int i = 0; i < 3; i++) {
	                    scoreBoxes[i].setIcon(blankImage);
	                    scoreBoxes[i].setOpaque(false);  // Restaura la opacidad
	                    scoreBoxes[i].setBackground(null);  // Restaura el fondo
	                }
	                filledScoreBoxes = 0;
	                selectedSurfers.clear();
	            }
	        });

	        competirButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                competir(); // Llama a la función competir al presionar el botón
	            }
	        });

	        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Espaciado vertical arriba
	        buttonsPanel.add(vaciarButton);
	        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaciado vertical entre botones
	        buttonsPanel.add(competirButton);
	        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Espaciado vertical abajo

	        controlPanel.add(buttonsPanel);
	        return controlPanel;
	    }
	    
	    

	    private int getNextEmptyScoreBox() {
	        for (int i = 0; i < 3; i++) {
	            if (scoreBoxes[i].getIcon() == blankImage) {
	                return i;
	            }
	        }
	        return -1; // Si todos los cuadros están llenos
	    }
	    
	    
	    
	    private void competir() {
	        // Verifica si hay 3 surfistas seleccionados
	        if (filledScoreBoxes < 3) {
	            JOptionPane.showMessageDialog(null, "Debes seleccionar 3 surfistas para competir.", "Alerta", JOptionPane.WARNING_MESSAGE);
	            return;
	        }

	        // Obtiene los nombres de los surfistas seleccionados
	        List<String> selectedSurfersList = new ArrayList<>(selectedSurfers);

	        // Simula la competición
	        List<ResultadoSurfista> resultados = simularCompeticion(selectedSurfersList);
	        
	        try {
				Thread.sleep( 1300 );  // Simula una pequeña espera (décimas de segundo) aleatoria
			} catch (InterruptedException e) {} 

	        // Muestra los resultados
	        actualizarUIResultados(resultados);
	        mostrarResultados(resultados);
	        
	    }
	    
	    
	    
	    private List<ResultadoSurfista> simularCompeticion(List<String> surfistas) {
	        List<ResultadoSurfista> resultados = new ArrayList<>();

	        // Genera una lista de puntuaciones posibles del 0 al 10
	        List<Integer> puntuacionesPosibles = new ArrayList<>();
	        for (int i = 4; i <= 10; i++) {
	            puntuacionesPosibles.add(i);
	        }

	        // Simula la competición y asigna puntuaciones aleatorias diferentes
	        for (String surfista : surfistas) {
	            // Verifica si aún hay puntuaciones posibles
	            if (puntuacionesPosibles.isEmpty()) {
	                break; // Sal del bucle si ya no hay puntuaciones posibles
	            }

	            // Elije una puntuación aleatoria de las posibles
	            int indicePuntuacion = new Random().nextInt(puntuacionesPosibles.size());
	            int puntuacion = puntuacionesPosibles.remove(indicePuntuacion);

	            // Agrega el resultado a la lista
	            resultados.add(new ResultadoSurfista(surfista, puntuacion));
	        }

	        // Ordena los resultados en orden descendente por puntuación
	        resultados.sort(Comparator.comparingInt(ResultadoSurfista::getPuntuacion).reversed());

	        return resultados;
	    }

	    
	    
	    
	    private void mostrarResultados(List<ResultadoSurfista> resultados) {
	        // Crea y muestra el texto con los resultados
	        StringBuilder resultadosText = new StringBuilder("Resultados:\n");
	        for (int i = 0; i < resultados.size(); i++) {
	            ResultadoSurfista resultado = resultados.get(i);
	            resultadosText.append(String.format("%d. %s - Puntuación: %d\n", i + 1, resultado.getNombre(), resultado.getPuntuacion()));
	        }

	        // Crea un JTextArea para mostrar los resultados
	        JTextArea resultadosArea = new JTextArea(resultadosText.toString());
	        resultadosArea.setEditable(false);

	        // Crea un JScrollPane para permitir el desplazamiento si hay muchos resultados
	        JScrollPane scrollPane = new JScrollPane(resultadosArea);

	        // Muestra los resultados en un cuadro de diálogo
	        JOptionPane.showMessageDialog(this, scrollPane, "Resultados de la Competición", JOptionPane.PLAIN_MESSAGE);
	    }
	    
	    
	    
	    private void actualizarUIResultados(List<ResultadoSurfista> resultados) {
	        if (resultados.size() == 3) {
	        	
	        	ArrayList<String> surfistasSeleccionados
	            = new ArrayList<>(selectedSurfers);
	            
        		int primerLugar = selectedSurfers.indexOf(resultados.get(0).getNombre());
        		scoreBoxes[primerLugar].setOpaque(true);
        		scoreBoxes[primerLugar].setBackground(Color.GREEN);
        		
        		int segundoLugar = selectedSurfers.indexOf(resultados.get(1).getNombre());
        		scoreBoxes[segundoLugar].setOpaque(true);
        		scoreBoxes[segundoLugar].setBackground(Color.ORANGE);
        		
        		int tercerLugar = selectedSurfers.indexOf(resultados.get(2).getNombre());
        		scoreBoxes[tercerLugar].setOpaque(true);
        		scoreBoxes[tercerLugar].setBackground(Color.RED);
            			            		           	            	            	          	    
	        }
	    }  
	  
	    
	    private static class ResultadoSurfista {
	        private final String nombre;
	        private final int puntuacion;

	        public ResultadoSurfista(String nombre, int puntuacion) {
	            this.nombre = nombre;
	            this.puntuacion = puntuacion;
	        }

	        public String getNombre() {
	            return nombre;
	        }

	        public int getPuntuacion() {
	            return puntuacion;
	        }
	        
	        @Override
	    	public String toString() {
	    		return "ResultadoSurfista [nombre=" + nombre + ", puntuacion=" + puntuacion + "]";
	    	}
	    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaCompeticion();
            }
        });
    }
}

