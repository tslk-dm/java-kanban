package tracker.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tracker.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class FileBackedTaskManagerTest {
    private File tempFile;

    private FileBackedTaskManager createManagerOnTempFile() throws IOException {
        tempFile = File.createTempFile("tasks", ".csv");
        return new FileBackedTaskManager(tempFile.toPath());
    }

    @AfterEach
    void cleanTempFile() throws IOException {
        if (tempFile != null) {
            Files.deleteIfExists(tempFile.toPath());
        }
    }

    @Test
    public void shouldLoadEmptyFile() throws IOException {
        FileBackedTaskManager manager = createManagerOnTempFile();

        FileBackedTaskManager loaded = FileBackedTaskManager.loadFromFile(tempFile);
        Assertions.assertTrue(loaded.getTasks().isEmpty(), "Список Tasks должен быть пустым");
        Assertions.assertTrue(loaded.getEpics().isEmpty(), "Список Epics должен быть пустым");
        Assertions.assertTrue(loaded.getSubtasks().isEmpty(), "Список Subtasks должен быть пустым");
    }

    @Test
    void shouldSaveEmptyFile() throws IOException {
        FileBackedTaskManager manager = createManagerOnTempFile();

        manager.save();

        List<String> lines = Files.readAllLines(tempFile.toPath(), StandardCharsets.UTF_8);

        Assertions.assertEquals(1, lines.size(), "Файл должен содержать заголовок");
        Assertions.assertEquals("id,type,name,status,description,epic", lines.getFirst(), "Неправильный заголовок CSV");
    }

    @Test
    void shouldSaveMultipleTasksToFile() throws IOException {
        FileBackedTaskManager manager = createManagerOnTempFile();

        manager.createTask(new Task("task1", "desc1", Status.NEW));
        manager.createEpic(new Epic("epic1", "descEpic"));
        manager.createSubtask(new Subtask("subtask1_Epic1", "decSubtask1", Status.NEW, 2));

        List<String> lines = Files.readAllLines(tempFile.toPath(), StandardCharsets.UTF_8);

        Assertions.assertEquals(4, lines.size(), "Неверное количество задач");
        Assertions.assertEquals("id,type,name,status,description,epic", lines.getFirst());

        Assertions.assertEquals("1,TASK,task1,NEW,desc1,", lines.get(1), "Task записана в файл неправильно");
        Assertions.assertEquals("2,EPIC,epic1,NEW,descEpic,", lines.get(2), "Epic записана в файл неправильно");
        Assertions.assertEquals("3,SUBTASK,subtask1_Epic1,NEW,decSubtask1,2", lines.get(3), "Subtask записана в файл неправильно");
    }

    @Test
    void shouldLoadMultipleTasksFromFile() throws IOException {
        FileBackedTaskManager manager = createManagerOnTempFile();
        Task task1 = new Task("task1", "desc1", Status.NEW);
        Epic epic1 = new Epic("epic1", "descEpic");
        Subtask subtask1 = new Subtask("subtask1_Epic1", "decSubtask1", Status.NEW, 2);

        manager.createTask(task1);
        manager.createEpic(epic1);
        manager.createSubtask(subtask1);

        FileBackedTaskManager loaded = FileBackedTaskManager.loadFromFile(tempFile);

        List<ReadOnlyTask> loadedTasks = loaded.getTasks();
        List<ReadOnlyEpic> loadedEpics = loaded.getEpics();
        List<ReadOnlySubtask> loadedSubtasks  = loaded.getSubtasks();

        Assertions.assertEquals(1, loadedTasks.size(), "Неверное количество Tasks");
        Assertions.assertEquals(1, loadedEpics.size(), "Неверное количество Epics");
        Assertions.assertEquals(1, loadedSubtasks.size(),  "Неверное количество Subtasks");

        Assertions.assertEquals(task1, loadedTasks.getFirst(), "Неверно загружен Task");
        Assertions.assertEquals(epic1, loadedEpics.getFirst(), "Неверно загружен Epic");
        Assertions.assertEquals(subtask1, loadedSubtasks.getFirst(), "Неверно загружен Subtask");
    }
}
