java.util.stream.Collectors Implementations of Collector that implement various useful reduction operations, such as accumulating elements into collections, summarizing elements according to various criteria, etc

    // Accumulate names into a List  List<String> list = people.stream().map(Person::getName).collect(Collectors.toList());
    // Accumulate names into a TreeSet  Set<String> set = people.stream().map(Person::getName).collect(Collectors.toCollection(TreeSet::new));  
    // Convert elements to strings and concatenate them, separated by commas  
       String joined = things.stream().map(Object::toString).collect(Collectors.joining(", "));  
       String joined = things.stream().map(Object::toString).collect(Collectors.joining(", ","",""));  
    // Compute sum of salaries of employee  int total = employees.stream().collect(Collectors.summingInt(Employee::getSalary));  
   // Group employees by department  Map<Department, List<Employee>> byDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));  
   // Compute sum of salaries by department  Map<Department, Integer> totalByDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.summingInt(Employee::getSalary)));
   // Partition students into passing and failing  Map<Boolean, List<Student>> passingFailing = students.stream().collect(Collectors.partitioningBy(s -> s.getGrade() >= PASS_THRESHOLD));



   Adapts a Collector accepting elements of type U to one accepting elements of type T by applying a mapping function to each input element before accumulation.
      public static <T, U, A, R>   Collector<T, ?, R> mapping(Function<? super T, ? extends U> mapper,
                                    Collector<? super U, A, R> downstream)     mapping(function , toCollection())
   to compute the set of last names of people in each city:
   Map<City, Set<String>> lastNamesByCity    = people.stream().collect(groupingBy(Person::getCity,
                                                                                  mapping(Person::getLastName, toSet())));


The flatMapping() collectors are most useful when used in a multi-level reduction, such as downstream of a groupingBy or partitioningBy. 
  For example, given a stream of Order, to accumulate the set of line items for each customer:
 Map<String, Set<LineItem>> itemsByCustomerName    = orders.stream().collect(groupingBy(Order::getCustomerName, 
                                                                                             flatMapping(order -> order.getLineItems().stream(),toSet())));

given a stream of Employee, to accumulate the employees in each department that have a salary above a certain threshold:
 Map<Department, Set<Employee>> wellPaidEmployeesByDepartment    = employees.stream().collect(groupingBy(Employee::getDepartment,         
                                                                                                          filtering(e -> e.getSalary() > 2000,toSet())));

 For example, one could adapt the toList() collector to always produce an immutable list with:
 List<String> list = people.stream().collect(collectingAndThen(toList(),Collections::unmodifiableList));

 Comparator<String> byLength = Comparator.comparing(String::length);  Map<City, String> longestLastNameByCity    = people.stream().collect(groupingBy(Person::getCity,
                                                                                                                                                      reducing("",Person::getLastName,BinaryOperator.maxBy(byLength))));

The following produces a Map mapping students to their grade point average:
 Map<Student, Double> studentToGPA    = students.stream().collect(toMap(Function.identity(),student -> computeGPA(student)));

And the following produces a Map mapping a unique identifier to students:
 Map<String, Student> studentIdToStudent    = students.stream().collect(toMap(Student::getId,Function.identity()));


Collector<T, ?, Map<K,U>> toMap(Function<? super T, ? extends K> keyMapper,
                                    Function<? super T, ? extends U> valueMapper,
                                    BinaryOperator<U> mergeFunction) 
  For example, if you have a stream of Person, and you want to produce a "phone book" mapping name to address, 
  but it is possible that two persons have the same name, you can do as follows to gracefully deal with these collisions, and produce a Map mapping names to a concatenated list of addresses:
 Map<String, String> phoneBook    = people.stream().collect(toMap(Person::getName,Person::getAddress,(s, a) -> s + ", " + a));

The following produces a ConcurrentMap mapping students to their grade point average:
 ConcurrentMap<Student, Double> studentToGPA    = students.stream().collect(toConcurrentMap(Function.identity(),student -> computeGPA(student)));
And the following produces a ConcurrentMap mapping a unique identifier to students:
 ConcurrentMap<String, Student> studentIdToStudent    = students.stream().collect(      toConcurrentMap(Student::getId,Function.identity()))



