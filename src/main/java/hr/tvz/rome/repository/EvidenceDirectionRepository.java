package hr.tvz.rome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.tvz.rome.model.BusinessUnit;
import hr.tvz.rome.model.EvidenceDirection;

public interface EvidenceDirectionRepository extends JpaRepository<EvidenceDirection, Long> {

}
