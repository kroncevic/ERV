package hr.tvz.rome.service.businessUnit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.rome.model.BusinessUnit;
import hr.tvz.rome.model.Employee;
import hr.tvz.rome.repository.BusinessUnitRepository;
import hr.tvz.rome.repository.EmployeesRepository;

@Service
public class BusinessUnitServiceImpl implements BusinessUnitService {
	
    @Autowired
    private EmployeesRepository employeesRepository;
    
    @Autowired
    private BusinessUnitRepository businessUnitRepository;

	@Override
	public List<BusinessUnit> findAll() {

		List<BusinessUnit> businessUnits = businessUnitRepository.findAll();
		
		if (businessUnits.isEmpty())
			return null;
		else
			return businessUnits;
		
	}

	@Override
	public BusinessUnit findByUsername(String username) {

        Employee employee = employeesRepository.findByUsername(username);
        if (employee == null)
            return null;
        else
        	return employee.getBusinessUnit();
	}

}
