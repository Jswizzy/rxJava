package rxjava;

import io.reactivex.Observable;

import java.math.BigDecimal;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class TradingPlatform {

    Observable<BigDecimal> pricesOf(String ticker) {
        return Observable
                .interval(50, MILLISECONDS)
                .flatMap(this::randomDelay)
                .map(this::randomStockPrice)
                .map(BigDecimal::valueOf);
    }

    Observable<Long> randomDelay(long x) {
        return Observable
                .just(x)
                .delay((long) (Math.random() * 100), MILLISECONDS);
    }

    double randomStockPrice(long x) {
        return 100 + Math.random() * 10 +
                (Math.sin(x / 100.0)) * 60.0;
    }

    public static void main(String[] args) throws InterruptedException {

        TradingPlatform tradingPlatform = new TradingPlatform();
        Observable<BigDecimal> prices = tradingPlatform.pricesOf("NFLX");
        Observable<BigDecimal> debounced = prices.debounce(100, MILLISECONDS);

        prices
                .debounce(x -> {
                    boolean goodPrice = x.compareTo(BigDecimal.valueOf(150)) > 0;
                    return Observable
                            .empty()
                            .delay(goodPrice? 100 : 1000, MILLISECONDS);
                });

        prices.subscribe(System.out::println);

        Thread.sleep(10000);
    }
}
