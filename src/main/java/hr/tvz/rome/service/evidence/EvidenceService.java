package hr.tvz.rome.service.evidence;

import hr.tvz.rome.controllers.entities.EvidenceRequest;
import hr.tvz.rome.model.EvidenceNew;
import hr.tvz.rome.model.decorators.EvidenceDecorator;

import java.util.List;

/**
 * Created by Marko on 11.6.2016..
 */
public interface EvidenceService {

    EvidenceNew create(EvidenceRequest evidenceRequest);

    List<EvidenceDecorator> findAll();

    List<EvidenceDecorator> findByUsername(String username);

    EvidenceDecorator findByUniqueId(String uuid);

    List<EvidenceDecorator> findToday();

    EvidenceDecorator findLatestUserEvidence(String username);

    EvidenceNew findOne(Long id);

    EvidenceNew findOneByUniqueId(String uuid);

    void delete(EvidenceNew evidenceNew);

}
