package persistence;

import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import data.cargaEvento;
import data.cargaSurfista;
import domain.Evento;
import domain.ResultadoEvento;
import domain.Surfista;




public class GestorBD {
	
	private final String PROPERTIES_FILE = "surfworld/conf/app.properties";
	private final String CSV_SURFISTAS = "resources/surfistas.csv";
	private final String CSV_EVENTOS = "resources/Eventos.csv";
	private final String CSV_RESULTADOEVENTOS = "resources/resultadoEventos.csv";
	
	private Properties properties;
	private String driverName;
	private String databaseFile;
	private String connectionString;
	
	private static Logger logger = Logger.getLogger(GestorBD.class.getName());
	
	public GestorBD() {
		try (FileInputStream fis = new FileInputStream("surfworld\\conf\\logger.properties")) {
			//Inicialización del Logger
			LogManager.getLogManager().readConfiguration(fis);
			
			//Lectura del fichero properties
			properties = new Properties();
			properties.load(new FileReader(PROPERTIES_FILE));
			
			driverName = properties.getProperty("driver");
			databaseFile = properties.getProperty("file");
			connectionString = properties.getProperty("connection");
			
			//Cargar el diver SQLite
			Class.forName(driverName);
		} catch (Exception ex) {
			logger.warning(String.format("Error al cargar el driver de BBDD: %s", ex.getMessage()));
		}
	}
	
	/**
	 * Inicializa la BBDD leyendo los datos de los ficheros CSV 
	 */
	public void initilizeFromCSV() {
		//Sólo se inicializa la BBDD si la propiedad initBBDD es true.
		if (properties.get("loadCSV").equals("true")) {
			//Se borran los datos, si existía alguno
			this.borrarDatos();
			
			//Se leen los surfistas del CSV
			List<Surfista> surfistas = cargaSurfista.cargarSurfistas();
			//Se insertan los surfistas en la BBDD
			this.insertarSurfista(surfistas.toArray(new Surfista[surfistas.size()]));
			
			//Se leen los eventos del CSV
			List<Evento> eventos = cargaEvento.cargarEventos();				
			
			
			//Se insertan los eventos en la BBDD
			this.insertarEvento(eventos.toArray(new Evento[eventos.size()]));				
		}
	}
	
