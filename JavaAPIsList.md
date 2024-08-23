*****************************************************************************************************************************
                                           java.lang.String
*****************************************************************************************************************************
 0.   public char[] toCharArray()
 1.   public String(char value[]) Allocates a new String so that it represents the sequence of characters currently contained in the character array argument// const
 2.   public String(StringBuilder builder) Allocates a new string that contains the sequence of characters currently contained in the string builder argument.// const
 3.   public int length()
 4.   public boolean isEmpty()
 5.   public char charAt(int index)
 6.   public boolean contentEquals(CharSequence cs)
 7.   public boolean equalsIgnoreCase(String anotherString)
 8.   public int compareTo(String anotherString)
 9.   public boolean startsWith(String prefix, int toffset) offset>=0  Tests if the substring of this string beginning at the specified index starts with the specified prefix
 10.  public boolean startsWith(String prefix) 
 11.  public boolean endsWith(String suffix)
 12.  public int indexOf(int ch)
 13.  public int lastIndexOf(int ch)
 14.  public int indexOf(String str)   Returns the index within this string of the first occurrence of the specified substring.
 15.  public int lastIndexOf(String str)
 16.  public String substring(int beginIndex)  0<=  beginIndex <= java.lang.Integer.MAX_VALUE
 17   public String substring(int beginIndex, int endIndex)  0<=   beginIndex/endIndex <= java.lang.Integer.MAX_VALUE
 18.  public CharSequence subSequence(int beginIndex, int endIndex)
 19.  public String concat(String str)
 20.  public String replace(char oldChar, char newChar)  Returns a string resulting from replacing all occurrences of oldChar in this string with newChar
 21.  public boolean contains(CharSequence s)
 22.  public boolean matches(String regex)
 23.  public String replaceAll(String regex, String replacement)
 24.  public String[] split(String regex)  "boo:and:foo".split(":") gives  { "boo", "and", "foo" }
 25.  public String replace(CharSequence target, CharSequence replacement)
 26.  public static String join(CharSequence delimiter, CharSequence... elements)
 27.  public static String join(CharSequence delimiter,Iterable<? extends CharSequence> elements) Returns a new String composed of copies of the CharSequence elements joined together with a copy of the specified delimiter.
         List<String> strings = List.of("Java", "is", "cool");  String message = String.join(" ", strings);  // message returned is: "Java is cool"    Set<String> strings =      new LinkedHashSet<>(List.of("Java", "is", "very", "cool"));  String message = String.join("-", strings);  // message returned is: "Java-is-very-cool"
 28.  public String toLowerCase()
 30.  public String toUpperCase()
 31.  public String trim()
 32.  public boolean isBlank()   Returns true if the string is empty or contains only white space codepoints, otherwise false.
 33.  public static String format(String format, Object... args)
      String.format("Missing mandatory field %s.", errorFields);
      String.format("current size %d is less than fixed size %d",curSize, array.length)
      String.format("Input value is not a multiple of referenceSize :  %d. Size: %d", referenceSize, inputSize))
 34.  public static String valueOf(char data[])
      public static String valueOf(Object obj)  Returns the string representation of the Object argument  or "null"
      public static String valueOf(boolean b)
      public static String valueOf(char c) 
      public static String valueOf(int i)
      public static String valueOf(long l)
      public static String valueOf(float f)
      public static String valueOf(double d)
      
 35.  public native String intern(); Returns a canonical representation for the string object. A pool of strings, initially empty, is maintained privately by the class String.
 12.  public int hashCode()
 13. public int indexOf(int ch)

*****************************************************************************************************************************
                                           java.util.Arrays 
