package rxjava;

import io.reactivex.Observable;
import io.reactivex.Single;

public class SingleEx {


    public static void main(String[] args) {
        Single.just("Hello")
                .map(String::length)
                .subscribe(System.out::println,
                        Throwable::printStackTrace);


        Observable<String> source =
                Observable.just("Alpha","Beta","Gamma");

        source.first("Nil") //returns a Single
                .subscribe(System.out::println);
    }
}

