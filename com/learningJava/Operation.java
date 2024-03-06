package com.learningJava;

@FunctionalInterface // -> has one abstract method
public interface Operation <T>{

    T operate(T value1, T value2);
}
