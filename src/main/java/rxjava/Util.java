package rxjava;

import io.reactivex.Observable;

import java.util.concurrent.CompletableFuture;

class Util {

    static <T> Observable<T> observe(CompletableFuture<T> future) {
        return Observable.create(subscriber -> {
            future.whenComplete((value, throwable) -> {
                if (throwable != null) {
                    subscriber.onError(throwable);
                } else {
                    subscriber.onNext(value);
                    subscriber.onComplete();
                }
            });
        });
    }

    static <T> CompletableFuture<T> toFuture(Observable<T> observable) {
        CompletableFuture<T> promise = new CompletableFuture<>();
        observable
                .firstElement()
                .subscribe(
                        promise::complete,
                        promise::completeExceptionally
                );
        return promise;
    }
}
