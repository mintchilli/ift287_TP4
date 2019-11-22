package JardinCollectif.Data;

import java.sql.Date;

import org.bson.Document;

public class Plante {

	private String idPlante;
	private String nomPlante;
	private int tempsCulture;

	public Plante(String nomPlante, int tempsCulture) {
		super();
		this.nomPlante = nomPlante;
		this.tempsCulture = tempsCulture;
	}
	
	public Plante(Document d) {
		this.idPlante = d.getObjectId("_id").toHexString();
		this.nomPlante = d.getString("nomPlante");
		this.tempsCulture = d.getInteger("tempsCulture");
	}

	public String getIdPlante() {
		return idPlante;
	}

	public void setIdPlante(String noPlante) {
		this.idPlante = noPlante;
	}

	public String getNomPlante() {
		return nomPlante;
	}

	public void setNomPlante(String nomPlante) {
		this.nomPlante = nomPlante;
	}

	public int getTempsCulture() {
		return tempsCulture;
	}

	public void setTempsCulture(int tempsCulture) {
		this.tempsCulture = tempsCulture;
	}
	
	public Document toDocument() {
		return new Document("nomPlante", nomPlante)
				.append("tempsCulture", tempsCulture);
	}
}
