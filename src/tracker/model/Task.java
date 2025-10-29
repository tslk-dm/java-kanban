package tracker.model;

import tracker.enums.TaskType;

public class Task implements ReadOnlyTask {
    private int id;
    private String name;
    private String description;
    private Status status;

    public Task(String name, String description, Status status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Task(int id, String name, String description, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "tracker.Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", taskStatus=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Task otherTask = (Task)o;
        return this.id == otherTask.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public static Task fromCsv(String value) {
        String[] splitValue = value.split(",");
        int id = Integer.parseInt(splitValue[0]);
        String name = splitValue[2];
        Status status = Status.valueOf(splitValue[3]);
        String description = splitValue[4];
        return new Task(id, name, description, status);
    }

    public String toCsv() {
        return String.format(
                "%d,%s,%s,%s,%s,",
                id,
                TaskType.TASK.name(),
                name,
                status.name(),
                description
        );
    }
}
