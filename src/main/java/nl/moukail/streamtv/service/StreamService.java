 package nl.moukail.streamtv.service;

import java.util.List;

import nl.moukail.streamtv.entity.Stream;

public interface StreamService {
	void addStream(Stream stream);
	List<Stream> listStream(int channelId);
	void removeStream(Integer id);
	Stream get(Integer id);
	void fetchYoutubeStreams();
	void testFetch();
}
