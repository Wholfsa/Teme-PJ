package lab5ex2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.*;
import java.util.*;

public class MainAPP {
    public static void scriere(List<PerecheNr> lista) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("perechi.json"), lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<PerecheNr> citire() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("perechi.json"), new TypeReference<List<PerecheNr>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        List<PerecheNr> lista = List.of(
                new PerecheNr(8, 13),
                new PerecheNr(21, 34),
                new PerecheNr(5, 6)
        );

        scriere(lista);
        List<PerecheNr> listaCitita = citire();

        if (listaCitita != null) {
            listaCitita.forEach(System.out::println);
        }
    }
}
