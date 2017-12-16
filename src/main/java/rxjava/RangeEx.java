package rxjava;


import io.reactivex.Observable;

import java.util.Random;

public class RangeEx {


    public static void main(String[] args) {
        Observable<Integer> randomInts = Observable.create(subscriber -> {
            Random random = new Random();
            while (!subscriber.isDisposed()) {
                subscriber.onNext(random.nextInt(1000));
            }
        });

        Observable<Integer> uniqueRandomInts = randomInts
                .distinct()
                .take(10);

        uniqueRandomInts.subscribe(System.out::println);
    }
}
