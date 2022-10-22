package com.foxmo.springcloud;

public class LambdaDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println("Lambda Demo First1")).start();
            new Thread(() -> System.out.println("Lambda Demo First2")).start();
            new Thread(() -> System.out.println("Lambda Demo First3")).start();
        }

    }
}
