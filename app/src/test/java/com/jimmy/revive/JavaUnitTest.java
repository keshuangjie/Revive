package com.jimmy.revive;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jimmy on 2018/5/17.
 */
public class JavaUnitTest {

    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void hello_Test() {
        List<User> list = new ArrayList();
        User u1 = new User("123");
        list.add(u1);
        u1 = new User("456");
        System.out.println(u1.id);
        changeUserId(u1);
        System.out.println(u1.id);
        assertEquals(list.get(0).toString(), u1.toString());
    }

    public void changeUserId(User user) {
        final User u2 = user;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                u2.id = "789";
            }
        };
        runnable.run();
    }

    public static class User {
        String id;
        public User(String id) {
            this.id = id;
        }
    }

}
