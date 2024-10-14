/**
 * Copyright 2024
 * Name: Node
 */
package com.anhvt.circular;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/14/2024
 */
public class Node {
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
