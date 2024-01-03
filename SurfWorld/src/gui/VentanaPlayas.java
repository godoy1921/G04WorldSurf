package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class VentanaPlayas extends JPanel{
	
	  private JLabel imageLabel;
	    private JLabel nameLabel;
	    private Timer timer;
	    private int currentIndex;
	    private String[] beachNames = {"Beach 1", "Beach 2", "Beach 3"}; // Nombres de las playas
	    private ImageIcon[] beachImages = {
	        new ImageIcon("beach1.jpg"), // Ruta de las imágenes de las playas
	        new ImageIcon("beach2.jpg"),
	        new ImageIcon("beach3.jpg")
	    };

	    public VentanaPlayas() {
	        setLayout(new BorderLayout());

	        imageLabel = new JLabel();
	        nameLabel = new JLabel();

	        add(imageLabel, BorderLayout.CENTER);
	        add(nameLabel, BorderLayout.SOUTH);

	        currentIndex = 0;
	        updateSlide();

	        int delay = 5000; // Tiempo en milisegundos para cambiar las imágenes (5 segundos)
	        timer = new Timer(delay, new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                currentIndex = (currentIndex + 1) % beachImages.length;
	                updateSlide();
	            }
	        });
	        timer.start();
	    }

	    private void updateSlide() {
	        imageLabel.setIcon(beachImages[currentIndex]);
	        nameLabel.setText(beachNames[currentIndex]);
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                JFrame frame = new JFrame("Beach Slide Show");
	                SlideShowPanel panel = new SlideShowPanel();
	                frame.add(panel);
	                frame.setSize(400, 400);
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.setVisible(true);
	            }
	        });
	    }
	}



