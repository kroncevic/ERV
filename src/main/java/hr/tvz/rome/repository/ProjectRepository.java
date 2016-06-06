package hr.tvz.rome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.tvz.rome.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
