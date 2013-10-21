package nl.moukail.streamtv.service;

import java.util.List;

import nl.moukail.streamtv.entity.Country;

public interface CountryService {
	public void addCountry(Country country);
	public List<Country> listCountry();
	public void removeCountry(Integer id);
	public Country get(Integer id);
}
