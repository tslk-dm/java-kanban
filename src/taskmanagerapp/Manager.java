package taskmanagerapp;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    private int counterId = 0;

    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public int getNextId(){
        return ++counterId;
    }

    // Получение списка всех задач
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    // Удаление всех задач
    public void deleteTasks() {
        tasks.clear();
    }

    // Получение задачи по идентификатору
    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    // Создание задачи
    public Task createTask(Task task) {
        task.setId(getNextId());
        tasks.put(task.getId(), task);
        return task;
    }

    // Обновление задачи
    public Task updateTask(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    // Удаление задачи по идентификатору
    public Task deleteTaskById(int id) {
        return tasks.remove(id);
    }

    // Получение списка всех эпиков
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    // Удаление всех эпиков
    public void deleteEpics() {
        epics.clear();
        subtasks.clear();
    }

    // Получение эпика по идентификатору
    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    // Создание эпика
    public Epic createEpic(Epic epic) {
        epic.setId(getNextId());
        epics.put(epic.getId(), epic);

        return epic;
    }

    // Обновление эпика
    public Epic updateEpic(Epic epic) {
        Epic updatedEpic = epics.get(epic.getId());
        updatedEpic.setName(epic.getName());
        updatedEpic.setDescription(epic.getDescription());

        return epic;
    }

    // Удаление эпика по идентификатору
    public Epic deleteEpicById(int id) {
        Epic epic = epics.get(id);
        for (Subtask subtask : epic.getSubtasks()) {
            subtasks.remove(subtask.getId());
        }
        return epics.remove(id);
    }

    // Получение списка всех подзадач определённого эпика
    public ArrayList<Subtask> getSubtasksByEpicId(int id) {
        Epic epic = epics.get(id);
        return epic.getSubtasks();
    }

    // Получение списка всех подзадач
    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    // Удаление всех подзадач
    public void deleteSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.deleteSubtasks();
        }
    }

    // Получение подзадачи по идентификатору
    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    // Создание подзадачи
    public Subtask createSubtask(Subtask subtask) {
        subtask.setId(getNextId());
        subtasks.put(subtask.getId(), subtask);

        Epic epic = epics.get(subtask.getEpicId());
        epic.addSubtask(subtask);

        return subtask;
    }

    // Обновление подзадачи
    public Subtask updateSubtask(Subtask subtask) {
        Task updatedSubtask = subtasks.get(subtask.getId());
        updatedSubtask.setName(subtask.getName());
        updatedSubtask.setDescription(subtask.getDescription());
        updatedSubtask.setStatus(subtask.getStatus());

        Epic epic = epics.get(subtask.getEpicId());
        epic.updateStatus();

        return subtask;
    }

    // Удаление подзадачи по идентификатору
    public Subtask deleteSubtaskById(int id) {
        Subtask subtask = subtasks.get(id);
        Epic epic = epics.get(subtask.getEpicId());
        epic.deleteSubtaskById(id);

        return subtasks.remove(id);
    }
}