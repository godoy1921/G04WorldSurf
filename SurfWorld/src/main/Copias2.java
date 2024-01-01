package main;

import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;

public class Copias2 extends JFrame {

    private static final int NUM_PARTICIPANTES = 16;

    public Torneo() {
        setTitle("Torneo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();

        try {
            Object root = graph.insertVertex(parent, null, "Campeonato", 400, 20, 80, 30);

            Object[] participantes = new Object[NUM_PARTICIPANTES];
            for (int i = 0; i < NUM_PARTICIPANTES; i++) {
                participantes[i] = graph.insertVertex(parent, null, "Participante " + (i + 1), 50 + i * 50, 80, 80, 30);
                graph.insertEdge(parent, null, "", root, participantes[i]);
            }

            organizarArbol(graph, root);
        } finally {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        add(graphComponent, BorderLayout.CENTER);
        setVisible(true);
    }

    private void organizarArbol(mxGraph graph, Object root) {
        mxCompactTreeLayout layout = new mxCompactTreeLayout(graph, false);
        layout.setHorizontal(false);
        layout.execute(root);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Torneo torneo = new Torneo();
            torneo.setVisible(true);
        });
    }
}

