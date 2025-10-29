package tracker.model;

public interface ReadOnlyTask {
    public int getId();

    public String getName();

    public String getDescription();

    public Status getStatus();
}
