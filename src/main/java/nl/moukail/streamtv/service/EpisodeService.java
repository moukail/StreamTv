package nl.moukail.streamtv.service;

import java.util.List;

import nl.moukail.streamtv.entity.Episode;

public interface EpisodeService {
	public void addEpisode(Episode episode);
	public List<Episode> listEpisode(Integer vodId);
	public void removeEpisode(Integer id);
	public Episode get(Integer id);
}