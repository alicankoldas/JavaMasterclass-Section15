package dev.lpa;

public class Task implements Comparable{

    private String assignee;
    private String description;

    private String status;

    private String priority;

    public Task(String assignee, String description, String status, String priority) {
        this.assignee = assignee;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(status.equals("ASSIGNED") ||
                status.equals("IN PROGRESS") ||  status.equals("NOT ASSIGNED")){
            this.status = status;
        }
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        if(priority.equals("HIGH") ||
                priority.equals("LOW") ||  priority.equals("MEDIUM")){
            this.priority = priority;
        }
    }

    @Override
    public int compareTo(Object o) {
        if(o.getClass() == getClass()){
            Task task = (Task)o;
            int result = task.getAssignee().
                    compareTo(this.getAssignee());
            if(result == 0){
                return task.getDescription().
                        compareTo(this.getDescription());
            }
            return result;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Task{" +
                "assignee='" + assignee + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", priority='" + priority + '\'' +
                '}' + '\n';
    }
}
