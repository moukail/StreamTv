package nl.moukail.streamtv.dao;

import java.util.List;

import nl.moukail.streamtv.entity.Episode;

public interface EpisodeDAO {
	public Episode get(Integer id);
	public List<Episode> listEpisode(int vodId);
	public void addEpisode(Episode episode);
	public void updateEpisode(Episode episode);
	public void removeEpisode(Integer id);
}