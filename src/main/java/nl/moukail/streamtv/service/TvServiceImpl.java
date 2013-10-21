package nl.moukail.streamtv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.moukail.streamtv.dao.ChannelDAO;
import nl.moukail.streamtv.entity.Channel;

@Service
@Qualifier("TvServiceImpl")
@Transactional
public class TvServiceImpl implements ChannelService {
	
	@Autowired
	@Qualifier("TvDAOImpl")
	private ChannelDAO channelDAO;
	
	@Autowired
	UserService userService;
	
	public void addChannel(Channel channel) {
		channelDAO.addChannel(channel);
		userService.getChannels().add(channel);
	}

	public List<Channel> listChannel() {
		return channelDAO.listChannel();
	}

	public void removeChannel(Integer id) {
		channelDAO.removeChannel(id);
	}

	public Channel get(Integer id) {
		return channelDAO.get(id);
	}
}
