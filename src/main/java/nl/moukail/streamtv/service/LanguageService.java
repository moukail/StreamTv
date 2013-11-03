package nl.moukail.streamtv.service;

import java.util.List;

import nl.moukail.streamtv.entity.Language;

public interface LanguageService {
	void addLanguage(Language language);
	List<Language> listLanguage();
	void removeLanguage(Integer id);
	Language get(int id);
}
