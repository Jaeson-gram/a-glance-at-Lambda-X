package com.learningJava;

import java.util.ArrayList;
import java.util.List;

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


    }
}
