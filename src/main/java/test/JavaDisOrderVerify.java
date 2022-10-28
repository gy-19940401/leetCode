package test;

/**
 * @Description: java 乱序执行验证
 * @author: GanYang
 * @Date: 2022/10/24 21:22
 * <p>
 * <p>
 * 指令重排
 * 1、volatile 通过 读屏障 + 写屏障 防止指令重排 {
 * Load相当于读屏障
 * Store相当于写屏障
 * StoreStore屏障、StoreLoad屏障、LoadLoad屏障、LoadStore屏障。
 * }
 * 2、final 写的重排序规则可以保证，在对象引用对任意线程可见之前，对象的 final 变量已经正确初始化了，而普通变量则不具有这个保障；
 * 读的重排序规则可以保证，在读一个对象的 final 变量之前，一定会先读这个对象的引用。如果读取到的引用不为空，
 */
public class JavaDisOrderVerify {

    static Integer a = 0, b = 0, x = 0, y = 0;

    public static void main(String[] args) throws InterruptedException {
        int count = 0;

        while (true) {
            a = 0;
            b = 0;
            x = 0;
            y = 0;

            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    x = b;
                }
            });

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;
                }
            });

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            //如果不存在 乱序 可能的结果 有 {
            // 1、a=1 ; x=0 ; b=1 ; y=1
            // 2、a=1 ; b=1 ; x=1 ; y=1
            // 3、b=1 ; a=1 ; x=1 ; y=1
            // 4、b=1 ; y=0 ; a=1 ; x=1
            // } 反正 x=b && y=a 永远不会 在前面两个执行 即 x,y 不能 同时等于 0

            //综上
            if (x == y && x == 0) {
                System.out.println("第 { " + count + " } 次出现乱序");
                break;
            }
            count++;
        }
    }
}
