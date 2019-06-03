package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to transform values.
 *
 * @author Sebastien Deleuze
 */
public class Part04Transform {


    Mono<User> capitalizeOne(Mono<User> mono) {
        return mono.map(u -> new User(u.getUsername().toUpperCase(),
                u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
    }


    Flux<User> capitalizeMany(Flux<User> flux) {
        return flux.map(u -> new User(u.getUsername().toUpperCase(),
                u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
    }


    Flux<User> asyncCapitalizeMany(Flux<User> flux) {
        return flux.flatMap(u -> Mono.just(new User(u.getUsername().toUpperCase(),
                u.getFirstname().toUpperCase(), u.getLastname().toUpperCase())));
    }

    Mono<User> asyncCapitalizeUser(User u) {
        return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
    }

}
