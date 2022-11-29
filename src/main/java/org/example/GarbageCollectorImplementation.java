package org.example;


import java.util.*;


public class GarbageCollectorImplementation implements GarbageCollector {
  @Override
  public List<ApplicationBean> collect(HeapInfo heap, StackInfo stack) {
    Map<String, ApplicationBean> beans = heap.getBeans();
    Deque<StackInfo.Frame> frames = stack.getStack();
    Set<ApplicationBean> beansSet = new HashSet<>(getBeans(beans));
    Set<ApplicationBean> aliveBeans = getAliveBeans(frames);
    return getGarbage(aliveBeans, beansSet);
  }

  private Set<ApplicationBean> getBeans(Map<String,ApplicationBean> beanMap){
    Set<ApplicationBean> beanSet = new HashSet<>();
    for (Map.Entry<String,ApplicationBean> beans: beanMap.entrySet()) {
      beanSet.add(beans.getValue());
    }
    return beanSet;
  }

  private Set <ApplicationBean> getAliveBeans(final Deque<StackInfo.Frame> frames) {
    Set<ApplicationBean> aliveBeanSet = new HashSet<>();
    for (StackInfo.Frame frame : frames) {
      for (ApplicationBean applicationBean : frame.getParameters()) {
        getChild(applicationBean, aliveBeanSet);
      }
    }
    return aliveBeanSet;
  }

  private Set<ApplicationBean> getChild(ApplicationBean bean, Set<ApplicationBean> aliveBeansSet) {
    aliveBeansSet.add(bean);
    for (ApplicationBean applicationBean : bean.getFieldValues().values()) {
      if (!aliveBeansSet.contains(applicationBean))
        aliveBeansSet.addAll(getChild(applicationBean, aliveBeansSet));
    }
    return aliveBeansSet;
  }

  private List<ApplicationBean> getGarbage(Set<ApplicationBean> aliveBeanSet, Set<ApplicationBean> beanSet) {
    beanSet.removeAll(aliveBeanSet);
    return new ArrayList<>(beanSet);
  }
}

