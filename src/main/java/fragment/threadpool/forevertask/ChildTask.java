package fragment.threadpool.forevertask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: GanYang
 * @Date: 2022/10/12 13:56
 */
public class ChildTask {
    /**
     * 线程池大小
     */
    private final int POOL_SIZE = 3;
    /**
     * 数据拆分大小
     */
    private final int SPLIT_SIZE = 4;
    private String taskName;

    /**
     * 接收jvm关闭信号，实现优雅停机
     */
    protected volatile boolean terminal = false;

    public ChildTask(String taskName) {
        this.taskName = taskName;
    }

    /**
     * @show 程序执行入口
     * @author Ganyang
     * @data 13:58
     */
    public void doExecute() {
        int i = 0;
        while (true) {
            System.out.println(taskName + ":Cycle-" + i + "-Begin");
            // 获取数据
            List<Cat> datas = queryData();
            // 处理数据
            taskExecute(datas);
            System.out.println(taskName + ":Cycle-" + i + "-End");
            if (terminal) {
                // 只有应用关闭，才会走到这里，用于实现优雅的下线
                break;
            }
            i++;
        }
        // 回收线程池资源
        ThreadPoolUtil.releaseExecutors(taskName);
    }

    // 优雅停机
    public void terminal() {
        // 关机
        terminal = true;
        System.out.println(taskName + " shut down");
    }

    // 处理数据
    private void doProcessData(List<Cat> datas, CountDownLatch latch) {
        try {
            for (Cat cat : datas) {
                System.out.println(taskName + ":" + cat.toString() + ",ThreadName:" + Thread.currentThread().getName());
                Thread.sleep(1000L);
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        } finally {
            if (latch != null) {
                latch.countDown();
            }
        }
    }

    // 处理单个任务数据
    private void taskExecute(List<Cat> sourceDatas) {
        if (sourceDatas.size() == 0) {
            return;
        }
        // 将数据拆成4份
        Map<Boolean, List<Cat>> collect = sourceDatas.stream().collect(Collectors.partitioningBy(i -> sourceDatas.indexOf(i) % SPLIT_SIZE == 0));
        final CountDownLatch latch = new CountDownLatch(collect.values().size());

        // 并发处理拆分的数据，共用一个线程池
        for (final List<Cat> datas : collect.values()) {
            ExecutorService executorService = ThreadPoolUtil.getOrInitExecutorService(taskName, POOL_SIZE);
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    doProcessData(datas, latch);
                }
            });
        }

        try {
            latch.await();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    // 获取永动任务数据
    private List<Cat> queryData() {
        List<Cat> datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            datas.add(new Cat().setCatName("罗小黑" + i));
        }
        return datas;
    }
}
