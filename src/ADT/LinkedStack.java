/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author Salvo Catanese
 */
public class LinkedStack<T> {
    private Node<T> top;
    private Node<T> first;
    protected int size;

    /**
    * Helper Class for GenericLinkedStack.
    */
    private static class Node<T> {
        private T data;
        private Node<T> next = null;

        Node(T element) {
            data = element;
        }
    }
    
        /**
     * Initializes an empty stack.
     */
    public LinkedStack() {
        first = null;
        top = null;
    }
    
    public void push(T element) {
        Node<T> newItem = new Node<T>(element);

        if (top == null) {
            top = newItem;
        } else {
            // New Top
            newItem.next = top;
            top = newItem;
            size++;
        }
    }
     
    public T pop() {
        if (top == null) {
           throw new IllegalStateException("Stack is empty.");
        }

        T output = top.data;
        top = top.next;
        size--;
        return output;
    }
    
    public T peek() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty.");
        }
        return top.data;
    }
    
    public boolean isEmpty() {
        return top == null;
    }
    
    public int size() {
        return size;
    }
    
    
    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>(6);
        stack.push(15);
        stack.push(23);
       
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
