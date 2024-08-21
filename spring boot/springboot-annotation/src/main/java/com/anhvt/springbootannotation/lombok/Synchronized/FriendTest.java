/**
 * Copyright 2024
 * Name: FriendTest
 */
package com.anhvt.springbootannotation.lombok.Synchronized;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/11/2024
 */
public class FriendTest {
    public static void main(String[] args) {
        Friend friend = new Friend();
        friend.sayHello();      // Hello!
        friend.getOne();        // One
        friend.getTwo();        // Two
        friend.printObject();   // object friend
    }
}
