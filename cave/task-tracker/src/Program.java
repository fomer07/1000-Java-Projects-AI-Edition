public class Program {

    private final FileManager fileManager;
    private final Home home;

    public Program() {
        this.fileManager = new FileManager();
        this.home = new Home();
    }

    public void run(){
        initialiseSetup();
        start();
    }

    public void initialiseSetup(){
        fileManager.loadDatabase();
    }

    public void start(){
        home.welcome();
    }
}
