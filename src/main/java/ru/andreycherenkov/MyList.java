package ru.andreycherenkov;

import java.util.Comparator;

/**
 *
 * Интерфейс MyList представляет собой интерфейс для списка элементов,
 * поддерживающий различные операции добавления, доступа, удаления и сортировки.
 * Этот интерфейс позволяет работать с элементами списка обобщенного типа T.
 *
 * @param <T> тип элементов, хранимых в коллекции
 *
 * @author Андрей Черенков
 */
public interface MyList <T> {

    void add(T element);
    void add(int index, T element);

    T get(int index);

    int size();

    int indexOf(T element);

    boolean remove(T element);

    void remove(int index);

    void set(int index, T element);

    void clear();

    void quickSort();

    void quickSort(Comparator<? super T> comparator);

    boolean contains(T element);
}
