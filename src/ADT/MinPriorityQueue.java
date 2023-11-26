package ADT;

import java.util.ArrayList;
import comparators.DefaultComparator;
import java.util.Comparator;

public class MinPriorityQueue<T>  {
    /**
     * The heap's data.
     */
    private ArrayList<T> heap;

    /** 
     * The comparator defining the ordering of keys in the priority queue. 
     */
    private Comparator<T> comp;
  
    /**
     * Creates a new {@link PriorityQueue}.
     */
    public MinPriorityQueue() {
        heap = new ArrayList<>(0);
        comp = new DefaultComparator();
    }
    
    /**
     * Creates an empty priority queue using the given comparator.
     * 
     * @param c comparator defining the order of keys in the priority queue
     */
    private MinPriorityQueue(Comparator<T> c) { 
        heap = new ArrayList<>(0);
        comp = c;
    }

    /**
     * Creates a new {@link PriorityQueue}.
     *
     * @param items An {@link ArrayList} to use as the backing array.
     */
    public MinPriorityQueue(ArrayList<T> items) {
        heap = items;
        comp = new DefaultComparator();
        buildHeap();
    }

   /**
    * Creates a {@link PriorityQueue} from {@link ArrayList} of keys.
    *
    * @param items An {@link ArrayList} of keys.
    * @param c A {@link Comparator} that defining the order of keys in the priority queue
    */
    public MinPriorityQueue(ArrayList<T> items, Comparator c) {
        heap = items;
        comp = c;
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
        while (parent != -1 && comp.compare(heap.get(i),heap.get(parent)) < 0) {
            swap(i, parent);
            i = parent;
            parent = parent(i);
        }
    }

  /**
   * Removes the entry with minimal key from the priority queue.
   * 
   * @return the removed entry (or null if empty)
   */
    public T extractMin() {
        if (heap.isEmpty()) {
            return null;
        }
        if (heap.size() == 1) {
            return heap.remove(0);
        }
        T min = heap.get(0);
        System.out.println("Extract Min: " + min);
        T last = heap.remove(heap.size() - 1);
        heap.set(0, last);
        heapify(0);
        return min;
    }

  /**
   * Returns (but not remove) the entry with minimal key.
   * 
   * @return entry having a minimal key or null if empty
   */
    public T min() {
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

    /**
     *  This method used to print all element of the heap
     */
    public void printHeapArray() {
        for (T item : heap) {
            System.out.print(item + ", ");
        }
        System.out.println();
    }

    /**
     * This method used to print all element of the heap by level.
     */
    public void printHeapLevels() {
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


    private void buildHeap() {
        for (int i = (int)(heap.size() / 2); i >= 0; i--) {
            heapify(i);
        }
    }

    private void heapify(int i) {
        int l = left(i);
        int r = right(i);
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

    
     public static void main(String[] args) {
        MinPriorityQueue<Integer> heap = new MinPriorityQueue<>();
        heap.insert(4);
        heap.insert(1);
        heap.insert(8);
        heap.insert(6);
        heap.insert(9);
        heap.insert(5);
        heap.insert(10);
        heap.insert(2);
        heap.insert(3);
        heap.insert(7);

        heap.printHeapLevels();
        
        for (int i = 0; i <heap.size(); i++) {
            heap.extractMin();
            heap.printHeapLevels();
        }
        
       /* ArrayList a= new ArrayList();
        a.add(22);
        a.add(2);
        a.add(32);
        a.add(1);
        MinPriorityQueue heap2 = new MinPriorityQueue(a);
        heap2.print();*/
    }
}
