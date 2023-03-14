import java.io.Serializable;

public class Viagem implements Serializable {
	private String desc;
	private int km, dias;
	private double venda, valor;

	public Viagem(int km, String desc, int dias, double venda) {
		setkm(km);
		setDescricao(desc);
		setDuracao(dias);
		setValorVendas(venda);
	}

	void setDescricao(String desc) {
		this.desc = desc;
	}

	public String getDescricao() {
		return desc;
	}

	void setkm(int km) {
		this.km = km;
	}

	public int getKM() {
		return this.km;
	}

	void setDuracao(int dias) {
		this.dias = dias;
	}

	public int getDuracao() {
		return this.dias;
	}

	void setValorVendas(double venda) {
		this.venda = venda;
	}

	public double getValorVendas() {
		return this.venda;
	}

	public double calcValorAReembolsar() {
		if (getKM() < 150) {
			valor = 0.40f * getKM();
		} else if (getKM() >= 150 && getKM() < 600) {
			valor = 0.38f * getKM();
		} else {
			valor = 0.35f * getKM();
		}
		return valor;
	}
}