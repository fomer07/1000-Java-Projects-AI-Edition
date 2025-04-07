import java.util.List;

public class TaskManager {

    private final DatabaseManager databaseManager;

    public TaskManager() {
        this.databaseManager = new DatabaseManager();
    }

    public void renderAllTasks(){
        List<Task> taskList = databaseManager.allTasks();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println(i+1 + ". " + task.getText());
        }
    }

    public void addTask(String text){
        databaseManager.addTask(text);
    }

    public void renderAllCurrentTasks(){
        List<Task> taskLists = databaseManager.allTasks();
        for (int i = 0; i < taskLists.size(); i++) {
            Task task = taskLists.get(i);
            if (task.getState().equals(State.AVAILABLE)){
                System.out.println(task.getText());
            }
        }
    }

    public void renderAllCompletedTasks(){
        List<Task> taskLists = databaseManager.allTasks();
        for (int i = 0; i < taskLists.size(); i++) {
            Task task = taskLists.get(i);
            if (task.getState().equals(State.COMPLETED)){
                System.out.println(task.getText());
            }
        }
    }

    public void renderAllInProgressTasks(){
        List<Task> taskLists = databaseManager.allTasks();
        for (int i = 0; i < taskLists.size(); i++) {
            Task task = taskLists.get(i);
            if (task.getState().equals(State.IN_PROGRESS)){
                System.out.println(task.getText());
            }
        }
    }

    public void updateState(String text, State state){
        databaseManager.updateState(text, state);
    }
}
