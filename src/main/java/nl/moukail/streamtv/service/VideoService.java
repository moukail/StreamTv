package nl.moukail.streamtv.service;

import java.util.List;

import nl.moukail.streamtv.entity.Video;

public interface VideoService {
	public void addVideo(Video video);
	public void updateVideo(Video video);
	public List<Video> listVideo();
	public void removeVideo(Integer id);
	public void fetchYoutubeVideos();
	public Video get(Integer id);
}
