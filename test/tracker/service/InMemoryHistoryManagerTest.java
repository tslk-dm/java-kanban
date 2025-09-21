package tracker.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tracker.model.Epic;
import tracker.model.Status;
import tracker.model.Subtask;
import tracker.model.Task;
import java.util.List;

public class InMemoryHistoryManagerTest {
    public static HistoryManager historyManager;

    @BeforeEach
    public void beforeEach() {
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    public void TaskShouldBeSaved() {
        Task task1 = new Task(1, "task1", "desc1", Status.NEW);
        Task task2 = new Task(2, "task2", "desс2", Status.IN_PROGRESS);

        historyManager.add(task1);
        historyManager.add(task2);

        List<Task> history =  historyManager.getHistory();

        Task actualTask1 = history.getFirst();

        Assertions.assertEquals(task1.getId(), actualTask1.getId(), "Поле id предыдущей task не сохранено");
        Assertions.assertEquals(task1.getName(), actualTask1.getName(), "Поле name предыдущей task не сохранено");
        Assertions.assertEquals(task1.getDescription(), actualTask1.getDescription(), "Поле description предыдущей task не сохранено");
        Assertions.assertEquals(task1.getStatus(), actualTask1.getStatus(), "Поле status предыдущей task не сохранено");
    }

    @Test
    public void EpicShouldBeSaved() {
        Epic epic1 = new Epic(1, "epic1", "desc1");
        Epic epic2 = new Epic(2, "epic2", "desc2");

        historyManager.add(epic1);
        historyManager.add(epic2);

        List<Task> history =  historyManager.getHistory();

        Epic actualTask1 = (Epic)history.getFirst();

        Assertions.assertEquals(epic1.getId(), actualTask1.getId(), "Поле id предыдущего epic не сохранено");
        Assertions.assertEquals(epic1.getName(), actualTask1.getName(), "Поле name предыдущего epic не сохранено");
        Assertions.assertEquals(epic1.getDescription(), actualTask1.getDescription(), "Поле description предыдущего epic не сохранено");
        Assertions.assertEquals(epic1.getStatus(), actualTask1.getStatus(), "Поле status предыдущего epic не сохранено");
        Assertions.assertEquals(epic1.getSubtasks(), actualTask1.getSubtasks(), "Поле subtasks предыдущего epic не сохранено");
    }

    @Test
    public void SubtaskShouldBeSaved() {
        Subtask subtask1 = new Subtask(3, "subtask1", "desc1", Status.NEW, 1);
        Subtask subtask2 = new Subtask(4, "subtask2", "desc2", Status.NEW, 2);

        historyManager.add(subtask1);
        historyManager.add(subtask2);

        List<Task> history =  historyManager.getHistory();

        Subtask actualTask1 = (Subtask)history.getFirst();

        Assertions.assertEquals(subtask1.getId(), actualTask1.getId(), "Поле id предыдущей subtask не сохранено");
        Assertions.assertEquals(subtask1.getName(), actualTask1.getName(), "Поле name предыдущей subtask не сохранено");
        Assertions.assertEquals(subtask1.getDescription(), actualTask1.getDescription(), "Поле description предыдущей subtask не сохранено");
        Assertions.assertEquals(subtask1.getStatus(), actualTask1.getStatus(), "Поле status предыдущей subtask не сохранено");
        Assertions.assertEquals(subtask1.getEpicId(), actualTask1.getEpicId(), "Поле epicId предыдущей subtask не сохранено");
    }

    @Test
    public void TasksShouldNotBeRepeated() {
        Task task1 = new Task(1, "task1", "desc1", Status.NEW);
        Epic epic1 = new Epic(2, "epic1", "desc1");
        Subtask subtask1 = new Subtask(3, "subtask1", "desc1", Status.NEW, 2);

        for (int i = 0; i < 2; i++){
            historyManager.add(task1);
            historyManager.add(epic1);
            historyManager.add(subtask1);
        }

        List<Task> history =  historyManager.getHistory();

        Assertions.assertEquals(3, history.size(), "Неверное количество задач при добавлении одинаковых задач");
        Assertions.assertEquals(task1.getId(), history.get(0).getId(), "Нарушен порядок при добавлении Task");
        Assertions.assertEquals(epic1.getId(), history.get(1).getId(), "Нарушен порядок при добавлении Epic");
        Assertions.assertEquals(subtask1.getId(), history.get(2).getId(), "Нарушен порядок при добавлении Subtask");
    }

    @Test
    public void TasksShouldNotBeMore10() {
        for (int i = 0; i < 11; i++){
            historyManager.add(new Task(i, "task", "desc", Status.NEW));
        }

        List<Task> history =  historyManager.getHistory();

        Assertions.assertEquals(10, history.size(), "Нарушено ограничение на количество хранимых задач");
    }

    @Test
    public void TasksShouldBeRemoved() {
        Task task1 = new Task(1, "task1", "desc1", Status.NEW);
        Task task2 = new Task(2, "task2", "desc2", Status.NEW);

        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.remove(1);

        List<Task> history =  historyManager.getHistory();

        Assertions.assertEquals(1, history.size(), "Неверное количество задач при удалении");
        Assertions.assertEquals(task2.getId(), history.getFirst().getId(), "Удалена не та задача");
    }
}
