package tracker.model;

import java.util.List;

public interface ReadOnlyEpic extends ReadOnlyTask {
    //public List<? extends ReadOnlySubtask> getSubtasks();

    public List<ReadOnlySubtask> getSubtasks();

}
