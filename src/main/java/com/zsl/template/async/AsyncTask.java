package com.zsl.template.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 无返回结果的异步调用
 * 异步并发执行了。主程序在异步调用之后，主程序并不会理会这三个函数是否执行完成了，
 * 由于没有其他需要执行的内容，所以程序就 自动结束 了，导致了任务 不完整 或是 没有输出 相关内容的情况
 * AsyncTask
 *
 * @author swiftzsl
 * @date 2021/8/8 14:49
 */
@Component
public class AsyncTask extends AbstractTask {
    //注意：@Async所修饰的函数不要定义为static类型，这样异步调用不会生效
    @Async
    public void doTaskOne() throws Exception {
        super.doTaskOne();
    }

    @Async
    public void doTaskTwo() throws Exception {
        super.doTaskTwo();
    }

    @Async
    public void doTaskThree() throws Exception {
        super.doTaskThree();
    }
}
