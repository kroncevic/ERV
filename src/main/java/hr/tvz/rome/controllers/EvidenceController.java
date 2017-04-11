package hr.tvz.rome.controllers;

import hr.tvz.rome.controllers.entities.EvidenceRequest;
import hr.tvz.rome.model.EvidenceNew;
import hr.tvz.rome.model.Project;
import hr.tvz.rome.model.decorators.EvidenceDecorator;
import hr.tvz.rome.service.evidence.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class EvidenceController {

    @Autowired
    EvidenceService evidenceService;

    @RequestMapping(value = "/evidence", method = RequestMethod.POST)
    public ResponseEntity<EvidenceNew> create(@RequestBody EvidenceRequest request) {
        return new ResponseEntity<>(evidenceService.create(request), HttpStatus.OK);
    }

    @RequestMapping(value = "/evidence/", method = RequestMethod.GET)
    public List<EvidenceDecorator> list() {
        return evidenceService.findAll();
    }

    @RequestMapping(value = "/evidence/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EvidenceDecorator> getUserEvidences(@PathVariable("username") String username) {
        return evidenceService.findByUsername(username);
    }

    @RequestMapping(value = "/evidence/today", method = RequestMethod.GET)
    public List<EvidenceDecorator> listToday() {
        return evidenceService.findToday();
    }

    @RequestMapping(value = "/evidence/latest/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public EvidenceDecorator findLatestUserEvidence(@PathVariable("username") String username) {
        return evidenceService.findLatestUserEvidence(username);
    }

    @RequestMapping(value = "/evidence/{uuid}", method = RequestMethod.DELETE)
    public ResponseEntity<EvidenceNew> deleteEvidence(@PathVariable String uuid) {
        EvidenceNew evidenceNew = evidenceService.findOneByUniqueId(uuid);
        try{
            evidenceService.delete(evidenceNew);}
        catch (Exception e){
            return new ResponseEntity<>(evidenceNew, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(evidenceNew, HttpStatus.OK);
    }

}
