package org.weather.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.weather.dto.LocationDto;
import org.weather.entity.Location;
import org.weather.entity.Session;
import org.weather.entity.User;
import org.weather.mapper.LocationMapper;
import org.weather.repository.LocationRepository;
import org.weather.repository.SessionRepository;
import org.weather.repository.UserRepository;
import org.weather.service.LocationService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("locationServiceImpl")
@Qualifier("locationsServiceImpl")
@Slf4j
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final SessionRepository sessionRepository;


    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, SessionRepository sessionRepository) {
        this.locationRepository = locationRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    @Transactional(readOnly = true) //todo почему без него не работает??? Причем тут прокси?
    public List<Location> listLocationsByUserId(int id) {
        return null;

    }

    @Override
    public LocationDto findLocationByCoordinates(int lat, int lon) {
        return null;
    }

    @Override
    @Transactional
    public void addLocation(LocationDto locationDto, String userCookies) {
        Optional<Session> session = sessionRepository.findBySessionId(UUID.fromString(userCookies));

        if (session.isEmpty()) {
            throw new UsernameNotFoundException("Сессии нет");
        }

        User user = session.get().getUser();
        log.info("addLocation {}", user.toString());

        Location location = LocationMapper.INSTANCE.toEntity(locationDto);

        location.setUser(user);
        locationRepository.save(location);
    }

    @Override
    @Transactional
    public void deleteLocation(String cookie, String lat, String lon) {
        User user = sessionRepository.findBySessionId(UUID.fromString(cookie))
                .orElseThrow(() -> new IllegalStateException("Пользователь не найден"))
                .getUser();

        BigDecimal latitude = new BigDecimal(lat);
        BigDecimal longitude = new BigDecimal(lon);

        locationRepository.deleteByUserIdAndCoordinates(user, latitude, longitude);
    }
}
