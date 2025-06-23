import java.util.*;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EvenOdd {
    public static void main(String[] args){

        String[] xyz={"hello","world"};

        List<String> uniqueCharacters = Arrays.stream(xyz).map(w -> w.split(""))
                .flatMap(Arrays::stream).distinct().collect(Collectors.toList());

        System.out.println("Unique Characters" +uniqueCharacters);

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

        Map<Boolean,List<Integer>> res = list.stream().collect(Collectors.partitioningBy(n -> n%2 == 0));

        List<List<Integer>> result = res.values().stream().collect(Collectors.toList());

        System.out.println("Even odd partition : "+ result);

        List<Integer> evenNumbers = list.stream().filter( s -> s%2 == 0).collect(Collectors.toList());

        System.out.println("Even numbers : " + evenNumbers);

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "Computer");
        map.put(2, "Mouse");
        map.put(8, "Computer");
        map.put(4, "Trackpad");
        map.put(6, "Mouse");
        map.put(5, "Computer");

        List<Integer> SortingKeys = map.entrySet().stream().filter( v -> v.getValue().equalsIgnoreCase("Computer"))
                        .map(k -> k.getKey()).sorted().collect(Collectors.toList());

        System.out.println("Sorting keys " + SortingKeys);

        Employee e1=new Employee("1","Ram","Sales",70000);
        Employee e2=new Employee("2","Raj","BA",50000);
        Employee e3=new Employee("3","Ravi","Dev",100000);
        Employee e4=new Employee("4","Rakesh","BA",60000);
        Employee e5=new Employee("5","Rajesh","Test",80000);
        Employee e6=new Employee("6","Ramesh","Sales",90000);


        List<Employee> EmployeeList=Arrays.asList(e1,e2,e3,e4,e5,e6);

        List<Employee> sortSalaries = EmployeeList.stream()
                .sorted(Comparator.comparingInt(Employee :: getSalary).reversed()).collect(Collectors.toList());

        System.out.println("Employee salaries sorting : "+ sortSalaries);

        Map<String,Double> AvgSalDept = EmployeeList.stream().
                collect(Collectors.groupingBy(Employee::getDesignation
                ,Collectors.averagingDouble(Employee::getSalary)));

        System.out.println("Average salary of an employee "+AvgSalDept);

        Collections.sort(EmployeeList);

        Collections.sort(EmployeeList, new EmployeeComparator());

        System.out.println("Comparator of Employees "+EmployeeList);

        Map<String,Optional<Employee>> highsalary = EmployeeList.stream()
                .collect(Collectors.groupingBy(Employee :: getDesignation ,
                Collectors.maxBy(Comparator.comparingInt(Employee :: getSalary))
                ));

        for(Map.Entry<String,Optional<Employee>> entry : highsalary.entrySet()){
            System.out.println(entry.getKey() +" => "+ entry.getValue());
        }



        List<String> stringList = Arrays.asList("sravani", "Swetha", "sai", "Sanket");

        List<String> lengthFour = stringList.stream().filter( s -> s.length() > 4 ).collect(Collectors.toList());

        System.out.println("String length > 4 " + lengthFour);

        Optional<String> BigString = stringList.stream().max(Comparator.comparingInt(String :: length));
        BigString.ifPresent(System.out::println);

        
        String Combined = stringList.stream().collect(Collectors.joining(",")).toString();

        System.out.println("Combine strings "+ Combined);

        String ReduceString = stringList.stream().collect(Collectors.reducing((s1,s2) -> s1+s2)).orElse(" ");
        System.out.println("Reducing operation " +ReduceString );

        List<Integer> myList = Arrays.asList(10,15,8,49,25,98,98,32,15);
        Map<Integer,Long> Duplicates = myList.stream().
                collect(Collectors.groupingBy( i -> i , LinkedHashMap::new , Collectors.counting()));

        List<Integer> DuplicateList = Duplicates.entrySet().stream()
                .filter(v -> v.getValue() > 1).map(k -> k.getKey()).collect(Collectors.toList());

        System.out.println("Duplicate Inetegers "+DuplicateList);

        List<Integer> SumList = Arrays.asList(10,15,8,49,25,98,98,32,15);

        Optional<Integer> Summlist = SumList.stream().collect(Collectors.reducing(Integer :: sum));

        Summlist.ifPresent(System.out::println);

        Optional<Integer> MaxValue = SumList.stream().collect(Collectors.maxBy(Comparator.comparingInt(i -> i)));
        int max=SumList.stream().max(Integer :: compare).get();
        System.out.println("Max value is " + max);
        MaxValue.ifPresent(System.out::println);

        String s="ravanis";

        Map<String,Long> StringCount=Arrays.stream(s.split(""))
                .collect(Collectors.groupingBy( i -> i , LinkedHashMap :: new , Collectors.counting()));

        String NonRepeatedcharacter= StringCount.entrySet().stream()
                .filter(v -> v.getValue()==1).map(k -> k.getKey()).findFirst().orElse("Not found");

        System.out.println("Non Repeated letter in String "+ NonRepeatedcharacter);

        List<Integer> SortedList = myList.stream().sorted().collect(Collectors.toList());

        System.out.println("Sorted list "+SortedList);

        List<Integer> SortedDescendingOrder = myList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        System.out.println("Sorted in Descending order is " + SortedDescendingOrder);

        //Group list of string based on length where key is lengthofstring and value is list of strings
        List<String> GroupBylength = Arrays.asList("sravani", "Swetha", "sai", "Sanket" , "ram");
        Map<Integer,List<String>> Grouping = GroupBylength.stream()
                .collect(Collectors.groupingBy(String :: length ,
                        Collectors.toList()));

        for(Map.Entry<Integer,List<String>> Entry :  Grouping.entrySet()){
            System.out.println(Entry.getKey() +" "+ Entry.getValue());
        }

        Double AverageSal = EmployeeList.stream().collect(Collectors.summarizingInt(Employee :: getSalary)).getAverage();

        System.out.println("Average sum of salary "+AverageSal);

        List al=Arrays.asList("apple","banana","apple","apple","carrot","carrot");

        Map<String,Long> uniqRes = (Map<String, Long>) al.stream()
                .collect(Collectors.groupingBy(w -> w , Collectors.counting()));

        List<String> all=uniqRes.entrySet().stream()
                .sorted(Map.Entry.<String,Long>comparingByValue().reversed())
                .limit(1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("unique elemeent in list "+all);






    }
}
