package logic.model;

import java.util.List;


public class Condominium {
	
	
	private int codiceCondominio;
	private String via;
	private List<User> listaPartecipanti;
	
	public Condominium(int codiceCondominio,String via, List<User> listaPartecipanti) {
		this.codiceCondominio = codiceCondominio;
		this.via = via;
		this.listaPartecipanti = listaPartecipanti;
	}

	public void setCodiceCondominio(int codiceCondominio) {
		this.codiceCondominio = codiceCondominio;
	}
	
	
	public int getCodiceCondominio() {
		return this.codiceCondominio;
	}
		
	public void setVia(String via) {
		this.via = via;
	}
	
	public String getVia() {
		return this.via;
	}
	
	public void setlistaPartecipanti(List<User> listaPartecipanti) {
		this.listaPartecipanti = listaPartecipanti;
	}
	
	public List<User> getlistaPartecipanti() {
		return this.listaPartecipanti;
	}
	
}
