package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import modele.Concert;
import modele.Conference;
import modele.Evenement;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class EvenementSerializer {

    public void  sauvegarder(Map<String, Evenement> evenementMap, String chemin) throws IOException {
        ObjectMapper mapperW= new ObjectMapper();

        mapperW.registerModule(new JavaTimeModule());
        mapperW.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        mapperW.enable(SerializationFeature.INDENT_OUTPUT);

        File fichier = new File(chemin);
        if (!fichier.exists()){
            fichier.createNewFile();
        }
        mapperW.writerWithDefaultPrettyPrinter().writeValue(new File(chemin), evenementMap);
        System.out.println("Evenment sauvegarder dans " + chemin);
    }
    public Map<String, Evenement> charger(String chemin) throws IOException {
        ObjectMapper mapper= new ObjectMapper();

        mapper.registerSubtypes(Conference.class, Concert.class);
        return mapper.readValue(new File(chemin), new TypeReference<Map<String, Evenement>>() {});

    }
}
