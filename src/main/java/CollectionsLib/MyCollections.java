package CollectionsLib;

import java.util.*;

public class MyCollections {
    private static final Set<Object> EMPTY_SET = new EmptySet<>();
    private static final List<Object> EMPTY_LIST = new EmptyList<>();
    private static final Map<Object, Object> EMPTY_MAP = new EmptyMap<>();

    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        sort(list, null);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> void sort(List<T> list, Comparator<? super T> comparator) {
        Object[] elems = list.toArray();
        Arrays.sort(elems, (Comparator) comparator);

        for (int i = 0; i < elems.length; i++) {
            list.set(i, (T) elems[i]);
        }
    }

    public static <T> int search(List<? super T> list, T elem) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(elem)) {
                return i;
            }
        }

        return -1;
    }

    @SafeVarargs
    public static <T> int search(List<? super T> list, T... elems) {
        int subLength = 0;

        for (int i = 0; i <= list.size() - elems.length; i++) {
            for (int k = 0, j = i; k < elems.length; j++, k++) {
                if (!list.get(j).equals(elems[k])) {
                    break;
                }

                subLength++;
            }

            if (subLength == elems.length) {
                return i;
            }
        }

        return -1;
    }

    @SuppressWarnings("unchecked")
    public static <T> Collection<T> readonlyCollection(Collection<? extends T> collection) {
        if (collection.getClass() == ReadonlyCollection.class) {
            return (Collection<T>) collection;
        }

        return new ReadonlyCollection<>(collection);
    }

    private static class ReadonlyCollection<E> implements Collection<E> {
        private final Collection<? extends E> collection;

        ReadonlyCollection(Collection<? extends E> collection) {
            this.collection = collection;
        }

        @Override
        public int size() {
            return collection.size();
        }

        @Override
        public boolean isEmpty() {
            return collection.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return collection.contains(o);
        }

        @SuppressWarnings({"rawtypes", "unchecked"})
        @Override
        public Iterator iterator() {
            return new Iterator<E>() {
                private final Iterator<? extends E> iterator = collection.iterator();

                public boolean hasNext() {
                    return iterator.hasNext();
                }

                public E next() {
                    return iterator.next();
                }
            };
        }

        @Override
        public Object[] toArray() {
            return collection.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return collection.toArray(a);
        }

        @Override
        public boolean add(E elem) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(Object o) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return collection.containsAll(c);
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> readonlyList(List<? extends T> list) {
        if (list.getClass() == ReadonlyList.class) {
            return (List<T>) list;
        }

        return new ReadonlyList<>(list);
    }

    private static class ReadonlyList<E> extends ReadonlyCollection<E> implements List<E> {
        private final List<? extends E> list;

        ReadonlyList(List<? extends E> list) {
            super(list);
            this.list = list;
        }

        @Override
        public boolean addAll(int index, Collection<? extends E> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public E get(int index) {
            return list.get(index);
        }

        @Override
        public E set(int index, E element) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(int index, E element) {
            throw new UnsupportedOperationException();
        }

        @Override
        public E remove(int index) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int indexOf(Object o) {
            return list.indexOf(o);
        }

        @Override
        public int lastIndexOf(Object o) {
            return list.lastIndexOf(o);
        }

        @Override
        public ListIterator<E> listIterator() {
            return listIterator(0);
        }

        @Override
        public ListIterator<E> listIterator(int index) {
            return new ListIterator<E>() {
                private final ListIterator<? extends E> iterator = list.listIterator(index);

                public boolean hasNext() {
                    return iterator.hasNext();
                }

                public E next() {
                    return iterator.next();
                }

                public boolean hasPrevious() {
                    return iterator.hasPrevious();
                }

                public E previous() {
                    return iterator.previous();
                }

                public int nextIndex() {
                    return iterator.nextIndex();
                }

                public int previousIndex() {
                    return iterator.previousIndex();
                }

                public void remove() {
                    throw new UnsupportedOperationException();
                }

                public void set(E e) {
                    throw new UnsupportedOperationException();
                }

                public void add(E e) {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Override
        public List<E> subList(int fromIndex, int toIndex) {
            return new ReadonlyList<>(list.subList(fromIndex, toIndex));
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Set<T> readonlySet(Set<? extends T> set) {
        if (set.getClass() == ReadonlySet.class) {
            return (Set<T>) set;
        }
        return new ReadonlySet<>(set);
    }

    private static class ReadonlySet<E> extends ReadonlyCollection<E> implements Set<E> {
        ReadonlySet(Set<? extends E> set) {
            super(set);
        }
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> readonlyMap(Map<? extends K, ? extends V> map) {
        if (map.getClass() == ReadonlyMap.class) {
            return (Map<K, V>) map;
        }
        return new ReadonlyMap<>(map);
    }

    private static class ReadonlyMap<K, V> implements Map<K, V> {
        private final Map<? extends K, ? extends V> map;

        ReadonlyMap(Map<? extends K, ? extends V> map) {
            this.map = map;
        }

        public int size() {
            return map.size();
        }

        public boolean isEmpty() {
            return map.isEmpty();
        }

        public boolean containsKey(Object key) {
            return map.containsKey(key);
        }

        public boolean containsValue(Object val) {
            return map.containsValue(val);
        }

        public V get(Object key) {
            return map.get(key);
        }

        public V put(K key, V value) {
            throw new UnsupportedOperationException();
        }

        public V remove(Object key) {
            throw new UnsupportedOperationException();
        }

        public void putAll(Map<? extends K, ? extends V> m) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            throw new UnsupportedOperationException();
        }

        private Set<K> keySet;
        private Set<Map.Entry<K, V>> entrySet;
        private Collection<V> values;

        public Set<K> keySet() {
            if (keySet == null) {
                keySet = readonlySet(map.keySet());
            }

            return keySet;
        }

        public Set<Map.Entry<K, V>> entrySet() {
            if (entrySet == null) {
                entrySet = new ReadonlyMap.ReadonlyEntrySet<>(map.entrySet());
            }

            return entrySet;
        }

        public Collection<V> values() {
            if (values == null) {
                values = readonlyCollection(map.values());
            }

            return values;
        }

        static class ReadonlyEntrySet<K, V> extends ReadonlySet<Entry<K, V>> {

            @SuppressWarnings({"unchecked", "rawtypes"})
            ReadonlyEntrySet(Set<? extends Map.Entry<? extends K, ? extends V>> set) {
                super((Set) set);
            }

            private record ReadonlyEntry<K, V>(Entry<? extends K, ? extends V> entry) implements Entry<K, V> {
                private ReadonlyEntry(Entry<? extends K, ? extends V> entry) {
                    this.entry = Objects.requireNonNull(entry);
                }

                public K getKey() {
                    return entry.getKey();
                }

                public V getValue() {
                    return entry.getValue();
                }

                public V setValue(V value) {
                    throw new UnsupportedOperationException();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Collection<T> writeonlyCollection(Collection<? super T> collection) {
        if (collection.getClass() == WriteonlyCollection.class) {
            return (Collection<T>) collection;
        }

        return new WriteonlyCollection<>(collection);
    }

    private static class WriteonlyCollection<E> implements Collection<E> {
        private final Collection<? super E> collection;

        WriteonlyCollection(Collection<? super E> collection) {
            this.collection = collection;
        }

        @Override
        public int size() {
            return collection.size();
        }

        @Override
        public boolean isEmpty() {
            return collection.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            throw new UnsupportedOperationException();
        }

        @SuppressWarnings({"rawtypes", "unchecked"})
        @Override
        public Iterator iterator() {
            return new Iterator<E>() {
                private final Iterator<? super E> iterator = collection.iterator();

                public boolean hasNext() {
                    return iterator.hasNext();
                }

                public E next() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Override
        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean add(E elem) {
            return collection.add(elem);
        }

        @Override
        public boolean remove(Object o) {
            return collection.remove(o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            return collection.addAll(c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return collection.removeAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return collection.retainAll(c);
        }

        @Override
        public void clear() {
            collection.clear();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> writeonlyList(List<? super T> list) {
        if (list.getClass() == WriteonlyList.class) {
            return (List<T>) list;
        }

        return new WriteonlyList<>(list);
    }

    private static class WriteonlyList<E> extends WriteonlyCollection<E> implements List<E> {
        private final List<? super E> list;

        WriteonlyList(List<? super E> list) {
            super(list);
            this.list = list;
        }

        @Override
        public boolean addAll(int index, Collection<? extends E> c) {
            return list.addAll(index, c);
        }

        @Override
        public E get(int index) {
            throw new UnsupportedOperationException();
        }

        @SuppressWarnings("unchecked")
        @Override
        public E set(int index, E element) {
            return (E) list.set(index, element);
        }

        @Override
        public void add(int index, E element) {
            list.add(index, element);
        }

        @SuppressWarnings("unchecked")
        @Override
        public E remove(int index) {
            return (E) list.remove(index);
        }

        @Override
        public int indexOf(Object o) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int lastIndexOf(Object o) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ListIterator<E> listIterator() {
            return listIterator(0);
        }

        @Override
        public ListIterator<E> listIterator(int index) {
            return new ListIterator<E>() {
                private final ListIterator<? super E> iterator = list.listIterator(index);

                public boolean hasNext() {
                    return iterator.hasNext();
                }

                public E next() {
                    throw new UnsupportedOperationException();
                }

                public boolean hasPrevious() {
                    return iterator.hasPrevious();
                }

                public E previous() {
                    throw new UnsupportedOperationException();
                }

                public int nextIndex() {
                    return iterator.nextIndex();
                }

                public int previousIndex() {
                    return iterator.previousIndex();
                }

                public void remove() {
                    list.remove(index);
                }

                public void set(E e) {
                    list.listIterator().set(e);
                }

                public void add(E e) {
                    list.add(e);
                }
            };
        }

        @Override
        public List<E> subList(int fromIndex, int toIndex) {
            throw new UnsupportedOperationException();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Set<T> writeonlySet(Set<? super T> set){
        if (set.getClass() == WriteonlySet.class) {
            return (Set<T>) set;
        }

        return new WriteonlySet<>(set);
    }

    private static class WriteonlySet<E> extends WriteonlyCollection<E> implements Set<E> {
        WriteonlySet(Set<? super E> set) {
            super(set);
        }
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> writeonlyMap(Map<? super K, ? super V> map) {
        if (map.getClass() == WriteonlyMap.class) {
            return (Map<K, V>) map;
        }

        return new WriteonlyMap<>(map);
    }

    private static class WriteonlyMap<K, V> implements Map<K, V> {
        private final Map<? super K, ? super V> map;

        WriteonlyMap(Map<? super K, ? super V> map) {
            this.map = map;
        }

        public int size() {
            return map.size();
        }

        public boolean isEmpty() {
            return map.isEmpty();
        }

        public boolean containsKey(Object key) {
            throw new UnsupportedOperationException();
        }

        public boolean containsValue(Object val) {
            throw new UnsupportedOperationException();
        }

        public V get(Object key) {
            throw new UnsupportedOperationException();
        }

        @SuppressWarnings("unchecked")
        public V put(K key, V value) {
            return (V) map.put(key, value);
        }

        @SuppressWarnings("unchecked")
        public V remove(Object key) {
            return (V) map.remove(key);
        }

        public void putAll(Map<? extends K, ? extends V> m) {
            map.putAll(m);
        }

        public void clear() {
            map.clear();
        }

        private Set<K> keySet;
        private Set<Map.Entry<K, V>> entrySet;
        private Collection<V> values;

        public Set<K> keySet() {
            throw new UnsupportedOperationException();
        }

        public Set<Map.Entry<K, V>> entrySet() {
            throw new UnsupportedOperationException();
        }

        public Collection<V> values() {
            throw new UnsupportedOperationException();
        }

        static class WriteonlyEntrySet<K, V> extends WriteonlySet<Entry<K, V>> {

            @SuppressWarnings({"unchecked", "rawtypes"})
            WriteonlyEntrySet(Set<? extends Map.Entry<? super K, ? super V>> set) {
                super((Set) set);
            }

            private record WriteonlyEntry<K, V>(Entry<? super K, ? super V> entry) implements Entry<K, V> {
                private WriteonlyEntry(Entry<? super K, ? super V> entry) {
                    this.entry = Objects.requireNonNull(entry);
                }

                public K getKey() {
                    throw new UnsupportedOperationException();
                }

                public V getValue() {
                    throw new UnsupportedOperationException();
                }

                @SuppressWarnings({"unchecked"})
                public V setValue(V value) {
                    return (V) entry.setValue(value);
                }
            }
        }
    }

    private static <E> Iterator<E> singletonIterator(final E e) {
        return new Iterator<E>() {
            private boolean hasNext = true;

            public boolean hasNext() {
                return hasNext;
            }

            public E next() {
                if (hasNext) {
                    hasNext = false;
                    return e;
                }

                throw new NoSuchElementException();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static <T> Set<T> singleton(T o) {
        return new SingletonSet<>(o);
    }

    private static class SingletonSet<E> extends AbstractSet<E> {
        private final E element;

        SingletonSet(E element) {
            this.element = element;
        }

        public Iterator<E> iterator() {
            return singletonIterator(element);
        }

        public int size() {
            return 1;
        }

        public boolean contains(Object o) {
            return o.equals(element);
        }
    }

    public static <T> List<T> singletonList(T o) {
        return new SingletonList<>(o);
    }

    private static class SingletonList<E> extends AbstractList<E> {
        private final E element;

        SingletonList(E element) {
            this.element = element;
        }

        public Iterator<E> iterator() {
            return singletonIterator(element);
        }

        public int size() {
            return 1;
        }

        public boolean contains(Object obj) {
            return obj.equals(element);
        }

        public E get(int index) {
            if (index != 0) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: 1");
            }

            return element;
        }
    }

    public static <K,V> Map<K,V> singletonMap(K key, V value) {
        return new SingletonMap<>(key, value);
    }


    private static class SingletonMap<K,V> extends AbstractMap<K,V> {
        private final K key;
        private final V value;

        SingletonMap(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public int size() {
            return 1;
        }

        public boolean isEmpty() {
            return false;
        }

        public boolean containsKey(Object key) {
            return key.equals(this.key);
        }

        public boolean containsValue(Object value) {
            return value.equals(this.value);
        }

        public V get(Object key) {
            return containsKey(key) ? value : null;
        }

        private transient Set<K> keySet;
        private transient Set<Map.Entry<K,V>> entrySet;
        private transient Collection<V> values;

        public Set<K> keySet() {
            if (keySet == null) {
                keySet = singleton(key);
            }

            return keySet;
        }

        public Set<Map.Entry<K,V>> entrySet() {
            if (entrySet == null) {
                entrySet = singleton(new SimpleImmutableEntry<>(key, value));
            }

            return entrySet;
        }

        public Collection<V> values() {
            if (values == null) {
                values = singleton(value);
            }

            return values;
        }

        @Override
        public boolean remove(Object key, Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean replace(K key, V oldValue, V newValue) {
            throw new UnsupportedOperationException();
        }

        @Override
        public V replace(K key, V value) {
            throw new UnsupportedOperationException();
        }
    }

    private static <T> Iterator<T> emptyIterator() {
        return new EmptyIterator<>();
    }

    private static class EmptyIterator<E> implements Iterator<E> {
        public boolean hasNext() {
            return false;
        }

        public E next() {
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new IllegalStateException();
        }
    }

    private static <T> ListIterator<T> emptyListIterator() {
        return new EmptyListIterator<>();
    }

    private static class EmptyListIterator<E> extends EmptyIterator<E> implements ListIterator<E> {
        public boolean hasPrevious() {
            return false;
        }

        public E previous() {
            throw new NoSuchElementException();
        }

        public int nextIndex() {
            return 0;
        }

        public int previousIndex() {
            return -1;
        }

        public void set(E e) {
            throw new IllegalStateException();
        }

        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Set<T> emptySet() {
        return (Set<T>) EMPTY_SET;
    }

    private static class EmptySet<E> extends AbstractSet<E> {
        public Iterator<E> iterator() {
            return emptyIterator();
        }

        public int size() {
            return 0;
        }

        public boolean isEmpty() {
            return true;
        }

        public void clear() {
        }

        public boolean contains(Object obj) {
            return false;
        }

        public boolean containsAll(Collection<?> c) {
            return c.isEmpty();
        }

        public Object[] toArray() {
            return new Object[0];
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> emptyList() {
        return (List<T>) EMPTY_LIST;
    }

    private static class EmptyList<E> extends AbstractList<E> {
        public Iterator<E> iterator() {
            return emptyIterator();
        }

        public ListIterator<E> listIterator() {
            return emptyListIterator();
        }

        public int size() {
            return 0;
        }

        public boolean isEmpty() {
            return true;
        }

        public void clear() {
        }

        public boolean contains(Object obj) {
            return false;
        }

        public boolean containsAll(Collection<?> c) {
            return c.isEmpty();
        }

        public Object[] toArray() {
            return new Object[0];
        }

        public E get(int index) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        public boolean equals(Object o) {
            return (o instanceof List) && ((List<?>) o).isEmpty();
        }

        @Override
        public void sort(Comparator<? super E> c) {
        }
    }

    @SuppressWarnings("unchecked")
    public static <K,V> Map<K,V> emptyMap() {
        return (Map<K,V>) EMPTY_MAP;
    }

    private static class EmptyMap<K,V> extends AbstractMap<K,V> {
        public int size() {
            return 0;
        }

        public boolean isEmpty() {
            return true;
        }

        public void clear() {
        }

        public boolean containsKey(Object key) {
            return false;
        }

        public boolean containsValue(Object value) {
            return false;
        }

        public V get(Object key) {
            return null;
        }

        public Set<K> keySet() {
            return emptySet();
        }

        public Collection<V> values() {
            return emptySet();
        }

        public Set<Map.Entry<K,V>> entrySet() {
            return emptySet();
        }

        public boolean equals(Object o) {
            return (o instanceof Map) && ((Map<?,?>)o).isEmpty();
        }

        @Override
        public boolean remove(Object key, Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean replace(K key, V oldValue, V newValue) {
            throw new UnsupportedOperationException();
        }

        @Override
        public V replace(K key, V value) {
            throw new UnsupportedOperationException();
        }
    }
}
