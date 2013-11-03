package nl.moukail.streamtv.service;

import java.util.List;

import nl.moukail.streamtv.entity.Episode;

public interface EpisodeService {
	void addEpisode(Episode episode);
	List<Episode> listEpisode(Integer vodId);
	void removeEpisode(Integer id);
	Episode get(Integer id);
}