*****************************************************************************************************************************


 1.  public static void sort(Object[] a)  Sorts the specified array of objects into ascending order, according to the natural ordering of its elements. All elements in the array must 
       implement the Comparable interface.
 2.  public static void sort(int[] a)
 3.  public static void sort(long[] a)
 4.  public static void sort(short[] a)
 5.  public static void sort(char[] a)
 6.  public static void sort(float[] a)
 7.  public static void sort(double[] a)
    
 9.  public static void parallelSort(short[] a)
 10. public static void parallelSort(int[] a)
 11. public static void parallelSort(long[] a)
 12. public static void parallelSort(float[] a)
     
 14. public static int binarySearch(long[] a, long key)
 15. public static boolean equals(int[] a, int[] a2)
 16. public static boolean equals(short[] a, short a2[]) 
 17. public static boolean equals(char[] a, char[] a2)
 18. public static boolean equals(boolean[] a, boolean[] a2)
 19. public static boolean equals(double[] a, double[] a2)
 20. public static boolean equals(float[] a, float[] a2)
     
 22. public static void fill(long[] a, long val)  Assigns the specified long value to each element of the specified array of longs.
 23. public static void fill(int[] a, int val)   Assigns the specified int value to each element of the specified array of ints.
 24. public static void fill(short[] a, short val)    Assigns the specified short value to each element of the specified array of shorts.

 25. public static int[] copyOf(int[] original, int newLength)
 26. public static <T> T[] copyOf(T[] original, int newLength)
    
 27. public static <T> List<T> asList(T... a)
 28. public static <T> Stream<T> stream(T[] array)
 29. public static IntStream stream(int[] array)
 30. public static LongStream stream(long[] array)
 31. public static DoubleStream stream(double[] array)


*****************************************************************************************************************************
                                           java.util.Collections
*****************************************************************************************************************************

   public static <T extends Comparable<? super T>> void sort(List<T> list)  orts the specified list into ascending order, according to the natural ordering of its elements. All 
   elements in the list must implement the Comparable interface
   public static <T> void sort(List<T> list, Comparator<? super T> c) 
   public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key)

   public static <T> boolean addAll(Collection<? super T> c, T... elements)
   public static <T> void fill(List<? super T> list, T obj)
   public static <T> void copy(List<? super T> dest, List<? extends T> src)
   public static <T extends Object & Comparable<? super T>> T min(Collection<? extends T> coll)
   public static <T> T min(Collection<? extends T> coll, Comparator<? super T> comp) 
   public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) 
   public static <T> T max(Collection<? extends T> coll, Comparator<? super T> comp)
   public static <T> boolean replaceAll(List<T> list, T oldVal, T newVal)

   public static <T> List<T> unmodifiableList(List<? extends T> list) 
   public static <T> Set<T> unmodifiableSet(Set<? extends T> s)
   public static <K,V> Map<K,V> unmodifiableMap(Map<? extends K, ? extends V> m)
   public static <K,V> SortedMap<K,V> unmodifiableSortedMap(SortedMap<K, ? extends V> m)
   public static <K,V> NavigableMap<K,V> unmodifiableNavigableMap(NavigableMap<K, ? extends V> m)
 
   public static <T> Collection<T> synchronizedCollection(Collection<T> c)
   public static <T> Set<T> synchronizedSet(Set<T> s)
   public static <T> List<T> synchronizedList(List<T> list) 
   public static <K,V> Map<K,V> synchronizedMap(Map<K,V> m)

   public static final <T> Set<T> emptySet()
   public static <E> SortedSet<E> emptySortedSet()
   public static final <T> List<T> emptyList()
   public static final <K,V> Map<K,V> emptyMap()


*****************************************************************************************************************************
                                           java.lang.System
*****************************************************************************************************************************

  public static native long currentTimeMillis()
  public static native long nanoTime();
  public static Properties getProperties()   keys  java.version,user.name,user.home,user.dir,file.separator,path.separator,line.separator
  public static String lineSeparator()
  public static void setProperties(Properties props)
  public static String setProperty(String key, String value)
  public static String getProperty(String key)
  public static String getProperty(String key, String default)

  public static String getenv(String name) Gets the value of the specified environment variable
  public static java.util.Map<String,String> getenv()
  public static void gc(){ Runtime.getRuntime().gc();}


*****************************************************************************************************************************
                                      interface  java.util.stream.Stream<T> extends BaseStream<T, Stream<T>>
