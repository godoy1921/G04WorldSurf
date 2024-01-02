package gui;

import javax.swing.*;

public class VentanaCompeticion extends JPanel{

	private static final long serialVersionUID = 1L;

	public VentanaCompeticion() {
        setName("Ventana de Competición");
        setSize(600, 400);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Simulación de Competición de Surf");
        JPanel panel = new JPanel();
        panel.add(label);
        add(panel);

        setVisible(true);
    }
    
    public void mostrarVentana() {
    	setVisible(true);
    }
    
    public void ocultarVentana() {
    	setVisible(false);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaCompeticion();
            }
        });
    }
}

