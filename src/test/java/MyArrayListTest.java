import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.andreycherenkov.MyArrayList;
import ru.andreycherenkov.MyList;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    private MyList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new MyArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
    }

    //Tests for size()
    @Test
    void whenAddAnother1000ElementsThenSizeIs2000() {
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        assertEquals(2000, list.size());
    }

    //Tests for get(int index)
    @Test
    void whenGetIndexIs999ThenReturn999() {
        assertEquals(999, list.get(999));
    }

    @Test
    void whenGetIndexIs1000ThenThrowsException() {
        try {
            list.get(1000);
        } catch (IndexOutOfBoundsException e) {
            assertNotNull(e);
        }
    }

    @Test
    void whenGetIndexIsNegativeThenThrowsException() {
        try {
            list.get(-1);
        } catch (IndexOutOfBoundsException e) {
            assertNotNull(e);
        }
    }

    //Tests for indexOf(T element);
    @Test
    void when500InListThenIndexOfIs500() {
        assertEquals(500, list.indexOf(500));
    }

    @Test
    void when1001NotInListThenIndexOfIsNegative() {
        assertEquals(-1, list.indexOf(1001));
    }

    //Test for add(int index, T element)
    @Test
    void testAddAtIndex() {
        list.add(0, 1001);
        list.add(1, 1002);
        assertEquals(1001, list.get(0));
        assertEquals(1002, list.get(1));
    }

    @Test
    void whenAddIndexIsNegativeThenThrowsException() {
        try {
            list.add(-1, 123);
        } catch (IndexOutOfBoundsException e) {
            assertNotNull(e);
        }
    }

    @Test
    void whenAddIndexIsMoreThanSizeThenThrowsException() {
        try {
            list.add(1000, 123);
        } catch (IndexOutOfBoundsException e) {
            assertNotNull(e);
        }
    }

    //Tests for add(T element)
    @Test
    void whenAddAtIndexIsNegativeThenThrowsException() {
        try {
            list.add(-1, 1);
        } catch (IndexOutOfBoundsException e) {
            assertNotNull(e);
        }
    }

    @Test
    void whenAddOneMoreElementThenTheLastOneIsAddedElement() {
        list.add(9999);
        assertEquals(9999, list.get(1000));
    }

    //Tests for remove(int index)
    @Test
    void whenRemoveAllElementsThenSizeIs0() {
        for (int i = 0; i < 1000; i++) {
            list.remove(0);
        }
        assertEquals(0, list.size());
    }

    @Test
    void whenRemoveIndexIsNegativeThenThrowsException() {
        try {
            list.remove(-1);
        } catch (IndexOutOfBoundsException e) {
            assertNotNull(e);
        }

    }

    @Test
    void whenRemove500ElementThenSizeIs999() {
        list.remove(500);
        assertEquals(999, list.size());
    }

    @Test
    void whenRemoveFirstElementThenGetReturn1IfIndexIs0() {
        list.remove(0);
        assertEquals(1, list.get(0));
    }

    //Tests for set(int index, T element)
    @Test
    void whenSetAllElementsTo1ThenListsContainsOnly1(){
        for (int i = 0; i < 1000; i++) {
            list.set(i, 1);
            assertEquals(1, list.get(i));
        }
    }

    @Test
    void whenSetAllElementsTo1ThenListsAreEquals() {
        for (int i = 0; i < 1000; i++) {
            list.set(i, 1);
        }

        MyList<Integer> list1 = new MyArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list1.add(1);
        }
        assertEquals(list1, list);
    }

    @Test
    void whenSetAllElementsTo1ThenListsAreNotEquals() {
        for (int i = 0; i < 1000; i++) {
            list.set(i, 1);
        }
        MyList<Integer> list1 = new MyArrayList<>();
        assertNotEquals(list1, list);
    }

    //Tests for clear
    @Test
    void clearTest() {
        list.clear();
        assertEquals(0, list.size());
    }

    //Tests for quickSort
    @Test
    void ListsOfIntegerAfterSortingAreEqual() {
        MyList<Integer> integerMyList = new MyArrayList<>();
        for (int i = 999; i >= 0; i--) {
            integerMyList.add(i);
        }
        integerMyList.quickSort();
        assertEquals(list, integerMyList);
    }

    //Tests for contains
    @Test
    void when1000InListThenContainsReturnTrue() {
        assertTrue(list.contains(500));
    }

    @Test
    void when1001NotInListThenContainsReturnFalse() {
        assertFalse(list.contains(1001));
    }
}