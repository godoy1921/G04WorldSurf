package clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Surfista {
    private int idSurfista;
    private String nombre;
    private String paisOrigen;
    private int puestoRanking;
    private List<Evento> eventos;

    public Surfista(int idSurfista, String nombre, String paisOrigen, int puestoRanking) {
        this.idSurfista = idSurfista;
    	this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.puestoRanking = puestoRanking;
        this.eventos = new ArrayList<>();
        
    }

    public int getIdSurfista() {
        return idSurfista;
    }

    public void setIdSurfista(int idSurfista) {
    	this.idSurfista = idSurfista;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }
    
    public int getPuestoRanking() {
        return puestoRanking;
    }

    public void setPuestoRanking(int puestoRanking) {
    	this.puestoRanking = puestoRanking;
    }
    
    public List<Evento> getEventos() {
		return eventos;
	}
    
    @Override
	public String toString() {
		return "Surfista [nombre=" + nombre + ", paisOrigen=" + paisOrigen + ", puestoRanking=" + puestoRanking + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idSurfista);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Surfista other = (Surfista) obj;
		return idSurfista == other.idSurfista;
	}

	public void addEvento(Evento evento) {
		if (evento != null && !this.eventos.contains(evento)) {
			eventos.add(evento);
		}


}
}

