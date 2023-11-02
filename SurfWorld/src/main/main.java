package main;

import javax.swing.SwingUtilities;

import gui.interfazGrafica;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new interfazGrafica();
            }
            
		});

	}

}
