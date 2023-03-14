import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Aplicacao2 {
    public static void deserializaVendedores() {
        ArrayList<Vendedor> l = null;
        try {
            File arq = new File("VendedoresVendas.bin");
            if (arq.exists()) {
                ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
                l = (ArrayList<Vendedor>) objInput.readObject();
                objInput.close();
            }
        } catch (IOException erro1) {
            System.out.printf("Erro 1: %s", erro1.getMessage());
        } catch (ClassNotFoundException erro2) {
            System.out.printf("Erro 2: %s", erro2.getMessage());
        }
    }

    public static void gravarJson(ArrayList<Vendedor> l) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(l);
        try {
            FileWriter arq = new FileWriter("VendedoresVendas2.json");
            PrintWriter j=new PrintWriter(arq);
            j.write(json);
            arq.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}