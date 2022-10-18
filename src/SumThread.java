import java.util.concurrent.Callable;

public class SumThread implements Callable<Long> {
    int[] mas;
    int begin, end;

    SumThread(int[] curr_mas, int curr_begin, int curr_end) {
        mas = curr_mas;
        begin = curr_begin;
        end = curr_end;
    }

    public Long call() {
        long partSum = 0;

        for (int i = begin; i <= end; i++)
            partSum = partSum + mas[i];

        return partSum;
    }
}