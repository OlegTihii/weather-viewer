package org.weather.exceptions;

public class LocationAlreadyExistException extends RuntimeException {
    public LocationAlreadyExistException(String location) {
        super(location);
    }
}
