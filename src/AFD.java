import java.lang.reflect.Array;
import java.util.ArrayList;

public class AFD {

    private AFD_State[] states;
    private char[] alphabet;
    private ArrayList<ArrayList<String>> table;

    AFD() {
        this.table = new ArrayList();
    }

    public void setStates(AFD_State[] states) {
        this.states = new AFD_State[states.length];
        System.arraycopy(states, 0, this.states, 0, states.length);
    }

    public void setTableRow(ArrayList<String> row) {
        this.table.add(row);
    }

    public ArrayList<ArrayList<String>> getTable() {
        return this.table;
    }

    // Check new DFA states can be
// entered in DFA table or not
    int indexing(AFD_State[] states, int last_index) {
        int i;

        for (i = 0; i < last_index; i++) {
            if (states[i].getStatus() == 0) {
                return 1;
            }
        }
        return -1;
    }

    /* To check New States in DFA */
    int new_states(AFD_State[] states, int last_index, String S) {

        int i;

// To check the current state is already
// being used as a DFA state or not in
// DFA transition table
        for (i = 0; i < last_index; i++) {
            if ((states[i].getState()).equals(S))
                return 0;
        }

// push the new

        return 1;
    }

    // Transition function from NFA to DFA
// (generally union of closure operation )
    String trans(String buffer, int M, String[] clsr_t, int st,
                 E_AFJD afjd, String TB) {

        int i, j, k, g;
        int arr[] = new int[st];
        int sz;
        reset(arr, st);
        String temp, temp2;
        char buff;
        ArrayList<ArrayList<String>> table = afjd.getTable();

// Transition function from NFA to DFA
        char[] afjd_states = afjd.getStates();

        for (i = 0; i < buffer.length(); i++) {

            j = new String(afjd_states).indexOf(buffer.charAt(i));

            temp = table.get(j).get(M);


            if (!temp.equals("-")) {
                sz = temp.length();
                g = 0;

                while (g < sz) {
                    k = new String(afjd_states).indexOf(temp.charAt(g));
                    temp2 = clsr_t[k];
                    this.check(arr, temp2, afjd_states);
                    g++;
                }
            }
        }
        temp = "0            ";

        temp = this.state(arr, st, temp, afjd_states);
        if (temp.charAt(0) != '0') {
            TB = temp.trim();
        } else
            TB = "-";
        return TB;
    }

    void reset(int ar[], int size) {
        int i;

        // reset all the values of
        // the mapping array to zero
        for (i = 0; i < size; i++) {
            ar[i] = 0;
        }
    }

    void check(int ar[], String buffer, char[] states) {
        int i, j;

        // To parse the individual states_count of NFA
        for (i = 0; i < buffer.length(); i++) {

            // Set hash map for the position
            // of the states_count which is found
            j = new String(states).indexOf(buffer.charAt(i));
            ar[j]++;
        }
    }

    String state(int ar[], int size, String buffer, char[] states) {
        int j, k = 0;


        for (j = 0; j < size; j++) {

            if (ar[j] != 0) {

                char[] bufferChars = buffer.toCharArray();
                bufferChars[k++] = states[j];
                buffer = String.valueOf(bufferChars);
            }
        }
        return buffer;
    }

    void Display_DFA(int last_index, int alphabet_count, int zz, AFD_State[] states) {
        int i, j;
        System.out.println("********************************************************");
        System.out.println("Tabela e kalimeve e AFD");
        System.out.println("Gjendjet e AFD:");

        for (i = 1; i < last_index; i++)
            System.out.print(states[i].getState() + " ");

        System.out.println("");
        System.out.print("Alfabeti: ");

        for (i = 0; i < alphabet_count; i++)
            System.out.print(i + "     ");
        System.out.println("  ");
        System.out.println(" --------------------");

        ArrayList<ArrayList<String>> table = this.getTable();


        for (i = 0; i < zz; i++) {
            System.out.print(states[i + 1].getState());
            System.out.println("       " + table.get(i));
            System.out.println(" ");
        }
    }
}
