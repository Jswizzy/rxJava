package rxjava;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Dish {

    private final byte[] oneKb = new byte[1_024];
    private final int id;

    Dish(int id) {
        this.id = id;
        System.out.println("Created: " + id);
    }

    public String toString() {
        return String.valueOf(id);
    }

    private static void sleepMillis(int sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static Observable<Integer> myRange(int from, int count) {
        return Observable.create(subscriber -> {
            int i = from;
            while (i < from + count) {
                if (!subscriber.isDisposed()) {
                    subscriber.onNext(i++);
                } else {
                    return;
                }
            }
            subscriber.onComplete();
        });
    }

    public static void main(String[] args) {

/*        Observable
                .range(1, 1_000_000_000)
                .map(Dish::new)
                .subscribe(x -> {
                    System.out.println("Washing: " + x);
                    sleepMillis(50);
                });*/
/*
        Observable<Dish> dishes = Observable
                .range(1, 1_000_000_000)
                .map(Dish::new);

        dishes
                .observeOn(Schedulers.io())
                .subscribe(x -> {
                    System.out.println("Washing: " + x);
                    sleepMillis(50);
                });*/

        myRange(1, 1_000_000_000)
                .map(Dish::new)
                .observeOn(Schedulers.io())
                .subscribe(x -> {
                            System.out.println("Washing: " + x);
                            sleepMillis(50);
                        },
                        Throwable::printStackTrace
                );
    }
}
