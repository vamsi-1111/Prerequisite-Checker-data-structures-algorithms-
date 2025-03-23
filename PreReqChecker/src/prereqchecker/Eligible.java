package prereqchecker;
import java.util.*;
public class Eligible {
    private static HashMap<String, Boolean> CoursesMap;private static HashMap<String, ArrayList<String>>PreMap;
    public static void main(String[] args) {
        if (args.length < 3) {return;}
        String inputFile = args[0];String eligible = args[1];String outputFilePath = args[2];
        loadAdjacencyList(inputFile);
        ArrayList<String> finished=loadEligibleCourses(eligible);ArrayList<String> cTT = new ArrayList<>();
        CoursesMap = new HashMap<>();
        for (String c : finished) {
            completeCourse(c);
        }for (String c : PreMap.keySet()) {
            if (!CoursesMap.containsKey(c) && canTakeCourse(c)) {cTT.add(c);}
        }saveEligibleCourses(outputFilePath, cTT);
    }private static void loadAdjacencyList(String inputFile) {
        StdIn.setFile(inputFile);
        int courseCount = StdIn.readInt();
        PreMap = new HashMap<>();
        for (int i = 0; i < courseCount; i++) {
            String courseName = StdIn.readString();
            PreMap.put(courseName, new ArrayList<>());
        }int edge =StdIn.readInt();
        for (int i = 0;i<edge; i++) {
            String c = StdIn.readString();String pre = StdIn.readString();PreMap.get(c).add(pre);}
    }private static void completeCourse(String courseName) {
        CoursesMap.put(courseName, true);
        if (PreMap.containsKey(courseName)) {
            for (String prerequisite : PreMap.get(courseName)){completeCourse(prerequisite);}}
    }private static void saveEligibleCourses(String outputFile, ArrayList<String> courses) {
        StdOut.setFile(outputFile);
        for (String c : courses) {StdOut.println(c);}}
    private static boolean canTakeCourse(String courseName) {
        if (!PreMap.containsKey(courseName)) {return true;}
        for (String prereq : PreMap.get(courseName)) {
            if (!CoursesMap.getOrDefault(prereq, false)) {return false;}
        }return true;} 
    private static ArrayList<String> loadEligibleCourses(String inputFilePath) {
        StdIn.setFile(inputFilePath);
        int eligible = StdIn.readInt();
        ArrayList<String> finishedCourses = new ArrayList<>();
        for (int i =0;i<eligible; i++) {
        finishedCourses.add(StdIn.readString());
        }CoursesMap = new HashMap<>();
        ArrayList<String> cTT = new ArrayList<>();
        for (String course : finishedCourses) {
            if (!CoursesMap.containsKey(course)) {
                cTT.add(course);
                completeCourse(course);
            }}return cTT;}
}
