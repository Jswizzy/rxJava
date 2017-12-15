package rxjava;

import io.reactivex.Observable;

public class Example2 {

    private static int start = 1;
    private static int count = 5;

    public static void main(String[] args) {
        Observable<Integer> source = Observable.defer(() ->
                Observable.range(start,count));

        source.subscribe(i -> System.out.println("Observer 1: " + i));

        //modify count
        count = 10;

        source.subscribe(i -> System.out.println("Observer 2: " + i));

        //

        Observable.fromCallable(() -> 1 / 0)
                .subscribe(i -> System.out.println("Received: " + i),
                        e -> System.out.println("Error Captured: " + e));
    }
}
