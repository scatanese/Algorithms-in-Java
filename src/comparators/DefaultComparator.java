package comparators;

import java.util.Comparator;

/**
 *
 * @author salvo
 */
public class DefaultComparator<T> implements Comparator <T>{

    @Override
    public int compare(T o1, T o2) throws ClassCastException {
        return ((Comparable<T>) o1).compareTo(o2);
    }
    
}
