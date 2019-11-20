package JardinCollectif.Data;

import org.bson.Document;

public class Lot {

	private Integer idLot;
	private String nomLot;
	private Integer noMaxMembre;

	public Lot(String nomLot, Integer noMaxMembre) {
		super();
		this.nomLot = nomLot;
		this.noMaxMembre = noMaxMembre;
	}
	public Lot(Document d) {
		super();
		this.idLot = d.getInteger("idLot");
		this.nomLot = d.getString("nomLot");
		this.noMaxMembre = d.getInteger("noMaxMembre");
	}

	public Integer getIdLot() {
		return idLot;
	}

	public void setIdLot(Integer idLot) {
		this.idLot = idLot;
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
		return new Document("idLot", idLot).append("nomLot", nomLot).append("noMaxMembre", noMaxMembre);
	}

}
