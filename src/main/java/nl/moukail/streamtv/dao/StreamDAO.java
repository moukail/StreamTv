package nl.moukail.streamtv.dao;

import java.util.List;

import nl.moukail.streamtv.entity.Stream;

public interface StreamDAO {
	public Stream get(Integer id);
	public List<Stream> listStream(int channelId);
	public void addStream(Stream stream);
	public void updateStream(Stream stream);
	public void removeStream(Integer id);
}
