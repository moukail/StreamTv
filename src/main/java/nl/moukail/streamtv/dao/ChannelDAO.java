package nl.moukail.streamtv.dao;

import java.util.List;

import nl.moukail.streamtv.entity.Channel;

public interface ChannelDAO {
	public void addChannel(Channel channel);
	public List<Channel> listChannel();
	public void removeChannel(Integer id);
	public Channel get(Integer id);
}