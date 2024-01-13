package main;



import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class SurfMangaSimulator extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel[] leftSurfers;
    private JLabel[] rightSurfers;
    private JLabel[] scoreBoxes;
    private ImageIcon blankImage;
    

    private int filledScoreBoxes = 0; // Contador de cuadros de puntuación llenados

    public SurfMangaSimulator() {
        super("Simulador de Manga de Surf");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        initializeSurfers();
        initializeScoreBoxes();

        setLayout(new GridLayout(1, 5));

        // Agrega los surfistas al panel
        add(createSurfersPanel(leftSurfers));
        add(createScorePanel());
        add(createSurfersPanel(rightSurfers));

        setLocationRelativeTo(null);
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
                        // Encuentra el próximo cuadro de puntuación vacío
                        int nextScoreBoxIndex = getNextEmptyScoreBox();
                        scoreBoxes[nextScoreBoxIndex].setIcon(new ImageIcon(image));
                        filledScoreBoxes++;

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
                        // Encuentra el próximo cuadro de puntuación vacío
                        int nextScoreBoxIndex = getNextEmptyScoreBox();
                        scoreBoxes[nextScoreBoxIndex].setIcon(new ImageIcon(image));
                        filledScoreBoxes++;

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
        JPanel panel = new JPanel(new GridLayout(2, 4));
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

    private int getNextEmptyScoreBox() {
        for (int i = 0; i < 3; i++) {
            if (scoreBoxes[i].getIcon() == blankImage) {
                return i;
            }
        }
        return -1; // Si todos los cuadros están llenos
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SurfMangaSimulator::new);
    }
}