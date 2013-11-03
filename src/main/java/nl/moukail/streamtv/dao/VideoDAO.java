package nl.moukail.streamtv.dao;

import java.util.List;

import nl.moukail.streamtv.entity.Video;

public interface VideoDAO {
	void addVideo(Video video);
	void updateVideo(Video video);
	List<Video> listVideo();
	void removeVideo(Integer id);
	Video get(Integer id);
}
