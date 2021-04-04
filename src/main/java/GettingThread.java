import java.util.Iterator;
import java.util.Map;

public class GettingThread extends Thread {
  private Map<Integer, Integer> map;
  private int[] array;

  public GettingThread(Map<Integer, Integer> map, int[] array) {
    this.map = map;
    this.array = array;
  }

  public void run() {
    System.out.println(Thread.currentThread().getName() + " begin");
    long start = System.currentTimeMillis();

    int count = 0;
    /*
    Iterator<Integer> it = map.keySet().iterator();

    while (it.hasNext()) {
      Integer key = it.next();
      map.remove(key);
      //System.out.println(Thread.currentThread().getName() + map.get(key));
      count++;
    }*/

    for (int i = 0; i < array.length; i++) {
      Integer value = null;
      while (value == null)
        value = map.get(array[i]);
      //System.out.println(Thread.currentThread().getName() + " " + value);
      count++;
    }
    long timeWorkCode = System.currentTimeMillis() - start;
    System.out.println(Thread.currentThread().getName() + " end. Count =" + count + ". Time = " + timeWorkCode);
  }
}
