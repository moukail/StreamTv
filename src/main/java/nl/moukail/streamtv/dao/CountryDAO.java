package nl.moukail.streamtv.dao;

import java.util.List;

import nl.moukail.streamtv.entity.Country;

public interface CountryDAO {
	
	void addCountry(Country country);
	List<Country> listCountry();
	void removeCountry(Integer id);
	Country get(int id);
	
}