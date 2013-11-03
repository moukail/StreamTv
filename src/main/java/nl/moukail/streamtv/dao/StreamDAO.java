package nl.moukail.streamtv.dao;

import java.util.List;

import nl.moukail.streamtv.entity.Stream;

public interface StreamDAO {
	Stream get(Integer id);
	List<Stream> listStream(int channelId);
	void addStream(Stream stream);
	void updateStream(Stream stream);
	void removeStream(Integer id);
}
