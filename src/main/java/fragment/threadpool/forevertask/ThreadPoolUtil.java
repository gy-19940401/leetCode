package fragment.threadpool.forevertask;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @Description: 线程池工具类
 * @author: GanYang
 * @Date: 2022/10/12 9:38
 */
public class ThreadPoolUtil {

    private static Map<String, ExecutorService> executorsMap = new ConcurrentHashMap<>();

    /**
     * 初始化一个线程池
     *
     * @param poolName     线程池名称
     * @param poolCoreSize 核心线程数
     * @return 线程池
     */
    private static ExecutorService init(String poolName, int poolCoreSize) {
        return new ThreadPoolExecutor(poolCoreSize
                , poolCoreSize,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(),
                threadFactory -> {
                    Thread thread = new Thread();
                    thread.setName(poolName);
                    thread.setDaemon(false);
                    return thread;
                },
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static ExecutorService getOrInitExecutorService(String poolName, int poolCoreSize) {
        ExecutorService executor = executorsMap.get(poolName);
        if (executor == null) {
            synchronized (ThreadPoolUtil.class) {
                executor = executorsMap.get(poolName);
                if (executor == null) {
                    executor = init(poolName, poolCoreSize);
                    executorsMap.put(poolName, executor);
                }
            }
        }
        return executor;
    }

    public static void releaseExecutors(String poolName) {
        ExecutorService removedExecutor = executorsMap.remove(poolName);
        if (Objects.nonNull(removedExecutor)) {
            removedExecutor.shutdown();
        }
    }
}
