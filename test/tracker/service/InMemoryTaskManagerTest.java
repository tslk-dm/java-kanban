package tracker.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tracker.model.ReadOnlyEpic;
import tracker.model.ReadOnlySubtask;
import tracker.model.ReadOnlyTask;
import tracker.model.Epic;
import tracker.model.Status;
import tracker.model.Subtask;
import tracker.model.Task;

import java.util.List;

class InMemoryTaskManagerTest {
    private static InMemoryTaskManager taskManager;

    @BeforeEach
    public void beforeEach() {
        taskManager = new InMemoryTaskManager();
    }

    @Test
    public void shouldAddTaskInTaskManager() {
        Task expectedTask = new Task("task1", "desc1", Status.NEW);
        int expectedTaskCount = 1;

        taskManager.createTask(expectedTask);
        List<ReadOnlyTask> tasks = taskManager.getTasks();

        int actualTaskCount = tasks.size();
        Assertions.assertEquals(expectedTaskCount, actualTaskCount,
                "В список Tasks добавлено неверное количество task");

        ReadOnlyTask actualTask = tasks.getFirst();
        Assertions.assertEquals(expectedTask, actualTask, "В список Tasks добавлена неверная task");
    }

    @Test
    public void shouldAddEpicInTaskManager() {
        Epic expectedEpic = new Epic("epic1", "desc1");
        int expectedEpicCount = 1;

        taskManager.createEpic(expectedEpic);
        List<ReadOnlyEpic> epics = taskManager.getEpics();

        int actualEpicCount = epics.size();
        Assertions.assertEquals(expectedEpicCount, actualEpicCount,
                "В список Epics добавлено неверное количество epic");

        ReadOnlyEpic actualEpic = epics.getFirst();
        Assertions.assertEquals(expectedEpic, actualEpic, "В список Epic добавлена неверная epic");
    }

    @Test
    public void shouldAddSubtaskInTaskManager() {
        Epic expectedEpic = new Epic("epic1", "desc1");
        Subtask expectedSubtask = new Subtask("epic1", "desc1", Status.NEW, 1);
        int expectedSubtaskCount = 1;

        taskManager.createEpic(expectedEpic);
        taskManager.createSubtask(expectedSubtask);
        List<ReadOnlySubtask> subtasks = taskManager.getSubtasks();

        int actualEpicCount = subtasks.size();
        Assertions.assertEquals(expectedSubtaskCount, actualEpicCount,
                "В список Subtasks добавлено неверное количество subtask");

        ReadOnlySubtask actualSubtask = subtasks.getFirst();
        Assertions.assertEquals(expectedSubtask, actualSubtask, "В список Subtasks добавлена неверная subtask");
    }

    @Test void shouldReturnTaskById() {
        Task expectedTask = new Task("task1", "desc1", Status.NEW);

        taskManager.createTask(expectedTask);

        ReadOnlyTask actualTask = taskManager.getTaskById(1);

        Assertions.assertEquals(expectedTask, actualTask, "Поиск task по id возвращает неправильную task");
    }

    @Test void shouldReturnEpicById() {
        Epic expectedEpic = new Epic("epic1", "desc1");

        taskManager.createEpic(expectedEpic);

        ReadOnlyEpic actualEpic = taskManager.getEpicById(1);

        Assertions.assertEquals(expectedEpic, actualEpic, "Поиск epic по id возвращает неправильную epic");
    }

    @Test void shouldReturnSubtaskById() {
        Epic epic = new Epic("epic1", "desc1");
        Subtask expectedSubtask = new Subtask("subtask1", "desc1", Status.NEW, 1);

        taskManager.createEpic(epic);
        taskManager.createSubtask(expectedSubtask);

        ReadOnlySubtask actualSubtask = taskManager.getSubtaskById(2);

        Assertions.assertEquals(expectedSubtask, actualSubtask, "Поиск subtask по id возвращает неправильную subtask");
    }

    @Test
    public void shouldBeDifferentTaskId(){
        Task task1 = new Task("task1", "desc1", Status.NEW);
        Task task2 = new Task("task2", "desc2", Status.DONE);

        taskManager.createTask(task1);
        taskManager.createTask(task2);

        Assertions.assertNotEquals(task1.getId(), task2.getId(), "InMemoryTaskManager добавляет task с одинаковым id");
    }

