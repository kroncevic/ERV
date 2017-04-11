package hr.tvz.rome.service.project;

import hr.tvz.rome.model.Project;
import hr.tvz.rome.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {


    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findByName(String name) {
        return projectRepository.findByName(name);
    }

    @Override
    public Project create(Project project) {
        return projectRepository.saveAndFlush(project);
    }

    @Override
    public Project findOne(Long id) {
        return projectRepository.findOne(id);
    }

    @Override
    public void delete(Project project) {
        projectRepository.delete(project);
    }
}
