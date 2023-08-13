package org.aspect.springnest.aspect;

import lombok.RequiredArgsConstructor;
import org.aspect.springnest.domain.Nest;
import org.aspect.springnest.domain.NestMain;
import org.aspect.springnest.domain.NestVoid;
import org.aspect.springnest.domain.ann.NestHandler;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class NestAspect {

	@Around("@annotation(org.aspect.springnest.domain.ann.NestHandler)")
	public Object aroundNestHandler(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			return joinPoint.proceed();
		} catch (Exception e) {
			NestHandler nestHandler = getHandler(joinPoint);
			if (canHandle(nestHandler, e)) {
				return executeNestHandle(nestHandler, e);
			} else {
				throw e;
			}
		}
	}

	private boolean canHandle(NestHandler nestHandler, Exception e) {
		Class<? extends Exception> onException = nestHandler.onException();
		return onException.isAssignableFrom(e.getClass());
	}

	private NestHandler getHandler(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		return method.getAnnotation(NestHandler.class);
	}

	private Object executeNestHandle(NestHandler nestHandler, Exception e) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		NestMain instance = nestHandler.handle().getDeclaredConstructor().newInstance();
		if (implementsNest(nestHandler.handle())) {
			return ((Nest<Exception, Object>) instance).nest(e);
		} else {
			((NestVoid<Exception>) instance).nest(e);
		}
		return null;
	}

	private boolean implementsNest(Class<? extends NestMain> clazz) {
		Class<?>[] interfaces = clazz.getInterfaces();
		for (Class<?> anInterface : interfaces) {
			if (anInterface.isAssignableFrom(Nest.class)) {
				return true;
			}
		}
		return false;
	}
}
