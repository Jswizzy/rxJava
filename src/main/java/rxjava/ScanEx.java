package rxjava;

import io.reactivex.Observable;

import java.math.BigInteger;

public class ScanEx {

    public static void main(String[] args) throws InterruptedException {

        Observable<BigInteger> factorials = Observable
                .range(2, 100)
                .scan(BigInteger.ONE, (big, cur) ->
                        big.multiply(BigInteger.valueOf(cur)));

        factorials.subscribe(System.out::println);
    }
}
