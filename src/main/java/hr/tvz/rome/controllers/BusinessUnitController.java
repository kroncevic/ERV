package hr.tvz.rome.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.rome.model.BusinessUnit;
import hr.tvz.rome.model.Employee;
import hr.tvz.rome.service.businessUnit.BusinessUnitService;

@RestController
@RequestMapping("/rest")
public class BusinessUnitController {
	
	@Autowired
	BusinessUnitService businessUnitService;
	
    @RequestMapping(value = "/businessUnit/", method = RequestMethod.GET)
    public List<BusinessUnit> listAllBusinessUnits() {
        return businessUnitService.findAll();
    }

    @RequestMapping(value = "/businessUnit/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusinessUnit getUser(@PathVariable("username") String username) {
        return businessUnitService.findByUsername(username);
    }

}
