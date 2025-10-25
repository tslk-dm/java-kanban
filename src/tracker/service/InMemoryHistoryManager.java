package tracker.service;

import tracker.model.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final HashMap<Integer, Node> hashMap = new HashMap<>();
    private int size;
    private Node head;
    private Node tail;

    private static class Node {
        public Node next;
        public Node prev;
        public Task data;

        public Node(Task data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private void linkLast(Task task) {
        Node node = new Node(task);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
            node.prev = tail;
        }
        tail = node;

        size++;
    }

    private ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<>(size);
        Node current = head;
        while (current != null) {
            tasks.add(current.data);
            current = current.next;
        }

        return tasks;
    }

    private void removeNode(Node node) {
        if (node == null) return;

        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
        size--;
    }

    // Добавлении задачи в историю
    @Override
    public void add(Task task) {
        if (hashMap.containsKey(task.getId())) {
            removeNode(hashMap.get(task.getId()));
            hashMap.remove(task.getId());
        }

        if (size >= 10) {
            removeNode(head);
        }

        linkLast(task);
        hashMap.put(task.getId(), tail);
    }

    // Получение последних 10 просмотренных задач
    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    // Удаление задачи из истории просмотра
    @Override
    public void remove(int id) {
        if (hashMap.containsKey(id)) {
            removeNode(hashMap.get(id));
            hashMap.remove(id);
            size--;
        }
    }
}
