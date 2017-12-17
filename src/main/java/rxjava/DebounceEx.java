package rxjava;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import static io.reactivex.Observable.defer;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class DebounceEx {

    Observable<Long> timedDebounce(Observable<Long> upstream) {
        Observable<Long> onTimeout = upstream
                .take(1)
                .concatWith(defer(() -> timedDebounce(upstream)));
        return upstream
                .debounce(100, MILLISECONDS)
                .timeout(1, SECONDS, onTimeout);
    }

    public static void main(String[] args) throws InterruptedException {

        ConnectableObservable<Long> upstream = Observable
                .interval(99, MILLISECONDS)
                .publish();
        upstream
                .debounce(100, MILLISECONDS)
                .timeout(1, SECONDS, upstream.take(1));
        upstream.connect();

        Thread.sleep(1000);
    }
}

