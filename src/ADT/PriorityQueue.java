/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import comparators.DefaultComparator;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author salvo
 */

public class PriorityQueue<T> {
    /**
     * The heap's data.
     */
    private ArrayList<T> heap;
    private boolean min;
    
    /** 
     * The comparator defining the ordering of keys in the priority queue. 
     */
    private Comparator<T> comp;
  
    /**
     * Default Constructor
     * 
     * Creates a new {@link PriorityQueue}.
     */
    public PriorityQueue() {
        heap = new ArrayList<>(0);
        comp = new DefaultComparator();
        min = true;
    }
    
    /**
     * Creates a new {@link PriorityQueue}.
     * 
     * @param min boolean true = min heap, false = max heap
     */
    public PriorityQueue(boolean min) {
        heap = new ArrayList<>(0);
        comp = new DefaultComparator();
        this.min = min;
    }
    
    /**
     * Creates an empty priority queue using the given comparator.
     * 
     * @param c comparator defining the order of keys in the priority queue
     */
    private PriorityQueue(Comparator<T> c) { 
        heap = new ArrayList<>(0);
        comp = c;
        this.min = true;
    }

    /**
     * Creates a new {@link PriorityQueue}.
     *
     * @param items An {@link ArrayList} to use as the backing array.
     * @param min boolean true = min heap, false = max heap
     */
    public PriorityQueue(ArrayList<T> items, boolean min) {
        heap = items;
        comp = new DefaultComparator();
        this.min = min;
        
        buildHeap();
    }

   /**
    * Creates a {@link PriorityQueue} from {@link ArrayList} of keys.
    *
    * @param items An {@link ArrayList} of keys.
    * @param c A {@link Comparator} that defining the order of keys in the priority queue
    * @param min boolean true = min heap, false = max heap
    */
    public PriorityQueue(ArrayList<T> items, Comparator c, boolean min) {
        heap = items;
        comp = c;
        this.min = min;
        
        buildHeap();
    }
    
  /**
   * Add a new item in the Priority Queue.
   * 
   * @param item the new entry
   */
    public void insert(T item) {
        int i = heap.size();
        heap.add(item);
        int parent = parent(i);
        System.out.println("[+] Insert: " + item);
        // bubbleup
        if (min){
            while (parent != -1 && comp.compare(heap.get(i),heap.get(parent)) < 0) {
                swap(i, parent);
                i = parent;
                parent = parent(i);
            }
        } else {
            while (parent != -1 && comp.compare(heap.get(i),heap.get(parent)) > 0) {
                swap(i, parent);
                i = parent;
                parent = parent(i);
            }
        }
    }

  /**
   * Removes the entry with minimal key from the priority queue.
   * 
   * @return the removed entry (or null if empty)
   */
    public T remove() {
        if (heap.isEmpty()) {
            return null;
        }
        if (heap.size() == 1) {
            return heap.remove(0);
        }
        T top = heap.get(0);
        System.out.println("[-] Remove: " + top);
        T last = heap.remove(heap.size() - 1);
        heap.set(0, last);
        heapify(0);
        return top;
    }

  /**
   * Returns (but not remove) the entry with minimal/maximal key.
   * 
   * @return entry having a minimal/maximal key or null if empty
   */
    public T top() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    public void clear() {
        heap.clear();
    }

    /**
     * Check if priority queue is empty.
     *
     * @return {@code true} if the priority queue is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
    /**
     * Check the size of prioriry queue.
     * 
     * @return the number of keys on this priority queue
     */
    public int size() {
        return heap.size();
    }


    private void buildHeap() {
        for (int i = (int)(heap.size() / 2); i >= 0; i--) {
            heapify(i);
        }
    }

    private void heapify(int i) {
        int l = left(i);
        int r = right(i);
        
        if (min) { // min heap
            int smallest = i;
            if (l < heap.size() && comp.compare(heap.get(l),heap.get(i)) < 0) {
                smallest = l;
            }
            if (r < heap.size() && comp.compare(heap.get(r),heap.get(smallest)) < 0) {
                smallest = r;
            }
            if (smallest != i) {
                swap(i, smallest);
                heapify(smallest);
            }
        }
        else // max heap
        {
            int larger = i;
            if (l < heap.size() && comp.compare(heap.get(l),heap.get(i)) > 0) {
                larger = l;
            }
            if (r < heap.size() && comp.compare(heap.get(r),heap.get(larger)) > 0) {
                larger = r;
            }
            if (larger != i) {
                swap(i, larger);
                heapify(larger);
            }
        }
    }

    private void swap(int i1, int i2) {
        T temp = heap.get(i1);
        heap.set(i1, heap.get(i2));
        heap.set(i2, temp);
    }

    private int parent(int i) {
        if (i == 0) {
            return -1;
        }
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }


    /**
     *  This method used to print all element of the heap
     */
    public void printArrayHeap() {
        if (heap.isEmpty()){
            System.out.println("Empty Priority Queue!");
        }
        else {
        System.out.println("### Array Heap ");
        System.out.print("| ");
        for (T item : heap) {
            System.out.print(item + " | ");
        }
        System.out.println("\n");
        }
    }

    /**
     * This method used to print all element of the heap by level.
     */
    public void printLevels() {
        if (heap.isEmpty()){
            System.out.println("Prioriti Queue Empty!");
        }
        else {
            StringBuilder strBinaryHeap = new StringBuilder();

            int start = 0;
            int levelSize = 1;
            int level = 1;
            strBinaryHeap.append("### Priority Queue\n");
            while (start < heap.size()) {
                // append all items at the current level
                strBinaryHeap.append("Level " + level + ": ");
                for (int i = start; i < start + levelSize && i < heap.size(); i++)
                    strBinaryHeap.append(heap.get(i) + " ");
                strBinaryHeap.append("\n"); // end level

                // next level
                start += levelSize;
                levelSize *= 2;
                level++;
            }
                
            System.out.println(strBinaryHeap);
        }
    }

    
     public static void main(String[] args) {
        
        PriorityQueue<Integer> heap = new PriorityQueue<>(false);
        heap.printArrayHeap();
        heap.insert(4);
        heap.printArrayHeap();
        heap.insert(1);
        heap.printArrayHeap();
        heap.insert(8);
        heap.printArrayHeap();

        heap.insert(6);
        heap.insert(9);
        heap.insert(5);
        heap.insert(10);
        heap.insert(2);
        heap.insert(3);
        heap.insert(7);

        heap.printArrayHeap();
        
        heap.printLevels();
        
        for (int i = 0; i <heap.size(); i++) {
            heap.remove();
            heap.printLevels();
        }
        
       /* ArrayList a= new ArrayList();
        a.add(22);
        a.add(2);
        a.add(32);
        a.add(1);
        PriorityQueue heap2 = new PriorityQueue(a);
        heap2.print();*/
    } 
}
