// *** Contato = JavaBeans ***
package br.gov.go.sefaz.agenda.model;

public class Contato {
	
	private String idcon;
	private String nome;
	private String fone;
	private String email;
	
	public Contato() {
		super();
	}
	
	public Contato(String idcon, String nome, String fone, String email) {
		super();
		this.idcon = idcon;
		this.nome = nome;
		this.fone = fone;
		this.email = email;
	}
	
	public String getIdcon() {
		return idcon;
	}
	
	public void setIdcon(String idcon) {
		this.idcon = idcon;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getFone() {
		return fone;
	}
	
	public void setFone(String fone) {
		this.fone = fone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		StringBuilder contatoSb = new StringBuilder();
		contatoSb
			.append("[ID: ").append(this.getIdcon()).append(" | ")
			.append("Nome: ").append(this.getNome()).append(" | ")
			.append("Fone: ").append(this.getFone()).append(" | ")
			.append("E-mail: ").append(this.getEmail()).append("]");
		return contatoSb.toString();
	}
}