package org.sparrow.thread7;

/**
 * @author wangjianchun
 * @create 2018/3/17
 */
public class Sub extends Main {

    public synchronized void operateISubMethod(){
        try {
            while(i > 0){
                i--;
                System.out.println("sub print i="+i);
                Thread.sleep(100);
                this.operateIMainMethod();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
