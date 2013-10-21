package nl.moukail.streamtv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.moukail.streamtv.dao.LanguageDAO;
import nl.moukail.streamtv.entity.Language;

@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageDAO languageDAO;
	
	public void addLanguage(Language language) {
		languageDAO.addLanguage(language);
	}
	
	public List<Language> listLanguage() {
		return languageDAO.listLanguage();
	}

	public void removeLanguage(Integer id) {
		languageDAO.removeLanguage(id);
	}

	public Language get(int id) {
		return languageDAO.get(id);
	}

}
