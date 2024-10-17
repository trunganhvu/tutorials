/**
 * Copyright 2024
 * Name: EmployeesTest
 */
package PrototypePattern.cloneable;

import java.util.List;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/17/2024
 */
public class EmployeesTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Employees eRoot = new Employees();
        eRoot.loadData();

        //Use the clone method to get the Employee object
        Employees eClone1 = (Employees) eRoot.clone();
        Employees eClone2 = (Employees) eRoot.clone();
        List<String> list1 = eClone1.getEmpList();
        list1.add("E");
        List<String> list2 = eClone2.getEmpList();
        list2.remove("A");

        System.out.println("eRoot List: " + eRoot.getEmpList());    // eRoot List: [A, B, C, D]
        System.out.println("eClone1 List: " + list1);               // eClone1 List: [A, B, C, D, E]
        System.out.println("eClone2 List: " + list2);               // eClone2 List: [B, C, D]
    }
}
