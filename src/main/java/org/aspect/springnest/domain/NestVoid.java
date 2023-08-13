package org.aspect.springnest.domain;

public interface NestVoid<T extends Exception> extends NestMain {
    void nest(T e);
}
