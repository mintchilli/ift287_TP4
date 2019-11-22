package JardinCollectif.Data;

import java.sql.Date;

import org.bson.Document;

public class PlanteLot {

	private String idLot;
	private String idPlante;
	private Date datePlantation;
	private Date dateDeRecoltePrevu;
	private int nbExemplaires;

	public PlanteLot(String idLot, String idPlante, Date datePlantation, Date dateDeRecoltePrevu, int nbExemplaires) {
		super();
		this.idLot = idLot;
		this.idPlante = idPlante;
		this.datePlantation = datePlantation;
		this.dateDeRecoltePrevu = dateDeRecoltePrevu;
		this.nbExemplaires = nbExemplaires;
	}
	
	public PlanteLot(Document d) {
		this.idLot = d.getString("idLot");
		this.idPlante = d.getString("idPlante");
		this.datePlantation = (Date) d.getDate("datePlantation");
		this.dateDeRecoltePrevu = (Date) d.getDate("dateDeRecoltePrevu");
		this.nbExemplaires = d.getInteger("nbExemplaires");
	}

	public String getIdLot() {
		return idLot;
	}

	public void setIdLot(String idLot) {
		this.idLot = idLot;
	}

	public String getIdPlante() {
		return idPlante;
	}

	public void setIdPlante(String idPlante) {
		this.idPlante = idPlante;
	}

	public Date getDatePlantation() {
		return datePlantation;
	}

	public void setDatePlantation(Date datePlantation) {
		this.datePlantation = datePlantation;
	}

	public Date getDateDeRecoltePrevu() {
		return dateDeRecoltePrevu;
	}

	public void setDateDeRecoltePrevu(Date dateDeRecoltePrevu) {
		this.dateDeRecoltePrevu = dateDeRecoltePrevu;
	}

	public int getNbExemplaires() {
		return nbExemplaires;
	}

	public void setNbExemplaires(int nbExemplaires) {
		this.nbExemplaires = nbExemplaires;
	}
	
	public Document toDocument() {
		return new Document("idLot", idLot).append("idPlante", idPlante).append("datePlantation", datePlantation)
				.append("dateDeRecoltePrevu", dateDeRecoltePrevu).append("nbExemplaires", nbExemplaires);
	}
}
