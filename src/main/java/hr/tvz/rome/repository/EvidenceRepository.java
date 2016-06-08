package hr.tvz.rome.repository;

import hr.tvz.rome.model.Employee;
import hr.tvz.rome.model.EvidenceNew;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EvidenceRepository extends JpaRepository<EvidenceNew, Long> {

    List<EvidenceNew> findByEmployee(Employee employee);
    List<EvidenceNew> findByTimestampGreaterThanEqual(Date date);

}
