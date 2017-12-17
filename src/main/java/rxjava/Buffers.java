package rxjava;

import io.reactivex.Observable;

import java.util.List;

public class Buffers {

    public static void main(String[] args) {

        Observable
                .range(1, 7)  //1, 2, 3, ... 7
                .buffer(3)
                .subscribe(System.out::println);

        Observable
                .range(1, 7)
                .buffer(3, 1)
                .subscribe(System.out::println);

        Observable<List<Integer>> odd = Observable
                .range(1, 7)
                .buffer(1, 2);

        odd.subscribe(System.out::println);

        Observable<Integer> integerObservable = odd.flatMapIterable(list -> list);

        integerObservable.subscribe(System.out::println);
    }
}
