package org.aspect.springnest;

import org.aspect.springnest.domain.ann.NestHandler;
import org.springframework.stereotype.Service;

@Service
public class FakeService {

    @NestHandler(handle = FakeHandle.class)
    public void test() {
        throw new RuntimeException("test");
    }

    @NestHandler(handle = FakeHandle2.class)
    public String test2() {
        throw new RuntimeException("test2");
    }
}
