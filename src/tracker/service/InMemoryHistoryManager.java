package tracker.service;

import tracker.model.Task;

import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> history = new LinkedList<>();

    // Добавлении задачи в историю
    @Override
    public void add(Task task) {
        if (history.size() >= 10) {
            history.removeFirst();
        }
        history.add(task);
    }

    // Получение последних 10 просмотренных задач
    @Override
    public List<Task> getHistory() {
        return new LinkedList<>(history);
    }
}
