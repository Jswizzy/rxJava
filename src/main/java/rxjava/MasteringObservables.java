package rxjava;

import io.reactivex.Observable;

public class MasteringObservables {

    private static void log(Object msg) {
        System.out.println(
                Thread.currentThread().getName() +
                        ": " + msg);
    }

    static <T> Observable<T> just(T x) {
        return Observable.create(subscriber -> {
                    subscriber.onNext(x);
                    subscriber.onComplete();
                }
        );
    }


    public static void main(String[] args) {

        log("Before");
        Observable
                .range(5, 3)
                .subscribe(i -> {
                    log(i);
                });
        log("After");

        //

        Observable<Integer> ints =
                Observable.create(subscriber -> {
                            log("Create");
                            subscriber.onNext(42);
                            subscriber.onComplete();
                        }
                );
        log("Starting");
        ints.subscribe(i -> log("Element A: " + i));
        ints.subscribe(i -> log("Element B: " + i));
        log("Exit");

        Observable<Integer> cache = ints.cache();


        log("Starting");
        cache.subscribe(i -> log("Element A: " + i));
        cache.subscribe(i -> log("Element B: " + i));
        log("Exit");



    }


}
