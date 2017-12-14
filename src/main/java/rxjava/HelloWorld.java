package rxjava;

import io.reactivex.*;
import io.reactivex.schedulers.Schedulers;

public class HelloWorld {

    public static Single<String> getDataA() {
        return Single.<String> create(o -> {
            o.onSuccess("DataA");
        }).subscribeOn(Schedulers.io());
    }

    public static Single<String> getDataB() {
        return Single.just("DataB")
                .subscribeOn(Schedulers.io());
    }




    public static void main(String[] args) {
        Flowable.just("Hello world").subscribe(System.out::println);


    }
}