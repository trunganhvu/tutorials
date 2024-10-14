/**
 * Copyright 2024
 * Name: Node
 */
package com.anhvt.singly;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/14/2024
 */
public class Node {
    private int value;
    private Node next;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
