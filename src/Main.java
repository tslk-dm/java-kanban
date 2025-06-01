import tracker.model.Epic;
import tracker.model.Status;
import tracker.model.Subtask;
import tracker.model.Task;
import tracker.service.InMemoryTaskManager;

public class Main {

    public static void main(String[] args) {
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

        inMemoryTaskManager.createTask(new Task("task1", "task1Desc", Status.NEW));
        inMemoryTaskManager.createTask(new Task("task2", "task2Desc", Status.NEW));

        inMemoryTaskManager.createEpic(new Epic("epic1", "epic1Desc"));
        inMemoryTaskManager.createSubtask(new Subtask("subtask1Epic1", "sub1Desc", Status.NEW, 3));
        inMemoryTaskManager.createSubtask(new Subtask("subtask2Epic1", "sub2Desc", Status.NEW, 3));

        inMemoryTaskManager.createEpic(new Epic("epic2", "epic2"));
        inMemoryTaskManager.createSubtask(new Subtask("subtask1Epic2", "subtask1Epic2", Status.NEW, 6));

        System.out.println(inMemoryTaskManager.getTasks());
        System.out.println(inMemoryTaskManager.getEpics());
        System.out.println(inMemoryTaskManager.getSubtasks());
        System.out.println("----");

        inMemoryTaskManager.updateTask(new Task(1,"newTask1", "newTask1Desc", Status.IN_PROGRESS));
        inMemoryTaskManager.updateTask(new Task(2, "newTask2", "newTask2Desc", Status.DONE));
        System.out.println(inMemoryTaskManager.getTasks());
        System.out.println("----");

        System.out.println(inMemoryTaskManager.getEpics());
        System.out.println(inMemoryTaskManager.getSubtasks());

        inMemoryTaskManager.updateSubtask(new Subtask(4, "newSubtask1Epic1", "newSub1Desc", Status.IN_PROGRESS, 3));
        inMemoryTaskManager.updateSubtask(new Subtask(5, "newSubtask2Epic1", "newSub2Desc", Status.DONE, 3));
        inMemoryTaskManager.updateSubtask(new Subtask(7, "newSubtask1Epic2", "newSub2Desc", Status.DONE, 6));

        System.out.println(inMemoryTaskManager.getEpics());
        System.out.println(inMemoryTaskManager.getSubtasks());
        System.out.println("----");

        inMemoryTaskManager.updateEpic(new Epic(3, "newEpic1", "newEpic1Desc"));

        System.out.println(inMemoryTaskManager.getEpics());
        System.out.println("----");

        inMemoryTaskManager.deleteTaskById(1);
        inMemoryTaskManager.deleteEpicById(3);

        System.out.println(inMemoryTaskManager.getTasks());
        System.out.println(inMemoryTaskManager.getEpics());
        System.out.println(inMemoryTaskManager.getSubtasks());
        System.out.println("----");

        inMemoryTaskManager.deleteSubtaskById(7);

        System.out.println(inMemoryTaskManager.getEpics());
        System.out.println(inMemoryTaskManager.getSubtasks());

        System.out.println("-----------");

        inMemoryTaskManager.createTask(new Task("task1", "task1Desc", Status.NEW));
        inMemoryTaskManager.createTask(new Task("task2", "task2Desc", Status.NEW));

        inMemoryTaskManager.createEpic(new Epic("epic1", "epic1Desc"));
        inMemoryTaskManager.createSubtask(new Subtask("subtask1Epic1", "sub1Desc", Status.NEW, 10));
        inMemoryTaskManager.createSubtask(new Subtask("subtask2Epic1", "sub2Desc", Status.NEW, 10));

        inMemoryTaskManager.createEpic(new Epic("epic2", "epic2"));
        inMemoryTaskManager.createSubtask(new Subtask("subtask1Epic2", "subtask1Epic2", Status.NEW, 13));

        System.out.println(inMemoryTaskManager.getTasks());
        System.out.println(inMemoryTaskManager.getEpics());
        System.out.println(inMemoryTaskManager.getSubtasks());
        System.out.println("----");

        inMemoryTaskManager.getTaskById(8);
        inMemoryTaskManager.getEpicById(10);
        inMemoryTaskManager.getSubtaskById(11);
        System.out.println(inMemoryTaskManager.getHistory());
    }
}
