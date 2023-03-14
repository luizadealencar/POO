import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Locale;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Aplicacao {
	private static ArrayList<Vendedor> l = new ArrayList<Vendedor>();

	public static void loadVendedores(String[] args) {
		Locale local = new Locale("pt", "BR");
		Locale.setDefault(local);

		String nome, desc;
		int km, dias;
		float venda;
		try {
			FileReader arq = new FileReader(args[0]);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			nome = linha;
			while (!"fim".equals(nome)) {
				linha = lerArq.readLine();
				km = Integer.parseInt(linha);
				Vendedor vendedor = new Vendedor(nome);
				while (km != 0) {
					linha = lerArq.readLine();
					desc = linha;
					linha = lerArq.readLine();
					dias = Integer.parseInt(linha);
					linha = lerArq.readLine();
					venda = Float.parseFloat(linha.replace(",", "."));
					Viagem viagem = new Viagem(km, desc, dias, venda);
					vendedor.adicionarViagem(viagem);
					linha = lerArq.readLine();
					km = Integer.parseInt(linha);
				}
				linha = lerArq.readLine();
				nome = linha;
				l.add(vendedor);
			}
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

	}

	public static void relatorioVendedores(String[] args) {
		try {
			FileWriter r = new FileWriter(args[1]);
			PrintWriter gravarArq = new PrintWriter(r);
			if (l.size() != 0) {
				for (Vendedor v : l) {
					gravarArq.printf("%s\n", v.getNome());
					if (v.calcValorTotalKm() != 0) {
						gravarArq.printf("Km total: %d\n", v.calcValorTotalKm());
						gravarArq.printf("Reembolso total: R$ %.2f\n", v.calcValorTotalAReembolsar());
						gravarArq.printf("Vendas total: R$ %.2f\n", v.calcValorTotalVendas());
					} else {
						gravarArq.printf("Km total: 0\n");
						gravarArq.printf("Reembolso total: R$ 0,00\n");
						gravarArq.printf("Vendas total: R$ 0,00\n");
					}
				}

				Viagem v;
				for (Vendedor i : l) {
					gravarArq.printf("%s\n", i.getNome());
					if (i.calcValorTotalKm() != 0) {
						v = i.calcValorMenorKm();
						gravarArq.printf("Menor km: %s %d km\n", v.getDescricao(), v.getKM());
						v = i.calcValorMaiorReembolso();
						gravarArq.printf("Maior reembolso: %s R$ %.2f\n", v.getDescricao(), v.calcValorAReembolsar());
						v = i.calcValorMaiorVendas();
						gravarArq.printf("Maior venda: %s R$ %.2f\n", v.getDescricao(), v.getValorVendas());
						v = i.calcValorMaiorDuracao();
						gravarArq.printf("Maior duracao: %s %d dias\n", v.getDescricao(), v.getDuracao());
					} else {
						gravarArq.printf("Sem viagens neste periodo\n");
					}
				}
			}
			r.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
	}

	public static void main(String[] args) {
		loadVendedores(args);
		relatorioVendedores(args);
		serializaVendedores(l);
		gravarJsonAp1(l);
		Aplicacao2.deserializaVendedores();
		Aplicacao2.gravarJson(l);
	}

	public static void serializaVendedores(ArrayList<Vendedor> lista) {
		File arq = new File("VendedoresVendas.bin");
		try {
			arq.delete();
			arq.createNewFile();
			ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
			objOutput.writeObject(lista);
			objOutput.close();
		} catch (IOException erro) {
			System.out.printf("Erro: %s", erro.getMessage());
		}
	}

	public static void gravarJsonAp1(ArrayList<Vendedor> l) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(l);
		try {
			FileWriter writer = new FileWriter("VendedoresVendas1.json");
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}