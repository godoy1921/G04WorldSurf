package tree;

import javax.swing.*;
public class Ejecute {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Árbol con 16 nodos");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Arbol arbol = new Arbol();
            arbol.construirArbol();

            ArbolPanel arbolPanel = new ArbolPanel(arbol);

            frame.add(arbolPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

