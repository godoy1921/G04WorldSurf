package tree;

import javax.swing.*;
import java.awt.*;

class ArbolPanel extends JPanel {
    private Arbol arbol;
    private int nodoSize = 30; // Tamaño de cada nodo

    public ArbolPanel(Arbol arbol) {
        this.arbol = arbol;
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarArbol(g, getWidth() / 2, 50, arbol.raiz, Integer.MAX_VALUE);
    }

    private int dibujarArbol(Graphics g, int x, int y, Nodo nodo, int espacio) {
        if (nodo != null) {
            int anchoIzquierdo = dibujarArbol(g, x - espacio, y + 50, nodo.izquierdo, espacio / 2);
            int anchoDerecho = dibujarArbol(g, x + espacio, y + 50, nodo.derecho, espacio / 2);

            int anchoTotal = anchoIzquierdo + nodoSize + anchoDerecho;

            g.setColor(Color.BLACK);
            g.fillOval(x - nodoSize / 2, y - nodoSize / 2, nodoSize, nodoSize);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(nodo.valor), x - 5, y + 5);

            if (nodo.izquierdo != null) {
                g.drawLine(x, y, x - espacio, y + 50);
            }
            if (nodo.derecho != null) {
                g.drawLine(x, y, x + espacio, y + 50);
            }

            return anchoTotal;
        }
        return 0;
    }
}
