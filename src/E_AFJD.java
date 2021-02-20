import java.util.ArrayList;

public class E_AFJD {
    private char[] states;
    private char[] alphabet;
    private ArrayList<ArrayList<String>> table;

    E_AFJD() {
        table = new ArrayList();
    }

    public void setStates(char[] states) {
        this.states = new char[states.length];
        System.arraycopy(states, 0, this.states, 0, states.length);
    }

    public void setAlphabet(char[] alphabet) {
        this.alphabet = new char[alphabet.length];
        System.arraycopy(alphabet, 0, this.alphabet, 0, alphabet.length);
    }

    public void setTableRow(ArrayList<String> row) {
        table.add(row);
    }

    public int getAlphabetLength(){
        return this.alphabet.length;
    }

    public char[] getStates(){
         return this.states;
    }

    public ArrayList<ArrayList<String>> getTable() {
        return this.table;
    }

    String[] showClosure(int closure_array[]) {
        String[] closure_table = new String[this.states.length];
        int states_count = this.states.length;
        String buffer;
        int i;
        for (i = 0; i < states_count; i++) {
            reset(closure_array, states_count);
            closure_array[i] = 2;

            String current_transition = this.table.get(i).get(this.alphabet.length);
            if (!current_transition.equals("-")) {

                // copy the NFA transition state to buffer
                buffer = current_transition;
                check(closure_array, buffer);
                int z = closure(closure_array, states_count);

                // till closure get completely saturated
                while (z != 100) {
                    String current = this.table.get(z).get(this.alphabet.length);
                    if (!current.equals("-")) {
                        buffer = current;

                        // call the check function
                        check(closure_array, buffer);
                    }
                    closure_array[z]++;
                    z = closure(closure_array, states_count);
                }
            }

            // print the e closure for every states_count of NFA
            System.out.print("Epsilon Mbyllja " + this.states[i] + ":");

            buffer = "          ";
            buffer = this.state(closure_array, states_count, buffer);
            closure_table[i] = buffer.trim();
            System.out.println(" " + closure_table[i]);
        }
        return closure_table;
    }

    void reset(int ar[], int size) {
        int i;

        // reset all the values of
        // the mapping array to zero
        for (i = 0; i < size; i++) {
            ar[i] = 0;
        }
    }

    void check(int ar[], String buffer) {
        int i, j;

        // To parse the individual states_count of NFA
        for (i = 0; i < buffer.length(); i++) {

            // Set hash map for the position 
            // of the states_count which is found
            j = new String(this.states).indexOf(buffer.charAt(i));
            ar[j]++;
        }
    }

    // To pick the next closure from closure set
    int closure(int ar[], int size) {
        int i;

// check new closure is present or not
        for (i = 0; i < size; i++) {
            if (ar[i] == 1)
                return i;
        }
        return (100);
    }

    String state(int ar[], int size, String buffer) {
        int j, k = 0;

// Combine multiple states of NFA
// to create new states of DFA
        for (j = 0; j < size; j++) {

            if (ar[j] != 0) {

                char[] bufferChars = buffer.toCharArray();
                bufferChars[k++] = this.states[j];
                buffer = String.valueOf(bufferChars);
            }
        }
        return buffer;
    }
}