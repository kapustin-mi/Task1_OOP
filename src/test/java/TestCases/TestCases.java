package TestCases;

import java.util.*;

public class TestCases {
    private static final List<Integer> SORTABLE_LIST = createSortableList();

    private static final int ELEM_FROM_SORTABLE_LIST = 13;
    private static final int ELEM_NOT_FROM_SORTABLE_LIST = 0;

    private static final List<Person> UNSORTABLE_LIST = createUnsortableList();

    private static final Person[] SEQUENCE_FROM_UNSORTABLE_LIST = new Person[]{new Person("Ваня", 18),
            new Person("Данил", 19)};
    private static final Person[] SEQUENCE_NOT_FROM_UNSORTABLE_LIST = new Person[]{new Person("Вова", 23),
            new Person("Саша", 15)};

    private static final Comparator<Person> personComparator = (o1, o2) -> Integer.compare(o1.age(), o2.age());

    private static final Collection<Integer> COLLECTION = createCollection();

    private static final Set<Integer> SET = createSet();

    private static final Map<Integer, String> MAP = createMap();

    private static List<Integer> createSortableList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(-8);
        list.add(60);
        list.add(13);
        list.add(-154);

        return list;
    }

    private static List<Person> createUnsortableList() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Ваня", 18));
        list.add(new Person("Данил", 19));
        list.add(new Person("Катя", 17));

        return list;
    }

    private static Collection<Integer> createCollection() {
        Collection<Integer> collection = new ArrayList<>();
        collection.add(7);
        collection.add(12);
        collection.add(-4);
        collection.add(28);
        collection.add(5);

        return collection;
    }

    private static Set<Integer> createSet() {
        Set<Integer> set = new HashSet<>();
        set.add(2);
        set.add(12);
        set.add(-3);
        set.add(-128);
        set.add(33);

        return set;
    }

    private static Map<Integer, String> createMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Ваня");
        map.put(2, "Катя");
        map.put(3, "Данил");
        map.put(4, "Валера");
        map.put(5, "Сергей");

        return map;
    }

    public static List<Integer> getSortableList() {
        return SORTABLE_LIST;
    }

    public static int getElemFromSortableList() {
        return ELEM_FROM_SORTABLE_LIST;
    }

    public static int getElemNotFromSortableList() {
        return ELEM_NOT_FROM_SORTABLE_LIST;
    }

    public static Person[] getSequenceFromUnsortableList() {
        return SEQUENCE_FROM_UNSORTABLE_LIST;
    }

    public static Person[] getSequenceNotFromUnsortableList() {
        return SEQUENCE_NOT_FROM_UNSORTABLE_LIST;
    }

    public static List<Person> getUnsortableList() {
        return UNSORTABLE_LIST;
    }

    public static Comparator<Person> getPersonComparator() {
        return personComparator;
    }

    public static Collection<Integer> getCollection() {
        return COLLECTION;
    }

    public static Set<Integer> getSet() {
        return SET;
    }

    public static Map<Integer, String> getMap() {
        return MAP;
    }
}
