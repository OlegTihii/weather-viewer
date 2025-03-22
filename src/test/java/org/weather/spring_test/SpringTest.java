package org.weather.spring_test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class SpringTest {

    @Autowired
    AbstractZaebalsIaMAN classA;

    @Test
    public void testA() {
        classA.postConstruct();
    }


}
