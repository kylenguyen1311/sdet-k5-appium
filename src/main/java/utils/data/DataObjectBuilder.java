package utils.data;

import com.google.gson.Gson;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataObjectBuilder {
    public static <T> T buildDataObject(String filePath, Class<T> dataType){
        String absolutePath = System.getProperty("user.dir").concat(filePath);

        try (        Reader reader = Files.newBufferedReader(Paths.get(absolutePath));
        ){
            Gson gson = new Gson();
            return gson.fromJson(reader, dataType);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }
}
