package rxjava;

import java.util.concurrent.TimeUnit;

import static io.reactivex.Observable.just;

public class DelayEx {

    public static void main(String[] args) {

        just(1, 2, 3).delay(1, TimeUnit.SECONDS).subscribe(System.out::println);

    }
}
