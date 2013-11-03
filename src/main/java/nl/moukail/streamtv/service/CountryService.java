package nl.moukail.streamtv.service;

import java.util.List;

import nl.moukail.streamtv.entity.Country;

public interface CountryService {
	void addCountry(Country country);
	List<Country> listCountry();
	void removeCountry(Integer id);
	Country get(Integer id);
}
