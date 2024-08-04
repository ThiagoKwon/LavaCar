package classes;

public class Carro {

	public String marca;
	private String placa;
	private String dono;

	public Carro(String marca, String placa, String dono) {
		this.marca = marca;
		this.placa = placa;
		this.dono = dono;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getDono() {
		return dono;
	}

	public void setDono(String dono) {
		this.dono = dono;
	}

}
