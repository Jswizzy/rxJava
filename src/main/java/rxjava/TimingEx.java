package rxjava;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import static io.reactivex.Observable.interval;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class TimingEx {


    public static void main(String[] args) throws InterruptedException {

        Observable<Long> red   = interval(10, MILLISECONDS);
        Observable<Long> green = interval(11, MILLISECONDS);

        Disposable disposable = Observable.zip(
                red.timestamp(),
                green.timestamp(),
                (r, g) -> r.time() - g.time()
        ).forEach(System.out::println);


        Thread.sleep(100);

        disposable.dispose();

        //

        Disposable disposable1 = Observable.combineLatest(
                interval(17, MILLISECONDS).map(x -> "S" + x),
                interval(10, MILLISECONDS).map(x -> "F" + x),
                (s, f) -> f + ":" + s
        ).forEach(System.out::println);

        Thread.sleep(100);

        disposable1.dispose();

        //

        Observable
                .just(1, 2)
                .startWith(0)
                .subscribe(System.out::println).dispose();

        Thread.sleep(100);

        //

    }
}
