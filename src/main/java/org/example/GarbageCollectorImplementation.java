package org.example;


import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;


public class GarbageCollectorImplementation implements GarbageCollector {
  @Override
  public List<ApplicationBean> collect(HeapInfo heap, StackInfo stack) {
    Map<String, ApplicationBean> beans = heap.getBeans();
    Deque<StackInfo.Frame> frames = stack.getStack();

    return new ArrayList<>();
  }

}

