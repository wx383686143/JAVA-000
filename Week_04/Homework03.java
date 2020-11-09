package java0.conc0303;

import java.util.concurrent.*;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class Homework03 {

    static int result;

    public static void main(String[] args) {
        
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // int reuslt = 0;
        // 异步执行 下面方法

        // Homework03.setNum1(); // 第一种

        /*Homework03 homework03 = new Homework03();
        homework03.setNum2(homework03); // 第二种*/

        // Homework03.setNum3(); // 第三种

        // Homework03.setNum4(); // 第四种

        // Homework03.setNum5(); // 第五种

        Homework03.setNum6(); // 第六种


        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);
         
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        
        // 然后退出main线程
    }

    public static void setNum7() {
        Thread thread = Homework03.getThread();
        thread.start();

    }

    /**
     * 6.Thread.activeCount判断线程活跃数
     */
    public static void setNum6() {
        Thread thread = Homework03.getThread();
        thread.start();
        while(Thread.activeCount() != 2) {
        }
    }

    /**
     * 5.CountDownLatch
     */
    public static void setNum5() {
        final CountDownLatch downLatch = new CountDownLatch(1);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    result = Homework03.sum();
                } finally {
                    downLatch.countDown();
                }
            }
        });
        thread.start();
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 4.使用线程池future返回特性
     */
    public static void setNum4() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() {
                return Homework03.sum();
            }
        });
        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    /**
     * 3.使用thread.isAlive
     */
    public static void setNum3() {
        Thread thread = Homework03.getThread();
        thread.start();
        while (thread.isAlive()){
        }
    }

    /**
     * 2.使用Object.wait()
     */
    public synchronized void setNum2(Object obj) {
        Thread thread = Homework03.getThread();
        thread.start();
        try {
            obj.wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1.使用Thread.join()实现
     * @return
     */
    public static void setNum1() {
        Thread thread = Homework03.getThread();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Thread getThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                result = Homework03.sum();
            }
        });
        return thread;
    }
    
    private static int sum() {
        return fibo(36);
    }
    
    private static int fibo(int a) {
        if ( a < 2) 
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
