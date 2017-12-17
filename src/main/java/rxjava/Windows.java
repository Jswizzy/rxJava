package rxjava;

import io.reactivex.Observable;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static io.reactivex.Observable.just;

public class Windows {

    public static void main(String[] args) {

        Random random = new Random();
        Observable
                .defer(() -> just(random.nextGaussian()))
                .repeat(1000)
                .buffer(100, 1)
                .map(Windows::averageOfList)
                .subscribe(System.out::println);

    }

    static double averageOfList(List<Double> list) {
        return list
                .stream()
                .collect(Collectors.averagingDouble(x -> x));
    }
}
