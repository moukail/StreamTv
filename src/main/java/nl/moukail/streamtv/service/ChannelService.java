package nl.moukail.streamtv.service;

import java.util.List;

import nl.moukail.streamtv.entity.Channel;

public interface ChannelService {
	void addChannel(Channel channel);
	List<Channel> listChannel();
	void removeChannel(Integer id);
	Channel get(Integer id);
}