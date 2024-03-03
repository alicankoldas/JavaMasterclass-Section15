package dev.lpa;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Set<Task> tasks = TaskData.getTasks("all");
        sortAndPrint("All Tasks", tasks);

        Set<Task> annsTasks = TaskData.getTasks("Ann");
        sortAndPrint("All Tasks", tasks);

        Set<Task> bobsTasks = TaskData.getTasks("Bob");
        sortAndPrint("All Tasks", tasks);

        Set<Task> carolsTasks = TaskData.getTasks("Carol");
        sortAndPrint("All Tasks", tasks);

        Comparator<Task> sortedByPriority =
                Comparator.comparing(Task::getPriority);
/*       Set<Task> annTasks = TaskData.getTasks("Ann");
        sortAndPrint("Ann's Tasks", annTasks, sortedByPriority);
*/
        List<Set<Task>> sets =
                List.of(annsTasks, bobsTasks, carolsTasks);

        Set<Task> assignedTasks = getUnion(sets);
        sortAndPrint("Assigned Tasks", assignedTasks);

        Set<Task> everyTask = getUnion(List.of(tasks, assignedTasks));
        sortAndPrint("The True All Tasks", everyTask);

        Set<Task> unassignedTasks = getDifference(tasks, assignedTasks);
        sortAndPrint("Unassigned Tasks", unassignedTasks,sortedByPriority);

        Set<Task> overlap = getUnion(List.of(
                getIntersect(annsTasks, bobsTasks),
                getIntersect(carolsTasks, bobsTasks),
                getIntersect(annsTasks, carolsTasks)
        ));
        sortAndPrint("Assigned to Multiples", overlap, sortedByPriority);

        List<Task> overlapping = new ArrayList<>();
        for(Set<Task> set: sets){
            Set<Task> dupes = getIntersect(set, overlap);
            overlapping.addAll(dupes);
        }
        Comparator<Task> priorityNatural = sortedByPriority.thenComparing(
                Comparator.naturalOrder());
        sortAndPrint("Overlapping", overlapping, priorityNatural);
    }

    private static Set<Task> getUnion(List<Set<Task>> sets){
        Set<Task> union = new HashSet<>();
        for(Set<Task> task : sets){
            union.addAll(task);
        }
        return union;
    }

    private static Set<Task> getIntersect(Set<Task> a, Set<Task> b){
        Set<Task> intersect = new HashSet<>(a);
        intersect.retainAll(b);
        return intersect;
    }

    private static Set<Task> getDifference(Set<Task> a, Set<Task> b){
        Set<Task> difference = new HashSet<>(a);
        difference.removeAll(b);
        return difference;
    }
    private static void sortAndPrint(String header, Collection<Task> collection){
        sortAndPrint(header, collection, null);
    }

    private static void sortAndPrint(String header, Collection<Task> collection,
                                Comparator<Task> sorter){
        String lineSeparator = "_".repeat(90);
        System.out.println(lineSeparator);
        System.out.println(header);
        System.out.println(lineSeparator);

        List<Task> list = new ArrayList<>(collection);
        list.sort(sorter);
        list.forEach(System.out::println);
    }


}