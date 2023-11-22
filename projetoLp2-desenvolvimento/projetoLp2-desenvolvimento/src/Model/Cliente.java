package Model;

public class Cliente {
	public Cliente(String ID, String nome, String CPF, String eMail, String telefone, String endereco) {
		super();
		this.ID = ID;
		this.nome = nome;
		this.CPF = CPF;
		this.eMail = eMail;
		this.telefone = telefone;
		this.endereco = endereco;
	}
	protected String ID;
	protected String nome;
	protected String CPF;
	protected String eMail;
	protected String telefone;
	protected String endereco;
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String CPF) {
		this.CPF = CPF;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}
