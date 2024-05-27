package ru.andreycherenkov;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Собственная реализация коллекции ArrayList, не является потокобезопасной.
 * Коллекция содержит следующие публичные методы для работы с коллекцией: add, get, size, indexOf, remove, set, clear
 * quickSort, contains
 *
 * @param <T> тип элементов, хранимых в коллекции
 *
 * @author Андрей Черенков
 */
public class MyArrayList<T> implements MyList<T> {

    /**
     * Базовый размер контейнера.
     * Здесь и далее под размерностью контейнера понимается выделяемое количетсво ячеек в памяти компьютера.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Нулевой размер контейнера, необходим для проверки в конструкторе с параметром размерности списка.
     */
    private static final int ZERO_CAPACITY = 0;

    /**
     * Контейнер (массив), в котором собственном хранятся элементы.
     * При инициализации создаётся контейнер на 10 элементов, если не используется конструктор с параметрами.
     */
    private Object[] container;
    /**
     * Размерность ArrayList (число элементов, которые он содержит)
     */
    private int size;

    /**
     * Конструктор без параметров, создающий список с базовой размерностью (DEFAULT_CAPACITY)
     */
    public MyArrayList() {
        this.container = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Конструктор, позволяющий указать базовую размерность коллекции.
     * @param initSize размерность списка, необходимая для инициализации контейнера
     * @throws IllegalArgumentException если указана отрицательная величина
     */
    public MyArrayList(int initSize) {
        if (initSize <= ZERO_CAPACITY) {
            throw new IllegalArgumentException();
        }
        this.container = new Object[initSize];
        this.size = 0;
    }

    /**
     * Вспомогательный метод, который возвращает контейнер с увеличенной в 2 раза размерностью.
     * @return контейнер с увеличенной размерностью
     */
    private Object[] increaseCapacity() {
        int newCapacity = container.length * 2;
        return Arrays.copyOf(container, newCapacity);
    }

    /**
     * Вспомогательный метод для проверки границ массива. Применяется во всех методах проверки,
     * кроме add(int index, T element).
     * @param index проверяемая граница
     * @throws IndexOutOfBoundsException если граница отрицательная или проверяемое значение превышает size
     */
    private void checkBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Метод для добавление элемента в конец списка.
     * @param element будет вставлен в конец списка
     */
    @Override
    public void add(T element) {
        if (size == container.length) {
            container = increaseCapacity();
        }
        container[size++] = element;
    }

    /**
     * Метод для вставки элемента в проивольную позицию списка. Если в указанной позиции уже есть элемент, то будет
     * произведён сдвиг элементов справа. Таким образом, данный метод не позволяет заменить уже существующие элементы.
     * @param index позация, куда будет вставлен элемент
     * @param element элемент, который будет вставлен в указанную позицию
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == container.length) {
            container = increaseCapacity();
        }
        System.arraycopy(container, index, container, index + 1, size - index);
        container[index] = element;
        size++;
    }

    /**
     * Метод для получения элемента списка, на указанной позиции.
     * @param index позиция, по которой будет получен элемент
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если позиция превышает size или является отрицательной величиной
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkBounds(index);
        return (T)container[index];
    }

    /**
     * Метод для удаления первого вхождения элемента списка. То есть, при наличии двух одинаковых элементов будет
     * будет удалён элемент с наименьшим индексом (первое вхождение). При успешном удалении происходит сдвиг
     * элементов списка.
     * @param element элемент, который будет удалён из списка
     * @return true при успешном удалении, false при неудачном (такого элемента не было найдено в списке).
     */
    @Override
    public boolean remove(T element) {
        int index = indexOf(element);
        if (index < 0) {
            return false;
        }
        int length = size - index - 1;
        if (length >= 0) {
            System.arraycopy(container, index + 1, container, index, length);
        }
        container[--size] = null;
        return true;
    }

    /**
     * Метод для удаления элемента списка по индексу. При успешном удалении происходит сдвиг оставшихся элементов.
     * @param index индекс элемента, который будет удалён
     * @throws IndexOutOfBoundsException если индекс превышает size или является отрицательным значением
     */
    @Override
    public void remove(int index) {
        checkBounds(index);
        int length = size - index - 1;
        if (length > 0) {
            System.arraycopy(container, index + 1, container, index, length);
        }
        container[--size] = null;
    }

    /**
     * Метод для замены одного элемента другим под указанным индексом.
     * @param index индекс элемента, который будет заменён
     * @param element элемент, который будет подставлен вместо старого
     * @throws IndexOutOfBoundsException если индекс превышает size или является отрицательным значением
     */
    @Override
    public void set(int index, T element) {
        checkBounds(index);
        container[index] = element;
    }

    /**
     * Метод для полной очистки списка, все объекты заменяются на null, size присваивается значение 0.
     */
    @Override
    public void clear() {
        Arrays.fill(container, null);
        size = 0;
    }

    /**
     * Метод для получения количества добавленных элементов.
     * @return количество элементов (size)
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод для проверки наличия элемента в коллекции. При усппешном нахождении возвращается true, иначе false.
     * @param element элемент, наличие которого необходимо определить в списке
     * @return true - при наличии элемента в списке, false - при отсутствии элемента в списке
     */
    @Override
    public boolean contains(T element) {
        for (Object o: container) {
            if (o != null && o.equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод для получения индекса первого вхождения элемента в списке. Например, ["a", "a"] вернёт 0.
     * При отсутсвии элемента в списке возвращается значение -1.
     * * @param element элемент, индекс которого необходимо получить
     * @return индекс элемента при удачном исходе, -1 при отсутствии искомого элемента в коллекции
     */
    @Override
    public int indexOf(T element) {
        for (int i = 0; i < container.length; i++) {
            if (container[i] != null && container[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Метод быстрой сортировки (обёртка).
     * Данная реализация метода требует реализации интерфейса Comparable от хранимых объектов.
     * @throws UnsupportedOperationException если не реализован интерфейс Comparable у класса хранимых объектов.
     */
    public void quickSort() {
        quickSort(0, this.size() - 1, null);
    }

    /**
     * Метод быстрой сортировки, позволяющий передать свою реализацию компаратора.
     * @param comparator необходим для создания собственной логики сортировки хранимых объектов.
     * @throws UnsupportedOperationException если не реализован интерфейс Comparable у класса хранимых объектов.
     */
    public void quickSort(Comparator<? super T> comparator) {
        quickSort(0, this.size() - 1, comparator);
    }

    /**
     * Вспомогательный метод для реализации публичных методов быстрой сортировки.
     * Метод рекурсивно вызывает себя для левой и правой частей массива,
     * используя результат partition в качестве опорного элемента.
     * После разделения массива метод рекурсивно вызывает себя для
     * левой (от low до pivot - 1) и правой (от pivot + 1 до high) частей массива.
     * @param low нижняя граница массива, который нужно разделить
     * @param high верхняя граница массива, который нужно разделить
     * @param comparator компаратор, необходимый для сравнения элементов массива
     */
    private void quickSort(int low, int high, Comparator<? super T> comparator) {
        if (low < high) {
            int pivot = partition(low, high, comparator);

            quickSort(low, pivot - 1, comparator);
            quickSort(pivot + 1, high, comparator);
        }
    }

    /**
     * Вспомогательный метод для реализации быстрой сортировки. Отвечает за раздлеление элементов в массиве.
     * @param low нижняя граница массива, который нужно разделить
     * @param high верхняя граница массива, который нужно разделить
     * @param comparator компаратор, необходимый для сравнения элементов массива
     * @return индекс опорного элемента
     */
    @SuppressWarnings("unchecked")
    private int partition(int low, int high, Comparator<? super T> comparator) {
        T pivot = this.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (comparator != null) {
                if (comparator.compare(this.get(j), pivot) <= 0) {
                    i++;
                    T temp = this.get(i);
                    this.set(i, this.get(j));
                    this.set(j, temp);
                }
            } else if (this.get(j) instanceof Comparable) {
                if (((Comparable<T>) this.get(j)).compareTo(pivot) <= 0) {
                    i++;
                    T temp = this.get(i);
                    this.set(i, this.get(j));
                    this.set(j, temp);
                }
            } else {
                throw new UnsupportedOperationException("Objects must implement Comparable or use a custom Comparator");
            }
        }
        T temp = this.get(i + 1);
        this.set(i + 1, this.get(high));
        this.set(high, temp);

        return i + 1;
    }

    /**
     * Переопределенный метод для вывода списка.
     * @return текстовое представление списка (String)
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object o : container) {
            if (o != null) {
                sb.append(o).append(", ");
            }
        }
        sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }

    /**
     * Переопределенный метод equals. Сравнение происходит по содержимому конейнера и размерности (size).
     * @param o объект, с которым будет сравниваться другой объект
     * @return true - если объекты одинаковые, false - если объекты не одинаковые
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MyArrayList<?> that = (MyArrayList<?>) o;
        return Arrays.equals(container, that.container) && size == that.size;
    }

    /**
     * Переопеделенный метод hashCode. Код генерируется на основе контейнера и размерности (size) списка.
     * @return хеш код списка
     */
    @Override
    public int hashCode() {
        int result = Arrays.hashCode(container);
        result = 32 * result + size;
        return result;
    }
}