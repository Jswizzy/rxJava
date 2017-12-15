package rxjava;

import io.reactivex.Maybe;

public class MaybeEx {

    public static void main(String[] args) {

        Maybe<Integer> presentSource = Maybe.just(100);

        presentSource.subscribe(s -> System.out.println("Process 1 received: " + s),
        Throwable::printStackTrace,
                () -> System.out.println("Process 1 done!"));

        //no emission
        Maybe<Integer> emptySource = Maybe.empty();

        emptySource.subscribe(s -> System.out.println("Process 2 received: " + s),
        Throwable::printStackTrace,
                () -> System.out.println("Process 2 done!"));
    }
}