*****************************************************************************************************************************

  Stream<T> filter(Predicate<? super T> predicate);

  <R> Stream<R> map(Function<? super T, ? extends R> mapper);

  IntStream mapToInt(ToIntFunction<? super T> mapper) Returns an IntStream consisting of the results of applying the given function to the elements of this stream
 
  LongStream mapToLong(ToLongFunction<? super T> mapper)

  DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper)

  <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)

  IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper);

  LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper)

  Stream<T> distinct(); Returns a stream consisting of the distinct elements (according to Object.equals(Object)) of this stream.

  Stream<T> sorted(); Returns a stream consisting of the elements of this stream, sorted according to natural order

  Stream<T> sorted(Comparator<? super T> comparator);

  Stream<T> peek(Consumer<? super T> action); Returns a stream consisting of the elements of this stream, additionally performing the provided action on each element as elements are 
     consumed from the resulting stream.

  Stream<T> limit(long maxSize); Returns a stream consisting of the elements of this stream, truncated to be no longer than maxSize in length.

  Stream<T> skip(long n); Returns a stream consisting of the remaining elements of this stream after discarding the first n elements of the stream

  void forEach(Consumer<? super T> action)

 Object[] toArray();

 reduce(T identity, BinaryOperator<T> accumulator);
       Integer sum = integers.reduce(0, (a, b) -> a+b);
        or:
       Integer sum = integers.reduce(0, Integer::sum);

  <R> R collect(Supplier<R> supplier,BiConsumer<R, ? super T> accumulator,BiConsumer<R, R> combiner);
     For example, the following will accumulate strings into an ArrayList:
     List<String> asList = stringStream.collect(ArrayList::new, ArrayList::add,ArrayList::addAll);
     The following will take a stream of strings and concatenates them into a single string:
     String concat = stringStream.collect(StringBuilder::new, StringBuilder::append,StringBuilder::append).toString();

   <R, A> R collect(Collector<? super T, A, R> collector);
       List<String> asList = stringStream.collect(Collectors.toList());
       Map<String, List<Person>> peopleByCity      = personStream.collect(Collectors.groupingBy(Person::getCity));
       The following will classify Person objects by state and city, cascading two Collectors together:
       Map<String, Map<String, List<Person>>> peopleByStateAndCity = personStream.collect(Collectors.groupingBy(Person::getState,Collectors.groupingBy(Person::getCity)));

  default List<T> toList()  Accumulates the elements of this stream into a unmodifiable List

  Optional<T> min(Comparator<? super T> comparator);
  Optional<T> max(Comparator<? super T> comparator);
  long count();
  boolean anyMatch(Predicate<? super T> predicate); Returns whether any elements of this stream match the provided predicate.
  boolean allMatch(Predicate<? super T> predicate); Returns whether all elements of this stream match the provided predicate.
  boolean noneMatch(Predicate<? super T> predicate); Returns whether no elements of this stream match the provided predicate.
  Optional<T> findFirst();
  Optional<T> findAny();
  public static<T> Stream<T> empty()  Returns an empty sequential Stream.
  public static<T> Stream<T> of(T t)  Returns a sequential Stream containing a single element.
  public static<T> Stream<T> of(T... values)  Returns a sequential ordered stream whose elements are the specified values.
  public static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b)


*****************************************************************************************************************************
                                      java.util.List
*****************************************************************************************************************************

    int size()
    boolean isEmpty()
    boolean contains(Object o)
    Iterator<E> iterator()
    Object[] toArray()
    <T> T[] toArray(T[] a)
    boolean add(E e)
    default void addLast(E e)
    default void addFirst(E e)
    void add(int index, E element);
    E get(int index)
    default E getFirst()
    default E getLast()
    int indexOf(Object o);
    int lastIndexOf(Object o);
    static <E> List<E> of()   Returns an unmodifiable list containing zero elements
    static <E> List<E> of(E e1)  Returns an unmodifiable list containing one element
    static <E> List<E> of(E... elements) Returns an unmodifiable list containing an arbitrary number of elements
    default E removeFirst()
    default E removeLast()
    boolean remove(Object o)  removes first occureence if exists else no change 
    boolean containsAll(Collection<?> c);
    boolean addAll(Collection<? extends E> c);   adds all elements of collection at end 
    boolean addAll(int index, Collection<? extends E> c); adds all elements of collection at specified index and shifts the elements of original list
    boolean removeAll(Collection<?> c);   Removes from this list all of its elements that are contained in the specified collection 
    boolean retainAll(Collection<?> c);   removes from this list all of its elements that are not contained in the specified collection.
    default void replaceAll(UnaryOperator<E> operator)  Replaces each element of this list with the result of applying the operator to that element.
    default void sort(Comparator<? super E> c)
    ListIterator<E> listIterator();
    E set(int index, E element);
    List<E> subList(int fromIndex, int toIndex); toIndex exclusive
    void clear();
    static <E> List<E> copyOf(Collection<? extends E> coll)  retruns an unmodifiable list containing elements of given collection
  

*****************************************************************************************************************************
                                      java.util.Iterator  interface 
