package porjeotooo;


import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DataManger {

    private static final String FILE_PATH = "porjeotooo.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void salvarDados(ClinicaService service) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(service, writer);
            System.out.println("[Integração Externa] Dados salvos em " + FILE_PATH + " com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados externos: " + e.getMessage());
        }
    }
    
}
