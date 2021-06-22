package com.nelsw;

import lombok.extern.log4j.Log4j2;

import java.util.stream.Stream;

@Log4j2
public class Main {

    public static void main(String[] args) {

        Stream.of(
                new P1(),
                new P2(),
                new P3(),
                new P4(),
                new P5(),
                new P6(),
                new P60())
                .forEach(p -> log.info(p.message()));
    }


}
