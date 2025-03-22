package org.weather.spring_test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
public class MockSpringTest {

    @InjectMocks
    ClassA classA;

    @Mock
    ClassB classB;


    @Test
    public void testA() {
        //  classA.postConstruct();
        Mockito.doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                System.out.println("Напиши какую то хуйню какую хочешь");
                return null;
            }
        }).when(classB).postConstruct();

        classA.postConstruct();
    }

    @Test
    public void testB() {
        //  classA.postConstruct();
        Mockito.doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                System.out.println("Другая хуйня");
                return null;
            }
        }).when(classB).postConstruct();

        classA.postConstruct();
    }
}
