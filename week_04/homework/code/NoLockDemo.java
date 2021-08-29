package code;

/**
 * @author C5318521
 * @since 8/30/2021
 */
public class NoLockDemo {

    private volatile Integer value = null;

    private void sum(int num) {
         value=fibo(num);
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        final NoLockDemo method = new NoLockDemo();

        Thread t = new Thread() {
            @Override
            public void run() {
                method.sum(45);
            }
        };

        t.start();
        int result = method.getValue(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }


    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }

    public int getValue() {
        while (value == null) {

        }
        return value;
    }


}

