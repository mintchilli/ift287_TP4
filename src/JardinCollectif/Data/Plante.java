package JardinCollectif.Data;

public class Plante {

	private Integer idPlante;
	private String nomPlante;
	private int tempsCulture;

	public Plante(String nomPlante, int tempsCulture) {
		super();
		this.nomPlante = nomPlante;
		this.tempsCulture = tempsCulture;
	}

	public Integer getIdPlante() {
		return idPlante;
	}

	public void setIdPlante(Integer noPlante) {
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
}
