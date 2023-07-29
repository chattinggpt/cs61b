public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    public class Node {
        private T item;
        private Node prev;
        private Node next;

        public Node(T item, Node p, Node n) {
            this.item = item;
            prev = p;
            next = n;
        }

        public Node(T item) {
            this.item = item;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        Node first = new Node(item, sentinel, sentinel.next);
        sentinel.next = first;
        first.next.prev = first;
        size += 1;
    }

    public void addLast(T item) {
        Node last = new Node(item, sentinel.prev, sentinel);
        sentinel.prev = last;
        last.prev.next = last;
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel.next;
        boolean first = true;
        while (p != sentinel) {
            if (!first) {
                System.out.print(" ");
            }
            first = false;
            System.out.print(p.item);
            p = p.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node first = sentinel.next;
        sentinel.next = first.next;
        sentinel.next.prev = sentinel;
        first.prev = null;
        first.next = null;
        size -= 1;
        return first.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node last = sentinel.prev;
        sentinel.prev = last.prev;
        sentinel.prev.next = sentinel;
        last.prev = null;
        last.next = null;
        size -= 1;
        return last.item;
    }

    public T get(int index) {
        int i = 0;
        Node p = sentinel.next;
        while (i < index & p != sentinel) {
            i += 1;
            p = p.next;
        }
        if (p == sentinel) {
            return null;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        return getRecursiveHelp(sentinel.next, index);
    }

    private T getRecursiveHelp(Node head, int index) {
        if (head == sentinel) {
            return null;
        }
        if (index == 0) {
            return head.item;
        }
        return getRecursiveHelp(head.next, index - 1);
    }
}
