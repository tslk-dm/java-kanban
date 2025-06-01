package tracker.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tracker.model.Epic;
import tracker.model.Status;
import tracker.model.Subtask;
import tracker.model.Task;

import java.util.ArrayList;

class InMemoryTaskManagerTest {
    private static InMemoryTaskManager taskManager;

    @BeforeEach
    public void BeforeEach(){
        taskManager = new InMemoryTaskManager();
    }

    @Test
    public void ShouldAddTaskInTaskManager() {
        Task expectedTask = new Task("task1", "desc1", Status.NEW);
        int expectedTaskCount = 1;

        taskManager.createTask(expectedTask);
        ArrayList<Task> tasks = taskManager.getTasks();

        int actualTaskCount = tasks.size();
        Assertions.assertEquals(expectedTaskCount, actualTaskCount,
                "В список Tasks добавлено неверное количество task");

        Task actualTask = tasks.getFirst();
        Assertions.assertEquals(expectedTask, actualTask, "В список Tasks добавлена неверная task");
    }

    @Test
    public void ShouldAddEpicInTaskManager() {
        Epic expectedEpic = new Epic("epic1", "desc1");
        int expectedEpicCount = 1;

        taskManager.createEpic(expectedEpic);
        ArrayList<Epic> epics = taskManager.getEpics();

        int actualEpicCount = epics.size();
        Assertions.assertEquals(expectedEpicCount, actualEpicCount,
                "В список Epics добавлено неверное количество epic");

        Task actualEpic = epics.getFirst();
        Assertions.assertEquals(expectedEpic, actualEpic, "В список Epic добавлена неверная epic");
    }

    @Test
    public void ShouldAddSubtaskInTaskManager() {
        Epic expectedEpic = new Epic("epic1", "desc1");
        Subtask expectedSubtask = new Subtask("epic1", "desc1", Status.NEW, 1);
        int expectedSubtaskCount = 1;

        taskManager.createEpic(expectedEpic);
        taskManager.createSubtask(expectedSubtask);
        ArrayList<Subtask> subtasks = taskManager.getSubtasks();

        int actualEpicCount = subtasks.size();
        Assertions.assertEquals(expectedSubtaskCount, actualEpicCount,
                "В список Subtasks добавлено неверное количество subtask");

        Subtask actualSubtask = subtasks.getFirst();
        Assertions.assertEquals(expectedSubtask, actualSubtask, "В список Subtasks добавлена неверная subtask");
    }

    @Test void ShouldReturnTaskById() {
        Task expectedTask = new Task("task1", "desc1", Status.NEW);

        taskManager.createTask(expectedTask);

        Task actualTask = taskManager.getTaskById(1);

        Assertions.assertEquals(expectedTask, actualTask, "Поиск task по id возвращает неправильную task");
    }

    @Test void ShouldReturnEpicById() {
        Epic expectedEpic = new Epic("epic1", "desc1");

        taskManager.createEpic(expectedEpic);

        Epic actualEpic = taskManager.getEpicById(1);

        Assertions.assertEquals(expectedEpic, actualEpic, "Поиск epic по id возвращает неправильную epic");
    }

    @Test void ShouldReturnSubtaskById() {
        Epic epic = new Epic("epic1", "desc1");
        Subtask expectedSubtask = new Subtask("subtask1", "desc1", Status.NEW, 1);

        taskManager.createEpic(epic);
        taskManager.createSubtask(expectedSubtask);

        Subtask actualSubtask = taskManager.getSubtaskById(2);

        Assertions.assertEquals(expectedSubtask, actualSubtask, "Поиск subtask по id возвращает неправильную subtask");
    }

    @Test
    public void ShouldBeDifferentTaskId(){
        Task task1 = new Task("task1", "desc1", Status.NEW);
        Task task2 = new Task("task2", "desc2", Status.DONE);

        taskManager.createTask(task1);
        taskManager.createTask(task2);

        Assertions.assertNotEquals(task1.getId(), task2.getId(), "InMemoryTaskManager добавляет task с одинаковым id");
    }

    @Test
    public void ShouldBeDifferentSubtaskId() {
        Epic epic1 = new Epic("epic1", "desc1");
        Subtask subtask1 = new Subtask("subtask1", "desc1", Status.NEW, 1);
        Subtask subtask2 = new Subtask("subtask2", "desc2", Status.DONE, 1);

        taskManager.createEpic(epic1);
        taskManager.createSubtask(subtask1);
        taskManager.createSubtask(subtask2);

        Assertions.assertNotEquals(subtask1.getId(), subtask2.getId(),
                "InMemoryTaskManager добавляет subtask с одинаковым id");
    }

