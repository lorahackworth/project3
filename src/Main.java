import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*I can't figure out how to get Gson to be imported properly, I tried to look online
but it's been incredibly buggy for me. The code for writing files works, as I took it from
my exercise 14, but I can't figure out how to make it run on my own laptop.
Have a great summer, Killoran, thank you for all that you taught me. :)
*/
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


public class Main {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            ArrayList<Task> myTasks = new ArrayList<>();

            //new task collection
            System.out.println("Here's a list of my secret tasks...");
            SecretTasks secretTasks = new SecretTasks();
            secretTasks.addTask(new Task("Get Batman(my cat) food", "Get his food, pour it in the bowl", 4));
            secretTasks.addTask(new Task("Put out the fire in the kitchen","WHAT?!", 5));
            secretTasks.addTask(new Task("CALL THE FIRE DEPARTMENT!", "Our home is burnt now", 3));
            secretTasks.addTask(new Task("Get eggs", "Go to the grocery store, visit the egg aisle, take a half carton of eggs, go to the checkout, run past the cashier, run through security, get in the getaway vehicle, escape the law, return home and make an omelette", 1));
            displaySorted(secretTasks.getTasks());


            //starts the loop by allowing user to make a first choice
            System.out.println();
            System.out.println("Please choose an option: \n(1)Add a task. \n(2)Remove a task. \n(3)Update a task. \n(4)List all tasks. \n(5)Delete tasks from file. \n(0)Exit.");
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
                if (userChoice.equals("5"))
                    deserializeSimple(myTasks);
                System.out.println("Please choose an option: \n(1)Add a task. \n(2)Remove a task. \n(3)Update a task. \n(4)List all tasks. \n(5)Delete tasks from file. \n(0)Exit.");
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


    //these two are broken because Gson will not import correctly
    static void serializeSimple(ArrayList<Task> a){
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("data.json")){
            gson.toJson(a,writer);
        } catch (IOException e) {e.printStackTrace();}
    }

    static void deserializeSimple(ArrayList<Task> a) {
        try (FileReader reader = new FileReader("data.json")) {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(reader);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Task>>() {
            }.getType();
            ArrayList<Task> classList = new Gson().fromJson(jsonElement, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}