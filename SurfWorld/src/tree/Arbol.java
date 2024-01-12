package tree;

class Arbol {
    Nodo raiz;

    public Arbol() {
        this.raiz = null;
    }

    public void construirArbol() {
        raiz = new Nodo(8);
        raiz.izquierdo = new Nodo(4);
        raiz.derecho = new Nodo(12);

        raiz.izquierdo.izquierdo = new Nodo(2);
        raiz.izquierdo.derecho = new Nodo(6);

        raiz.derecho.izquierdo = new Nodo(10);
        raiz.derecho.derecho = new Nodo(14);

        raiz.izquierdo.izquierdo.izquierdo = new Nodo(1);
        raiz.izquierdo.izquierdo.derecho = new Nodo(3);

        raiz.izquierdo.derecho.izquierdo = new Nodo(5);
        raiz.izquierdo.derecho.derecho = new Nodo(7);

        raiz.derecho.izquierdo.izquierdo = new Nodo(9);
        raiz.derecho.izquierdo.derecho = new Nodo(11);

        raiz.derecho.derecho.izquierdo = new Nodo(13);
        raiz.derecho.derecho.derecho = new Nodo(15);
    }
}