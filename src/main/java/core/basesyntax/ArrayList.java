package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == arrayList.length) {
            grow(value, size);
        }
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == 0 && index == 0) {
            arrayList[size] = value;
            size++;
            return;
        }
        if ((index < 0 || index > size)) {
            throw new ArrayListIndexOutOfBoundsException("Index" + index + "does not exist");
        }
        grow(value, index);
        size++;
    }

    @Override
    public void addAll(List<T> inputList) {
        for (int i = 0; i < inputList.size(); i++) {
            add(inputList.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
        arrayList[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (isEqual(arrayList[i], element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("Element: " + element + " doesn't exist!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isEqual(T arrElement, T element) {
        return arrElement == element
                || (arrElement != null && element != null
                && arrElement.getClass().equals(element.getClass())
                && arrElement.equals(element));
    }

    private void grow(T value, int index) {
        T[] tempArray = (T[]) new Object[size + 1];
        if (size + 1 > arrayList.length) {
            tempArray = (T[]) new Object[size + (size >> 1)];
        }
        System.arraycopy(arrayList, 0, tempArray,0, index);
        tempArray[index] = value;
        System.arraycopy(arrayList, index, tempArray,index + 1, size - index);
        arrayList = tempArray;
    }

    private void checkIndex(int index) {
        if ((index < 0 || index >= size)) {
            throw new ArrayListIndexOutOfBoundsException("Index" + index + "does not exist");
        }
    }
}
