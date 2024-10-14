package com.anhvt;

import com.anhvt.circular.CircularLinkedLst;
import com.anhvt.doubly.DoubleLinkedLst;
import com.anhvt.singly.LinkedLst;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/14/2024
 */
public class Main {
    public static void main(String[] args) {

        LinkedLst first = new LinkedLst();

        first.insertLast(3);
        first.insertLast(1);
        first.insertLast(5);
        first.delete(2);
        first.display();


        DoubleLinkedLst list = new DoubleLinkedLst();
        list.insertFirst(3);
        list.insertFirst(2);
        list.insertFirst(8);
        list.insertFirst(17);
        list.insertLast(99);
        list.insert(8, 65);
        list.delete(8);
        list.display();


        CircularLinkedLst circularLinkedLst = new CircularLinkedLst();
        circularLinkedLst.insert(23);
        circularLinkedLst.insert(3);
        circularLinkedLst.insert(19);
        circularLinkedLst.insert(75);
        circularLinkedLst.display();
        circularLinkedLst.delete(19);
        circularLinkedLst.display();
        System.out.println(circularLinkedLst.find(30).getVal());
    }
}