package comparators;

import java.util.Comparator;

public class FloatComparator implements Comparator<Float> {

@Override
  public int compare(Float o1, Float o2) {
    if(o1 > o2){
      return 1;
    }else if(o1 < o2){
      return -1;
    }
    return 0;
  }
}