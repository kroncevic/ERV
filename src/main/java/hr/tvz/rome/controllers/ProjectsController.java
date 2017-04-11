package hr.tvz.rome.controllers;

import hr.tvz.rome.model.Project;
import hr.tvz.rome.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class ProjectsController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/project/", method = RequestMethod.GET)
    public List<Project> list() {
        return projectService.findAll();
    }

    @RequestMapping(value = "/project/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Project getProject(@PathVariable("name") String name) {
        return projectService.findByName(name);
    }

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public ResponseEntity<Project> createProject(@RequestBody Project request) {
        return new ResponseEntity<>(projectService.create(request), HttpStatus.OK);
    }

    @RequestMapping(value = "/project/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Project> deleteProject(@PathVariable Long id) {
        Project project = projectService.findOne(id);
        try{
        projectService.delete(project);}
        catch (Exception e){
            return new ResponseEntity<>(project, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

}
