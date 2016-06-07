package hr.tvz.rome.repository;

import hr.tvz.rome.model.Location;
import hr.tvz.rome.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Location findByName(String name);

}
