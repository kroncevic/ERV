package hr.tvz.rome.service.location;

import hr.tvz.rome.model.Location;
import hr.tvz.rome.model.Project;

import java.util.List;

public interface LocationService {

    List<Location> findAll();

    Location findByName(String name);

    Location create(Location location);

    Location findOne(Long id);

    void delete(Location location);

}
