package tracker.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskTest {
    @Test
    public void TasksWithEqualsIdShouldBeEquals () {
        Task task1 = new Task(1, "task1", "desc1", Status.NEW);
        Task task2 = new Task(1, "task2", "des2", Status.DONE);

        Assertions.assertEquals(task1, task2, "Экземпляры Task не равны друг другу, если их id равны");
    }
}