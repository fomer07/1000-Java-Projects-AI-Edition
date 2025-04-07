import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String FILE_PATH = "tasks.txt";

    public List<Task> allTasks() {
        List<Task> taskList = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null){
                if (!line.equals("")){
                    Task task = mapStringToTaskObject(line);
                    if (task.getState() != State.DELETED){
                        taskList.add(task);
                    }
                }
            }
        }catch (IOException e){
            System.out.println("Error reading task file: " + e.getMessage());
        }
        return taskList;
    }

    public void addTask(String text){
        Task task = new Task(text, State.AVAILABLE);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(task.toString());
            writer.newLine();
        }catch (IOException e) {
            System.out.println("Error writing task file: " + e.getMessage());
        }
    }

    public Task mapStringToTaskObject(String string){
        int lastSpace = string.lastIndexOf(' ');
        if (lastSpace == -1) throw new IllegalArgumentException("Invalid line format: " + string);

        String text = string.substring(0, lastSpace);
        String stateString = string.substring(lastSpace + 1);
        State state = State.valueOf(stateString);

        return new Task(text, state);
    }

    public void updateState(String text, State state){
        rewriteTasksFile(text, state);
    }

    public void rewriteTasksFile(String text, State state){

        try(    BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("temp_" + FILE_PATH))){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                if (!line.equals("")){
                    if(line.contains(text)){
                        line = text + " " + state;
                    }
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }
        }catch (IOException e) {
            System.out.println("Error writing task file: " + e.getMessage());
        }

        File file = new File(FILE_PATH);
        file.delete();
        new File("temp_" + FILE_PATH).renameTo(file);

    }

}
