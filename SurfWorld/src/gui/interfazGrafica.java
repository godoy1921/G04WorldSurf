package gui;

import javax.swing.*;

import data.cargaEvento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class interfazGrafica {
	
	

   
        
        public interfazGrafica() {
            JFrame frame = new JFrame("Aplicación de Surf");
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            frame.add(panel);

	        JButton inicioButton = new JButton("Inicio");
	        JButton eventosButton = new JButton("Eventos");
	        JButton competicionButton = new JButton("Competición");
	
	        inicioButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	SwingUtilities.invokeLater(new Runnable() {
	                    public void run() {
	                    	ventanaInicio ventana = new ventanaInicio();
	                        ventana.setVisible(true);
	                    }
	                    
	        		});
	                
	            }
	        });
	
	        eventosButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                new ventanaEventos(cargaEvento.cargarEventos());
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

