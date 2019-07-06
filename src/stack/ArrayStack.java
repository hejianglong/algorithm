package stack;

public class ArrayStack {

    int size = 5;

    int[] content = new int[size];

    int position = -1;

    public void push(int data) {
        ++position;
        if (position == size - 1) {
            int oldSize = size;
            size = size * 2;
            int[] tmpContent = new int[size];
            for (int i = 0; i < oldSize; i++) {
                tmpContent[i] = content[i];
            }
            content = tmpContent;
        }
        content[position] = data;
    }

    public int pop() {
        if (position == -1) {
            return 0;
        }
        int val = content[position];
        content[position] = 0;
        position--;
        return val;
    }
}