1. Find out the count of male and female employees present in the organization?

   public static void getCountOfMaleFemale(List<Employee> employeeList) {
         Map<String, Long> noOfMaleAndFemaleEmployees=
         employeeList.stream()
                     .collect(Collectors.groupingBy
                     (Employee::getGender, Collectors.counting()));    
         System.out.println(noOfMaleAndFemaleEmployees);
     }

   Output: {Male=11, Female=6}
2.  Write a program to print the names of all departments in the organization.


 public static void getDepartmentName(List<Employee> employeeList){
         employeeList.stream()
         .map(Employee::getDepartment)
         .distinct()
         .forEach(System.out::println);
     }


 Output: 
 HR
 Sales And Marketing
 Infrastructure
 Product Development
 Security And Transport
 Account And Finance

3. Find the average age of Male and Female Employees.

   public static void getGender(List<Employee> employeeList) {
    Map<String, Double> avgAge = employeeList.stream()
                                 .collect(Collectors.groupingBy
                                 (Employee::getGender, 
                                 Collectors.averagingInt
                                 (Employee::getAge)));
                 System.out.println(avgAge);
     }

 Output: {Male=30.181818181818183, Female=27.166666666666668}


4.Get the Names of employees who joined after 2015.

  public static void getNameOfEmp(List<Employee> employeeList) {
         employeeList.stream()
         .filter(e -> e.getYearOfJoining() > 2015)
         .map(Employee::getName)
         .forEach(System.out::println);
     }

 Output: 
 Iqbal Hussain
 Amelia Zoe
 Nitin Joshi
 Nicolus Den
 Ali Baig



5.Count the number of employees in each department.

   public static void countByDept(List<Employee> employeeList) {
         Map<String, Long> countByDept = employeeList.stream()
                                         .collect(Collectors.groupingBy
                                         (Employee::getDepartment, 
                                         Collectors.counting()));
         Set<Entry<String, Long>> entrySet = countByDept.entrySet();
         for (Entry<String, Long> entry : entrySet)
         {
             System.out.println(entry.getKey()+" : "+entry.getValue());
         }
     }

 Output:
 Product Development : 5
 Security And Transport : 2
 Sales And Marketing : 3
 Infrastructure : 3
 HR : 2
 Account And Finance : 2


6.Find out the average salary of each department.

   public static void avgSalary(List<Employee> employeeList) {
         Map<String, Double> avgSalary = employeeList.stream()
                                         .collect(Collectors.groupingBy
                                                 (Employee::getDepartment, 
                                                 Collectors.averagingDouble(Employee::getSalary)));
         Set<Entry<String, Double>> entrySet = avgSalary.entrySet();
         for (Entry<String, Double> entry : entrySet) 
         {
             System.out.println(entry.getKey()+" : "+entry.getValue());
         }
     }

 Output:
 Product Development : 31960.0
 Security And Transport : 10750.25
 Sales And Marketing : 11900.166666666666
 Infrastructure : 15466.666666666666
 HR : 23850.0
 Account And Finance : 24150.0


7. Find out the oldest employee, his/her age and department?

  public static void oldestEmp(List<Employee> employeeList) {
         Optional<Employee> oldestEmp = employeeList.stream()
                                             .max(Comparator
                                                     .comparingInt(Employee::getAge));        Employee oldestEmployee = oldestEmp .get();

         System.out.println("Name : "+oldestEmployee.getName());    
         System.out.println("Age : "+oldestEmployee.getAge());     
         System.out.println("Department : "+oldestEmployee.getDepartment());
     }

 Output:
 Name : Iqbal Hussain
 Age : 43
 Department : Security And Transport


8. Find out the average and total salary of the organization.

   public static void getEmpSalary(List<Employee> employeeList) {
         DoubleSummaryStatistics empSalary = employeeList.stream()
                                                 .collect(Collectors
                                                         .summarizingDouble(Employee::getSalary));

         System.out.println("Average Salary = "+empSalary.getAverage());
         System.out.println("Total Salary = "+empSalary.getSum());
     }

 Output:
 Average Salary = 21141.235294117647
 Total Salary = 359401.0
