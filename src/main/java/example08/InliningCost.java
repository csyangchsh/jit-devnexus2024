package example08;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

@Fork(2)
@Warmup(iterations=2)
@Measurement(iterations=3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class InliningCost {
  static final int[] NUMS = randomInts(1_000_000);
  
  static final int[] randomInts(int size) {
    ThreadLocalRandom tlRandom = ThreadLocalRandom.current();
    
	int[] nums = new int[size];
    for ( int i = 0; i < nums.length; ++i ) {
      nums[i] = tlRandom.nextInt();
    }    
    return nums;
  }

  @Benchmark
  public int sumInlined() {
    int sum = 0;
    for ( int i = 0; i < NUMS.length; ++i ) {
      sum += inlinedIdentity(NUMS[i]);
    }
    return sum;
  }
  
  @CompilerControl(CompilerControl.Mode.INLINE)
  static int inlinedIdentity(int x) {
	return x;
  }

  @Benchmark
  public int sumNotInlined() {
    int sum = 0;
    for ( int i = 0; i < NUMS.length; ++i ) {
      sum += notInlinedIdentity(NUMS[i]);
    }
    return sum;
  }
  
  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  static int notInlinedIdentity(int x) {
	return x;  
  }
}
