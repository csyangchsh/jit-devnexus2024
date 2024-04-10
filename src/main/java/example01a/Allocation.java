package example01a;

// Run with -XX:+PrintCompilation to see elapsed
public class Allocation {
  static Object globalSink = null;
	
  public static void main(String[] args) {
	for ( int chunk = 0; chunk < 500; ++chunk ) {
	  long startTimeNs = System.nanoTime();
	         
	  for ( int i = 0; i < 1_000; ++i ) {
		globalSink = new Object();
	  }

	  long endTimeNs = System.nanoTime();
	  System.out.printf("%5d%n", endTimeNs - startTimeNs);
    }
  }
}
