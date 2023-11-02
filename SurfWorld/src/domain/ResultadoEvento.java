package domain;

import java.util.Objects;

public class ResultadoEvento {
    private int idResultado;
    private Evento evento;
    private Surfista surfista;
    private int posicion;

    public ResultadoEvento(int idResultado, Evento evento, Surfista surfista, int posicion) {
        this.idResultado = idResultado;
        this.evento = evento;
        this.surfista = surfista;
        this.posicion = posicion;
    }

    public int getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Surfista getSurfista() {
        return surfista;
    }

    public void setSurfista(Surfista surfista) {
        this.surfista = surfista;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

	@Override
	public String toString() {
		return "ResultadoEvento [evento=" + evento + ", surfista=" + surfista + ", posicion=" + posicion + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idResultado);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultadoEvento other = (ResultadoEvento) obj;
		return idResultado == other.idResultado;
	}
}

