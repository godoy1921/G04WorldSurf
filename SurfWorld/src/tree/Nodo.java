package tree;

class Nodo {
    int valor;
    Nodo izquierdo, derecho;

    public Nodo(int valor) {
        this.valor = valor;
        this.izquierdo = this.derecho = null;
    }
}
