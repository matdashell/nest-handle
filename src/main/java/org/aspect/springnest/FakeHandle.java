package org.aspect.springnest;

import lombok.NoArgsConstructor;
import org.aspect.springnest.domain.NestVoid;

@NoArgsConstructor
public class FakeHandle implements NestVoid<Exception> {

    @Override
    public void nest(Exception e) {
        System.out.println("FakeHandle");
    }
}
