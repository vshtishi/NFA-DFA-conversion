import java.util.ArrayList;

public class AFD {

    private String[] states;
    private char[] alphabet;
    private ArrayList<ArrayList<String>> table;

    AFD() {
        table = new ArrayList();
    }

    public void setStates(String[] states) {
        this.states = new String[states.length];
        System.arraycopy(states, 0, this.states, 0, states.length);
    }
}
