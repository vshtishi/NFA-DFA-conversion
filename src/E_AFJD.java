import java.util.ArrayList;

public class E_AFJD {
    private char[] states;
    private char[] alphabet;
    private ArrayList<ArrayList<String>> table;

    E_AFJD(){
           table = new ArrayList();
    }

    public void setStates(char[] states){
        this.states = new char[states.length];
        System.arraycopy(states, 0, this.states, 0, states.length);
    }

    public void setAlphabet(char[] alphabet){
        this.alphabet = new char[alphabet.length];
        System.arraycopy(alphabet, 0, this.alphabet, 0, alphabet.length);
    }

    public void setTableRow(ArrayList<String> row){
        table.add(row);
    }

    public ArrayList<ArrayList<String>> getTable(){
        return this.table;
    }
}
