public class ArrayDeque<T> {
    private static final float r = 0.25F;
    private static final int initArraySize = 8;
    private int arraySize;
    private int qSize;
    private int head;
    private int tail;
    private T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[initArraySize];
        head = initArraySize / 2;
        tail = head - 1;
        qSize = 0;
        arraySize = initArraySize;
    }

    private void resize(boolean up) {
        T[] newItems;
        int newHead;
        if (up) {
            newItems = (T[]) new Object[arraySize * 2];
            newHead = arraySize - qSize / 2 - 1;
            System.arraycopy(items, head, newItems, newHead, qSize);
            items = newItems;
            arraySize *= 2;
        }
        else {
            newItems = (T[]) new Object[arraySize / 2];
            newHead = arraySize / 4 - qSize / 2 - 1;
            System.arraycopy(items, head, newItems, newHead, qSize);
            items = newItems;
            arraySize /= 2;
        }
        head = newHead;
        tail = head + qSize - 1;
    }
    public void addFirst(T item) {
        if (head == 0) {
            resize(true);
        }
        items[head-1] = item;
        head -= 1;
        qSize += 1;
    }
    public void addLast(T item) {
        if (tail == arraySize-1) {
            resize(true);
        }
        items[tail+1] = item;
        tail += 1;
        qSize += 1;
    }
    public boolean isEmpty() {
        if (qSize == 0) return true;
        return false;
    }
    public int size() {
        return qSize;
    }
    public void printDeque() {
        for (int i=0; i<qSize; i++){
            if (i != 0) {
                System.out.print(" ");
            }
            System.out.print(items[i]);
        }
    }
    public T removeFirst() {
        if (qSize == 0) return null;
        T item = items[head];
        head += 1;
        qSize -= 1;
        if ((float) qSize < arraySize * r) resize(false);
        return item;
    }
    public T removeLast() {
        if (qSize == 0) return null;
        T item = items[tail];
        tail -= 1;
        qSize -= 1;
        if ((float) qSize < arraySize * r) resize(false);
        return item;
    }
    public T get(int index) {
        if (index < 0 | index >= qSize) return null;
        return items[head + index];
    }
}