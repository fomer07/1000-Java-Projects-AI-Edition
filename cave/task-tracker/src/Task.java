public class Task {

    private String text;
    private State state;

    public Task(String text, State state) {
        this.text = text;
        this.state = state;
    }

    public String getText() {
        return text;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return text + " " + state;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setState(State state) {
        this.state = state;
    }
}
