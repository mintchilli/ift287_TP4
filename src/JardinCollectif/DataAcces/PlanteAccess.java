package JardinCollectif.DataAcces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

import JardinCollectif.Data.Membre;
import JardinCollectif.Data.Plante;
import JardinCollectif.Data.PlanteLot;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;

import java.sql.Date;

public class PlanteAccess {

	Connexion conn;

	public PlanteAccess(Connexion cx) {
		conn = cx;
	}
	
	public boolean ajouterPlante(String nomPlante, int tempsDeCulture) {
		try {
			Plante p = new Plante(nomPlante, tempsDeCulture);
			conn.getConnection().getCollection("Plante").insertOne(p.toDocument());
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean retirerPlante(String nomPlante) {
		try {
			if (getPlanteNbrTotal(getPlanteId(nomPlante)) > 0)
				return false;
			
			conn.getConnection().getCollection("Plante").deleteOne(eq("nomPlante", nomPlante));

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean planterPlante(String idLot, String idPlante, Date datePlantation, int nbExemplaires, Date dateDeRecoltePrevu) {
		try {
			PlanteLot pl = new PlanteLot(idLot, idPlante, datePlantation, dateDeRecoltePrevu, nbExemplaires);
			conn.getConnection().getCollection("PlanteLot").insertOne(pl.toDocument());

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean recolterPlante(String idPlante, String idLot) {
		try {
			conn.getConnection().getCollection("PlanteLot")
				.deleteOne(and(eq("idLot", idLot), eq("idPlante", idPlante)));
			
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String getPlanteId(String nomPlante) {
		try {
			return conn.getConnection().getCollection("Plante")
			.find(eq("nomPlante", nomPlante))
			.first()
			.getString("idPlante");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "-1";
	}
	
	public String getPlanteNom(String idPlante) {
		try {
			return conn.getConnection().getCollection("Plante")
					.find(eq("idPlante", idPlante))
					.first()
					.getString("nomPlante");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int getPlanteNbrTotal(String idPlante) {
		try {
			int total = 0;
			MongoCursor<Document> cursor = conn.getConnection()
					.getCollection("PlanteLot")
					.find(eq("idPlante", idPlante))
					.iterator();
			
			while (cursor.hasNext())
			{
				total += cursor.next().getInteger("nbExemplaires");
			}
			
			return total;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public int getTempsCulture(String nomPlante) {
		try {
			return conn.getConnection().getCollection("Plante")
					.find(eq("nomPlante", nomPlante))
					.first()
					.getInteger("tempsCulture");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public Date getDatePlantation(String idLot, String idPlante) {
		try {
			return (Date) conn.getConnection().getCollection("PlanteLot")
				.find(and(eq("idLot", idLot), eq("idPlante", idPlante)))
				.first()
				.getDate("datePlantation");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Date getDateDeRecoltePrevu(String idLot, String idPlante) {
		try {
			return (Date) conn.getConnection().getCollection("PlanteLot")
					.find(and(eq("idLot", idLot), eq("idPlante", idPlante)))
					.first()
					.getDate("dateDeRecoltePrevu");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<String> getPlantesList() {
		try {
			MongoCursor<Document> cursor = conn.getConnection()
					.getCollection("Plante")
					.find()
					.iterator();
			
			ArrayList<String> ret = new ArrayList<String>();
			
			while (cursor.hasNext())
			{
				Plante p = new Plante(cursor.next());
				
				String data = "Plante : ";
				data += p.getNomPlante();
				data += ", Nombre d'exemplaires : ";
				data += Integer.toString(getPlanteNbrTotal(p.getIdPlante()));
				
				ret.add(data);
			}

			return ret;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> getPlantesPourLot(String idLot) {
		try {
			MongoCursor<Document> cursor = conn.getConnection().getCollection("PlanteLot")
				.find(eq("idLot", idLot))
				.iterator();
			
			ArrayList<String> ret = new ArrayList<String>();
			
			while (cursor.hasNext())
			{
				PlanteLot pl = new PlanteLot(cursor.next());
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				
				String data = "Plante : ";
				data += getPlanteNom(pl.getIdPlante());
				data += ", Date de plantation : ";
				data += df.format(pl.getDatePlantation());
				data += ", Date de recolte prevu : ";
				data += df.format(pl.getDateDeRecoltePrevu());
				
				ret.add(data);
			}

			return ret;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
