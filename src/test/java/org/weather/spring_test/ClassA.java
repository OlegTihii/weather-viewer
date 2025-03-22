package org.weather.spring_test;

public class ClassA implements AbstractZaebalsIaMAN {

    public ClassB classB;

    public ClassA(ClassB classB) {
        System.out.println("Я заебался в конструкторе");
        this.classB = classB;
    }

    public void postConstruct() {
        System.out.println("Я заебался");
        classB.postConstruct();
    }
}
