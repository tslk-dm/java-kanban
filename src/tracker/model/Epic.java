package tracker.model;

import tracker.enums.TaskType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Epic extends Task implements ReadOnlyEpic {
    private ArrayList<Subtask> subtasks;

    public Epic(String name, String description) {
        super(name, description, Status.NEW);
        this.subtasks = new ArrayList<>();
    }

    public Epic(int id, String name, String description) {
        super(id, name, description, Status.NEW);
        this.subtasks = new ArrayList<>();
    }

    public Epic(int id, String name, String description, Status status) {
        super(id, name, description, status);
        this.subtasks = new ArrayList<>();
    }

//    public List<? extends ReadOnlySubtask> getSubtasks() {
//        return Collections.unmodifiableList(subtasks);
//    }

    public List<ReadOnlySubtask> getSubtasks() {
        return Collections.unmodifiableList(subtasks);
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
        updateStatus();
    }

    public void deleteSubtasks() {
        subtasks.clear();
    }

    public void deleteSubtaskById(int id) {
        for (Subtask subtask : subtasks) {
            if (subtask.getId() == id) {
                subtasks.remove(subtask);
                break;
            }
        }

        updateStatus();
    }

    public void updateStatus() {
        if (subtasks.isEmpty()) {
            super.setStatus(Status.NEW);
            return;
        }

        Status firstSubtaskStatus = subtasks.getFirst().getStatus();
        for (Subtask subtask : subtasks) {
            if (firstSubtaskStatus == subtask.getStatus()) {
                super.setStatus(firstSubtaskStatus);
            } else {
                super.setStatus(Status.IN_PROGRESS);
            }
        }
    }

    @Override
    public String toString() {
        return "tracker.Epic{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", status=" + super.getStatus() +
                ", subtasks=" + subtasks +
                '}';
    }


    public static Epic fromCsv(String value) {
        String[] splitValue = value.split(",");
        int id = Integer.parseInt(splitValue[0]);
        String name = splitValue[2];
        Status status = Status.valueOf(splitValue[3]);
        String description = splitValue[4];
        return new Epic(id, name, description, status);
    }

    @Override
    public String toCsv() {
        return String.format(
                "%d,%s,%s,%s,%s,",
                super.getId(),
                TaskType.EPIC.name(),
                super.getName(),
                super.getStatus().name(),
                super.getDescription()
        );
    }
}