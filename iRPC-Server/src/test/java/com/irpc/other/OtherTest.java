package com.irpc.other;

import com.irpc.api.calculator.Calculator;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class OtherTest {

    @Test
    public void test1() {
        Class cl = Integer.class;

        // It returns an array of Methods
        // that denotes the private, protected, public and default
        // Methods of the class Integer
        Method[] methods = cl.getMethods();
        List<Integer> list  = new ArrayList<Integer>();
        list.add(0);

        // Traverse Integer class
        for (int i = 0; i < 1; ++i) {
            Class declare_classes = methods[i].getDeclaringClass();
            // System.out.print(" Declaring Methods Class: ");
            System.out.println(declare_classes.toString());
            System.out.println(declare_classes.getName().toString());
        }
    }

    @Test
    public void AddTest() {
        Calculator calculator = new Calculator();
        System.out.println(calculator.add(232, 232));

        Calculator c2 = new Calculator();
        System.out.println(c2.add("abc", "234"));
    }
}
