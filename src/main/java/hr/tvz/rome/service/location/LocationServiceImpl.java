package hr.tvz.rome.service.location;

import hr.tvz.rome.model.Location;
import hr.tvz.rome.model.Project;
import hr.tvz.rome.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marko on 18.6.2016..
 */
@Service
public class LocationServiceImpl implements LocationService {


    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location findByName(String name) {
        return locationRepository.findByName(name);
    }

    @Override
    public Location create(Location location) {
        return locationRepository.saveAndFlush(location);
    }

    @Override
    public Location findOne(Long id) {
        return locationRepository.findOne(id);
    }

    @Override
    public void delete(Location location) {
        locationRepository.delete(location);
    }
}
