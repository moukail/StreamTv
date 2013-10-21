package nl.moukail.streamtv.service;

import java.util.List;

import nl.moukail.streamtv.dao.VodDAO;
import nl.moukail.streamtv.entity.Vod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("FilmServiceImpl")
@Transactional
public class FilmServiceImpl implements VodService{

	@Autowired
	@Qualifier("FilmDAOImpl")
	private VodDAO vodDAO;
	
	public void addVod(Vod vod) {
		vodDAO.addVod(vod);
	}

	public List<Vod> listVod() {
		return vodDAO.listVod();
	}

	public void removeVod(Integer id) {
		vodDAO.removeVod(id);
	}

	public Vod get(Integer id) {
		return vodDAO.get(id);
	}

}
