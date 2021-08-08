package com.zsl.template.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * AsyncCallBackTask
 *
 * @author swiftzsl
 * @date 2021/8/8 14:51
 */
@Component
public class AsyncCallBackTask extends AbstractTask {
    @Async
    public Future<String> doTaskOneCallback() throws Exception {
        super.doTaskOne();
        return new AsyncResult<>("任务一完成");
    }

    @Async
    public Future<String> doTaskTwoCallback() throws Exception {
        super.doTaskTwo();
        return new AsyncResult<>("任务二完成");
    }

    @Async
    public Future<String> doTaskThreeCallback() throws Exception {
        super.doTaskThree();
        return new AsyncResult<>("任务三完成");
    }
}
