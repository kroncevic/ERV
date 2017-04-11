package hr.tvz.rome.service.evidence;

import hr.tvz.rome.controllers.entities.EvidenceRequest;
import hr.tvz.rome.model.*;
import hr.tvz.rome.model.decorators.EvidenceDecorator;
import hr.tvz.rome.repository.*;
import hr.tvz.rome.utilities.DateTimeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EvidenceServiceImpl implements EvidenceService {

    private static final String prijava = "Prijava";
    private static final String odjava = "Odjava";

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

    @Override
    public EvidenceNew create(EvidenceRequest request) {
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

        String uniqueId;

        if (evidenceType.getName().equals("Odjava")) {
            uniqueId = request.getUniqueId();
        } else {
            uniqueId = UUID.randomUUID().toString();
        }

        EvidenceNew evidenceNew = new EvidenceNew(employee, project, location, new Date(), evidenceType, uniqueId);

        return evidenceRepository.saveAndFlush(evidenceNew);
    }

    @Override
    public List<EvidenceDecorator> findAll() {

        return createFromDatabaseList(evidenceRepository.findAll());
    }

    @Override
    public List<EvidenceDecorator> findByUsername(String username) {
        Employee employee = employeesRepository.findByUsername(username);
        if (employee == null) {
            return null;
        }
        return createFromDatabaseList(evidenceRepository.findByEmployee(employee));
    }

    @Override
    public EvidenceDecorator findByUniqueId(String uuid) {
        return new EvidenceDecorator(evidenceRepository.findFirstByUniqueId(uuid));
    }

    @Override
    public List<EvidenceDecorator> findToday() {
        return createFromDatabaseList(evidenceRepository.findByTimestampGreaterThanEqual(DateTimeBuilder.fromDateTime(DateTimeBuilder.now().buildDateTime().getStartOfDay()).buildDate()));

    }

    @Override
    public EvidenceDecorator findLatestUserEvidence(String username) {
        Employee employee = employeesRepository.findByUsername(username);
        if (employee == null) {
            return null;
        }
        return new EvidenceDecorator(evidenceRepository.findFirstByEmployeeOrderByTimestampDesc(employee));
    }

    private List<EvidenceDecorator> createFromDatabaseList(List<EvidenceNew> evidenceNews){

        Map<String, EvidenceDecorator> evidenceMap = new HashMap<>();

        evidenceNews.forEach(evidence -> {

            if(evidence.getType().getName().equals(prijava)){
                evidenceMap.put(evidence.getUniqueId(), new EvidenceDecorator(evidence));
            } else if(evidenceMap.containsKey(evidence.getUniqueId())){
                evidenceMap.get(evidence.getUniqueId()).setSignOutTimestampFromDate(evidence.getTimestamp());
            }
        });


        return new ArrayList<>(evidenceMap.values());
    }

    @Override
    public EvidenceNew findOne(Long id) {
        return evidenceRepository.findOne(id);
    }

    @Override
    public EvidenceNew findOneByUniqueId(String uuid) {
        return evidenceRepository.findFirstByUniqueId(uuid);
    }

    @Override
    public void delete(EvidenceNew evidenceNew) {
        evidenceRepository.delete(evidenceNew);
    }

}
