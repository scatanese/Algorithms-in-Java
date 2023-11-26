/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparators;

import java.util.Comparator;

/**
 *
 * @author salvo
 */

public class LongComparator implements Comparator<Long> {
	  
	@Override
	  public int compare(Long o1, Long o2) {
	    if(o1 > o2){
	      return 1;
	    }else if(o1 < o2){
	      return -1;
	    }
	    return 0;
	  }

}