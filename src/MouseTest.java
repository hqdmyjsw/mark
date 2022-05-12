import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author = hqdmy
 * @date = 2019-06-18
 */
public class MouseTest {
    public static void main(String[] args) throws AWTException
    {
        gtx3();
    }

    private static void gtx1() {
        int lastx=0,lasty=0,maxX=1600,maxY=900,step=200;
        try{
            Robot robot = new Robot();
            robot.setAutoWaitForIdle(true);
            robot.setAutoDelay(randomMiddle(500,2000));
            robot.setAutoWaitForIdle(true);
            robot.mouseMove(0, 0);
            robot.delay(1000);
            boolean loop = true;
            Point start = new Point();
            Point end = new Point();
            while (loop){
                try {
                    Thread.sleep(5000L+new Random().nextInt(60) );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                end = randomPoint(maxX,maxY);
                bezirMove(robot,start,end,randomMiddle(1000,8000),1000);
                start = end;
            }

            System.exit(0);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void gtx2() {
        int lastx=0,lasty=0,maxX=1600,maxY=900,step=200;
        try{
            Robot robot = new Robot();

            robot.setAutoDelay(5);
            robot.setAutoWaitForIdle(true);
            robot.mouseMove(0, 0);
            robot.delay(1000);
            boolean loop = true;
            Point start = new Point();
            Point end = new Point();
            while (loop){
                try {
                    Thread.sleep(5000L+new Random().nextInt(60) );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                robot.keyPress(KeyEvent.VK_META);


                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);

                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);

                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);

                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);

                robot.keyRelease(KeyEvent.VK_META);


                loop = false;
            }
            System.exit(0);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private static void gtx3() {
        int lastx=0,lasty=0,maxX=1600,maxY=900,step=200;
        try{
            Robot robot = new Robot();

            robot.setAutoDelay(5);
            robot.setAutoWaitForIdle(true);
            robot.mouseMove(0, 0);
            robot.delay(1000);
            boolean loop = true;
            Point start = new Point();
            Point end = new Point();
            while (loop){
                try {
                    Thread.sleep(5000L+randomMiddle(1,2000) );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                BasicMove basicMove = new BasicMove(lastx, lasty, maxX, maxY, step, robot).invoke();
//                lastx = basicMove.getLastx();
//                lasty = basicMove.getLasty();
                end = randomPoint(maxX,maxY);
                bezirMove(robot,start,end,randomMiddle(1000,8000),1000);
                start = end;


                robot.keyPress(KeyEvent.VK_META);
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);

                int tabLoopTime = randomMiddle(5,20);
                for (int i = 0; i <tabLoopTime ; i++) {
                    robot.keyPress(KeyEvent.VK_TAB);
                    robot.keyRelease(KeyEvent.VK_TAB);
                    robot.delay(randomMiddle(500,1500));
                }


                robot.keyRelease(KeyEvent.VK_META);

                //loop = false;
            }
//            robot.mouseMove(200, 10);
//            robot.delay(1000);
//            robot.mouseMove(40, 130);

            System.exit(0);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void bezirMove(Robot robot,Point start,Point end,int timeSpanMills,int partNum){
        ArrayList<Point> line = bezirLine(start,end,partNum);
        robot.mouseMove(start.x,start.y);
        for (int i = 0; i < partNum; i++) {
            Point next = line.get(i);
            robot.delay(timeSpanMills/partNum);
            robot.mouseMove(next.x,next.y);
            System.out.printf("lastX:%d lastY:%d",next.x,next.y).println();
        }
    }
    public static  ArrayList<Point> bezirLine(Point start,Point end,int partNum){
        Point key = new Point(randomMiddle(start.x,end.x),randomMiddle(start.y,end.y));
        ArrayList<Point> rtn = new ArrayList<>(partNum);
        for (int i = 0; i < partNum; i++) {
            float t  = (float) (i+1)/partNum;
            Point item = bezireBasic(start,end,key,t);
            rtn.add(item);
        }
        return rtn;
    }

    public static Point bezireBasic(Point start,Point end,Point key,float t){
        Point rtn = new Point();
        rtn.x = bezireBasic(start.x ,end.x,key.x,t);
        rtn.y = bezireBasic(start.y ,end.y,key.y,t);
        System.out.printf("start:%s",start.toString()).println();
        return rtn;
    }
    public static int bezireBasic(int start,int end, int key,float t){
        int rtn = (int) (Math.pow((1-t),2)*start + 2*t*(1-t)*key+ Math.pow(t,2)*end);
        return rtn;
    }

    public static Point randomPoint(int maxX,int maxY){
        Point rtn = new Point(new Random().nextInt(maxX),new Random().nextInt(maxY));
        System.out.printf("random point:%s",rtn.toString()).println();
        return rtn;
    }
    public static int randomMiddle(int start,int end){
        int rtn = new Random().nextInt(Math.abs(end-start)) + start;
        System.out.printf("start:%d,end:%d,rtn:%d",start,end,rtn).println();
        return rtn;
    }

    private static class BasicMove {
        private int lastx;
        private int lasty;
        private int maxX;
        private int maxY;
        private int step;
        private Robot robot;

        public BasicMove(int lastx, int lasty, int maxX, int maxY, int step, Robot robot) {
            this.lastx = lastx;
            this.lasty = lasty;
            this.maxX = maxX;
            this.maxY = maxY;
            this.step = step;
            this.robot = robot;
        }

        public int getLastx() {
            return lastx;
        }

        public int getLasty() {
            return lasty;
        }

        public BasicMove invoke() {
            lastx = lastx + new Random().nextInt(step)*(new Random().nextInt(10)%2==0?-1:1);
            lasty = lasty + new Random().nextInt(step)*(new Random().nextInt(10)%2==0?-1:1);
            lastx = lastx<=maxX?lastx:lastx-maxX;
            lastx = lastx<=0?(maxX+lastx):lastx;
            lasty = lasty<=maxY?lasty:lasty-maxY;
            lasty = lasty<=0?(maxY+lasty):lasty;
            robot.mouseMove(lastx, lasty);
            System.out.printf("lastX:%d lastY:%d",lastx,lasty).println();
            robot.delay(10);
            return this;
        }
    }
}
