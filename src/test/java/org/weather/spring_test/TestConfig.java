package org.weather.spring_test;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;

@Configuration
public class TestConfig {

    @Bean
    public ClassA classA(ClassB mockClassB) {
        return new ClassA(mockClassB);
    }

    @Bean
    public ClassB classB() {
        return new ClassB();
    }

    @Bean
    @Primary
    public ClassB mockClassB() {
        ClassB mock = Mockito.mock(ClassB.class);
        Mockito.doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                System.out.println("called with arguments: " + Arrays.toString(args));
                return null;
            }
        }).when(mock).postConstruct();
        return mock;
    }
}
