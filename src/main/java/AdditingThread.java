import java.util.Map;

public class AdditingThread extends Thread {
  private Map<Integer, Integer> map;
  private int[] array;

  public AdditingThread(Map<Integer, Integer> map, int[] array) {
    this.map = map;
    this.array = array;
  }

  public void run() {
    System.out.println(Thread.currentThread().getName() + " begin");
    long start = System.currentTimeMillis();

    for (int i = 0; i < array.length; i++) {
      map.put(array[i], array[i]);
    }

    long timeWorkCode = System.currentTimeMillis() - start;
    System.out.println(Thread.currentThread().getName() + " end. Time = " + timeWorkCode);
  }
}
