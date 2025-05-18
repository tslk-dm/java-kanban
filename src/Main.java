public class Main {

    public static void main(String[] args) {
        Manager taskManager = new Manager();

        taskManager.createTask(new Task("task1", "task1Desc", Status.NEW));
        taskManager.createTask(new Task("task2", "task2Desc", Status.NEW));

        taskManager.createEpic(new Epic("epic1", "epic1Desc"));
        taskManager.createSubtask(new Subtask("subtask1Epic1", "sub1Desc", Status.NEW, 3));
        taskManager.createSubtask(new Subtask("subtask2Epic1", "sub2Desc", Status.NEW, 3));

        taskManager.createEpic(new Epic("epic2", "epic2"));
        taskManager.createSubtask(new Subtask("subtask1Epic2", "subtask1Epic2", Status.NEW, 6));

        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());
        System.out.println("----");

        taskManager.updateTask(new Task(1,"newTask1", "newTask1Desc", Status.IN_PROGRESS));
        taskManager.updateTask(new Task(2, "newTask2", "newTask2Desc", Status.DONE));
        System.out.println(taskManager.getTasks());
        System.out.println("----");

        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());

        taskManager.updateSubtask(new Subtask(4, "newSubtask1Epic1", "newSub1Desc", Status.IN_PROGRESS, 3));
        taskManager.updateSubtask(new Subtask(5, "newSubtask2Epic1", "newSub2Desc", Status.DONE, 3));
        taskManager.updateSubtask(new Subtask(7, "newSubtask1Epic2", "newSub2Desc", Status.DONE, 6));

        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());
        System.out.println("----");

        taskManager.updateEpic(new Epic(3, "newEpic1", "newEpic1Desc"));

        System.out.println(taskManager.getEpics());
        System.out.println("----");

        taskManager.deleteTaskById(1);
        taskManager.deleteEpicById(3);

        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());
        System.out.println("----");

        taskManager.deleteSubtaskById(7);

        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());
    }
}
