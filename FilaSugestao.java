package fila;

import entidades.Mensagem;

public class FilaSugestao {

	public final int N = 3;
	Mensagem dados[] = new Mensagem [N];
	int ini, fim, cont;

	public void init() {
		ini = fim = cont = 0;

	}

	public boolean isEmpty() {
		if (cont == 0)
			return true;
		else
			return false;
	}

	public boolean isFull() {
		if (cont == N)
			return true;
		else
			return false;
	}

	public void enqueue(Mensagem mensagem) {
		if (isFull())
			System.out.println("Fila Cheia!");
		else {
			dados[fim] = mensagem;
			fim = (fim + 1) % N;
			cont++;
		}

	}

	public Mensagem dequeue() {
		Mensagem mensagem = dados[ini];
		ini = (ini + 1) % N;
		cont--;
		return mensagem;
	}

	public Mensagem first() {
		return dados[ini];
	}
	
	
}