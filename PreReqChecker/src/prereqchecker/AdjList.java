package prereqchecker;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class generates an adjacency list from a given input file containing information about courses
 * and their prerequisites. It then outputs the adjacency list to another file.
 *
 * Implementation Steps:
 *
 * Step 1:
 *   - AdjListInputFile name is passed through the command line as args[0].
 *   - Read from AdjListInputFile with the format:
 *     1. a (int): number of courses in the graph
 *     2. a lines, each with 1 course ID
 *     3. b (int): number of edges in the graph
 *     4. b lines, each with a source ID
 *
 * Step 2:
 *   - AdjListOutputFile name is passed through the command line as args[1].
 *   - Output to AdjListOutputFile with the format:
 *     1. c lines, each starting with a different course ID, then
 *        listing all of that course's prerequisites (space separated)
 */
public class AdjList {

    /**
     * The main method of the program.
     *
     * @param args Command line arguments specifying input and output files.
     */
    public static void main(String[] args) {
        if (args.length < 2) {return;}
        StdIn.setFile(args[0]);StdOut.setFile(args[1]);int num=StdIn.readInt();
        HashMap<String, ArrayList<String>> adj = buildAdjacencyList(num);
        printAdjacencyList(adj);
    }private static HashMap<String, ArrayList<String>> buildAdjacencyList(int numCourses) {
        HashMap<String, ArrayList<String>> adj = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {adj.put(StdIn.readString(), new ArrayList<>());}
        int Edges = StdIn.readInt();
        for (int i = 0; i < Edges; i++) {
            String sId = StdIn.readString();String tId = StdIn.readString();adj.get(sId).add(tId);
        }return adj;}
    private static void printAdjacencyList(HashMap<String, ArrayList<String>> adjacencyList) {
        for (Map.Entry<String, ArrayList<String>> e : adjacencyList.entrySet()) {
            StdOut.print(e.getKey() + " ");
            ArrayList<String> prereq = e.getValue();
            for (String pre : prereq) {StdOut.print(pre + " ");}StdOut.println();}}
}
