package nl.moukail.streamtv.dao;

import java.util.List;

import nl.moukail.streamtv.entity.Episode;

public interface EpisodeDAO {
	Episode get(Integer id);
	List<Episode> listEpisode(int vodId);
	void addEpisode(Episode episode);
	void updateEpisode(Episode episode);
	void removeEpisode(Integer id);
}