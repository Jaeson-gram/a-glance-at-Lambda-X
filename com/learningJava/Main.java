package com.learningJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

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


        //FUNCTION INTERFACES

        //Unary Interface -> takes a single parameter, and returns a result of the same type
        list.replaceAll(s -> s.charAt(0) + " - " + s.toUpperCase());
        System.out.println("--------------------");
        list.forEach(s -> System.out.println(s));

        System.out.println("------------------");

        String[] emptyString = new String[10];
        System.out.println(Arrays.toString(emptyString));
        Arrays.fill(emptyString, "");
        System.out.println(Arrays.toString(emptyString));

        Arrays.setAll(emptyString, (i) -> "" + (i + 1) + ". "
        + switch (i) {
            case 0 -> "one";
            case 1 -> "two";
            case 2 -> "three";
            case 3 -> "four";
            default -> "...";
        });
        System.out.println(Arrays.toString(emptyString));


        //SUPPLIER -> Takes no argument but returns some instance of some type - T .
        // T get()
        //more like a factory method code, however, it doesn't have to be a new or distinct result returned.

        //Jesus Loves You :) He'll never leave you, till the end of the ages <3 let go. (you can look up what <3 means)

        //see randomlySelectedValues() below
        System.out.println("------------------");

        String[] names = {"Joshua", "Precious", "Ukela", "Patra"};
        String[] randomList = randomlySelectedValues(7, names, () -> new Random().nextInt(0, names.length));
        System.out.println(Arrays.toString(randomList));




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

    //Supplier Interface
    public static String[] randomlySelectedValues(int count, String[] values, Supplier<Integer> s){
        //the method returns an array of string with the same number of elements passed as the first argunent - count
        //it will use the second argument to get a randomly picked value from the array
        //it will use a Supplier interface to get an Integer to use as the index to pick the name

        String[] selectedValues = new String[count];
        for (int i = 0; i < count; i ++){ // -> this: for (int i = 0; i < count - 1; i ++).. always provided a null at the end
            selectedValues[i] = values[s.get()];
        }

        return selectedValues;
    }
}
