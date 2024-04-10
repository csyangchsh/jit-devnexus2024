package example01b;

public class AllocationEliminated {
  public static void main(String[] args) {
	for ( int chunk = 0; chunk < 500; ++chunk ) {
	  long startTimeNs = System.nanoTime();
	         
	  for ( int i = 0; i < 1_000; ++i ) {
	    new Object();
	  }

	  long endTimeNs = System.nanoTime();
	  System.out.printf("%3d\t%5d%n", chunk, endTimeNs - startTimeNs);
    }
  }
}
