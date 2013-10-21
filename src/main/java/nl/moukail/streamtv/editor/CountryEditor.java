package nl.moukail.streamtv.editor;

import java.beans.PropertyEditorSupport;

import nl.moukail.streamtv.entity.Country;
import nl.moukail.streamtv.service.CountryService;

public class CountryEditor extends PropertyEditorSupport {
	
	private final CountryService countryService;
	 
    public CountryEditor(CountryService countryService) {
        this.countryService = countryService;
    }
 
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Country country = countryService.get(Integer.parseInt(text));
        setValue(country);
	}
}