    @Test
    public void ShouldBeDifferentEpicId(){
        Epic epic1 = new Epic("epic1", "desc1");
        Epic epic2 = new Epic("epic2", "desc2");

        taskManager.createEpic(epic1);
        taskManager.createEpic(epic2);

        Assertions.assertNotEquals(epic1.getId(), epic2.getId(), "InMemoryTaskManager добавляет epic с одинаковым id");
    }

    @Test
    public void ShouldReturnTaskWithIdEqualCalledId() {
        Task expectedTask = new Task("task1", "desc1", Status.NEW);

        taskManager.createTask(expectedTask);
        Task actualTask = taskManager.getTaskById(1);

        Assertions.assertEquals(expectedTask, actualTask, "Id возвращаемой task не соответствует запрошенному id");
    }

    @Test
    public void ShouldReturnEpicWithIdEqualCalledId() {
        Epic expectedEpic = new Epic("epic1", "desc1");

        taskManager.createEpic(expectedEpic);
        Epic actualEpic = taskManager.getEpicById(1);

        Assertions.assertEquals(expectedEpic, actualEpic, "Id возвращаемой epic не соответствует запрошенному id");
    }

    @Test
    public void ShouldReturnSubtaskWithIdEqualCalledId() {
        Epic epic1 = new Epic("epic1", "desc1");
        Subtask expectedSubtask = new Subtask("subtask1", "desc1", Status.NEW, 1);

        taskManager.createEpic(epic1);
        taskManager.createSubtask(expectedSubtask);
        Task actualSubtask = taskManager.getSubtaskById(2);

        Assertions.assertEquals(expectedSubtask, actualSubtask,
                "Id возвращаемой subtask не соответствует запрошенному id");
    }

    @Test
    public void TaskDoesNotChangeWhenAdded() {
        Task expectedTask = new Task("task1", "desc1", Status.NEW);

        taskManager.createTask(expectedTask);

        Task actualTask = taskManager.getTaskById(1);

        Assertions.assertEquals(expectedTask.getName(), actualTask.getName(),
                "При добавлении task изменяется поле name");
        Assertions.assertEquals(expectedTask.getDescription(), actualTask.getDescription(),
                "При добавлении task изменяется поле description");
        Assertions.assertEquals(expectedTask.getStatus(), actualTask.getStatus(),
                "При добавлении task изменяется поле Status");
    }

    @Test
    public void EpicDoesNotChangeWhenAdded() {
        Epic expectedEpic = new Epic("epic1", "desc1");

        taskManager.createEpic(expectedEpic);

        Epic actualEpic = taskManager.getEpicById(1);

        Assertions.assertEquals(expectedEpic.getName(), actualEpic.getName(),
                "При добавлении epic изменяется поле name");
        Assertions.assertEquals(expectedEpic.getDescription(), actualEpic.getDescription(),
                "При добавлении epic изменяется поле description");
        Assertions.assertEquals(expectedEpic.getSubtasks(), actualEpic.getSubtasks(),
                "При добавлении epic изменяется поле subtasks");
    }

    @Test
    public void SubtaskDoesNotChangeWhenAdded() {
        Epic epic = new Epic("epic1", "desc1");
        Subtask expectedSubtask = new Subtask("subtask1", "desc1", Status.NEW, 1);

        taskManager.createEpic(epic);
        taskManager.createSubtask(expectedSubtask);

        Subtask actualSubtask = taskManager.getSubtaskById(2);

        Assertions.assertEquals(expectedSubtask.getName(), actualSubtask.getName(),
                "При добавлении subtask изменяется поле name");
        Assertions.assertEquals(expectedSubtask.getDescription(), actualSubtask.getDescription(),
                "При добавлении subtask изменяется поле description");
        Assertions.assertEquals(expectedSubtask.getStatus(), actualSubtask.getStatus(),
                "При добавлении subtask изменяется поле status");
        Assertions.assertEquals(expectedSubtask.getEpicId(), actualSubtask.getEpicId(),
                "При добавлении subtask изменяется поле epicId");
    }
}