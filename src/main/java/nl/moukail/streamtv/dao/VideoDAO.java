package nl.moukail.streamtv.dao;

import java.util.List;

import nl.moukail.streamtv.entity.Video;

public interface VideoDAO {
	public void addVideo(Video video);
	public void updateVideo(Video video);
	public List<Video> listVideo();
	public void removeVideo(Integer id);
	public Video get(Integer id);
}
