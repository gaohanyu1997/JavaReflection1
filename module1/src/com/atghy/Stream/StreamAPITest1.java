package com.atghy.Stream;

import com.atghy.lambda2.Employee;
import com.atghy.lambda2.EmployeeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
*   测试Stream的中间操作
* */
public class StreamAPITest1 {
    //1 筛选与切片
    @Test
    public void test1(){
        List<Employee> list = EmployeeData.getEmployees();
        //filter(Predicate p)：接收Lambda,从流中排除某些元素
        Stream<Employee> stream = list.stream();
        //练习：查询员工表中薪资大于9000的员工信息
        stream.filter(e -> e.getSalary() > 9000).forEach(System.out::println);
        System.out.println("*******");
        //limit(long maxSize)：截断流,使其元素不超过给定数量
        list.stream().limit(1).forEach(System.out::println);
        System.out.println("*******");
        //skip(long n)：跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个,则返回一个空流，与limit(n)互补
        list.stream().skip(3).forEach(System.out::println);
        System.out.println("*******");
        //distinct()：筛选,通过流所生成元素的hashCode()和equals()去除重复元素
        list.add(new Employee(1001,"马化腾",34,6000.38));
        list.add(new Employee(1001,"马化腾",34,6000.38));
        System.out.println(list);
        System.out.println("*******");
        list.stream().distinct().forEach(System.out::println);
    }
    //2 映射
    @Test
    public void test2(){
        //map(Function f)：接收一个函数作为参数,将元素转换成其他形式或提取信息
        //该函数会被应用到每个元素上,并将其映射成一个新的元素
        List<String> list = Arrays.asList("aa","bb","cc","dd");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);
        System.out.println("****");
        //练习：获取员工姓名长度大于3的员工的姓名
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<String> nameStream = employees.stream().map(Employee::getName);
        nameStream.filter(name -> name.length()>3).forEach(System.out::println);
        System.out.println("****");
        //练习2：
        Stream<Stream<Character>> streamStream = list.stream().map(StreamAPITest1::fromStringTomStream);
        streamStream.forEach(s ->{
            s.forEach(System.out::println);
        });
        System.out.println("****");
        //flatMap(Function f)：接收一个函数作为参数,将流中的每个值都换成另一个流，然后把所有流连接成一个流
        Stream<Character> characterStream = list.stream().flatMap(StreamAPITest1::fromStringTomStream);
        characterStream.forEach(System.out::println);

    }
    //将字符串中的多个字符构成的集合转换为对应的Stream的实例
    public static Stream<Character> fromStringTomStream(String str){
        ArrayList<Character> list = new ArrayList<>();
        for(Character c:str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }
    //3 排序
    @Test
    public void test3(){
        //sorted()：产生一个新流,其中按自然顺序排序
        /*List<Integer> integers = Arrays.asList(12,-10, 32, 25, 87, 18, 45);
        integers.stream().sorted().forEach(System.out::println);*/
        //抛异常，原因：Employee没有实现Compartor接口
        //List<Employee> list = EmployeeData.getEmployees();
        //list.stream().sorted().forEach(System.out::println);

        //sorted(Compartor com)：产生一个新流,其中按比较器顺序排序
        List<Employee> list = EmployeeData.getEmployees();
        list.stream().sorted((e1,e2)-> {
            int ageValue = Integer.compare(e1.getAge(),e2.getAge());
            if(ageValue != 0){
                return ageValue;
            }else{
                return Double.compare(e1.getSalary(),e2.getSalary());
            }
        }).forEach(System.out::println);

    }
}
