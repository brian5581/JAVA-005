import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    private volatile Integer value = null;
    private final Semaphore semaphore = new Semaphore(1);

    SemaphoreDemo() throws InterruptedException {
        semaphore.acquire();
    }

    public void sum(int num) throws InterruptedException {
        Thread.sleep(5000);
        value = fibo(num);
        semaphore.release();

    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        final SemaphoreDemo method = new SemaphoreDemo();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    method.sum(45);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        int result = method.getValue(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }

    public int getValue() throws InterruptedException {
        int result;
        semaphore.acquire();
        result=this.value;
        semaphore.release();
        return result;
    }
}