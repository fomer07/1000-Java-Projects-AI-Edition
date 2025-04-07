import java.io.*;

import java.util.List;

public class FileManager {

    private static final String FILE_PATH = "tasks.txt";
    private final File file = new File(FILE_PATH);


    public void loadDatabase(){
        if(!file.exists()){
            try {
                file.createNewFile();
            }catch (Exception e){
                System.out.println("Error creating task file: " + e.getMessage());
            }
        }
    }

}
