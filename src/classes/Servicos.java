package classes;

import java.util.Scanner;

import processos.Repository;

public class Servicos extends Repository {

	Scanner scan = new Scanner(System.in);

	Cliente clienteLogado;
	Carro carroEscolhido;

	public void telaInicial() {
		System.out.printf("1- LOGIN 2- CADASTRO 3- SAIR: ");
		int op = scan.nextInt();
		if (op == 1) {
			this.clienteLogado = login();
			System.out.println("BEM-VINDO " + this.clienteLogado.getNome() + " !!");
			escolherCarro();
			escolherLavagem();
			System.out.println("SUAS INFORMAÇÕES ATUALIZADAS:");
			System.out.println("NOME --> " + this.clienteLogado.getNome());
			System.out.println("SALDO --> " + this.clienteLogado.getSaldo());
			telaInicial();
		} else if (op == 2) {
			cadastrarCliente();
			telaInicial();
		} else if (op == 3) {
			System.out.println("VOLTE SEMPRE!");
		} else {
			System.out.println("OPÇÃO INVÁLIDA!");
			telaInicial();
		}
	}

	public void escolherLavagem() {
		if (this.clienteLogado.getSaldo() >= 0) {
			Scanner scan = new Scanner(System.in);
			System.out.println("ESCOLHA O SERVIÇO:");
			System.out.println("1- LAVAGEM SIMPLES" + " // " + "2- LAVAGEM COMPLETA" + " // " + "3- LAVAGEM LANCER");
			int escolha = scan.nextInt();

			if (escolha == 1) {
				if (this.clienteLogado.getSaldo() >= 40) {
					lavagemSimples();
				}
			} else if (escolha == 2) {
				if (this.clienteLogado.getSaldo() >= 70) {
					lavagemCompleta();
				}
			} else if (escolha == 3) {
				if (this.clienteLogado.getSaldo() >= 1000) {
					lavagemLancer();
				}
			} else {
				System.out.println("OPÇÃO INVÁLIDA!");
				escolherLavagem();
			}
		} else {
			System.out.println("SEM SALDO");
		}
	}

	public void escolherCarro() {
		System.out.printf("1- ESCOLHER CARRO 2- CADASTRAR CARRO: ");
		int op = scan.nextInt();
		if (op == 1) {
			this.carroEscolhido = listarCarros();
			if (this.carroEscolhido == null) {
				escolherCarro();
			}
		} else if (op == 2) {
			cadastrarCarro();
			escolherCarro();
		}
	}

	public void lavagemSimples() {
		System.out.println("A LAVAGEM SIMPLES CUSTA R$40.00");
		this.clienteLogado.setSaldo(pagar(40));
	}

	public void lavagemCompleta() {
		System.out.println("A LAVAGEM COMPLETA CUSTA R$70.00");
		this.clienteLogado.setSaldo(pagar(70));
	}

	public void lavagemLancer() {
		System.out.println("A LAVAGEM LANCER CUSTA R$1.000");
		this.clienteLogado.setSaldo(pagar(1000));
	}
}
