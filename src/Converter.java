import java.util.ArrayList;
import java.util.HashMap;

public class Converter {

    public static void main(String[] args) {
        final String EPSILON = "Eps";

        int i, j;
        int zz = 0;
        int last_index = 0;
        String T_buf = new String();

        int states_count = 6;
        int alphabet_count = 2;
        char[] states = {'A', 'B', 'C', 'D', 'E', 'F'};
        char[] alphabet = {'0', '1'};

        System.out.print("Gjendjet e E-AFJD: ");

        for (i = 0; i < states_count; i++) {
            System.out.print(states[i] + " ");
        }
        System.out.println(" ");

        System.out.print("Alfabeti i E-AFJD: ");

        for (i = 0; i < alphabet_count; i++) {
            System.out.print(alphabet[i] + " ");
        }
        System.out.print(EPSILON);
        System.out.println(" ");
        E_AFJD sample = new E_AFJD();
        sample.setStates(states);
        sample.setAlphabet(alphabet);
        AFD result = new AFD();
        ArrayList<String> A_transition = new ArrayList<>();
        ArrayList<String> B_transition = new ArrayList<>();
        ArrayList<String> C_transition = new ArrayList<>();
        ArrayList<String> D_transition = new ArrayList<>();
        ArrayList<String> E_transition = new ArrayList<>();
        ArrayList<String> F_transition = new ArrayList<>();

        A_transition.add("FC");
        A_transition.add("-");
        A_transition.add("BF");
        sample.setTableRow(A_transition);
        B_transition.add("-");
        B_transition.add("C");
        B_transition.add("-");
        sample.setTableRow(B_transition);
        C_transition.add("-");
        C_transition.add("-");
        C_transition.add("D");
        sample.setTableRow(C_transition);
        D_transition.add("E");
        D_transition.add("A");
        D_transition.add("-");
        sample.setTableRow(D_transition);
        E_transition.add("A");
        E_transition.add("-");
        E_transition.add("BF");
        sample.setTableRow(E_transition);
        F_transition.add("-");
        F_transition.add("-");
        F_transition.add("-");
        sample.setTableRow(F_transition);
        ArrayList<ArrayList<String>> table = sample.getTable();

        System.out.println("Tabela e kalimeve te E-AFJD: ");
        System.out.println(" ");
        System.out.print("     [");

        for (i = 0; i < alphabet_count; i++)
            System.out.print(alphabet[i] + ", ");
        System.out.print(EPSILON);

        System.out.println("]");
        System.out.print("----------------------");

        System.out.println("   ");
        for (i = 0; i < states_count; i++) {
            System.out.print(states[i] + "->|");

            System.out.println(" " + table.get(i));
            System.out.println("   |");
        }

        sample.setStates(states);
        int closure_ar[] = new int[states_count];
        String closure_table[] = new String[states_count];

        closure_table = sample.showClosure(closure_ar);
        AFD_State[] AFD_states = new AFD_State[states_count];

        for (int x = 0; x < states_count; x++) {
            AFD_states[x] = new AFD_State();
        }

        AFD_states[last_index++].setState("-");
        AFD_states[last_index - 1].setStatus(1);

        String buffer = "                ";

        buffer = closure_table[0];

        AFD_states[last_index++].setState(buffer);


        int Sm = 1, ind = 1;
        int start_index = 1;

// Filling up the DFA table with transition values
// Till new states can be entered in DFA table


        while (ind != -1) {

            AFD_states[start_index].setStatus(1);
            Sm = 0;
            ArrayList<String> transition = new ArrayList<>();
            for (i = 0; i < alphabet_count; i++) {

                T_buf = result.trans(buffer, i, closure_table, states_count, sample, T_buf);

                // storing the new DFA state in buffer
                transition.add(T_buf);

                // parameter to control new states
                int append = 1;

// To check the current state is already
// being used as a DFA state or not in
// DFA transition table
                for (int m = 0; m < last_index; m++) {
                    if ((AFD_states[m].getState()).equals(T_buf))
                        append = 0;
                }

                if (append == 1) {
                    AFD_states[last_index++].setState(T_buf);


                    AFD_states[last_index - 1].setStatus(0);
                }

                Sm = Sm + append;


            }
            ind = result.indexing(AFD_states, last_index);

            if (ind != -1)
                buffer = AFD_states[++start_index].getState();

            zz++;
            result.setTableRow(transition);

        }
// display the DFA TABLE
        result.Display_DFA(last_index, alphabet_count, zz, AFD_states);

    }


}
