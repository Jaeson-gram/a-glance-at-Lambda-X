package com.learningJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;

public class Main {

    public static void main(String[] args) {
	// write your code here

        final String nato = "nato";// must be final or effective final

        List<String> list = new ArrayList<>(List.of(
                "alpha", "bravo", "charlie", "delta"
        ));

        for (String s : list){
            System.out.println(s);
        }

        System.out.println("-----------");
        list.forEach((s) -> System.out.println(s)); // -> list.forEach(System.out::println);

        System.out.println("-----------------");
        list.forEach((s) -> {
            String first = s.substring(0,1);
            System.out.println(nato + " " + s + " means " + first.toUpperCase());

            //Also, even though we cannot use the variables in the Lambda X's code blocks outside the scope of that Lambda X,
            // we also are not allowed to use variable names outside the Lambda X's code blocks as passed parameters in the Lambda X
            // list.forEach(nato) -> { ..... will not compile

        });
        System.out.println();
        int result = calculator((Integer a, Integer b) -> a + b, 16, 7);
        // Lambda Expression can be replaced with method reference -> int result = calculator(Integer::sum, 16, 7);

        // int result = calculator((var a, Integer b) -> a + b, 16, 7); -> Cannot mix 'var' and explicitly typed parameters in lambda expression

        var result2 = calculator(( a, b) -> a / b, 16.0, 7.0);
        var result3 = calculator((a, b) -> a.toUpperCase() + " " + b.toUpperCase(), "Ayomide", "OreOluwa");


//        var result4 = calculator(( a, b) -> { var c = a / b;
//            return c;
//        }, 16.0, 7.0);
        //            -> return statements only used in codeblocks, and when the method has a return type defined for it, you've got to have that return statement in your code-block

        System.out.println("-----------------");
        int anotherResult = calculator2((Integer a, Integer b) -> a + b, 16, 7);
        var anotherResult2 = calculator2(( a, b) -> a / b, 16.0, 7.0);
        var anotherResult3 = calculator2((a, b) -> a.toUpperCase() + " " + b.toUpperCase(), "Ayomide", "OreOluwa");

        //CONSUMER INTERFACES
        System.out.println();
        var coords = Arrays.asList(
                new double[] {47.8922, -91.2384},
                new double[] {58.8922, -91.2084},
                new double[] {52.8922, -89.2384}
        );

        coords.forEach(s -> System.out.println(Arrays.toString(s)));

        BiConsumer<Double, Double> p1 = (lat, lon) -> System.out.printf("[lat:%.3f lon:%.3f]%n", lat, lon); // doesn't return anything - Consumer

//        var firstPoint = coords.get(0);
//        coords.forEach((s) -> processPoint(firstPoint[0], firstPoint[1], p1));

        System.out.println();
        coords.forEach(s -> processPoint(s[0], s[1], p1));

        //OR

//        System.out.println();
//        coords.forEach(s -> processPoint(s[0], s[1], (lat, lon) -> System.out.printf("[lat:%.3f lon:%.3f]%n", lat, lon)));


        //PREDICATE INTERFACE -> takes argument(s), tests, and returns a boolean
        list.removeIf(s -> s.equalsIgnoreCase("bravo"));
        list.forEach(s -> System.out.println(s));

        System.out.println();

        list.addAll(List.of("easy", "echoing", "errands"));
        list.forEach(s -> System.out.println(s));

        System.out.println();

        list.removeIf(s -> s.contains("er")); //-> removes (er)rands
        list.forEach(s -> System.out.println(s));

        







    }

    public static <T> T calculator(Operation<T> function, T value1, T value2){

        T result = function.operate(value1,value2);
        System.out.println("Result of operation: " + result);

        return result;
    }

    public static <T> T calculator2(BinaryOperator<T> function, T value1, T value2){

        T result = function.apply(value1,value2);
        System.out.println("Result of operation: " + result);

        return result;
    }

    //BiConsumer -> doesn't return a value, or it's return value can be ignored
    public static <T> void processPoint(T t1, T t2, BiConsumer<T, T> consumer){

        consumer.accept(t1, t2);
    }
}
