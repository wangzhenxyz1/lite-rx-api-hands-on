/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.pivotal.literx;

import java.time.Duration;
import java.util.function.Supplier;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Learn how to use StepVerifier to test Mono, Flux or any other kind of Reactive Streams Publisher.
 *
 * @author Sebastien Deleuze
 */
public class Part03StepVerifier {


    void expectFooBarComplete(Flux<String> flux) {
        StepVerifier.create(flux).expectSubscription().expectNext("foo", "bar").expectComplete().verify();
    }


    void expectFooBarError(Flux<String> flux) {
        StepVerifier.create(flux).expectSubscription().expectNext("foo", "bar").expectError().verify();
    }


    void expectSkylerJesseComplete(Flux<User> flux) {
        StepVerifier.create(flux).expectSubscription()
                .expectNextMatches(u -> "swhite".equalsIgnoreCase(u.getUsername()))
                .expectNextMatches(u -> "jpinkman".equalsIgnoreCase(u.getUsername()))
                .expectComplete()
                .verify();
    }


    void expect10Elements(Flux<Long> flux) {
        StepVerifier.create(flux).expectSubscription()
                .expectNextCount(10)
                .expectComplete()
                .verify();
    }


    void expect3600Elements(Supplier<Flux<Long>> supplier) {
        StepVerifier.withVirtualTime(supplier)
                .expectSubscription()
                .thenAwait(Duration.ofHours(1))
                .expectNextCount(3600)
                .expectComplete()
                .verify();
    }

    private void fail() {
        throw new AssertionError("workshop not implemented");
    }

}
