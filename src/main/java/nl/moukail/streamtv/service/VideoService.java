package nl.moukail.streamtv.service;

import java.util.List;

import nl.moukail.streamtv.entity.Video;

public interface VideoService {
	void addVideo(Video video);
	void updateVideo(Video video);
	List<Video> listVideo();
	void removeVideo(Integer id);
	void fetchYoutubeVideos();
	Video get(Integer id);
}
