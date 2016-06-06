package hr.tvz.rome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.tvz.rome.model.Vacation;

public interface VacationRepository extends JpaRepository<Vacation, Long> {

}
