package tracker.service;

import java.util.*;

import tracker.model.ReadOnlyEpic;
import tracker.model.ReadOnlySubtask;
import tracker.model.ReadOnlyTask;
import tracker.model.Epic;
import tracker.model.Subtask;
import tracker.model.Task;

public class InMemoryTaskManager implements TaskManager {
    private int counterId = 0;

    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private final Managers managers = new Managers();
    private final HistoryManager historyManager = managers.getDefaultHistory();

    private int getNextId() {
        return ++counterId;
    }

    @Override
    public List<ReadOnlyTask> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    // Удаление всех задач
    @Override
    public void deleteTasks() {
        for (Integer id : tasks.keySet()) {
            historyManager.remove(id);
        }
        tasks.clear();
    }

    // Получение задачи по идентификатору
    @Override
    public ReadOnlyTask getTaskById(int id) {
        Task task = tasks.get(id);
        historyManager.add(task);
        return task;
    }

    // Создание задачи
    @Override
    public ReadOnlyTask createTask(Task task) {
        task.setId(getNextId());
        tasks.put(task.getId(), task);
        return task;
    }

    // Обновление задачи
    @Override
    public ReadOnlyTask updateTask(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    // Удаление задачи по идентификатору
    @Override
    public ReadOnlyTask deleteTaskById(int id) {
        historyManager.remove(id);
        return tasks.remove(id);
    }

    // Получение списка всех эпиков
    @Override
    public List<ReadOnlyEpic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    // Удаление всех эпиков
    @Override
    public void deleteEpics() {
        for (Integer id : epics.keySet()) {
            historyManager.remove(id);
        }

        for (Integer id : subtasks.keySet()) {
            historyManager.remove(id);
        }

        epics.clear();
        subtasks.clear();
    }

    // Получение эпика по идентификатору
    @Override
    public ReadOnlyEpic getEpicById(int id) {
        Epic epic = epics.get(id);
        historyManager.add(epic);

        return epic;
    }

    // Создание эпика
    @Override
    public ReadOnlyEpic createEpic(Epic epic) {
        epic.setId(getNextId());
        epics.put(epic.getId(), epic);

        return epic;
    }

    // Обновление эпика
    @Override
    public ReadOnlyEpic updateEpic(Epic epic) {
        Epic updatedEpic = epics.get(epic.getId());
        updatedEpic.setName(epic.getName());
        updatedEpic.setDescription(epic.getDescription());

        return epic;
    }

    // Удаление эпика по идентификатору
    @Override
    public ReadOnlyEpic deleteEpicById(int id) {
        Epic epic = epics.get(id);
        for (ReadOnlySubtask subtask : epic.getSubtasks()) {
            historyManager.remove(subtask.getId());
            subtasks.remove(subtask.getId());
        }

        historyManager.remove(id);
        return epics.remove(id);
    }

    // Получение списка всех подзадач определённого эпика
    @Override
    public List<ReadOnlySubtask> getSubtasksByEpicId(int id) {
        Epic epic = epics.get(id);
        return new ArrayList<>(epic.getSubtasks());
    }

    // Получение списка всех подзадач
    @Override
    public List<ReadOnlySubtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    // Удаление всех подзадач
    @Override
    public void deleteSubtasks() {
        for (Integer id : subtasks.keySet()) {
            historyManager.remove(id);
        }
        subtasks.clear();

        for (Epic epic : epics.values()) {
            epic.deleteSubtasks();
        }
    }

    // Получение подзадачи по идентификатору
    @Override
    public ReadOnlySubtask getSubtaskById(int id) {
        Subtask subtask = subtasks.get(id);
        historyManager.add(subtask);

        return subtask;
    }

    // Создание подзадачи
    @Override
    public ReadOnlySubtask createSubtask(Subtask subtask) {
        subtask.setId(getNextId());
        subtasks.put(subtask.getId(), subtask);

        Epic epic = epics.get(subtask.getEpicId());
        epic.addSubtask(subtask);

        return subtask;
    }

    // Обновление подзадачи
    @Override
    public ReadOnlySubtask updateSubtask(Subtask subtask) {
        Task updatedSubtask = subtasks.get(subtask.getId());
        updatedSubtask.setName(subtask.getName());
        updatedSubtask.setDescription(subtask.getDescription());
        updatedSubtask.setStatus(subtask.getStatus());

        Epic epic = epics.get(subtask.getEpicId());
        epic.updateStatus();

        return subtask;
    }

    // Удаление подзадачи по идентификатору
    @Override
    public ReadOnlySubtask deleteSubtaskById(int id) {
        Subtask subtask = subtasks.get(id);
        Epic epic = epics.get(subtask.getEpicId());
        epic.deleteSubtaskById(id);
        historyManager.remove(id);

        return subtasks.remove(id);
    }

    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    public Map<Integer, Task> getAllTasks() {
        Map<Integer, Task> allTasks = new TreeMap<>();
        allTasks.putAll(tasks);
        allTasks.putAll(epics);
        allTasks.putAll(subtasks);
        return allTasks;
    }
}