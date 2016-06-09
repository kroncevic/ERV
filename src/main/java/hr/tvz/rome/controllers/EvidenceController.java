package hr.tvz.rome.controllers;

import hr.tvz.rome.controllers.entities.EvidenceRequest;
import hr.tvz.rome.model.*;
import hr.tvz.rome.model.decorators.EvidenceDecorator;
import hr.tvz.rome.repository.*;
import hr.tvz.rome.utilities.DateTimeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Marko on 4.6.2016..
 */
@RestController
@RequestMapping("/rest")
public class EvidenceController {

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private EvidenceTypeRepository evidenceTypeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private EvidenceRepository evidenceRepository;

    @RequestMapping(value = "/evidence", method = RequestMethod.POST)
    public ResponseEntity<EvidenceRequest> create(@RequestBody EvidenceRequest request) {
        Employee employee = employeesRepository.findByUsername(request.getUsername());

        EvidenceType evidenceType = evidenceTypeRepository.findByName(request.getType());

        Project project = projectRepository.findByName(request.getProject());
        if (project == null) {
            project = new Project(request.getProject());
            project = projectRepository.saveAndFlush(project);
        }

        Location location = locationRepository.findByName(request.getLocation());
        if (location == null) {
            location = new Location(request.getLocation());
            location = locationRepository.saveAndFlush(location);
        }

        EvidenceNew evidenceNew = new EvidenceNew(employee, project, location, new Date(), evidenceType);

        evidenceRepository.saveAndFlush(evidenceNew);

        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @RequestMapping(value = "/evidence/", method = RequestMethod.GET)
    public List<EvidenceDecorator> list() {
        List<EvidenceDecorator> evidenceDecorators = new ArrayList<>();
        evidenceRepository.findAll().forEach(evidence -> evidenceDecorators.add(new EvidenceDecorator(evidence)));
        return evidenceDecorators;
    }

    @RequestMapping(value = "/evidence/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EvidenceDecorator>> getUserEvidences(@PathVariable("username") String username) {
        Employee employee = employeesRepository.findByUsername(username);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<EvidenceDecorator> evidenceDecorators = new ArrayList<>();
        evidenceRepository.findByEmployee(employee).forEach(evidence -> evidenceDecorators.add(new EvidenceDecorator(evidence)));
        return new ResponseEntity<>(evidenceDecorators, HttpStatus.OK);
    }

    @RequestMapping(value = "/evidence/today", method = RequestMethod.GET)
    public List<EvidenceDecorator> listToday() {
        List<EvidenceDecorator> evidenceDecorators = new ArrayList<>();
        evidenceRepository.findByTimestampGreaterThanEqual(DateTimeBuilder.fromDateTime(DateTimeBuilder.now().buildDateTime().getStartOfDay()).buildDate()).forEach(evidence -> evidenceDecorators.add(new EvidenceDecorator(evidence)));
        return evidenceDecorators;
    }

    @RequestMapping(value = "/evidence/latest/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EvidenceDecorator> findLatestUserEvidence(@PathVariable("username") String username) {
        Employee employee = employeesRepository.findByUsername(username);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new EvidenceDecorator(evidenceRepository.findFirstByEmployeeOrderByTimestampDesc(employee)), HttpStatus.OK);
    }

}
