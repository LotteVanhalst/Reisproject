package be.kdg.reisproject.parsing;

import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.model.Reizen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.*;
import com.google.gson.*;

/**
 * @author Lotte Vanhalst
 * @version 1.0 8/05/2019 16:49
 */
public class ReizenGsonParser {
    public static void writeJson(Reizen reizen, String fileName){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();

        String jsonString = gson.toJson(reizen);
        try (FileWriter jsonWriter = new FileWriter(fileName)) {
            jsonWriter.write(jsonString);
            System.out.println("Json file saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Reizen readJso(String fileName){
        Reizen reizen = null;
        try (BufferedReader data = new BufferedReader(new FileReader(fileName))) {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.setPrettyPrinting().create();
            reizen = gson.fromJson(data, Reizen.class);
            for (Reis reis : reizen.getBeschikbareReizen()) {
                System.out.println("\t" + reis);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reizen;
    }
}
