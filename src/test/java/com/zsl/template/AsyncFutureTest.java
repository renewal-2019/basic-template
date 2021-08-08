package com.zsl.template;

import com.zsl.template.async.AsyncCallBackTask;
import com.zsl.template.async.AsyncExecutorTask;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.Future;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.sleep;

/**
 * AsyncFutureTest
 *
 * @author swiftzsl
 * @date 2021/8/8 14:53
 */
@SpringBootTest
// 需要注入bean
@ExtendWith(SpringExtension.class)
public class AsyncFutureTest {
    @Autowired
    private AsyncCallBackTask asyncCallBackTask;

    @Autowired
    private AsyncExecutorTask asyncExecutorTask;

    @Test
    public void testAsyncCallbackTask() throws Exception {
        long start = currentTimeMillis();
        Future<String> task1 = asyncCallBackTask.doTaskOneCallback();
        Future<String> task2 = asyncCallBackTask.doTaskTwoCallback();
        Future<String> task3 = asyncCallBackTask.doTaskThreeCallback();

        // 三个任务都调用完成，退出循环等待
        while (!task1.isDone() || !task2.isDone() || !task3.isDone()) {
            sleep(1000);
        }

        long end = currentTimeMillis();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }

    @Test
    public void testAsyncExecutorTask() throws Exception {
        asyncExecutorTask.doTaskOneCallback();
        asyncExecutorTask.doTaskTwoCallback();
        asyncExecutorTask.doTaskThreeCallback();

        sleep(30 * 1000L);
    }
}
