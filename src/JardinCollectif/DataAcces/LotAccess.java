package JardinCollectif.DataAcces;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

import JardinCollectif.Data.Lot;
import JardinCollectif.Data.MembreLot;

public class LotAccess {

	Connexion conn;

	public LotAccess(Connexion cx) {
		conn = cx;
	}

	public boolean ajouterLot(String nomLot, int noMaxMembre) {
		try {
			Lot l = new Lot(nomLot, noMaxMembre);
			conn.getConnection().getCollection("Lot").insertOne(l.toDocument());

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean rejoindreLot(int idLot, int noMembre) {
		try {
			MembreLot ml = new MembreLot(noMembre, idLot);
			conn.getConnection().getCollection("Lot").insertOne(ml.toDocument());

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public int getLotid(String nomLot) {
		try {
			Lot lot = new Lot(conn.getConnection().getCollection("Lot").find(eq("nomLot", nomLot)).first());

			Integer idLot = lot.getIdLot();

			if (idLot != null) {
				return idLot;
			}

			return -1;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	public boolean accepterDemande(int idLot, int noMembre) {
		try {
			conn.getConnection().getCollection("MembreLot").updateOne(eq("noMembre", noMembre), set("validationAdmin", true));

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean refuserDemande(int idLot, int noMembre) {
		try {
			conn.getConnection().getCollection("MembreLot").deleteOne(and(eq("noMembre", noMembre),eq("idLot", idLot)));
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public int getMembreMax(String nomLot) {
		try {
			Lot l = new Lot(conn.getConnection().getCollection("Lot").find(eq("nomLot", nomLot)).first());
			Integer max = l.getNoMaxMembre();

			if (max != null) {
				return max;
			}
			return -1;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public boolean supprimerLot(String nomLot) {
		try {
			conn.getConnection().getCollection("MembreLot").deleteOne(eq("nomLot", nomLot));
			conn.getConnection().getCollection("Lot").deleteOne(eq("nomLot", nomLot));
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public int getPlantsForLot(String nomLot) {
		try {

			return (int) conn.getConnection().getCollection("MembreLot").countDocuments(eq("idLot", getLotid(nomLot)));

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public ArrayList<String> getLots() {
		try {

			List<Lot> lots = new ArrayList<Lot>();
			MongoCursor<Document> cursor = conn.getConnection().getCollection("Lot").find().iterator();
			while (cursor.hasNext()) {
				lots.add(new Lot(cursor.next()));

			}

			ArrayList<String> ret = new ArrayList<String>();

			for (Lot lot : lots) {
				String data = "";
				data += lot.getNomLot();
				data += ",";
				data += lot.getNoMaxMembre();
				ret.add(data);
			}

			return ret;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Integer> getMembrePourLot(int lotId) {
		try {
			List<Lot> l = new ArrayList<Lot>();
			MongoCursor<Document> cursor = conn.getConnection().getCollection("Lot").find(and(eq("idLot", lotId),eq("validationAdmin", true))).iterator();
			while (cursor.hasNext()) {
				l.add(new Lot(cursor.next()));

			}
			cursor = conn.getConnection().getCollection("MembreLot").find(and(eq("idLot", lotId),eq("validationAdmin", true))).iterator();
			List<MembreLot> ml = new ArrayList<MembreLot>();
			while (cursor.hasNext()) {
				ml.add(new MembreLot(cursor.next()));

			}

			ArrayList<Integer> ret = new ArrayList<Integer>();

			for (MembreLot membreLots : ml) {
				ret.add(membreLots.getIdMembre());
			}

			return ret;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
