package org.aspect.springnest;

import lombok.NoArgsConstructor;
import org.aspect.springnest.domain.Nest;
import org.aspect.springnest.domain.NestVoid;

@NoArgsConstructor
public class FakeHandle2 implements Nest<Exception, String> {

    @Override
    public String nest(Exception e) {
        return "FakeHandle2";
    }
}
