package test.gson;

import com.google.gson.Gson;
import utils.data.DataObjectBuilder;

public class TestGson {
    public static void main(String[] args) {
//        String jsonObject = "[{\n" +
//                "  \"username\": \"kyle\",\n" +
//                "  \"password\": \"12345678\",\n" +
//                "  \"job\": {\n" +
//                "    \"position\": \"SWE lv2\"\n" +
//                "  }\n" +
//                "},\n" +
//                "{\n" +
//                "  \"username\": \"thinh\",\n" +
//                "  \"password\": \"123456789\",\n" +
//                "  \"job\": {\n" +
//                "    \"position\": \"QAE lv1\"\n" +
//                "  }\n" +
//                "}]";
//        Gson gson = new Gson();
//        LoginCred[] loginCreds = gson.fromJson(jsonObject, LoginCred[].class);
//        for (LoginCred loginCred : loginCreds) {
////            System.out.println(loginCred);
//            System.out.println(loginCred.getJob().getPosition());
//        }
        String jsonFilePath = "/src/main/java/test/gson/LoginCred.json";
        LoginCred[] loginCreds = DataObjectBuilder.buildDataObject(jsonFilePath, LoginCred[].class);
        for (LoginCred loginCred : loginCreds) {
            System.out.println(loginCred);
            System.out.println(loginCred.getJob().getPosition());
        }
        String animalJsonFilePath = "/src/main/java/test/gson/Animal.json";
        Animal animal = DataObjectBuilder.buildDataObject(animalJsonFilePath, Animal.class);
        System.out.println(animal);

    }
}
