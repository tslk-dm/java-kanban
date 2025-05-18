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




//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class Manager {
//    private int counterId = 0;
//
//    HashMap<Integer, Task> tasks = new HashMap<>();
//    HashMap<Integer, Epic> epics = new HashMap<>();
//    HashMap<Integer, Subtask> subtasks = new HashMap<>();
//
//
///*
//    a. Получение списка всех задач.
//    b. Удаление всех задач.
//    c. Получение по идентификатору.
//    d. Создание. Сам объект должен передаваться в качестве параметра.
//    e. Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
//    f. Удаление по идентификатору.
//
//    a. Получение списка всех подзадач определённого эпика.
//
//    a. Менеджер сам не выбирает статус для задачи. Информация о нём приходит менеджеру вместе с информацией о самой задаче.
//       По этим данным в одних случаях он будет сохранять статус, в других будет рассчитывать.
//    b. Для эпиков:
//        если у эпика нет подзадач или все они имеют статус NEW, то статус должен быть NEW.
//        если все подзадачи имеют статус DONE, то и эпик считается завершённым — со статусом DONE.
//        во всех остальных случаях статус должен быть IN_PROGRESS.
//*/
//
//    public int getNextId(){
//        return ++counterId;
//    }
//
//    //------------------
//
//    // + Получение списка всех задач
//    public ArrayList<Task> getTasks() {
//        return new ArrayList<Task>(tasks.values());
//    }
//
//    // + Удаление всех задач
//    public void deleteTasks() {
//        tasks.clear();
//    }
//
//    // + Получение задачи по идентификатору
//    public Task getTaskById(int id) {
//        return tasks.get(id);
//    }
//
//    // + Создание задачи
//    public Task createTask(Task task) {
//        task.setId(getNextId());
//        tasks.put(task.getId(), task);
//        return task;
//    }
//
//    // + Обновление задачи
//    public Task updateTask(Task task) {
//        tasks.put(task.getId(), task);
//        return task;
//    }
//
//    // + Удаление задачи по идентификатору
//    public Task deleteTaskById(int id) {
//        return tasks.remove(id);
//    }
//
//
//    //-----------------
//
//
//    // + Получение списка всех эпиков
//    public ArrayList<Epic> getEpics() {
//        return new ArrayList<Epic>(epics.values());
//    }
//
//    // + Удаление всех эпиков
//    public void deleteEpics() {
//        epics.clear();
//        subtasks.clear();
//    }
//
//    // + Получение эпика по идентификатору
//    public Epic getEpicById(int id) {
//        return epics.get(id);
//    }
//
//    // + Создание эпика
//    public Epic createEpic(Epic epic) {
//        epic.setId(getNextId());
//        epics.put(epic.getId(), epic);
//        return epic;
//    }
//
//    // Обновление эпика
//    public Epic updateEpic(Epic epic) {
//        epics.put(epic.getId(), epic);
//        return epic;
//    }
//
//    // + Удаление эпика по идентификатору
//    public Epic deleteEpicById(int id) {
//        Epic epic = getEpicById(id);
//        for (Subtask subtask : epic.getSubtasks()) {
//            subtasks.remove(subtask.id);
//        }
//        return epics.remove(id);
//    }
//
//    // + Получение списка всех подзадач определённого эпика
//    public ArrayList<Subtask> getSubtasksByEpicId(int id) {
//        Epic epic = epics.get(id);
//        return epic.getSubtasks();
//    }
//
//
//    //------------------
//
//    // + Получение списка всех подзадач
//    public ArrayList<Subtask> getSubtasks() {
//        return new ArrayList<Subtask>(subtasks.values());
//    }
//
//    // + Удаление всех подзадач
//    public void deleteSubtasks() {
//        subtasks.clear();
//        for (Epic epic : epics.values()) {
//            epic.deleteSubtasks();
//        }
//    }
//
//    // + Получение подзадачи по идентификатору
//    public Subtask getSubtasksById(int id) {
//        return subtasks.get(id);
//    }
//
//    // + Создание подзадачи
//    public Subtask createSubtasks(Subtask subtask) {
//        subtask.setId(getNextId());
//        subtasks.put(subtask.getId(), subtask);
//
//        Epic epic = getEpicBySubtask(subtask); // а если null
//        epic.addSubtask(subtask);
//        return subtask;
//    }
//
//    // + Обновление подзадачи
//    public Subtask updateSubtasks(Subtask subtask) {
//        subtasks.put(subtask.getId(), subtask);
//
//        //Epic epic = getEpicBySubtask(subtask);
//        //epic.updateSubtask(subtask);
//        return subtask;
//    }
//
//    // + Удаление подзадачи по идентификатору
//    public Subtask deleteSubtasksById(int id) {
//        Epic epic = getEpicBySubtask(subtasks.get(id));
//        epic.deleteSubtaskById(id);
//
//        return subtasks.remove(id);
//    }
//
//    //------------------
//
//    private Epic getEpicBySubtask(Subtask subtask) {
//        int epicId = subtask.getEpicId();
//        return epics.get(epicId);
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