    @Test
    public void shouldBeDifferentSubtaskId() {
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
    public void shouldBeDifferentEpicId(){
        Epic epic1 = new Epic("epic1", "desc1");
        Epic epic2 = new Epic("epic2", "desc2");

        taskManager.createEpic(epic1);
        taskManager.createEpic(epic2);

        Assertions.assertNotEquals(epic1.getId(), epic2.getId(), "InMemoryTaskManager добавляет epic с одинаковым id");
    }

    @Test
    public void shouldReturnTaskWithIdEqualCalledId() {
        Task expectedTask = new Task("task1", "desc1", Status.NEW);

        taskManager.createTask(expectedTask);
        ReadOnlyTask actualTask = taskManager.getTaskById(1);

        Assertions.assertEquals(expectedTask, actualTask, "Id возвращаемой task не соответствует запрошенному id");
    }

    @Test
    public void shouldReturnEpicWithIdEqualCalledId() {
        Epic expectedEpic = new Epic("epic1", "desc1");

        taskManager.createEpic(expectedEpic);
        ReadOnlyEpic actualEpic = taskManager.getEpicById(1);

        Assertions.assertEquals(expectedEpic, actualEpic, "Id возвращаемой epic не соответствует запрошенному id");
    }

    @Test
    public void shouldReturnSubtaskWithIdEqualCalledId() {
        Epic epic1 = new Epic("epic1", "desc1");
        Subtask expectedSubtask = new Subtask("subtask1", "desc1", Status.NEW, 1);

        taskManager.createEpic(epic1);
        taskManager.createSubtask(expectedSubtask);
        ReadOnlySubtask actualSubtask = taskManager.getSubtaskById(2);

        Assertions.assertEquals(expectedSubtask, actualSubtask,
                "Id возвращаемой subtask не соответствует запрошенному id");
    }

    @Test
    public void saskDoesNotChangeWhenAdded() {
        Task expectedTask = new Task("task1", "desc1", Status.NEW);

        taskManager.createTask(expectedTask);

        ReadOnlyTask actualTask = taskManager.getTaskById(1);

        Assertions.assertEquals(expectedTask.getName(), actualTask.getName(),
                "При добавлении task изменяется поле name");
        Assertions.assertEquals(expectedTask.getDescription(), actualTask.getDescription(),
                "При добавлении task изменяется поле description");
        Assertions.assertEquals(expectedTask.getStatus(), actualTask.getStatus(),
                "При добавлении task изменяется поле Status");
    }

    @Test
    public void spicDoesNotChangeWhenAdded() {
        Epic expectedEpic = new Epic("epic1", "desc1");

        taskManager.createEpic(expectedEpic);

        ReadOnlyEpic actualEpic = taskManager.getEpicById(1);

        Assertions.assertEquals(expectedEpic.getName(), actualEpic.getName(),
                "При добавлении epic изменяется поле name");
        Assertions.assertEquals(expectedEpic.getDescription(), actualEpic.getDescription(),
                "При добавлении epic изменяется поле description");
        Assertions.assertEquals(expectedEpic.getSubtasks(), actualEpic.getSubtasks(),
                "При добавлении epic изменяется поле subtasks");
    }

    @Test
    public void subtaskDoesNotChangeWhenAdded() {
        Epic epic = new Epic("epic1", "desc1");
        Subtask expectedSubtask = new Subtask("subtask1", "desc1", Status.NEW, 1);

        taskManager.createEpic(epic);
        taskManager.createSubtask(expectedSubtask);

        ReadOnlySubtask actualSubtask = taskManager.getSubtaskById(2);

        Assertions.assertEquals(expectedSubtask.getName(), actualSubtask.getName(),
                "При добавлении subtask изменяется поле name");
        Assertions.assertEquals(expectedSubtask.getDescription(), actualSubtask.getDescription(),
                "При добавлении subtask изменяется поле description");
        Assertions.assertEquals(expectedSubtask.getStatus(), actualSubtask.getStatus(),
                "При добавлении subtask изменяется поле status");
        Assertions.assertEquals(expectedSubtask.getEpicId(), actualSubtask.getEpicId(),
                "При добавлении subtask изменяется поле epicId");
    }

    @Test
    public void subtaskShouldBeRemovedFromEpic() {
        Epic epic = new Epic(1, "epic1", "desc1");
        Subtask subtask1 = new Subtask("subtask1", "desc1", Status.NEW, 1);
        Subtask subtask2 = new Subtask("subtask2", "desc2", Status.NEW, 1);

        taskManager.createEpic(epic);
        taskManager.createSubtask(subtask1);
        taskManager.createSubtask(subtask2);
        taskManager.deleteSubtaskById(2);

        Assertions.assertEquals(1, taskManager.getEpicById(1).getSubtasks().size(),
                "Подзадача не удаляется из Epic после удаления из Subtasks");
        Assertions.assertEquals(3, taskManager.getEpicById(1).getSubtasks().getFirst().getId(),
                "Подзадача не удаляется из Epic после удаления из Subtasks");
    }
}