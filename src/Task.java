public class Task implements Comparable<Task> {
    private String name;
    private String description;
    private int priority;

    public static int numOfTasks = 0;

    public Task(String name, String description, int priority) {
        this.name = name;
        this.description = description;
        this.priority = priority;

        numOfTasks++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "\nTask: " + this.getName() +
                "\nDescription: " + this.getDescription() +
                "\nPriority: " + this.getPriority();
    }

    @Override
    public int compareTo(Task o) {
        int compareResult = Integer.valueOf(this.priority).compareTo(Integer.valueOf(o.priority));
        if(compareResult!=0) return -compareResult;
        return compareResult;
    }
}

