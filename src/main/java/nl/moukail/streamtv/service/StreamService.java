 package nl.moukail.streamtv.service;

import java.util.List;

import nl.moukail.streamtv.entity.Stream;

public interface StreamService {
	public void addStream(Stream stream);
	public List<Stream> listStream(int channelId);
	public void removeStream(Integer id);
	public Stream get(Integer id);
	public void fetchYoutubeStreams();
	public void testFetch();
}