	public void crearBBDD() {
		//Sólo se crea la BBDD si la propiedad initBBDD es true.
		if (properties.get("createBBDD").equals("true")) {
			//La base de datos tiene 3 tablas: Surfista, Evento y ResultadoEvento
			String sql1 = "CREATE TABLE IF NOT EXISTS Surfista (\n"
	                + " idSurfista INTEGER PRIMARY KEY,\n"
	                + " nombre TEXT NOT NULL,\n"
	                + " paisOrigen TEXT NOT NULL,\n"
	                + " puestoRanking INTEGER,\n"
	                + " UNIQUE(nombre));";
	
			String sql2 = "CREATE TABLE IF NOT EXISTS Evento (\n"
	                + " idEvento INTEGER PRIMARY KEY,\n"
	                + " nombre TEXT NOT NULL,\n"
	                + " fechaInicio TEXT NOT NULL,\n"
	                + " fechaFin TEXT NOT NULL,\n"
	                + " participantes TEXT NOT NULL\n"
	                + ");";
	
			String sql3 = "CREATE TABLE IF NOT EXISTS ResultadoEvento (\n"
	                + " idResultado INTEGER,\n"
	                + " evento TEXT NOT NULL,\n"
	                + " surfista TEXT NOT NULL,\n"
	                + " posicion INTEGER \n"
	                + ");";
			
	        //Se abre la conexión y se crea un PreparedStatement para crer cada tabla
			//Al abrir la conexión, si no existía el fichero por defecto, se crea.
			try (Connection con = DriverManager.getConnection(connectionString);
			     PreparedStatement pStmt1 = con.prepareStatement(sql1);
				 PreparedStatement pStmt2 = con.prepareStatement(sql2);
				 PreparedStatement pStmt3 = con.prepareStatement(sql3)) {
				
				//Se ejecutan las sentencias de creación de las tablas
		        if (!pStmt1.execute() && !pStmt2.execute() && !pStmt3.execute()) {
		        	logger.info("Se han creado las tablas");
		        }
			} catch (Exception ex) {
				logger.warning(String.format("Error al crear las tablas: %s", ex.getMessage()));
			}
		}
	}
	
	
	/**
	 * Borra las tablas y el fichero de la BBDD.
	 */
	public void borrarBBDD() {
		//Sólo se borra la BBDD si la propiedad deleteBBDD es true
		if (properties.get("deleteBBDD").equals("true")) {	
			String sql1 = "DROP TABLE IF EXISTS Surfista;";
			String sql2 = "DROP TABLE IF EXISTS Evento";
			String sql3 = "DROP TABLE IF EXISTS ResultadoEvento;";
			
	        //Se abre la conexión y se crea un PreparedStatement para borrar cada tabla
			try (Connection con = DriverManager.getConnection(connectionString);
			     PreparedStatement pStmt1 = con.prepareStatement(sql1);
				 PreparedStatement pStmt2 = con.prepareStatement(sql2);
				 PreparedStatement pStmt3 = con.prepareStatement(sql3)) {
				
				//Se ejecutan las sentencias de borrado de las tablas
		        if (!pStmt1.execute() && !pStmt2.execute() && !pStmt3.execute()) {
		        	logger.info("Se han borrado las tablas");
		        }
			} catch (Exception ex) {
				logger.warning(String.format("Error al borrar las tablas: %s", ex.getMessage()));
			}
			
			try {
				//Se borra físicamente el fichero de la BBDD
				Files.delete(Paths.get(databaseFile));
				logger.info("Se ha borrado el fichero de la BBDD");
			} catch (Exception ex) {
				logger.warning(String.format("Error al borrar el fichero de la BBDD: %s", ex.getMessage()));
			}
		}
	}
	
	
	/**
	 * Borra los datos de la BBDD.
	 */
	public void borrarDatos() {
		//Sólo se borran los datos si la propiedad cleanBBDD es true
		if (properties.get("cleanBBDD").equals("true")) {	
			String sql1 = "DELETE FROM Surfista;";
			String sql2 = "DELETE FROM Evento;";
			String sql3 = "DELETE FROM ResultadoEvento;";
			
	        //Se abre la conexión y se crea un PreparedStatement para borrar los datos de cada tabla
			try (Connection con = DriverManager.getConnection(connectionString);
			     PreparedStatement pStmt1 = con.prepareStatement(sql1);
				 PreparedStatement pStmt2 = con.prepareStatement(sql2);
				 PreparedStatement pStmt3 = con.prepareStatement(sql3)) {
				
				//Se ejecutan las sentencias de borrado de las tablas
		        if (!pStmt1.execute() && !pStmt2.execute() && !pStmt3.execute()) {
		        	logger.info("Se han borrado los datos");
		        }
			} catch (Exception ex) {
				logger.warning(String.format("Error al borrar los datos: %s", ex.getMessage()));
			}
		}
	}
	
	
	/**
	 * Inserta Surfistas en la BBDD
	 */
	public void insertarSurfista(Surfista... surfistas) {
		//Se define la plantilla de la sentencia SQL
		String sql = "INSERT INTO Surfista (idSurfista, nombre, paisOrigen, puestoRanking) VALUES (?, ?, ?, ?);";
		
		//Se abre la conexión y se crea el PreparedStatement con la sentencia SQL
		try (Connection con = DriverManager.getConnection(connectionString);
			 PreparedStatement pStmt = con.prepareStatement(sql)) {
									
			//Se recorren los surfistas y se insertan uno a uno
			for (Surfista s : surfistas) {
				//Se añaden los parámetros al PreparedStatement
				pStmt.setString(1, Integer.toString(s.getIdSurfista()));
				pStmt.setString(2, s.getNombre());
				pStmt.setString(3, s.getPaisOrigen());
				pStmt.setString(4, Integer.toString(s.getPuestoRanking()));
				
				if (pStmt.executeUpdate() != 1) {					
					logger.warning(String.format("No se ha insertado el Surfista: %s", s));
				} else {			
					logger.info(String.format("Se ha insertado el Surfista: %s", s));
				}
			}
			
			logger.info(String.format("%d Surfistas insertados en la BBDD", surfistas.length));
		} catch (Exception ex) {
			logger.warning(String.format("Error al insertar surfistas: %s", ex.getMessage()));
		}			
	}
	
	
	public void insertarEvento(Evento... eventos) {
		//Se define la plantilla de la sentencia SQL			
		String sql = "INSERT INTO Evento (idEvento, nombre, fechaInicio, fechaFin, participantes) VALUES (?, ?, ?, ?, ?);";
		
		//Se abre la conexión y se crea el PreparedStatement con la sentencia SQL
		try (Connection con = DriverManager.getConnection(connectionString);
			 PreparedStatement pStmt = con.prepareStatement(sql)) {
			
			//Se recorren los eventos y se insertan uno a uno
			for (Evento e : eventos) {
				//Se definen los parámetros de la sentencia SQL
				pStmt.setString(1, Integer.toString(e.getIdEvento()));
				pStmt.setString(2, e.getNombre());
				pStmt.setString(3, e.getFechaInicio());
				pStmt.setString(4, e.getFechaFin());
				
				StringBuilder sb = new StringBuilder();
				List<Surfista> surfistas = e.getParticipantes();
				String delim = "-";
				for (Surfista surfista : surfistas) {
				    sb.append(convertirSurfistaACadena(surfista)).append(delim);
				}
				sb.deleteCharAt(sb.length() - 1); // Eliminar el último delimitador extra

				pStmt.setString(5, sb.toString());

				
				/*Meter una cadena de surfistas como string
				StringBuilder sb = new StringBuilder();
				List<Surfista> surfistas = e.getParticipantes();
				String delim = "-";
		        int i = 0;
		        while (i < surfistas.size() - 1)
		        {
		            sb.append(surfistas.get(i));
		            sb.append(delim);
		            i++;
		        }
		        sb.append(surfistas.get(i));
				pStmt.setString(5, sb.toString());*/
				
				if (pStmt.executeUpdate() != 1) {					
					logger.warning(String.format("No se ha insertado el Evento: %s", e));
				} else {
										
					
					
					logger.info(String.format("Se ha insertado el Evento: %s", e));
				}
			}
			
			logger.info(String.format("%d Eventos insertados en la BBDD", eventos.length));
		} catch (Exception ex) {
			logger.warning(String.format("Error al insertar eventos: %s", ex.getMessage()));
		}				
	}
	
	
	/**
	 * Inserta resultadoEvento en la BBDD
	 */
	public void insertarResultadoEvento(ResultadoEvento... resultadoEventos) {
		//Se define la plantilla de la sentencia SQL
		String sql = "INSERT INTO ResultadoEvento (idResultado, evento, surfista, posicion) VALUES (?, ?, ?, ?);";
		
		//Se abre la conexión y se crea el PreparedStatement con la sentencia SQL
		try (Connection con = DriverManager.getConnection(connectionString);
			 PreparedStatement pStmt = con.prepareStatement(sql)) {
									
			//Se recorren los clientes y se insertan uno a uno
			for (ResultadoEvento r : resultadoEventos) {
				//Se añaden los parámetros al PreparedStatement
				pStmt.setString(1, Integer.toString(r.getIdResultado()));
				pStmt.setString(2, String.valueOf(r.getEvento()));
				pStmt.setString(3, String.valueOf(r.getSurfista()));
				pStmt.setString(4, Integer.toString(r.getPosicion()));
				
				if (pStmt.executeUpdate() != 1) {					
					logger.warning(String.format("No se ha insertado el ResultadoEvento: %s", r));
				} else {
						
					logger.info(String.format("Se ha insertado el ResultadoEvento: %s", r));
				}
			}
			
			logger.info(String.format("%d ResultadoEvento insertados en la BBDD", resultadoEventos.length));
		} catch (Exception ex) {
			logger.warning(String.format("Error al insertar ResultadoEvento: %s", ex.getMessage()));
		}			
	}
	
	
	/**
	 * Recupera los Surfistas de la BBDD.
	 */
	public List<Surfista> getSurfistas() {
		List<Surfista> surfistas = new ArrayList<>();
		String sql = "SELECT * FROM Surfista";
		
		//Se abre la conexión y se crea el PreparedStatement con la sentencia SQL
		try (Connection con = DriverManager.getConnection(connectionString);
		     PreparedStatement pStmt = con.prepareStatement(sql)) {			
			
			//Se ejecuta la sentencia y se obtiene el ResultSet
			ResultSet rs = pStmt.executeQuery();			
			Surfista surfista;
			
			//Se recorre el ResultSet y se crean objetos
			while (rs.next()) {
				surfista = new Surfista(rs.getInt("idSurfista"), 
						rs.getString("nombre"), 
						rs.getString("paisOrigen"), 
						rs.getInt("puestoRanking"));
				
				//Se inserta cada nuevo cliente en la lista de clientes
				surfistas.add(surfista);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			logger.info(String.format("Se han recuperado %d surfistas.", surfistas.size()));			
		} catch (Exception ex) {
			logger.warning(String.format("Error recuperar los surfistas: %s", ex.getMessage()));						
		}		
		
		return surfistas;
	}
	
	/**
	 * Recupera de la BBDD un Personaje a partir de su ID 
	 */
	public Surfista getSurfistaById(int id) {
		Surfista surfista = null;
		String sql = "SELECT * FROM Surfista WHERE idSurfista = ? LIMIT 1";
		
		//Se abre la conexión y se crea el PreparedStatement con la sentencia SQL
		try (Connection con = DriverManager.getConnection(connectionString);
		     PreparedStatement pStmt = con.prepareStatement(sql)) {			
			
			//Se definen los parámetros de la sentencia SQL
			pStmt.setInt(1, id);
			
			//Se ejecuta la sentencia y se obtiene el ResultSet
			ResultSet rs = pStmt.executeQuery();			

			//Se procesa el único resultado
			if (rs.next()) {
				surfista = new Surfista(rs.getInt("idSurfista"), 
						rs.getString("nombre"), 
						rs.getString("paisOrigen"), 
						rs.getInt("puestoRanking"));
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			logger.info(String.format("Se ha recuperado el surfista %s", surfista));			
		} catch (Exception ex) {
			logger.warning(String.format("Error recuperar los surfistas con id %d: %s", id, ex.getMessage()));						
		}		
		
		return surfista;
	}
	
	/**
	 * Recupera los Eventos de la BBDD. 
	 */
	public List<Evento> getEventos() {
		List<Evento> eventos = new ArrayList<>();
		String sql = "SELECT * FROM Evento";
		
		//Se abre la conexión y se crea el PreparedStatement con la sentencia SQL
		try (Connection con = DriverManager.getConnection(connectionString);
		     PreparedStatement pStmt = con.prepareStatement(sql)) {			
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = pStmt.executeQuery();			
			Evento evento;
			
			//Se recorre el ResultSet y se crean los Comics
			
			
			while (rs.next()) {
				
				/*String[] surfistasSeparados = rs.getString("participantes").split(";");
				
				ArrayList<Surfista> listaSurfistas = new ArrayList<>();

				// Recorrer cada parte separada (cada surfista)
				for (String surfistaInfo : surfistasSeparados) {
				    // Dividir la información de cada surfista por el guion
				    String[] info = surfistaInfo.split("-");

				    // Parsear los datos para crear un objeto Surfista
				    int id = Integer.parseInt(info[0]);
				    String nombre = info[1];
				    String pais = info[2];
				    int ranking = Integer.parseInt(info[3]);

				    // Crear el objeto Surfista y agregarlo a la lista
				    Surfista surfista = new Surfista(id, nombre, pais, ranking);
				    listaSurfistas.add(surfista);
				}*/
				
				String participantesString = rs.getString("participantes");
			    logger.info("Participantes de la base de datos: " + participantesString);

			    List<Surfista> listaSurfistas = new ArrayList<>();

			    String[] surfistasInfo = participantesString.split("-");
			    for (int i = 0; i < surfistasInfo.length; i += 4) {
			        int id = Integer.parseInt(surfistasInfo[i]);
			        String nombre = surfistasInfo[i + 1];
			        String pais = surfistasInfo[i + 2];
			        int ranking = Integer.parseInt(surfistasInfo[i + 3]);

			        Surfista surfista = new Surfista(id, nombre, pais, ranking);
			        listaSurfistas.add(surfista);
			    }

			    evento = new Evento(
			        rs.getInt("idEvento"),
			        rs.getString("nombre"),
			        rs.getString("fechaInicio"),
			        rs.getString("fechaFin"),
			        listaSurfistas
			    );

			    eventos.add(evento);
			}
			//Se cierra el ResultSet
			rs.close();
		
	
			logger.info(String.format("Se han recuperado %d eventos", eventos.size()));			
		}catch (Exception ex) {
			logger.warning(String.format("Error recuperar los eventos: %s", ex.getMessage()));						
		}		
		
		return eventos;
	}
	
	//metodo para crear el objeto surfista en una cadena para insertarlo en la base de datos
	public String convertirSurfistaACadena(Surfista surfista) {
	    // Aquí conviertes el surfista en una cadena con un formato específico
	    return String.format("%d-%s-%s-%d", surfista.getIdSurfista(), surfista.getNombre(), surfista.getPaisOrigen(), surfista.getPuestoRanking());
	}

	public Surfista convertirCadenaASurfista(String cadena) {
	    // Aquí conviertes la cadena a un objeto Surfista utilizando la lógica inversa del formateo
	    String[] partes = cadena.split("-");
	    int id = Integer.parseInt(partes[0]);
	    String nombre = partes[1];
	    String pais = partes[2];
	    int ranking = Integer.parseInt(partes[3]);
	    return new Surfista(id, nombre, pais, ranking);
	}

	

}
