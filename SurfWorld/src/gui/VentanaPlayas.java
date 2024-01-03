package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class VentanaPlayas extends JPanel {

    private JLabel imageLabel;
    private JLabel nameLabel;
    private Timer timer;
    private int currentIndex;
    private String[] beachNames = {"HOSSEGOR - Quick Silver Pro", "JBAY - JBay Open", "MARGARET RIVER - Margaret River Pro", "PIPELINE - Billabong Pipe Masters", "TAHITI - Tahiti Pro"};
    private ImageIcon[] beachImages = {
        new ImageIcon("surfworld\\img\\hossegor.jpg"),
        new ImageIcon("surfworld\\img\\JBay.jpg"),
        new ImageIcon("surfworld\\img\\margaret.jpg"),
        new ImageIcon("surfworld\\img\\pipeline.jpg"),
        new ImageIcon("surfworld\\img\\tahiti.jpg")
    };

    public VentanaPlayas() {
        setLayout(new BorderLayout());
        Color lightBlue = new Color(135, 206, 250);
        setBackground(lightBlue);

        imageLabel = new JLabel();
        nameLabel = new JLabel();
        
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        add(imageLabel, BorderLayout.CENTER);
        add(nameLabel, BorderLayout.SOUTH);

        currentIndex = 0;
        updateSlide();

        int delay = 5000;
        timer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex + 1) % beachImages.length;
                updateSlide();
            }
        });
        timer.start();
    }

    private void updateSlide() {
    	// Escalar la imagen para que todas tengan el mismo tamaño
        ImageIcon currentImageIcon = beachImages[currentIndex];
        Image currentImage = currentImageIcon.getImage().getScaledInstance(1500, 700, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(currentImage);

        imageLabel.setIcon(scaledImageIcon);
        nameLabel.setText(beachNames[currentIndex]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Beach Slide Show");
                VentanaPlayas panel = new VentanaPlayas();
                frame.add(panel);
                frame.setSize(400, 400);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
