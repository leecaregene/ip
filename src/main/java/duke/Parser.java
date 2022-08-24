package duke;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses a given input and performs various actions to the task list and the UI,
     *
     * @param input    Full command entered by the user.
     * @param taskList Task list to perform operations on.
     * @param ui       UI for displaying output.
     * @return True when a termination is requested, false otherwise.
     * @throws DukeException If the input contains errors.
     */
    public static boolean parse(String input, TaskList taskList, Ui ui) throws DukeException {
        String[] command = input.split(" ", 2);
        if (command[0].equalsIgnoreCase("bye")) {
            ui.showBye();
            return true;
        } else if (command[0].equalsIgnoreCase("list")) {
            ui.showTasks(taskList);
        } else if (command[0].equalsIgnoreCase("mark")) {
            int index = Integer.parseInt(command[1]) - 1;
            taskList.mark(index);
            ui.showMarked(taskList.getTask(index));
        } else if (command[0].equalsIgnoreCase("unmark")) {
            int index = Integer.parseInt(command[1]) - 1;
            taskList.unmark(index);
            ui.showUnmarked(taskList.getTask(index));
        } else if (command[0].equalsIgnoreCase("todo")) {
            if (command.length < 2) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            Task newTask = new Todo(command[1]);
            taskList.add(newTask);
            ui.showAdded(newTask, taskList.getSize());
        } else if (command[0].equalsIgnoreCase("deadline")) {
            String[] arguments = command[1].split(" /by ", 2);
            Task newTask = new Deadline(arguments[0], arguments[1]);
            taskList.add(newTask);
            ui.showAdded(newTask, taskList.getSize());
        } else if (command[0].equalsIgnoreCase("event")) {
            String[] arguments = command[1].split(" /at ", 2);
            Task newTask = new Event(arguments[0], arguments[1]);
            taskList.add(newTask);
            ui.showAdded(newTask, taskList.getSize());
        } else if (command[0].equalsIgnoreCase("delete")) {
            int index = Integer.parseInt(command[1]) - 1;
            Task task = taskList.getTask(index);
            taskList.remove(index);
            ui.showRemoved(task, taskList.getSize());
        } else if (command[0].equalsIgnoreCase("find")) {
            ui.showResults(taskList.search(command[1]));
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return false;
    }
}
