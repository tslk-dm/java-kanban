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
    public void beforeEach(){
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    public void PreviousTaskShouldBeSavedInHistoryManager(){
        Task task1 = new Task("task1", "desc1", Status.NEW);
        Task task2 = new Task("task2", "desс2", Status.IN_PROGRESS);

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
    public void PreviousEpicShouldBeSavedInHistoryManager(){
        Epic epic1 = new Epic("epic1", "desc1");
        Epic epic2 = new Epic("epic2", "desc2");

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
    public void PreviousSubtaskShouldBeSavedInHistoryManager(){
        Subtask subtask1 = new Subtask("subtask1", "desc1", Status.NEW, 1);
        Subtask subtask2 = new Subtask("subtask2", "desc2", Status.NEW, 2);

        historyManager.add(subtask1);
        historyManager.add(subtask2);

        List<Task> history =  historyManager.getHistory();

        Subtask actualTask1 = (Subtask)history.getFirst();

        Assertions.assertEquals(actualTask1.getId(), actualTask1.getId(), "Поле id предыдущей subtask не сохранено");
        Assertions.assertEquals(actualTask1.getName(), actualTask1.getName(), "Поле name предыдущей subtask не сохранено");
        Assertions.assertEquals(actualTask1.getDescription(), actualTask1.getDescription(), "Поле description предыдущей subtask не сохранено");
        Assertions.assertEquals(actualTask1.getStatus(), actualTask1.getStatus(), "Поле status предыдущей subtask не сохранено");
        Assertions.assertEquals(actualTask1.getEpicId(), actualTask1.getEpicId(), "Поле epicId предыдущей subtask не сохранено");
    }
}
