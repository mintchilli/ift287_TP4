package JardinCollectif.Data;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Lot {

	private String idLot;
	private String nomLot;
	private Integer noMaxMembre;

	public Lot(String nomLot, Integer noMaxMembre) {
		super();
		this.nomLot = nomLot;
		this.noMaxMembre = noMaxMembre;
	}
	public Lot(Document d) {
		super();
		this.idLot = d.getObjectId("_id").toHexString();
		this.nomLot = d.getString("nomLot");
		this.noMaxMembre = d.getInteger("noMaxMembre");
	}

	public String getIdLot() {
		return idLot;
	}


	public String getNomLot() {
		return nomLot;
	}

	public void setNomLot(String nomLot) {
		this.nomLot = nomLot;
	}

	public Integer getNoMaxMembre() {
		return noMaxMembre;
	}

	public void setNoMaxMembre(Integer noMaxMembre) {
		this.noMaxMembre = noMaxMembre;
	}

	public Document toDocument() {
		return new Document("nomLot", nomLot).append("noMaxMembre", noMaxMembre);
	}

}
