/*
 * Copyright (c) 2005, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package example02;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

// Needs to be compiled with maven then run java -jar target/benchmarks.jar
@Fork(2)
@Warmup(iterations=2)
@Measurement(iterations=3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class OptimizeStringConcat {
  static final String GREETING = "Hello, ";
  static final User user = new User("devnexus");
  
  @Benchmark
  public String concat() {
	// This will get different results depending on javac version used for compilation
	// Compiled with Java 10+ benefits from indified String concat
	return GREETING + user.name;
  }
  
  @Benchmark
  public String concatLike() {
	return new StringBuilder(GREETING).
	  append(user.name).
	  toString();
  }
  
  @Benchmark
  public String notQuiteConcatLike() {
	StringBuilder builder = new StringBuilder(GREETING);
	builder.append(user.name);
	return builder.toString();
  }
  
  
}

final class User {
  final String name;
  
  public User(final String name) {
	this.name = name;
  }
}
