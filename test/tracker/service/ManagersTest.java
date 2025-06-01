package tracker.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {
    @Test
    public void ManagersShouldReturnNotNull(){
        Managers manager = new Managers();
        TaskManager taskManager = manager.getDefault();
        Assertions.assertNotNull(taskManager, "Утилитарный класс вернул null вместо taskManager");

        HistoryManager historyManager = manager.getDefaultHistory();
        Assertions.assertNotNull(historyManager, "Утилитарный класс вернул null, вместо historyManager");
    }
}