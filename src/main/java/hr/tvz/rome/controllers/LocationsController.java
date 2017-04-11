package hr.tvz.rome.controllers;

import hr.tvz.rome.model.Location;
import hr.tvz.rome.service.location.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class LocationsController {

    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "/location/", method = RequestMethod.GET)
    public List<Location> listProjects() {
        return locationService.findAll();
    }

    @RequestMapping(value = "/location/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Location getProject(@PathVariable("name") String name) {
        return locationService.findByName(name);
    }

    @RequestMapping(value = "/location", method = RequestMethod.POST)
    public ResponseEntity<Location> createProject(@RequestBody Location request) {
        return new ResponseEntity<>(locationService.create(request), HttpStatus.OK);
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Location> deleteProject(@PathVariable Long id) {
        Location location = locationService.findOne(id);
        try {
            locationService.delete(location);
        } catch (Exception e) {
            return new ResponseEntity<>(location, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(location, HttpStatus.OK);
    }


}
