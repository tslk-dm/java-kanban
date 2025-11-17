package tracker.model;

import tracker.enums.TaskType;

public class Subtask extends Task implements ReadOnlySubtask {
    private int epicId;

    public Subtask(String name, String description, Status status, int epicId) {
        super(name, description, status);
        this.epicId = epicId;
    }

    public Subtask(int id, String name, String description, Status status, int epicId) {
        super(id, name, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "tracker.Subtask{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", status=" + super.getStatus() +
                ", epicId=" + epicId +
                '}';
    }

    public static Subtask fromCsv(String value) {
        String[] splitValue = value.split(",");
        int id = Integer.parseInt(splitValue[0]);
        String name = splitValue[2];
        Status status = Status.valueOf(splitValue[3]);
        String description = splitValue[4];
        int epicId = Integer.parseInt(splitValue[5]);
        return new Subtask(id, name, description, status, epicId);
    }

    @Override
    public String toCsv() {
        return String.format(
                "%d,%s,%s,%s,%s,%d",
                super.getId(),
                TaskType.SUBTASK.name(),
                super.getName(),
                super.getStatus().name(),
                super.getDescription(),
                epicId
        );
    }
}
