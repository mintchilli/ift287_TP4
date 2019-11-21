package JardinCollectif.DataAcces;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.set;
import JardinCollectif.Data.Membre;
import JardinCollectif.Data.MembreLot;

public class MembreAccess {

	Connexion conn;

	public MembreAccess(Connexion cx) {
		conn = cx;
	}

	public boolean inscrireMembre(String prenom, String nom, String motDePasse, int noMembre) {
		try {

			Membre m = new Membre(prenom, nom, motDePasse, noMembre);
			conn.getConnection().getCollection("Membre").insertOne(m.toDocument());
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean supprimerMembre(int noMembre) {
		try {
			conn.getConnection().getCollection("Membre").deleteOne(eq("noMembre", noMembre));

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean makeAdmin(int noMembre) {
		try {
			conn.getConnection().getCollection("Membre").updateOne(eq("noMembre", noMembre), set("estAdmin", true));

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<String> getMembreLots(int noMembre) {
		ArrayList<String> ret = new ArrayList<String>();
		MongoCursor<Document> cursor = conn.getConnection().getCollection("MembreLot").find(eq("noMembre", noMembre))
				.iterator();
		try {
			while (cursor.hasNext()) {
				MembreLot ml = new MembreLot(cursor.next());
				ret.add(ml.getIdLot());
			}
			cursor.close();
			return ret;

		} catch (Exception e) {
			e.printStackTrace();
			return ret;
		}

	}

	public int getMembreCount(String string) {
		try {
			return (int) conn.getConnection().getCollection("MembreLot").countDocuments(eq("_id", string));

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public ArrayList<String> getMembreList() {
		try {
			List<Membre> membres = new ArrayList<Membre>();
			MongoCursor<Document> cursor = conn.getConnection().getCollection("Membre").find().iterator();
			while (cursor.hasNext()) {
				membres.add(new Membre(cursor.next()));

			}

			ArrayList<String> ret = new ArrayList<String>();

			for (Membre membre : membres) {
				String data = "Prenom : ";
				data += membre.getPrenom();
				data += ", Nom : ";
				data += membre.getNom();
				data += ", Est un administrateur: ";
				if (membre.getIsAdmin())
					data += "oui";
				else
					data += "non";
				ret.add(data);
			}
			cursor.close();
			return ret;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getMembre(int noMembre) {
		try {
			Membre membre = new Membre(conn.getConnection().getCollection("Membre").find(eq("noMembre", noMembre)).first());

			String data = "";

			data = "Prenom : ";
			data += membre.getPrenom();
			data += ", Nom : ";
			data += membre.getNom();
			data += ", Est un administrateur: ";
			if (membre.getIsAdmin())
				data += "oui";
			else
				data += "non";

			return data;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
