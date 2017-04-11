package hr.tvz.rome.service.project;

import hr.tvz.rome.model.Project;

import java.util.List;

public interface ProjectService {

    List<Project> findAll();

    Project findByName(String name);

    Project create(Project project);

    Project findOne(Long id);

    void delete(Project project);

}
