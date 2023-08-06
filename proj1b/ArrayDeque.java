public class ArrayDeque<T> implements Deque<T>{
    private static final float R = 0.25F;
    private static final int INIT_ARRAY_SIZE = 8;
    private static final int THRESHOLD = 16;
    private static final int F = 2;
    private int arraySize;
    private int qSize;
    private int head;
    private int tail;
    private T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[INIT_ARRAY_SIZE];
        head = INIT_ARRAY_SIZE / 2;
        tail = head - 1;
        qSize = 0;
        arraySize = INIT_ARRAY_SIZE;
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
        } else {
            newItems = (T[]) new Object[arraySize / 2];
            newHead = arraySize / F / 2 - qSize / 2 - 1;
            System.arraycopy(items, head, newItems, newHead, qSize);
            items = newItems;
            arraySize /= 2;
        }
        head = newHead;
        tail = head + qSize - 1;
    }

    @Override
    public void addFirst(T item) {
        if (head == 0) {
            resize(true);
        }
        items[head - 1] = item;
        head -= 1;
        qSize += 1;
    }

    @Override
    public void addLast(T item) {
        if (tail == arraySize - 1) {
            resize(true);
        }
        items[tail + 1] = item;
        tail += 1;
        qSize += 1;
    }

    @Override
    public boolean isEmpty() {
        if (qSize == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return qSize;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < qSize; i++) {
            if (i != 0) {
                System.out.print(" ");
            }
            System.out.print(items[i]);
        }
    }

    @Override
    public T removeFirst() {
        if (qSize == 0) {
            return null;
        }
        T item = items[head];
        head += 1;
        qSize -= 1;
        if (arraySize >= THRESHOLD & (float) qSize < arraySize * R) {
            resize(false);
        }
        return item;
    }

    @Override
    public T removeLast() {
        if (qSize == 0) {
            return null;
        }
        T item = items[tail];
        tail -= 1;
        qSize -= 1;
        if (arraySize >= THRESHOLD & (float) qSize < arraySize * R) {
            resize(false);
        }
        return item;
    }

    @Override
    public T get(int index) {
        if (index < 0 | index >= qSize) {
            return null;
        }
        return items[head + index];
    }
}
