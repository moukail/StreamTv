package nl.moukail.streamtv.service;

import java.util.List;

import nl.moukail.streamtv.dao.CountryDAO;
import nl.moukail.streamtv.entity.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDAO countryDAO;
	
	
	public void addCountry(Country country) {
		countryDAO.addCountry(country);
	}

	public List<Country> listCountry() {
		return countryDAO.listCountry();
	}

	public void removeCountry(Integer id) {
		countryDAO.removeCountry(id);
	}

	public Country get(Integer id) {
		return countryDAO.get(id);
	}

}
