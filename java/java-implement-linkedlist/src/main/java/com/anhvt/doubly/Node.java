/**
 * Copyright 2024
 * Name: Node
 */
package com.anhvt.doubly;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/14/2024
 */
public class Node {
    private int val;
    private Node next;
    private Node prev;

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node next, Node prev) {
        this.val = val;
        this.next = next;
        this.prev = prev;
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

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }
}
