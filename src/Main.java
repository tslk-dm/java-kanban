import tracker.model.Epic;
import tracker.model.Status;
import tracker.model.Subtask;
import tracker.model.Task;
import tracker.service.FileBackedTaskManager;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        Path path = Paths.get("./test.csv");
        FileBackedTaskManager manager = FileBackedTaskManager.loadFromFile(path.toFile());

        manager.createTask(new Task("task1", "desc1", Status.NEW));
        manager.createEpic(new Epic("epic1", "descEpic"));
        manager.createSubtask(new Subtask("subtask1_Epic1", "decSubtask1", Status.NEW, 2));
    }
}
