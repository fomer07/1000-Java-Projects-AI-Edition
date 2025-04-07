import java.util.Scanner;

public class Home {

    private final TaskManager taskManager;

    public Home() {
        this.taskManager = new TaskManager();
    }

    public void welcome(){
        System.out.println("Hello, World!");
        System.out.println("Welcome to your Task Tracking application!.. ");
        System.out.println("To navigate in the system please kindly press corresponding alphabet character");
        System.out.println("--- List Your All Tasks a---");
        System.out.println("--- List Your All Current Tasks b---");
        System.out.println("--- List Your All Completed Tasks c---");
        System.out.println("--- List Your All Tasks In Progress d---"); // TODO change it
        System.out.println("--- Add A Brand New Task To Your Tasks f---"); // TODO change it
        System.out.println("--- Exit from your progress q---");

        Scanner scanner = new Scanner(System.in);
        char c = scanner.next().charAt(0);

        switch (c){
            case 'a':
                tasks();
                break;
            case 'b':
                currentTask();
                break;
            case 'c':
                completedTask();
                break;
            case 'd':
                inProgressTasks();
                break;
            case 'f':
                addTask();
                break;
            case 'q':
                System.exit(0);
                break;
        }

    }

    public void tasks(){
        System.out.println("----- List of Your All Tasks -----");
        taskManager.renderAllTasks();
        System.out.println("Press 'q' to exit.. ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (s.charAt(0) == 'q'){
            welcome();
        }

        if (s.startsWith("delete")){
            String substring = s.substring(s.indexOf(" ") + 1);
            taskManager.updateState(substring, State.DELETED);
            tasks();
        }

    }

    public void currentTask(){
        System.out.println("----- List of Your All Current Tasks -----");
        taskManager.renderAllCurrentTasks();
        System.out.println("Press 'q' to exit.. ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (s.charAt(0) == 'q'){
            welcome();
        }

        if (s.endsWith("in progress")){
            String substring = s.substring(0,s.lastIndexOf(" ")-3);
            taskManager.updateState(substring, State.IN_PROGRESS);
            currentTask();
        }
    }

    public void completedTask(){
        System.out.println("----- List of Your All Completed Tasks -----");
        taskManager.renderAllCompletedTasks();
        System.out.println("Press 'q' to exit.. ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (s.charAt(0) == 'q'){
            welcome();
        }

        if (s.startsWith("move")){
            String substring = s.substring(s.indexOf(" ") + 1);
            taskManager.updateState(substring, State.COMPLETED_D);
            completedTask();
        }
    }

    public void inProgressTasks(){
        System.out.println("----- List of Your All In Progress Tasks -----");
        taskManager.renderAllInProgressTasks();
        System.out.println("Press 'q' to exit.. ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (s.charAt(0) == 'q'){
            welcome();
        }

        if (s.endsWith("completed")){
            String substring = s.substring(0,s.lastIndexOf(" "));
            taskManager.updateState(substring, State.COMPLETED);
            inProgressTasks();
        }
    }

    public void addTask(){
        System.out.println("----- Please text your most important task ---");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        if (!text.isEmpty()){
            taskManager.addTask(text);
        }
        System.out.println("Well done");
        System.out.println("Going back to the home... ");
        welcome();
    }
}
