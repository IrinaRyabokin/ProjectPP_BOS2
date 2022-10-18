import java.util.ArrayList;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int size = 1000000;
        int[] mas = new int[size];
        long SumMas = 0;
        for (int i = 0; i < size; i++)
            mas[i] = i;
        for (int i = 0; i < size; i++)
            SumMas = SumMas + mas[i];
        System.out.println("Сума в однопоточному режимі:");
        System.out.println(SumMas);
//
        int NumThreads = 10;
        int NumTasks = 50;
        int[] beginMas = new int[NumTasks];
        int[] endMas = new int[NumTasks];
        for (int i = 0; i < NumTasks; i++)
            beginMas[i] = size / NumTasks * i;
        for (int i = 0; i < NumTasks - 1; i++)
            endMas[i] = beginMas[i + 1] - 1;

        endMas[NumTasks - 1] = size - 1;

        ExecutorService executor = Executors.newFixedThreadPool(NumThreads);
        ArrayList<Future<Long>> results = new ArrayList<>();
        for (int i =0; i < NumTasks; i++)
        {
            Future<Long> result = executor.submit(new SumThread(mas, beginMas[i], endMas[i]));
            results.add(result);
        }

        long sumMas2 = 0;

        for (Future<Long> res: results)
        {
            sumMas2 += res.get();
        }

        executor.shutdown();

        System.out.println("Сума в багатопоточному режимі:");
        System.out.println(sumMas2);
    }
}