*****************************************************************************************************************************

    boolean hasNext()
    E next();
    default void remove()
    default void forEachRemaining(Consumer<? super E> action)


*****************************************************************************************************************************
                                      java.lang.Iterable<T> 
*****************************************************************************************************************************

   Collection Interface extends Iterable  and that is why you can iterate over any collection using this pattern
     Collection<String> collection = ...; 
     for (String element: collection) {
      // do someting with element
     }


   Implementing this interface allows an object to be the target of the enhanced for statement (sometimes called the "for-each loop" statement)
   default void forEach(Consumer<? super T> action)
   Iterator<T> iterator();
   default Spliterator<T> spliterator()


*****************************************************************************************************************************
                                     interface java.util.ListIterator<E> extends Iterator<E>
*****************************************************************************************************************************


    boolean hasNext();
    E next();  NoSuchElementException if the iteration has no next element
    boolean hasPrevious();
    E previous();  NoSuchElementException if the iteration has no previous   element
    int nextIndex();
    int previousIndex();
    void add(E e);
    void remove();
    void set(E e); Replaces the last element returned by next or previous with the specified element


*****************************************************************************************************************************
                                      interface java.util.Set<E> extends Collection<E>
*****************************************************************************************************************************

    int size();
    boolean isEmpty();
    boolean contains(Object o);
    Iterator<E> iterator();
    Object[] toArray();
    <T> T[] toArray(T[] a);
    boolean add(E e);
    boolean remove(Object o);
    boolean containsAll(Collection<?> c);
    boolean addAll(Collection<? extends E> c);
    boolean retainAll(Collection<?> c);
    boolean removeAll(Collection<?> c);
    void clear();
    static <E> Set<E> of()   Returns an unmodifiable set containing zero elements.
    static <E> Set<E> of(E e1)   Returns an unmodifiable set containing one element.
    static <E> Set<E> of(E... elements)  Returns an unmodifiable set containing an arbitrary number of elements.
    static <E> Set<E> copyOf(Collection<? extends E> coll) Returns an unmodifiable Set containing the elements of the given Collection.


*****************************************************************************************************************************
                                      java.util.Map<K, V> 
*****************************************************************************************************************************

    inner interface  interface Entry<K, V>     K getKey();     V getValue();   V setValue(V value);
    int size(); 
    boolean isEmpty();
    boolean containsKey(Object key);
    boolean containsValue(Object value);
    V get(Object key);
    default V getOrDefault(Object key, V defaultValue)
    default V putIfAbsent(K key, V value)  If the specified key is not already associated with a value (or is mapped to null) associates it with the given value and returns null, else returns the current value.
    V put(K key, V value);  If the map previously contained a mapping for the key, the old value is replaced by the specified value and old value retruned or null
    V remove(Object key); Returns the value to which this map previously associated the key, or null if the map contained no mapping for the key.
    default boolean remove(Object key, Object value) 
    void putAll(Map<? extends K, ? extends V> m);
    default V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)  return get(K) if existing else return  mappingFunction.apply(key)
    void clear();
    Set<K> keySet();
    Collection<V> values();
    Set<Map.Entry<K, V>> entrySet();
    default void forEach(BiConsumer<? super K, ? super V> action)
    default V getOrDefault(Object key, V defaultValue)
    static <K, V> Map<K, V> of() Returns an unmodifiable map containing zero mappings.
    static <K, V> Map<K, V> of(K k1, V v1)   Returns an unmodifiable map containing a single mapping
    static <K, V> Entry<K, V> entry(K k, V v)  returns an Entry containing the specified key and value
    static <K, V> Map<K, V> ofEntries(Entry<? extends K, ? extends V>... entries)  Returns an unmodifiable map containing keys and values extracted from the given entries
    static <K, V> Map<K, V> copyOf(Map<? extends K, ? extends V> map)  Returns an unmodifiable Map containing the entries of the given Map 


   Map<String, Object> reverseTreeMap = new TreeMap<String, Object>(Collections.reverseOrder())


*****************************************************************************************************************************
                                    Functional Interfaces
*****************************************************************************************************************************

      java.util.function.Consumer<T>  void accept(T t);   
      default Consumer<T> andThen(Consumer<? super T> after)
      java.util.function.Function<<T, R> >       
      R apply(T t);    static <T> Function<T, T> identity() {  return t -> t; }















 An Enumeration can be converted into an Iterator by using the Enumeration.asIterator method.
