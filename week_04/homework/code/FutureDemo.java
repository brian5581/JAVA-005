package code;

import java.util.concurrent.*;

public class FutureDemo {
    
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> result = executor.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                return sum();
            }
        });
        executor.shutdown();
        try {
            System.out.println("result:" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
    
}
