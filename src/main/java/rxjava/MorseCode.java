package rxjava;

import io.reactivex.Observable;

import static io.reactivex.Observable.empty;
import static io.reactivex.Observable.just;
import static rxjava.MorseCode.Sound.DAH;
import static rxjava.MorseCode.Sound.DI;

public class MorseCode {

    enum Sound {DI, DAH}

    Observable<Sound> toMorseCode(char ch) {
        switch (ch) {
            case 'a':
                return just(DI, DAH);
            case 'b':
                return just(DAH, DI, DI, DI);
            case 'c':
                return just(DAH, DI, DAH, DI);
            //...
            case 'p':
                return just(DI, DAH, DAH, DI);
            case 'r':
                return just(DI, DAH, DI);
            case 's':
                return just(DI, DI, DI);
            case 't':
                return just(DAH);
            //...
            default:
                return empty();
        }
    }

    Observable<Sound> fromObservable(Observable<Character> characterObservable){
        return characterObservable
                .map(Character::toLowerCase)
                .flatMap(this::toMorseCode);
    }


    public static void main(String[] args) {

        Observable<Character> message = just('S', 'p', 'a', 'r', 't', 'a');

        new MorseCode().fromObservable(message).subscribe(System.out::println);

    }
}

