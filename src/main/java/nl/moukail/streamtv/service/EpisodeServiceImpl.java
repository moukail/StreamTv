package nl.moukail.streamtv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.moukail.streamtv.dao.EpisodeDAO;
import nl.moukail.streamtv.entity.Episode;

@Service
@Transactional
public class EpisodeServiceImpl implements EpisodeService{

	@Autowired
	private EpisodeDAO episodeDAO;
	
	public void addEpisode(Episode episode) {
		episodeDAO.addEpisode(episode);
	}

	public List<Episode> listEpisode(Integer vodId) {
		return episodeDAO.listEpisode(vodId);
	}

	public void removeEpisode(Integer id) {
		episodeDAO.removeEpisode(id);
	}

	public Episode get(Integer id) {
		return episodeDAO.get(id);
	}
	
}
