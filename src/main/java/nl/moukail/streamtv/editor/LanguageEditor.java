package nl.moukail.streamtv.editor;

import java.beans.PropertyEditorSupport;

import nl.moukail.streamtv.entity.Language;
import nl.moukail.streamtv.service.LanguageService;

public class LanguageEditor extends PropertyEditorSupport {

	private final LanguageService languageService;
	 
    public LanguageEditor(LanguageService languageService) {
        this.languageService = languageService;
    }
 
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Language language = languageService.get(Integer.parseInt(text));
        setValue(language);
	}
	
}
