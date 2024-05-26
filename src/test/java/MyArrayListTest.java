import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.andreycherenkov.MyArrayList;
import ru.andreycherenkov.MyList;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    private MyList<Integer> integerMyList;
    private MyList<String> stringMyList;

    @BeforeEach
    void setUp() {
        integerMyList = new MyArrayList<>();
        stringMyList = new MyArrayList<>();
        for (int i = 0; i < 1000; i++) {
            integerMyList.add(i);
            stringMyList.add(i + "");
        }
    }

    //Tests for size()
    @Test
    void whenAddAnother1000ElementsThenSizeIs2000() {
        for (int i = 0; i < 1000; i++) {
            integerMyList.add(i);
        }
        assertEquals(2000, integerMyList.size());
    }

    @Test
    void whenAddAnotherStrElementsThenSizeIs2000() {
        for (int i = 0; i < 1000; i++) {
            stringMyList.add(i + "A");
        }
        assertEquals(2000, stringMyList.size());
    }

    //Tests for get(int index)
    @Test
    void whenGetIndexIs999ThenReturn999() {
        assertEquals(999, integerMyList.get(999));
    }

    @Test
    void whenGetIndexIsStr999ThenReturn999() {
        assertEquals("999", stringMyList.get(999));
    }


    @Test
    void whenGetIndexIs1000ThenThrowsException() {
        try {
            integerMyList.get(1000);
        } catch (IndexOutOfBoundsException e) {
            assertNotNull(e);
        }
    }

    @Test
    void whenGetIndexIsNegativeThenThrowsException() {
        try {
            integerMyList.get(-1);
        } catch (IndexOutOfBoundsException e) {
            assertNotNull(e);
        }
    }

    //Tests for indexOf(T element);
    @Test
    void when500InListThenIndexOfIs500() {
        assertEquals(500, integerMyList.indexOf(500));
    }

    @Test
    void when1001NotInListThenIndexOfIsNegative() {
        assertEquals(-1, integerMyList.indexOf(1001));
    }

    @Test
    void whenStr500InListThenIndexOfIs500() {
        assertEquals(500, stringMyList.indexOf("500"));
    }

    @Test
    void whenStrANotInListThenIndexOfIs500() {
        assertEquals(-1, stringMyList.indexOf("A"));
    }

    //Test for add(int index, T element)
    @Test
    void testAddIntegerAtIndex() {
        integerMyList.add(0, 1001);
        integerMyList.add(1, 1002);
        assertEquals(1001, integerMyList.get(0));
        assertEquals(1002, integerMyList.get(1));
    }

    @Test
    void testAddStringAtIndex() {
        stringMyList.add(0, "1001");
        stringMyList.add(1, "1002");
        assertEquals("1001", stringMyList.get(0));
        assertEquals("1002", stringMyList.get(1));
    }

    @Test
    void whenAddIndexIsNegativeThenThrowsException() {
        try {
            integerMyList.add(-1, 123);
        } catch (IndexOutOfBoundsException e) {
            assertNotNull(e);
        }
    }

    @Test
    void whenAddIndexIsMoreThanSizeThenThrowsException() {
        try {
            integerMyList.add(1000, 123);
        } catch (IndexOutOfBoundsException e) {
            assertNotNull(e);
        }
    }

    //Tests for add(T element)
    @Test
    void whenAddAtIndexIsNegativeThenThrowsException() {
        try {
            integerMyList.add(-1, 1);
        } catch (IndexOutOfBoundsException e) {
            assertNotNull(e);
        }
    }

    @Test
    void whenAddOneMoreElementThenTheLastOneIsAddedElement() {
        integerMyList.add(9999);
        assertEquals(9999, integerMyList.get(1000));
    }

    //Tests for remove(int index)
    @Test
    void whenRemoveAllElementsThenSizeIs0() {
        for (int i = 0; i < 1000; i++) {
            integerMyList.remove(0);
        }
        assertEquals(0, integerMyList.size());
    }

    @Test
    void whenRemoveIndexIsNegativeThenThrowsException() {
        try {
            integerMyList.remove(-1);
        } catch (IndexOutOfBoundsException e) {
            assertNotNull(e);
        }

    }

    @Test
    void whenRemove500ElementThenSizeIs999() {
        integerMyList.remove(500);
        assertEquals(999, integerMyList.size());
    }

    @Test
    void whenRemoveFirstElementThenGetReturn1IfIndexIs0() {
        integerMyList.remove(0);
        assertEquals(1, integerMyList.get(0));
    }

    @Test
    void whenRemoveFirstStrElementThenSize999() {
        stringMyList.remove(0);
        assertEquals(999, stringMyList.size());
    }

    //Tests for remove(T element)
    @Test
    void whenRemoveAThenReturnFalse() {
        assertFalse(stringMyList.remove("A"));
    }

    @Test
    void whenRemoveAllElementsThenReturnAlwaysTrue() {
        for (int i = 0; i < 1000; i++) {
            assertTrue(stringMyList.remove(i + ""));
        }
    }

    @Test
    void whenRemoveAllOneElementThenSizeIs999() {
        stringMyList.remove("1");
        assertEquals(999, stringMyList.size());
    }

    //Tests for set(int index, T element)
    @Test
    void whenSetAllElementsTo1ThenListsContainsOnly1(){
        for (int i = 0; i < 1000; i++) {
            integerMyList.set(i, 1);
            assertEquals(1, integerMyList.get(i));
        }
    }

    @Test
    void whenSetAllElementsTo1ThenListsAreEquals() {
        for (int i = 0; i < 1000; i++) {
            integerMyList.set(i, 1);
        }

        MyList<Integer> list1 = new MyArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list1.add(1);
        }
        assertEquals(list1, integerMyList);
    }

    @Test
    void whenSetAllElementsToAThenListsAreEquals() {
        for (int i = 0; i < 1000; i++) {
            stringMyList.set(i, "A");
        }

        MyList<String> list1 = new MyArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list1.add("A");
        }
        assertEquals(list1, stringMyList);
    }

    @Test
    void whenSetAllElementsTo1ThenListsAreNotEquals() {
        for (int i = 0; i < 1000; i++) {
            integerMyList.set(i, 1);
        }
        MyList<Integer> list1 = new MyArrayList<>();
        assertNotEquals(list1, integerMyList);
    }

    @Test
    void whenSetAllElementsToAThenListsAreNotEquals() {
        for (int i = 0; i < 1000; i++) {
            stringMyList.set(i, "A");
        }
        MyList<String> list1 = new MyArrayList<>();
        assertNotEquals(list1, integerMyList);
    }

    //Tests for clear
    @Test
    void clearTestOfIntegerList() {
        integerMyList.clear();
        assertEquals(0, integerMyList.size());
    }

    @Test
    void clearTestOfStringList() {
        stringMyList.clear();
        assertEquals(0, stringMyList.size());
    }

    //Tests for quickSort
    @Test
    void ListsOfIntegerAfterSortingAreEqual() {
        MyList<Integer> integerMyList1 = new MyArrayList<>();
        for (int i = 999; i >= 0; i--) {
            integerMyList1.add(i);
        }
        integerMyList1.quickSort();
        assertEquals(integerMyList, integerMyList1);
    }

    @Test
    void ListsOfStringAfterSortingAreEqual() {
        MyList<String> stringMyList1 = new MyArrayList<>();
        for (int i = 999; i >= 0; i--) {
            stringMyList1.add(i + "");
        }
        //Сортируем исходный список, поскольку сортировка строк происходит по кодам символов.
        stringMyList.quickSort();
        stringMyList1.quickSort();
        assertEquals(stringMyList1, stringMyList);
    }

    //Tests for contains
    @Test
    void when1000InListThenContainsReturnTrue() {
        assertTrue(integerMyList.contains(500));
    }

    @Test
    void when1001NotInListThenContainsReturnFalse() {
        assertFalse(integerMyList.contains(1001));
    }

    @Test
    void whenString1000InListThenContainsReturnTrue() {
        assertTrue(stringMyList.contains(500 + ""));
    }

    @Test
    void whenString1001NotInListThenContainsReturnFalse() {
        assertFalse(stringMyList.contains(1001 + ""));
    }

    //Test for hashCode
    @Test
    void whenHashCodeAreNotTheSameThenEqualsReturnFalse() {
        MyList<Integer> integerMyList1 = new MyArrayList<>();
        assertNotEquals(integerMyList.hashCode(), integerMyList1.hashCode());
        assertFalse(integerMyList.equals(integerMyList1));

        MyList<String> stringMyList1 = new MyArrayList<>();
        assertNotEquals(stringMyList1.hashCode(), stringMyList.hashCode());
        assertFalse(stringMyList.equals(stringMyList1));
    }
}