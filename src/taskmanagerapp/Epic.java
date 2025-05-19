package taskmanagerapp;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Subtask> subtasks;

    public Epic(String name, String description) {
        super(name, description, Status.NEW);
        this.subtasks = new ArrayList<>();
    }

    public Epic(int id, String name, String description) {
        super(id, name, description, Status.NEW);
        this.subtasks = new ArrayList<>();
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
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
        return "taskmanagerapp.Epic{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", status=" + super.getStatus() +
                ", subtasks=" + subtasks +
                '}';
    }
}