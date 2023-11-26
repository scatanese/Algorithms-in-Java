/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author salvo catanese
 */

public class ArrayStack<T> {
    protected int capacity;
    private T[] data;
    private int top;
    public static final int DEFAULT_SIZE=50;
    
    public ArrayStack() { 
        this(DEFAULT_SIZE); 
    }

    public ArrayStack(int cap) {
        capacity=cap;
        data = (T[]) new Object[capacity];  
        top = -1;
    }

    public int size() { 
        return (top + 1); 
    }
    
    public boolean isEmpty() { 
        return (top == -1); 
    }

    public void push(T e) throws IllegalStateException {
        if (size() == capacity)
            throw new IllegalStateException("Stack is full");
        data[++top] = e;
    }

    public T top() {
        if (isEmpty()) return null;
        return data[top];
    }
    
    public T pop() throws IllegalStateException {
        T element;
        if(isEmpty())
            throw new IllegalStateException("Stack is empty.");
        element = data[top];
        data[top--] = null; // Dereferences S[top] for garbage collector
        return element;
    }

    
    

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>(6);
        stack.push(15);
        stack.push(23);
       
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
