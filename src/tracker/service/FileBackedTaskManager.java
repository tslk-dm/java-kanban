package tracker.service;

import tracker.enums.TaskType;
import tracker.exceptions.ManagerSaveException;
import tracker.model.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileBackedTaskManager extends InMemoryTaskManager {
    private final Path path;

    public FileBackedTaskManager(Path path) {
        this.path = path;

        try {
            if (Files.notExists(path)) {
                if (path.getParent() != null) {
                    Files.createDirectories(path.getParent());
                }
                Files.createFile(path);
            }
        } catch (IOException exception) {
            throw new ManagerSaveException(exception);
        }
    }

    public static FileBackedTaskManager loadFromFile(File file) {
        FileBackedTaskManager manager = new FileBackedTaskManager(file.toPath());
        manager.loadFromFile();
        return manager;
    }

    private void loadFromFile() {
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.isEmpty())
                    continue;

                String[] lineObject = line.split(",");

                TaskType taskType = TaskType.valueOf(lineObject[1]);
                switch (taskType) {
                    case TASK -> createTask(Task.fromCsv(line));
                    case EPIC -> createEpic(Epic.fromCsv(line));
                    case SUBTASK -> createSubtask(Subtask.fromCsv(line));
                    default -> throw new IllegalStateException(String.format("Unexpected value: %s", taskType));
                }
            }
        } catch (IOException exception) {
            throw new ManagerSaveException(String.format("Не удалось загрузить данные из файла %s", path), exception);
        }
    }

    public void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write("id,type,name,status,description,epic");
            writer.newLine();

            for (Task task : getAllTasks().values()) {
                writer.write(task.toCsv());
                writer.newLine();
            }
        } catch (IOException exception) {
            throw new ManagerSaveException(String.format("Не удалось сохранить данные в файл %s", path), exception);

        }
    }

    @Override
    public void deleteTasks() {
        super.deleteTasks();
        save();
    }

    @Override
    public ReadOnlyTask createTask(Task task) {
        ReadOnlyTask readOnlyTask = super.createTask(task);
        save();
        return readOnlyTask;
    }

    @Override
    public ReadOnlyTask updateTask(Task task) {
        ReadOnlyTask readOnlyTask = super.updateTask(task);
        save();
        return readOnlyTask;
    }

    @Override
    public ReadOnlyTask deleteTaskById(int id) {
        ReadOnlyTask readOnlyTask = super.deleteTaskById(id);
        save();
        return readOnlyTask;
    }

    @Override
    public void deleteEpics() {
        super.deleteEpics();
        save();
    }

    @Override
    public ReadOnlyEpic createEpic(Epic epic) {
        ReadOnlyEpic readOnlyEpic = super.createEpic(epic);
        save();
        return readOnlyEpic;
    }

    @Override
    public ReadOnlyEpic updateEpic(Epic epic) {
        ReadOnlyEpic readOnlyEpic = super.updateEpic(epic);
        save();
        return readOnlyEpic;
    }

    @Override
    public ReadOnlyEpic deleteEpicById(int id) {
        ReadOnlyEpic readOnlyEpic = super.deleteEpicById(id);
        save();
        return readOnlyEpic;
    }

    @Override
    public void deleteSubtasks() {
        super.deleteSubtasks();
        save();
    }

    @Override
    public ReadOnlySubtask createSubtask(Subtask subtask) {
        ReadOnlySubtask readOnlySubtask = super.createSubtask(subtask);
        save();
        return readOnlySubtask;
    }

    @Override
    public ReadOnlySubtask updateSubtask(Subtask subtask) {
        ReadOnlySubtask readOnlySubtask = super.updateSubtask(subtask);
        save();
        return readOnlySubtask;
    }

    @Override
    public ReadOnlySubtask deleteSubtaskById(int id) {
        ReadOnlySubtask readOnlySubtask = super.deleteSubtaskById(id);
        save();
        return readOnlySubtask;
    }
}
