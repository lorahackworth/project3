import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            ArrayList<Task> myTasks = new ArrayList<>();
            //starts the loop by allowing user to make a first choice
            System.out.println();
            System.out.println("Please choose an option: \n(1)Add a task. \n(2)Remove a task. \n(3)Update a task. \n(4)List all tasks. \n(0)Exit.");
            String userChoice = input.nextLine();

            // starts loop to continue as long as user choice doesn't equal 0
            while (!(userChoice.equals("0"))) {
                //user choices, at the beginning of the loop so it shows up before prompting another response
                if (userChoice.equals("1"))
                    addTask(myTasks);
                if (userChoice.equals("2"))
                    removeTask(myTasks);
                if (userChoice.equals("3"))
                    updateTask(myTasks);
                if (userChoice.equals("4"))
                    viewTasks(myTasks);
                System.out.println("\nPlease choose an option: \n(1)Add a task. \n(2)Remove a task. \n(3)Update a task. \n(4)List all tasks. \n(0)Exit.");
                userChoice = input.nextLine();
            }
            //goodbye message :)
            if (userChoice.equals("0"))
                System.out.println("\n~ Goodbye! ~");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    static void addTask(ArrayList<Task> a) {
        //creates task name
        System.out.println("What's the task name?");
        String taskName = input.nextLine();

        //creates description of task
        System.out.println("What's the description of the task?");
        String taskDescription = input.nextLine();

        //creates priority of task
        System.out.println("What's the priority of the task?");
        int taskPriority = input.nextInt();
        input.nextLine();

        //creates new task
        Task newTask = new Task(taskName, taskDescription, taskPriority);

        a.add(newTask);
    }

    static void removeTask(ArrayList<Task> a) {
        System.out.println("\nWhat task would you like to remove? (List starts at 0.)");
        sortTask(a);
        for (int i = 0; i < a.size(); i++) {
            System.out.println("\nTask Number: " + i + a.get(i));
        }
        int taskToRemove = input.nextInt();
        input.nextLine();
        a.remove(taskToRemove);
    }

    static void updateTask(ArrayList<Task> a){
        System.out.println("\nWhat task would you like to update? (List starts at 0.)");
        for (int i = 0; i < a.size(); i++) {
            System.out.println("\nTask Number: " + i + a.get(i));
        }
        int updateTask = input.nextInt();
        //added below in order to check for input and not skip ahead
        input.nextLine();
        //creates task name
        System.out.println("What's the updated task name?");
        String taskName = input.nextLine();

        //retrieves user email
        System.out.println("What's the updated description of the task?");
        String taskDescription = input.nextLine();

        //retrieves user phone number
        System.out.println("What's the updated priority of the task?");
        int taskPriority = input.nextInt();
        input.nextLine();

        //creates new task
        Task newTask = new Task(taskName, taskDescription, taskPriority);
        a.set(updateTask, newTask);

        System.out.println("\nYour updated tasks...");
        displaySorted(a);
    }

    static void viewTasks(ArrayList<Task> a) {
        System.out.println("\nDo you want to view all tasks or tasks of a specific priority?" +
                "\n(0)All tasks\n(1)Tasks with a priority of 1\n(2)Tasks with a priority of 2\n(3)Tasks with a priority of 3\n(4)Tasks with a priority of 4\n(5)Tasks with a priority of 5");
        int priority = input.nextInt();
        input.nextLine();

        if(priority<=5 && priority >=0) {
            if(priority!=0)
                System.out.println("\nHere is a list of all your tasks with a priority of " + priority + ": ");
            if (priority == 0)
                System.out.println("\nHere is a list of all your tasks: ");
        } else {
            //data validator, makes sure user input is correct
            while(!(priority<=5 && priority>=0)) {
                System.out.println("\nIncorrect input. Please try again.");
                System.out.println("\nDo you want to view all tasks or tasks of a specific priority?" +
                        "\n(0)All tasks\n(1)Tasks with a priority of 1\n(2)Tasks with a priority of 2\n(3)Tasks with a priority of 3\n(4)Tasks with a priority of 4\n(5)Tasks with a priority of 5");
                priority = input.nextInt();
                input.nextLine();
            }
        }
        //displays tasks
        sortTask(a);
        for (int i = 0; i < a.size(); i++) {
            Task currentTask = a.get(i);
            int taskPriority = currentTask.getPriority();
            // if priority is 0 or if priority is equal to the task's priority, display either all tasks
            // or tasks of a specific priority
            if(priority == 0 || priority == taskPriority){
                System.out.println("\nTask Number: " + i + a.get(i));
            }
        }

    }

    //if you want tasks sorted without displaying them,
    // like in update task where task index is showcased
    static void sortTask(ArrayList<Task> a){
        Collections.sort(a);
    }

    //if you want tasks sorted & displayed
    static void displaySorted(ArrayList<Task>a){
        Collections.sort(a);
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
    }
}