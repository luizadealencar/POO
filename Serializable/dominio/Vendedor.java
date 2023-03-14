import java.util.ArrayList;
import java.io.Serializable;

class Vendedor implements Serializable {
	private String nome;
	private ArrayList<Viagem> viagens = new ArrayList<Viagem>();

	public Vendedor(String nome) {
		setNome(nome);
	}

	void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	void adicionarViagem(Viagem viagem) { // viagem é um objeto, uma única viagem, e viagens é uma lista de Viagem
		this.viagens.add(viagem);
	}

	ArrayList<Viagem> getViagem() {
		return this.viagens;
	}

	public int calcValorTotalKm() {
		int totalkm = 0;
		for (int j = 0; j < this.viagens.size(); j++) {
			totalkm = totalkm + viagens.get(j).getKM();
		}
		return totalkm;
	}

	public double calcValorTotalVendas() {
		double totalvendas = 0;
		for (int j = 0; j < this.viagens.size(); j++) {
			totalvendas = totalvendas + viagens.get(j).getValorVendas();
		}
		return totalvendas;
	}

	public double calcValorTotalAReembolsar() {
		double totalreembolso = 0;
		for (int j = 0; j < this.viagens.size(); j++) {
			totalreembolso = totalreembolso + viagens.get(j).calcValorAReembolsar();
		}
		return totalreembolso;
	}

	public Viagem calcValorMenorKm() {
		int menorkm = viagens.get(0).getKM();
		int index = 0;
		for (int j = 0; j < this.viagens.size(); j++) {
			if (menorkm > viagens.get(j).getKM()) {
				menorkm = viagens.get(j).getKM();
				index = j;
			}
		}
		return this.viagens.get(index);
	}

	public Viagem calcValorMaiorReembolso() {
		double maioreembolso = viagens.get(0).calcValorAReembolsar();
		int index = 0;
		for (int j = 0; j < this.viagens.size(); j++) {
			if (maioreembolso < viagens.get(j).calcValorAReembolsar()) {
				maioreembolso = viagens.get(j).calcValorAReembolsar();
				index = j;
			}
		}
		return this.viagens.get(index);
	}

	public Viagem calcValorMaiorVendas() {
		double maiorvendas = viagens.get(0).getValorVendas();
		int index = 0;
		for (int j = 0; j < this.viagens.size(); j++) {
			if (maiorvendas < viagens.get(j).getValorVendas()) {
				maiorvendas = viagens.get(j).getValorVendas();
				index = j;
			}
		}
		return this.viagens.get(index);
	}

	public Viagem calcValorMaiorDuracao() {
		int maiordias = viagens.get(0).getDuracao();
		int index = 0;
		for (int j = 0; j < this.viagens.size(); j++) {
			if (maiordias < viagens.get(j).getDuracao()) {
				maiordias = viagens.get(j).getDuracao();
				index = j;
			}
		}
		return this.viagens.get(index);
	}

}