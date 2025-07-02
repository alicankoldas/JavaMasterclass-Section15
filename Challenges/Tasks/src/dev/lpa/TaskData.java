package dev.lpa;

import java.util.HashSet;
import java.util.Set;

public class TaskData {

    private static final String allTasks =
            """
                All Tasks
                Infrastructure, Logging, High
                Infrastructure, DB Access, Medium
                Infrastructure, Security, High
                Infrastructure, Password Policy, Medium
                Data Design, Task Table, Medium
                Data Design, Employee Table, Medium
                Data Design, Cross Reference Tables, High
                Data Design, Encryption Policy, High
                Data Access, Write Views, Low
                Data Access, Set Up Users, Low
                Data Access, Set Up Access Policy, Low       
            """;

    private static final String annTasks =
            """
                Ann's Tasks
                Infrastructure, Security, High, In Progress
                Infrastructure, Password Policy,Medium, In Progress
                Research, Cloud solutions, Medium, In Progress
                Data Design, Encryption Policy, High
                Data Design, Project Table, Medium
                Data Access, Write Views,Low, In Progress      
            """;

    private static final String bobTasks =
            """
                Bob's Tasks
                Infrastructure, Security, High, In Progress
                Infrastructure, Password Policy, Medium
                Data Design,Encryption Policy,High
                Data Access,Write Views, Low, In Progress 
            """;

    private static final String carolTasks =
            """
                Carol's Tasks
                Infrastructure, Logging, High, In Progress
                Infrastructure, DB Access, Medium
                Infrastructure, Password Policy, Medium
                Data Design, Task Table, High
                Data Access, Write Views, Low     
            """;

    public  Set<Task> getData(){
        Set<Task> data = new HashSet<>();
        addTask("all", data);
        addTask("ann", data);
        addTask("bob", data);
        addTask("carol", data);
        return data;
    }

    private String getFileToBeProceeded(String value){
        if(value.equals("all")){
            return allTasks;
        }
        else if(value.equals("ann")){
            return annTasks;
        }
        else if(value.equals("bob")){
            return bobTasks;
        }
        else if(value.equals("carol")){
            return carolTasks;
        }
        return null;

    }

    private void addTask(String value, Set<Task> tasks){
        String fileToBeProceeded = getFileToBeProceeded(value);
        String[] lines = fileToBeProceeded.split("\n");
        for(int i = 1; i < lines.length; i++){
            String[] taskElements =  lines[i].split(",");
            if(value.equals("all")){
                tasks.add(new Task(value, (taskElements[0] + " " + taskElements[1]).trim(), "NOT ASSIGNED", taskElements[2].toUpperCase()));
            }
            else{
                if(taskElements.length > 3){
                    tasks.add(new Task(value, (taskElements[0] + " " + taskElements[1]).trim(), taskElements[3].toUpperCase(), taskElements[2].toUpperCase()));
                }
                else{
                    tasks.add(new Task(value, (taskElements[0] + " " + taskElements[1]).trim(), "ASSIGNED", taskElements[2].toUpperCase()));
                }
            }

        }
    }
}
