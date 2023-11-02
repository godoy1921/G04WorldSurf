package ventanas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class interfazGrafica {
	
	

   
        
        public interfazGrafica() {
            JFrame frame = new JFrame("Aplicación de Surf");
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            frame.add(panel);

	        JButton inicioButton = new JButton("Ir a la ventana de Inicio");
	        JButton eventosButton = new JButton("Ir a la ventana de Eventos");
	        JButton competicionButton = new JButton("Ir a la ventana de Competición");
	
	        inicioButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	SwingUtilities.invokeLater(new Runnable() {
	                    public void run() {
	                    	new ventanaInicio();
	                    }
	                    
	        		});
	                
	            }
	        });
	
	        eventosButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                new ventanaEventos();
	            }
	        });
	
	        competicionButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                new ventanaCompeticion();
	            }
	        });

        panel.add(inicioButton);
        panel.add(eventosButton);
        panel.add(competicionButton);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
}

