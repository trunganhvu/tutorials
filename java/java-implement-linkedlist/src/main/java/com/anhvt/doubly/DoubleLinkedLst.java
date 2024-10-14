/**
 * Copyright 2024
 * Name: DoubleLinkedLst
 */
package com.anhvt.doubly;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/14/2024
 */
public class DoubleLinkedLst {
    private Node head;

    public void display() {
        Node node = head;
        Node last = null;
        while (node != null) {
            System.out.print(node.getVal() + " -> ");
            last = node;
            node = node.getNext();
        }
        System.out.println("END");

        System.out.println("Print in reverse");
        while (last != null) {
            System.out.print(last.getVal() + " -> ");
            last = last.getPrev();
        }
        System.out.println("START");
    }

    public void insert(int after, int val) {
        Node p = find(after);

        if (p == null) {
            System.out.println("does not exist");
            return;
        }

        Node node = new Node(val);
        node.setNext(p.getNext());
        p.setNext(node);
        node.setPrev(p);
        if (node.getNext() != null) {
            node.getNext().setPrev(node);
        }
    }

    public void insertFirst(int val) {
        Node node = new Node(val);
        node.setNext(head);
        node.setPrev(null);
        if (head != null) {
            head.setPrev(node);
        }
        head = node;
    }

    public void insertLast(int val) {
        Node node = new Node(val);
        Node last = head;

        node.setNext(null);

        if (head == null) {
            node.setPrev(null);
            head = node;
            return;
        }

        while (last.getNext() != null) {
            last = last.getNext();
        }

        last.setNext(node);
        node.setPrev(last);
    }

    public void delete(int value) {
        Node node = head;
        while (node.getNext() != null) {
            if (node.getNext().getVal() == value) {
                node.setNext(node.getNext().getNext());
            }
            node = node.getNext();
        }
    }

    public Node find(int value) {
        Node node = head;
        while (node != null) {
            if (node.getVal() == value) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }


}
