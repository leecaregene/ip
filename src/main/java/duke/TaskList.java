package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility wrapper class that makes working with task lists easier.
 */
public class TaskList {
    private List<Task> tasks;
    private Storage storage;

    TaskList(Storage storage, Ui ui) {
        this.storage = storage;
        try {
            tasks = storage.loadTasks();
        } catch (Exception e) {
            ui.showError(e.getMessage());
            tasks = new ArrayList<>();
        }
    }

    /**
     * Marks the task located at the given index.
     *
     * @param index Index of the task in the list.
     * @throws DukeException If task list could not be saved.
     */
    public void mark(int index) throws DukeException {
        Task task = tasks.get(index);
        task.mark();
        storage.saveTasks(tasks);
    }

    /**
     * Unmarks the task located at the given index.
     *
     * @param index Index of the task in the list.
     * @throws DukeException If task list could not be saved.
     */
    public void unmark(int index) throws DukeException {
        Task task = tasks.get(index);
        task.unmark();
        storage.saveTasks(tasks);
    }

    /**
     * Adds the given task to the list.
     *
     * @param task Task to be added.
     * @throws DukeException If task list could not be saved.
     */
    public void add(Task task) throws DukeException {
        tasks.add(task);
        storage.saveTasks(tasks);
    }

    /**
     * Removes the task located at the given index.
     *
     * @param index Index of the task in the list.
     * @throws DukeException If task list could not be saved.
     */
    public void remove(int index) throws DukeException {
        tasks.remove(index);
        storage.saveTasks(tasks);
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            temp.append(i + 1);
            temp.append(".");
            temp.append(tasks.get(i));
            temp.append("\n");
        }
        return temp.toString();
    }
}