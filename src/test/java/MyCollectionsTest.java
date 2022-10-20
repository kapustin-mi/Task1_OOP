import CollectionsLib.MyCollections;
import TestCases.TestCases;
import TestCases.Person;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class MyCollectionsTest {

    @Test
    public void sort() {
        List<Integer> actual = TestCases.getSortableList();
        MyCollections.sort(actual);

        List<Integer> expected = TestCases.getSortableList();
        Collections.sort(expected);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void sortWithComparator() {
        Comparator<Person> ageComp = TestCases.getPersonComparator();

        List<Person> actual = TestCases.getUnsortableList();
        MyCollections.sort(actual, ageComp);

        List<Person> expected = TestCases.getUnsortableList();
        Collections.sort(expected, ageComp);

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ClassCastException.class)
    public void sortWithoutComparator() {
        List<Person> actual = TestCases.getUnsortableList();
        MyCollections.sort(actual, null);
    }

    @Test
    public void search() {
        List<Integer> list = TestCases.getSortableList(); // 1, -8, 60, 13, -154
        int elemFromList = TestCases.getElemFromSortableList(), // 13
                elemNotFromList = TestCases.getElemNotFromSortableList(); // 0

        Assert.assertEquals(3, MyCollections.search(list, elemFromList));
        Assert.assertEquals(-1, MyCollections.search(list, elemNotFromList));
    }

    @Test
    public void searchSeq() {
        List<Person> list = TestCases.getUnsortableList(); //Катя - 17, Ваня - 18, Данил - 19
        Person[] seqFromList = TestCases.getSequenceFromUnsortableList(); // Ваня - 18, Данил - 19
        Person[] seqNotFromList = TestCases.getSequenceNotFromUnsortableList(); // Вова - 23, Саша - 15

        Assert.assertEquals(1, MyCollections.search(list, seqFromList));
        Assert.assertEquals(-1, MyCollections.search(list, seqNotFromList));
    }

    @Test
    public void readonlyCollection() {
        Collection<Integer> collection = TestCases.getCollection();
        Collection<Integer> readonly = MyCollections.readonlyCollection(collection);

        Class<UnsupportedOperationException> expectedExcep = UnsupportedOperationException.class;

        Class<? extends UnsupportedOperationException> actualForAdd = Assert.assertThrows(UnsupportedOperationException.class,
                () -> readonly.add(12)).getClass();
        Assert.assertEquals(expectedExcep, actualForAdd);

        Class<? extends UnsupportedOperationException> actualForRemove = Assert.assertThrows(UnsupportedOperationException.class,
                () -> readonly.remove(12)).getClass();
        Assert.assertEquals(expectedExcep, actualForRemove);

        Class<? extends UnsupportedOperationException> actualForClear = Assert.assertThrows(UnsupportedOperationException.class,
                readonly::clear).getClass();
        Assert.assertEquals(expectedExcep, actualForClear);

        Assert.assertEquals(collection.size(), readonly.size());
        Assert.assertEquals(Arrays.toString(collection.toArray()), Arrays.toString(readonly.toArray()));
    }

    @Test
    public void readonlyList() {
        List<Integer> list = TestCases.getSortableList();
        List<Integer> readonly = MyCollections.readonlyList(list);

        Class<UnsupportedOperationException> expectedExcep = UnsupportedOperationException.class;

        Class<? extends UnsupportedOperationException> actualForSet = Assert.assertThrows(UnsupportedOperationException.class,
                () -> readonly.set(2, 12)).getClass();
        Assert.assertEquals(expectedExcep, actualForSet);

        Class<? extends UnsupportedOperationException> actualForRemove = Assert.assertThrows(UnsupportedOperationException.class,
                () -> readonly.remove(0)).getClass();
        Assert.assertEquals(expectedExcep, actualForRemove);

        Class<? extends UnsupportedOperationException> actualForAdd = Assert.assertThrows(UnsupportedOperationException.class,
                () -> readonly.add(2, 3)).getClass();
        Assert.assertEquals(expectedExcep, actualForAdd);

        Assert.assertEquals(list.get(0), readonly.get(0));
        Assert.assertEquals(list.subList(0, 2), readonly.subList(0, 2));
    }

    @Test
    public void readonlySet() {
        Set<Integer> set = TestCases.getSet();
        Set<Integer> readonly = MyCollections.readonlySet(set);

        Class<UnsupportedOperationException> expectedExcep = UnsupportedOperationException.class;

        Class<? extends UnsupportedOperationException> actualForAdd = Assert.assertThrows(UnsupportedOperationException.class,
                () -> readonly.add(15)).getClass();
        Assert.assertEquals(expectedExcep, actualForAdd);

        Class<? extends UnsupportedOperationException> actualForRemove = Assert.assertThrows(UnsupportedOperationException.class,
                () -> readonly.remove(15)).getClass();
        Assert.assertEquals(expectedExcep, actualForRemove);

        Class<? extends UnsupportedOperationException> actualForClear = Assert.assertThrows(UnsupportedOperationException.class,
                readonly::clear).getClass();
        Assert.assertEquals(expectedExcep, actualForClear);

        Assert.assertEquals(set.size(), readonly.size());
        Assert.assertEquals(Arrays.toString(set.toArray()), Arrays.toString(readonly.toArray()));
    }

    @Test
    public void readonlyMap() {
        Map<Integer, String> map = TestCases.getMap();
        Map<Integer, String> readonly = MyCollections.readonlyMap(map);

        Class<UnsupportedOperationException> expectedExcep = UnsupportedOperationException.class;

        Class<? extends UnsupportedOperationException> actualForAdd = Assert.assertThrows(UnsupportedOperationException.class,
                () -> readonly.put(15, "Вова")).getClass();
        Assert.assertEquals(expectedExcep, actualForAdd);

        Class<? extends UnsupportedOperationException> actualForRemove = Assert.assertThrows(UnsupportedOperationException.class,
                () -> readonly.remove(3)).getClass();
        Assert.assertEquals(expectedExcep, actualForRemove);

        Class<? extends UnsupportedOperationException> actualForClear = Assert.assertThrows(UnsupportedOperationException.class,
                readonly::clear).getClass();
        Assert.assertEquals(expectedExcep, actualForClear);

        Assert.assertEquals(map.size(), readonly.size());
        Assert.assertEquals(map.keySet(), readonly.keySet());
    }

    @Test
    public void writeonlyCollection() {
        Collection<Integer> collection = TestCases.getCollection();
        Collection<Integer> writeonly = MyCollections.writeonlyCollection(collection);

        Class<UnsupportedOperationException> expectedExcep = UnsupportedOperationException.class;

        Class<? extends UnsupportedOperationException> actualForArr = Assert.assertThrows(UnsupportedOperationException.class,
                writeonly::toArray).getClass();
        Assert.assertEquals(expectedExcep, actualForArr);

        Class<? extends UnsupportedOperationException> actualForContains = Assert.assertThrows(UnsupportedOperationException.class,
                () -> writeonly.contains(2)).getClass();
        Assert.assertEquals(expectedExcep, actualForContains);

        int size = collection.size();
        Assert.assertEquals(size, writeonly.size());
        Assert.assertTrue(writeonly.add(2));
        Assert.assertEquals(size + 1, writeonly.size());
    }

    @Test
    public void writeonlyList() {
        List<Integer> list = TestCases.getSortableList();
        List<Integer> writeonly = MyCollections.writeonlyList(list);

        Class<UnsupportedOperationException> expectedExcep = UnsupportedOperationException.class;

        Class<? extends UnsupportedOperationException> actualForGet = Assert.assertThrows(UnsupportedOperationException.class,
                () -> writeonly.get(2)).getClass();
        Assert.assertEquals(expectedExcep, actualForGet);

        Class<? extends UnsupportedOperationException> actualForGetIndex = Assert.assertThrows(UnsupportedOperationException.class,
                () -> writeonly.indexOf(2)).getClass();
        Assert.assertEquals(expectedExcep, actualForGetIndex);

        int size = list.size();
        Assert.assertTrue(writeonly.add(17));
        Assert.assertEquals(size + 1, writeonly.size());
    }

    @Test
    public void writeonlySet() {
        Set<Integer> set = TestCases.getSet();
        Set<Integer> writeonly = MyCollections.writeonlySet(set);

        Class<UnsupportedOperationException> expectedExcep = UnsupportedOperationException.class;

        Class<? extends UnsupportedOperationException> actualForArr = Assert.assertThrows(UnsupportedOperationException.class,
                writeonly::toArray).getClass();
        Assert.assertEquals(expectedExcep, actualForArr);

        Class<? extends UnsupportedOperationException> actualForContains = Assert.assertThrows(UnsupportedOperationException.class,
                () -> writeonly.contains(2)).getClass();
        Assert.assertEquals(expectedExcep, actualForContains);

        int size = set.size();
        Assert.assertTrue(writeonly.add(152));
        Assert.assertEquals(size + 1, writeonly.size());
        Assert.assertTrue(writeonly.remove(152));
    }

    @Test
    public void writeonlyMap() {
        Map<Integer, String> map = TestCases.getMap();
        Map<Integer, String> writeonly = MyCollections.writeonlyMap(map);

        Class<UnsupportedOperationException> expectedExcep = UnsupportedOperationException.class;

        Class<? extends UnsupportedOperationException> actualForContK = Assert.assertThrows(UnsupportedOperationException.class,
                () -> writeonly.containsKey(1)).getClass();
        Assert.assertEquals(expectedExcep, actualForContK);

        Class<? extends UnsupportedOperationException> actualForContV = Assert.assertThrows(UnsupportedOperationException.class,
                () -> writeonly.containsValue("Ваня")).getClass();
        Assert.assertEquals(expectedExcep, actualForContV);

        int size = map.size();
        writeonly.put(6, "Марк");
        Assert.assertEquals(size + 1, writeonly.size());
    }

    @Test
    public void singleton() {
        Set<Integer> singleton = MyCollections.singleton(1);

        Class<UnsupportedOperationException> expectedExcep = UnsupportedOperationException.class;

        Class<? extends UnsupportedOperationException> actualForAdd = Assert.assertThrows(UnsupportedOperationException.class,
                () -> singleton.add(2)).getClass();
        Assert.assertEquals(expectedExcep, actualForAdd);

        Assert.assertTrue(singleton.contains(1));
        Assert.assertEquals(1, singleton.size());
    }

    @Test
    public void singletonList() {
        List<Integer> singletonList = MyCollections.singletonList(1);

        Class<UnsupportedOperationException> expectedExcep = UnsupportedOperationException.class;

        Class<? extends UnsupportedOperationException> actualForAdd = Assert.assertThrows(UnsupportedOperationException.class,
                () -> singletonList.add(2)).getClass();
        Assert.assertEquals(expectedExcep, actualForAdd);

        Class<? extends UnsupportedOperationException> actualForRemove = Assert.assertThrows(UnsupportedOperationException.class,
                () -> singletonList.remove(0)).getClass();
        Assert.assertEquals(expectedExcep, actualForRemove);

        Assert.assertTrue(singletonList.contains(1));
        Assert.assertEquals(1, singletonList.size());
    }

    @Test
    public void singletonMap() {
        Map<Integer, String> singletonMap = MyCollections.singletonMap(1, "Ваня");

        Class<UnsupportedOperationException> expectedExcep = UnsupportedOperationException.class;

        Class<? extends UnsupportedOperationException> actualForAdd = Assert.assertThrows(UnsupportedOperationException.class,
                () -> singletonMap.put(2, "Кристина")).getClass();
        Assert.assertEquals(expectedExcep, actualForAdd);

        Class<? extends UnsupportedOperationException> actualForRemove = Assert.assertThrows(UnsupportedOperationException.class,
                () -> singletonMap.remove(1)).getClass();
        Assert.assertEquals(expectedExcep, actualForRemove);

        Assert.assertTrue(singletonMap.containsKey(1));
        Assert.assertEquals(1, singletonMap.size());
    }

    @Test
    public void emptySet() {
        Set<Integer> emptySet = MyCollections.emptySet();

        Class<UnsupportedOperationException> expectedExcep = UnsupportedOperationException.class;

        Class<? extends UnsupportedOperationException> actualForAdd = Assert.assertThrows(UnsupportedOperationException.class,
                () -> emptySet.add(1)).getClass();
        Assert.assertEquals(expectedExcep, actualForAdd);

        Assert.assertTrue(emptySet.isEmpty());
    }

    @Test
    public void emptyList() {
        List<Integer> emptyList = Collections.emptyList();

        Class<UnsupportedOperationException> expectedExcep = UnsupportedOperationException.class;

        Class<? extends UnsupportedOperationException> actualForAdd = Assert.assertThrows(UnsupportedOperationException.class,
                () -> emptyList.add(1)).getClass();
        Assert.assertEquals(expectedExcep, actualForAdd);

        Assert.assertTrue(emptyList.isEmpty());
    }

    @Test
    public void emptyMap() {
        Map<Integer, String> emptyMap = MyCollections.emptyMap();

        Class<UnsupportedOperationException> expectedExcep = UnsupportedOperationException.class;

        Class<? extends UnsupportedOperationException> actualForAdd = Assert.assertThrows(UnsupportedOperationException.class,
                () -> emptyMap.put(1, "Ваня")).getClass();
        Assert.assertEquals(expectedExcep, actualForAdd);

        Assert.assertTrue(emptyMap.isEmpty());
    }
}