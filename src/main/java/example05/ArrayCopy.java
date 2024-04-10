package example05;

import java.util.concurrent.ThreadLocalRandom;

public class ArrayCopy {
  static final int[] NUMS = randomInts(1_000_000);
  
  static final int[] randomInts(int size) {
    ThreadLocalRandom tlRandom = ThreadLocalRandom.current();
    
	int[] nums = new int[size];
    for ( int i = 0; i < nums.length; ++i ) {
      nums[i] = tlRandom.nextInt();
    }    
    return nums;
  }
  
  static Object sink;

  public static void main(String[] args) {
	long startNs, endNs;
	
	startNs = System.nanoTime();
    sink = arraycopy();
    endNs = System.nanoTime();
    System.out.printf("%20s\t%10d%n", "arraycopy", endNs - startNs);
	
	startNs = System.nanoTime();
    sink = copyloop();
    endNs = System.nanoTime();
    System.out.printf("%20s\t%10d%n", "copyloop", endNs - startNs); 
  }
  
  static final int[] arraycopy() {		
	int[] copy = new int[NUMS.length];
	System.arraycopy(NUMS, 0, copy, 0, NUMS.length);
	return copy;
  }
  
  static final int[] copyloop() {
	int[] copy = new int[NUMS.length];
	for ( int i = 0; i < NUMS.length; ++i ) {
	  copy[i] = NUMS[i];
	}
	return copy;
  }
}
