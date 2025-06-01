package tracker.service;

import tracker.model.Task;

import java.util.List;

public interface HistoryManager {
    // Добавлении задачи в историю
    public void add(Task task);

    // Получение последних 10 просмотренных задач
    public List<Task> getHistory();
}
