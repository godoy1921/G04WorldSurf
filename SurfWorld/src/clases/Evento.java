package clases;

import java.util.List;
import java.util.Objects;

public class Evento {
    private int idEvento;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private List<Surfista> participantes;

    public Evento(int idEvento, String nombre, String fechaInicio, String fechaFin, List<Surfista> participantes) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.participantes = participantes;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Surfista> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Surfista> participantes) {
        this.participantes = participantes;
    }

	@Override
	public int hashCode() {
		return Objects.hash(idEvento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return idEvento == other.idEvento;
	}

	@Override
	public String toString() {
		return "Evento [nombre=" + nombre + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	}
}
