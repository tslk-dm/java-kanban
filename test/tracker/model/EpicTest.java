package tracker.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EpicTest {
    @Test
    public void EpicsShouldBeEquals() {
        Epic epic1 = new Epic(1, "epic1", "desc1");
        Epic epic2 = new Epic(1, "epic2", "desc2");

        Assertions.assertEquals(epic1, epic2, "Экземпляры Epic не равны друг другу, если их id равны");
    }
}