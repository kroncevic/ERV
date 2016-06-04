package hr.tvz.rome.controllers;

import hr.tvz.rome.model.Evidence;
import hr.tvz.rome.repository.EvidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Marko on 4.6.2016..
 */
@RestController
@RequestMapping("/rest")
public class EvidenceController {

    @Autowired
    private EvidenceRepository evidenceRepository;

    @RequestMapping(value = "/evidence", method = RequestMethod.POST)
    public Evidence create(@RequestBody Evidence evidence) {
        return evidenceRepository.saveAndFlush(evidence);
    }

}
