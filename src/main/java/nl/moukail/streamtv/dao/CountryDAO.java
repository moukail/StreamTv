package nl.moukail.streamtv.dao;

import java.util.List;

import nl.moukail.streamtv.entity.Country;

public interface CountryDAO {
	
	public void addCountry(Country country);
	public List<Country> listCountry();
	public void removeCountry(Integer id);
	public Country get(int id);
	
}