package aplicacao;

import java.util.Scanner;

import entidades.Mensagem;
import fila.FilaMensagens;
import fila.FilaReclamacao;
import fila.FilaResolucao;
import fila.FilaSugestao;

public class AtendimentoMensagem {

	public static void main(String[] args) {
		FilaReclamacao filaReclamacao = new FilaReclamacao();
		FilaSugestao filaSugestao = new FilaSugestao();
		FilaResolucao filaResolucao = new FilaResolucao();

		String nome;
		String email;
		int tel;
		int motivoCont;
		String mensagem;

		FilaMensagens filaMensagens = new FilaMensagens();
		filaMensagens.init();

		Scanner le = new Scanner(System.in);

		int opcao;
		char tipoMens;
		String respNome;
		String respNomeW;
		int resolver = 0;
		char opAtend = 0;

		do {
			System.out.println(" 0 - Encerrar programa");
			System.out.println(" 1 - Recebimento da mensagem");
			System.out.println(" 2 - Atendimento da mensagem");
			System.out.println(" 3 - Recebimento e Encaminhamento de Resolução");
			System.out.println("Opção: ");
			opcao = le.nextInt();

			switch (opcao) {
			case 0:
				if (filaMensagens.isEmpty() && filaReclamacao.isEmpty() && filaSugestao.isEmpty()
						&& filaResolucao.isEmpty()) {
					System.out.println("Atendimento encerrado!");
				} else {
					System.out.println("Ainda há mensagens!");
					opcao = -1;
				}
				break;
			case 1:
				System.out.println("Recebimento da mensagem:");
				System.out.println("(W) - WhatsApp ou (A) - APP");
				tipoMens = le.next().toUpperCase().charAt(0);

				while (tipoMens != 'W' && tipoMens != 'A') {
					System.out.println("Digite corretamente: W ou A");
					tipoMens = le.next().toUpperCase().charAt(0);

				} // validação resposta
				switch (tipoMens) {
				case 'A':
					System.out.println("Nome (opcional) :");
					System.out.println("Dejesa inserir seu nome?");
					respNome = le.next();

					while (respNome.equalsIgnoreCase("sim") == false && respNome.equalsIgnoreCase("não") == false) {
						System.out.println("Sim ou não?");
						respNome = le.next();

					} // validação resposta

					//if (respNome == "sim") {
						System.out.println("Nome:");
						nome = le.next();
					//}
						
						System.out.println("Email:");
						email = le.next();
					
					break;
				case 'W':
					System.out.println("Nome (opcional) :");
					System.out.println("Dejesa inserir seu nome?");
					respNomeW = le.next();

					// validar respNomeW

					//if (respNomeW == "sim") {
						System.out.println("Nome:");
						nome = le.next();
					//}
					System.out.println("Telefone:");
					tel = le.nextInt();

					break;
				default:
					System.out.println("Opção inválida!");
				}

				System.out.println("Motivo do Contato: ");
				System.out.println(" 1 - Reclamação");
				System.out.println(" 2 - Sugestão");
				motivoCont = le.nextInt();

				while (motivoCont != 1 && motivoCont != 2) {
					System.out.println("Digite corretamente!");
					motivoCont = le.nextInt();

				}

				System.out.println("Sua mensagem:");
				mensagem = le.next();
				Mensagem msg = new Mensagem(mensagem);

				switch (motivoCont) {
				case 1:
					filaReclamacao.enqueue(msg);
					break;
				case 2:
					filaSugestao.enqueue(msg);
					break;
				default:
					System.out.println("Opção inválida!");

				}

				break;

			case 2:

				if (filaMensagens.isEmpty() && filaReclamacao.isEmpty() && filaSugestao.isEmpty()
						&& filaResolucao.isEmpty()) {
					System.out.println("Não há mensagens para resolver!");
				} else {
					System.out.println("Você quer resolver:");
					System.out.println(" 1 - Reclamação");
					System.out.println(" 2 - Sugestão");
					resolver = le.nextInt();

					while (resolver != 1 && resolver != 2) {
						System.out.println("Digite corretamente!");
						motivoCont = le.nextInt();

					} // validar

					if (resolver == 1 && filaReclamacao.isEmpty() == false) {
						System.out.println(filaReclamacao.dequeue());

						System.out.println(" A - Assunto prontamente respondido!");
						System.out.println(" B - Assunto precisa ser encaminhado para outro setor!");
						opAtend = le.next().toUpperCase().charAt(0);
						while (opAtend != 'A' && opAtend != 'B') {
							System.out.println("Digite corretamente: A ou B");
							tipoMens = le.next().toUpperCase().charAt(0);

						} // validação resposta
					

					} else if (resolver == 1 && filaReclamacao.isEmpty()) {
						System.out.println("Fila de reclamação vazia!");
						tipoMens = 0;
					} else if (resolver == 2) {
						System.out.println(filaSugestao.dequeue());
						System.out.println(" A - Assunto prontamente respondido!");
						System.out.println(" B - Assunto precisa ser encaminhado para outro setor!");
						opAtend = le.next().toUpperCase().charAt(0);

						while (opAtend != 'A' && opAtend != 'B') {
							System.out.println("Digite corretamente: A ou B");
							tipoMens = le.next().toUpperCase().charAt(0);

						} // validação resposta
						
					} else {
						System.out.println("Fila de sugestão vazia!");
					}

					switch (opAtend) {
					case 'A':
						System.out.println(
								"Enviada resposta para cliente: sua solicitação já foi resolvida. Obrigado!!!");
						break;
					default:
						System.out.println("Por favor aguarde, em breve você receberá resposta!");
						// filaResolucao.enqueue(mensagem);
					}

				} // fecha resolução (case 2)

				break;
			case 3:
				if (filaResolucao.isEmpty()) {
					System.out.println("Não há mensagens pendentes para resolver!");
				} else {
					System.out.println("Resolução da mensagem: " + filaResolucao.dequeue());
					System.out.println(
							"Enviada resposta para cliente: sua solicitação já foi resolvida pelo setor responsável. Obrigado!!!");
				}
				break;
			default:
				System.out.println("Opção inválida! \n");
			}

		} while (opcao != 0);

		le.close();

	}

}
