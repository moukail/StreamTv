package nl.moukail.streamtv.dao;

import java.util.List;

import nl.moukail.streamtv.entity.Language;

public interface LanguageDAO {

	void addLanguage(Language language);
	List<Language> listLanguage();
	void removeLanguage(Integer id);
	Language get(int id);

}
