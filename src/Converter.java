import java.util.ArrayList;

public class Converter {

    public static void main(String[] args) {
        final String EPSILON = "Eps";

        int i, j;

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

    }
}
