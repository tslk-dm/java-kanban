import tracker.model.Epic;
import tracker.model.Status;
import tracker.model.Subtask;
import tracker.model.Task;
import tracker.service.InMemoryTaskManager;

public class Main {

    public static void main(String[] args) {
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

        inMemoryTaskManager.createTask(new Task("task1", "", Status.NEW));
        inMemoryTaskManager.createTask(new Task("task2", "", Status.NEW));
        inMemoryTaskManager.createTask(new Task("task3", "", Status.NEW));
        inMemoryTaskManager.createTask(new Task("task4", "", Status.NEW));

        inMemoryTaskManager.createEpic(new Epic("epic1", ""));
        inMemoryTaskManager.createSubtask(new Subtask("subtask1_Epic1", "", Status.NEW, 5));
        inMemoryTaskManager.createSubtask(new Subtask("subtask2_Epic1", "", Status.NEW, 5));

        inMemoryTaskManager.createEpic(new Epic("epic2", ""));
        inMemoryTaskManager.createSubtask(new Subtask("subtask1_Epic2", "", Status.NEW, 8));
        inMemoryTaskManager.createSubtask(new Subtask("subtask2_Epic2", "", Status.NEW, 8));
        inMemoryTaskManager.createSubtask(new Subtask("subtask3_Epic2", "", Status.NEW, 8));

        inMemoryTaskManager.createEpic(new Epic("epic3", ""));

        System.out.println(inMemoryTaskManager.getTasks());
        System.out.println("----");
        System.out.println(inMemoryTaskManager.getEpics());
        System.out.println("----");
        System.out.println(inMemoryTaskManager.getSubtasks());
        System.out.println("        ");

        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getEpicById(8);
        inMemoryTaskManager.getTaskById(2);
        inMemoryTaskManager.getTaskById(3);
        inMemoryTaskManager.getTaskById(4);
        System.out.println(inMemoryTaskManager.getHistory());

        inMemoryTaskManager.getTaskById(2);
        inMemoryTaskManager.getEpicById(8);
        inMemoryTaskManager.getTaskById(3);

        System.out.println(inMemoryTaskManager.getHistory());

        inMemoryTaskManager.deleteTaskById(1);
        System.out.println(inMemoryTaskManager.getTasks());
        System.out.println("---");
        System.out.println(inMemoryTaskManager.getHistory());

        inMemoryTaskManager.deleteEpicById(8);

        System.out.println("---");
        System.out.println(inMemoryTaskManager.getEpics());
        System.out.println(inMemoryTaskManager.getSubtasks());
        System.out.println("---");
        System.out.println(inMemoryTaskManager.getHistory());


    }
}
