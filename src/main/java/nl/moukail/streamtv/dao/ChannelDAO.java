package nl.moukail.streamtv.dao;

import java.util.List;

import nl.moukail.streamtv.entity.Channel;

public interface ChannelDAO {
	void addChannel(Channel channel);
	List<Channel> listChannel();
	void removeChannel(Integer id);
	Channel get(Integer id);
}