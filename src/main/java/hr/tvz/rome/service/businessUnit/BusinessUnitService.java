package hr.tvz.rome.service.businessUnit;

import java.util.List;

import hr.tvz.rome.model.BusinessUnit;

public interface BusinessUnitService {
	
	List<BusinessUnit> findAll();
	
    BusinessUnit findByUsername(String username);

}
