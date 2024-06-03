package org.infinispan.playground.embeddedhotrod;

import java.util.Map;

import org.infinispan.tasks.ServerTask;
import org.infinispan.tasks.TaskContext;

public class ServerTaskExample implements ServerTask {

    public static final String TASK_NAME = "just-example";
    private static final ThreadLocal<TaskContext> treadLocalContext = new ThreadLocal();

    @Override
    public void setTaskContext(TaskContext taskContext) {
        treadLocalContext.set(taskContext);
    }

    @Override
    public Object call() throws Exception {
        TaskContext taskContext = treadLocalContext.get();
        Map<String, Object> params = (Map<String, Object>) taskContext.getParameters().get();
        int first = (Integer) params.get("first");
        int second = (Integer) params.get("second");
        return first + second;
    }

    @Override
    public String getName() {
        return TASK_NAME;
    }

}
