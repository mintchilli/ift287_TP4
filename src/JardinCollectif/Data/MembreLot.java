package JardinCollectif.Data;

import org.bson.Document;

public class MembreLot {

	private Integer noMembre;
	private Integer idLot;
	private Boolean validationAdmin;

	public MembreLot(Integer noMembre, Integer idLot) {
		super();
		this.noMembre = noMembre;
		this.idLot = idLot;
		this.validationAdmin = false;
	}

	public MembreLot(Document d) {
		this.noMembre = d.getInteger("noMembre");
		this.idLot = d.getInteger("idLot");
		this.validationAdmin = d.getBoolean("validationAdmin");
	}

	public Integer getIdMembre() {
		return noMembre;
	}

	public void setIdMembre(Integer noMembre) {
		this.noMembre = noMembre;
	}

	public Integer getIdLot() {
		return idLot;
	}

	public void setNomLot(Integer idLot) {
		this.idLot = idLot;
	}

	public Boolean getValidationAdmin() {
		return validationAdmin;
	}

	public void setValidationAdmin(Boolean validationAdmin) {
		this.validationAdmin = validationAdmin;
	}

	public Document toDocument() {
		return new Document("noMembre", noMembre).append("idLot", idLot).append("validationAdmin", validationAdmin);
	}

}
