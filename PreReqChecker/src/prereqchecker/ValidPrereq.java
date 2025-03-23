package prereqchecker;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

public class ValidPrereq {

    private Map<String, ArrayList<String>> courseGraph;

    public ValidPrereq() {
        courseGraph = new HashMap<>();
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            StdOut.println("Usage: java -cp bin prereqchecker.ValidPrereq <Adjacency List Input File> <Valid Prereq Input File> <Valid Prereq Output File>");
            return;
        }

        ValidPrereq checker = new ValidPrereq();
        checker.loadCourses(args[0]);
        checker.loadAndAddPrerequisite(args[1]);
        boolean isPrerequisiteValid = checker.isPrerequisiteStructureValid();
        checker.writeOutput(args[2], isPrerequisiteValid);
    }

    private void loadCourses(String fileName) {
        StdIn.setFile(fileName);
        int numberOfCourses = StdIn.readInt();
        for (int i = 0; i < numberOfCourses; i++) {
            String courseID = StdIn.readString();
            courseGraph.put(courseID, new ArrayList<>());
        }

        int numberOfEdges = StdIn.readInt();
        for (int i = 0; i < numberOfEdges; i++) {
            String courseID = StdIn.readString();
            String prerequisiteID = StdIn.readString();
            courseGraph.get(courseID).add(prerequisiteID);
        }
    }

    private void loadAndAddPrerequisite(String fileName) {
        StdIn.setFile(fileName);
        String course = StdIn.readString();
        String prerequisite = StdIn.readString();
        courseGraph.get(course).add(prerequisite);
    }

    private boolean isPrerequisiteStructureValid() {
        for (String course : courseGraph.keySet()) {
            if (!isCourseValid(course, new HashSet<>())) {
                return false;
            }
        }
        return true;
    }

    private boolean isCourseValid(String course, Set<String> visited) {
        if (visited.contains(course)) {
            return false;
        }
        visited.add(course);
        for (String prereq : courseGraph.get(course)) {
            if (!isCourseValid(prereq, visited)) {
                return false;
            }
        }
        visited.remove(course);
        return true;
    }

    private void writeOutput(String fileName, boolean isPrerequisiteValid) {
        StdOut.setFile(fileName);
        StdOut.println(isPrerequisiteValid ? "YES" : "NO");
    }
}
