package tracker.service;

import tracker.model.Epic;
import tracker.model.Subtask;
import tracker.model.Task;

import java.util.ArrayList;

public interface TaskManager {
    // Получение списка всех задач
    ArrayList<Task> getTasks();

    // Удаление всех задач
    void deleteTasks();

    // Получение задачи по идентификатору
    Task getTaskById(int id);

    // Создание задачи
    Task createTask(Task task);

    // Обновление задачи
    Task updateTask(Task task);

    // Удаление задачи по идентификатору
    Task deleteTaskById(int id);

    // Получение списка всех эпиков
    ArrayList<Epic> getEpics();

    // Удаление всех эпиков
    void deleteEpics();

    // Получение эпика по идентификатору
    Epic getEpicById(int id);

    // Создание эпика
    Epic createEpic(Epic epic);

    // Обновление эпика
    Epic updateEpic(Epic epic);

    // Удаление эпика по идентификатору
    Epic deleteEpicById(int id);

    // Получение списка всех подзадач определённого эпика
    ArrayList<Subtask> getSubtasksByEpicId(int id);

    // Получение списка всех подзадач
    ArrayList<Subtask> getSubtasks();

    // Удаление всех подзадач
    void deleteSubtasks();

    // Получение подзадачи по идентификатору
    Subtask getSubtaskById(int id);

    // Создание подзадачи
    Subtask createSubtask(Subtask subtask);

    // Обновление подзадачи
    Subtask updateSubtask(Subtask subtask);

    // Удаление подзадачи по идентификатору
    Subtask deleteSubtaskById(int id);
}
