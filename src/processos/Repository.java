package processos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.Carro;
import classes.Cliente;
import classes.Pagamento;

public abstract class Repository implements Pagamento {
	Scanner scan = new Scanner(System.in);
	List<Carro> listaCarros = new ArrayList<Carro>();
	List<Cliente> listaClientes = new ArrayList<Cliente>();

	Cliente clienteLogado;
	Carro carroEscolhido;

	public Cliente login() {
		System.out.printf("DIGITE SEU NOME --> ");
		String nome = scan.next();
		Cliente cliente = new Cliente(nome, 0);
		if (!validarCliente(cliente, 2)) {
			System.out.println("DADOS INVÁLIDOS!");
			login();
		}
		return this.clienteLogado;
	}

	public void cadastrarCarro() {
		Carro carro = setCarro();
		if (validarCarro(carro, 1)) {
			this.listaCarros.add(carro);
			System.out.println("SEU(A) " + carro.getMarca() + " FOI CADASTRADO(A) " + carro.getDono());
		}
	}

	public void cadastrarCliente() {
		Cliente cliente = setCliente();
		if (validarCliente(cliente, 1)) {
			this.listaClientes.add(cliente);
			System.out.println("CADASTRO REALIZADO COM SUCESSO " + cliente.getNome());
		}
	}

	public double pagar(double valor) {
		System.out.println("FORMAS DE PAGAMENTO:");
		System.out.printf("1- DÉBITO 2- PIX:  ");
		int op = scan.nextInt();
		if (op == 1) {
			debito(valor);
		} else if (op == 2) {
			pix(valor);
		} else {
			System.out.println("OPÇÃO INVÁLIDA");
			pagar(valor);
		}
		return this.clienteLogado.getSaldo();
	}

	public Carro listarCarros() {
		int numCarros = 0;
		for (Carro c : this.listaCarros) {
			if (c.getDono().equals(this.clienteLogado.getNome())) {
				System.out.println("MARCA --> " + c.getMarca());
				System.out.println("PLACA --> " + c.getPlaca());
				System.out.println("============================");
				numCarros++;
			}
		}
		if (numCarros == 0) {
			System.out.println("NENHUM CARRO CADASTRADO!");
			return null;
		} else {
			System.out.printf("DIGITE A PLACA DO CARRO ESCOLHIDO --> ");
			String placa = scan.next();
			for (Carro c : this.listaCarros) {
				if (c.getDono().equals(this.clienteLogado.getNome()) && c.getPlaca().equals(placa)) {
					this.carroEscolhido = c;
					break;
				}
			}
			if (this.carroEscolhido == null) {
				System.out.println("CARRO NÃO ENCONTRADO! TENTE NOVAMENTE.");
			}
		}
		return this.carroEscolhido;
	}

	@Override
	public double debito(double valor) {
		if (valor <= this.clienteLogado.getSaldo()) {
			this.clienteLogado.setSaldo(this.clienteLogado.getSaldo() - valor);
			System.out.println("TRANSAÇÃO APROVADA!");
		} else {
			System.out.println("SEM SALDO");
		}
		return this.clienteLogado.getSaldo();
	}

	@Override
	public double pix(double valor) {
		if (valor <= this.clienteLogado.getSaldo()) {
			this.clienteLogado.setSaldo(this.clienteLogado.getSaldo() - valor);
			System.out.println("PIX ENVIADO PARA: THIAGO KWON!");
		} else {
			System.out.println("SEM SALDO");
		}
		return this.clienteLogado.getSaldo();
	}

	public boolean validarCarro(Carro carro, int tipoValidacao) {
		boolean retorno = true;
		if (tipoValidacao == 1) {
			for (Carro c : this.listaCarros) {
				if (c.getPlaca().equals(carro.getPlaca())) {
					System.out.println("CARRO JÁ CADASTRADO");
					retorno = false;
				}
			}
		} else {
			for (int i = 0; i < this.listaCarros.size(); i++) {
				List<Carro> lista = this.listaCarros;
				if (!lista.get(i).getPlaca().equals(carro.getPlaca())
						|| !lista.get(i).getMarca().equals(carro.getMarca())
						|| !lista.get(i).getDono().equals(carro.getDono())) {
					retorno = false;
				} else {
					retorno = true;
				}
			}
		}
		return retorno;
	}

	public boolean validarCliente(Cliente cliente, int tipoValidacao) {
		boolean retorno = true;
		if (tipoValidacao == 1) {
			for (Cliente c : this.listaClientes) {
				if (c.getNome().equals(cliente.getNome())) {
					System.out.println("CLIENTE JÁ CADASTRADO");
					retorno = false;
				}
			}
		} else {
			for (int i = 0; i < this.listaClientes.size(); i++) {
				List<Cliente> lista = this.listaClientes;
				if (!lista.get(i).getNome().equals(cliente.getNome())) {
					retorno = false;
				} else {
					this.clienteLogado = lista.get(i);
					retorno = true;
					break;
				}
			}
		}
		return retorno;
	}

	public Carro setCarro() {
		System.out.println("INSIRA AS INFORMAÇÕES DO SEU CARRO:");
		System.out.printf("MARCA --> ");
		String marca = scan.next();
		System.out.printf("PLACA --> ");
		String placa = scan.next();
		Carro carro = new Carro(marca, placa, this.clienteLogado.getNome());
		return carro;
	}

	public Cliente setCliente() {
		System.out.println("INSIRA SUAS INFORMAÇÕES:");
		System.out.printf("SEU NOME --> ");
		String nome = scan.next();
		System.out.printf("SEU SALDO --> ");
		double saldo = scan.nextDouble();
		Cliente cliente = new Cliente(nome, saldo);
		return cliente;
	}

}
