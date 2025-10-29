package tracker.service;

import tracker.model.ReadOnlyEpic;
import tracker.model.ReadOnlySubtask;
import tracker.model.ReadOnlyTask;
import tracker.model.Epic;
import tracker.model.Subtask;
import tracker.model.Task;

import java.util.List;

public interface TaskManager {
    List<ReadOnlyTask> getTasks();

    void deleteTasks();

    ReadOnlyTask createTask(Task task);

    ReadOnlyTask updateTask(Task task);

    ReadOnlyTask getTaskById(int id);

    ReadOnlyTask deleteTaskById(int id);


    List<ReadOnlyEpic> getEpics();

    void deleteEpics();

    ReadOnlyEpic createEpic(Epic epic);

    ReadOnlyEpic updateEpic(Epic epic);

    ReadOnlyEpic getEpicById(int id);

    ReadOnlyEpic deleteEpicById(int id);

    List<ReadOnlySubtask> getSubtasks();

    void deleteSubtasks();


    List<ReadOnlySubtask> getSubtasksByEpicId(int id);

    ReadOnlySubtask createSubtask(Subtask subtask);

    ReadOnlySubtask updateSubtask(Subtask subtask);

    ReadOnlySubtask getSubtaskById(int id);

    ReadOnlySubtask deleteSubtaskById(int id);
}
