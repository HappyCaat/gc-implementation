package org.example;


import java.util.*;


public class GarbageCollectorImplementation implements GarbageCollector {
  @Override
  public List<ApplicationBean> collect(HeapInfo heap, StackInfo stack) {
    Map<String, ApplicationBean> beans = heap.getBeans();
    Deque<StackInfo.Frame> frames = stack.getStack();
    Set<ApplicationBean> beansSet = new HashSet<>(getBeans(beans));
    return new ArrayList<>();
  }

  private Set<ApplicationBean> getBeans(Map<String,ApplicationBean> beanMap){
    Set<ApplicationBean> beanSet = new HashSet<>();
    for (Map.Entry<String,ApplicationBean> beans: beanMap.entrySet()) {
      beanSet.add(beans.getValue());
    }
    return beanSet;
  }

}

