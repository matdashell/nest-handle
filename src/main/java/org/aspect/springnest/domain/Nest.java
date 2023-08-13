package org.aspect.springnest.domain;

public interface Nest<T extends Exception, R> extends NestMain {
	R nest(T e);
}
