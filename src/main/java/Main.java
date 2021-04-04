import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  private static final int MAX_NUMBER = 500000;
  // при значениях 500000 и выше ConcurrentHashMap начинает заметно опережать
  // по скорости работы обертку над HashMap

  public static void main(String[] args) {
    Map<Integer, Integer> map1 = new ConcurrentHashMap<Integer, Integer>();
    Map<Integer, Integer> map2 = Collections.synchronizedMap(new HashMap<Integer, Integer>());
    // число потоков в пуле
    int threadCount = 3;
    // генерим массив случайных чисел
    int[] array = new int[MAX_NUMBER];
    Random random = new Random();
    for (int i = 0; i < MAX_NUMBER; i++) {
      array[i] = random.nextInt();
    }
    // пул потоков для записи в ConcurrentHashMap
    ExecutorService executor = Executors.newFixedThreadPool(threadCount);
    for (int i = 0; i < threadCount; i++) {
      executor.submit(new AdditingThread(map1, array));
    }
    executor.shutdown();
    // пул потоков для чтения ConcurrentHashMap
    ExecutorService executor1 = Executors.newFixedThreadPool(threadCount);
    for (int i = 0; i < threadCount; i++) {
      executor1.submit(new GettingThread(map1, array));
    }
    executor1.shutdown();
    try {
      Thread.sleep(2000);
      System.out.println("");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    // пул потоков для записи в HashMap с оберткой
    ExecutorService executor2 = Executors.newFixedThreadPool(threadCount);
    for (int i = 0; i < threadCount; i++) {
      executor2.submit(new AdditingThread(map2, array));
    }
    executor2.shutdown();

    // пул потоков для чтения HashMap с оберткой
    ExecutorService executor3 = Executors.newFixedThreadPool(threadCount);
    for (int i = 0; i < threadCount; i++) {
      executor3.submit(new GettingThread(map2, array));
    }
    executor3.shutdown();
  }

}
