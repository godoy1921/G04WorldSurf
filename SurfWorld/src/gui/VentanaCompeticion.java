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
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

public class VentanaCompeticion extends JPanel{

	private static final long serialVersionUID = 1L;
	
	
	private JLabel[] leftSurfers;
    private JLabel[] rightSurfers;
    private JLabel[] scoreBoxes;
    private ImageIcon blankImage;
    

    private int filledScoreBoxes = 0; // Contador de cuadros de puntuación llenados
    private Set<String> selectedSurfers = new HashSet<>();// Conjunto de surfistas seleccionados
	
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
	                }
	                filledScoreBoxes = 0;
	                selectedSurfers.clear();
	            }
	        });

	        competirButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Lógica para competir (lo que quieras hacer)
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
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaCompeticion();
            }
        });
    }
}

