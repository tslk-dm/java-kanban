package tracker.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SubtaskTest {
    @Test
    public void SubtasksWithEqualsIdShouldBeEquals () {
        Subtask subtask1 = new Subtask(1, "subtask1", "desc1", Status.NEW, 3);
        Subtask subtask2 = new Subtask(1, "subtask2", "desc2", Status.NEW, 2);

        Assertions.assertEquals(subtask1, subtask2, "Экземпляры Subtask не равны друг другу, если их id равны");
    }
}