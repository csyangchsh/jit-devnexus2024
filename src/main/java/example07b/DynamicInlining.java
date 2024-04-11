package example07b;

import java.util.HashMap;
import java.util.Map;

public class DynamicInlining {
  static int globalSink;
  
  public static void main(String[] args)
  	throws InterruptedException
  {
	// first loop to trigger C1
	for ( int i = 0; i < 5_000; ++i ) {
	  hotMethod();
	}
	
	// wait for C1 to finish
	Thread.sleep(2_000);
	
	// second loop to trigger C2
	for ( int i = 0; i < 15_000; ++i ) {
	  hotMethod();
	}
	
	// wait for C2 to finish
	Thread.sleep(5_000);	
  }
  
  static void hotMethod() {
	globalSink = makeMap();
  }
  
  static int makeMap() {
	Map<String, String> map = new HashMap<>();
	map.put("foo", "bar");
	return map.size();
  }
}
