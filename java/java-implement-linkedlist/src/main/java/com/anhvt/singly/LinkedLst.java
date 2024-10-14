/**
 * Copyright 2024
 * Name: LinkedLst
 */
package com.anhvt.singly;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/14/2024
 */
public class LinkedLst {
    private Node head;
    private Node tail;
    private int size;

    public LinkedLst() {
        this.size = 0;
    }

    public void insert(int val, int index) {
        if (index == 0) {
            insertFirst(val);
            return;
        }
        if (index == size) {
            insertLast(val);
            return;
        }

        // Go to index
        Node temp = head;
        for (int i = 1; i < index; i++) {
            temp = temp.getNext();
        }

        // Create new now
        Node node = new Node(val, temp.getNext());
        temp.setNext(node);

        size++;
    }

    public void insertFirst(int val) {
        Node node = new Node(val);
        node.setNext(head);
        head = node;

        if (tail == null) {
            tail = head;
        }
        size++;
    }

    public void insertLast(int val) {
        if (tail == null) {
            insertFirst(val);
            return;
        }
        Node node = new Node(val);
        tail.setNext(node);
        tail = node;
        size++;
    }

    public int delete(int index) {
        if (index == 0) {
            return deleteFirst();
        }
        if (index == size - 1) {
            return deleteLast();
        }

        Node prev = get(index - 1);
        int val = prev.getNext().getValue();

        prev.setNext(prev.getNext().getNext());
        size--;
        return val;
    }

    public int deleteFirst() {
        int val = head.getValue();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        size--;
        return val;
    }

    public int deleteLast() {
        if (size <= 1) {
            return deleteFirst();
        }

        Node secondLast = get(size - 2);
        int val = tail.getValue();
        tail = secondLast;
        tail.setNext(null);
        size--;
        return val;
    }

    public Node find(int value) {
        Node node = head;
        while (node != null) {
            if (node.getValue() == value) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    public Node get(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }

    public void duplicates() {
        Node node = head;

        while (node.getNext() != null) {
            if (node.getValue() == node.getNext().getValue()) {
                node.setNext(node.getNext().getNext());
                size--;
            } else {
                node = node.getNext();
            }
        }
        tail = node;
        tail.setNext(null);
    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.getValue() + " -> ");
            temp = temp.getNext();
        }
        System.out.println("END");
    }

}
