package org.example.ioc;

public class Container {

    public <T> T get(Class<T> clazz) {
        return (T) clazz;
    }
}
