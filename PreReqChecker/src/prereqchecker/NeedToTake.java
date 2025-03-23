package prereqchecker;
import java.util.Queue;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * This class identifies courses that a student needs to take based on prerequisites.
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
 *   - NeedToTakeInputFile name is passed through the command line as args[1].
 *   - Read from NeedToTakeInputFile with the format:
 *     1. One line, containing a course ID
 *     2. c (int): Number of courses
 *     3. c lines, each with one course ID
 *
 * Step 3:
 *   - NeedToTakeOutputFile name is passed through the command line as args[2].
 *   - Output to NeedToTakeOutputFile with the format:
 *     1. Some number of lines, each with one course ID
 */
public class NeedToTake {
    public static void main(String[] args) {
        if (args.length < 3) {
            return;
        }
HashMap<String, HashSet<String>> preGraph = buildPrerequisiteGraph(args[0]);
        StdIn.setFile(args[1]);
        String targetCourse = StdIn.readLine();
        HashSet<String> takenCourses = readCoursesFromFile(args[1]);HashSet<String> cTT = new HashSet<>();cTT.add(targetCourse);HashSet<String> flatGraph = flattenGraph(takenCourses, preGraph);
        cTT = flattenGraph(cTT,preGraph);HashSet<String> eligibleCourses =findEligibleCourses(cTT,flatGraph,targetCourse);
StdOut.setFile(args[2]);printCourses(eligibleCourses);
    }
    private static HashSet<String> flattenGraph(HashSet<String> takenCourses, HashMap<String, HashSet<String>> graph) {
        HashSet<String> flatGraph = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        for (String course : takenCourses) {q.add(course);
        }while (!q.isEmpty()) {String course = q.remove();
            if (!flatGraph.contains(course)) {
                flatGraph.add(course);
                for (String prerequisite : graph.get(course)) {q.add(prerequisite);
                }}}return flatGraph;
    }   private static HashMap<String, HashSet<String>> buildPrerequisiteGraph(String adjacencyListFilePath) {
        StdIn.setFile(adjacencyListFilePath);
        int numCourses = StdIn.readInt();StdIn.readLine();
        HashMap<String, HashSet<String>> preGraph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            preGraph.put(StdIn.readLine(), new HashSet<>());}int numEdges = StdIn.readInt();
        StdIn.readLine();
        for (int i = 0; i <numEdges; i++) {
            String targetCourse = StdIn.readString();
            String prerequisiteCourse = StdIn.readString();StdIn.readLine();
            HashSet<String> prerequisites = preGraph.get(targetCourse);
            prerequisites.add(prerequisiteCourse);preGraph.put(targetCourse, prerequisites);}return preGraph;}
    private static HashSet<String> findEligibleCourses(HashSet<String> cToTake, HashSet<String> flatGraph, String targetCourse) {
        HashSet<String> elCourses = new HashSet<>();
        for (String course : cToTake) {
         boolean take =!flatGraph.contains(course) && !course.equals(targetCourse);
    if (take)elCourses.add(course);}return elCourses;
    }private static void printCourses(HashSet<String>C) {
        for (String c : C){StdOut.println(c);System.out.println(c);}
    }
    private static HashSet<String> readCoursesFromFile(String filePath) {
        HashSet<String> css = new HashSet<>();
        StdIn.setFile(filePath);StdIn.readLine();
        int numCourses= StdIn.readInt();StdIn.readLine();
        for (int i = 0; i <numCourses; i++) {
        String cs = StdIn.readLine();css.add(cs);}return css;}
}
