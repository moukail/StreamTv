package nl.moukail.streamtv.service;

import java.util.List;

import nl.moukail.streamtv.entity.Language;

public interface LanguageService {
	public void addLanguage(Language language);
	public List<Language> listLanguage();
	public void removeLanguage(Integer id);
	public Language get(int id);
}
