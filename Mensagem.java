package entidades;

public class Mensagem {


	String mensagem;
	
	


	//construtor
	public Mensagem(String mensagem) {
		super();
		this.mensagem = mensagem;
	}




	@Override
	public String toString() {
		return "Mensagem:  " + mensagem ;
	}




	public String getMensagem() {
		return mensagem;
	}




	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
