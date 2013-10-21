package nl.moukail.streamtv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="stream")
public class Stream {
	
	@Id
	@Column(name="id")
	@GeneratedValue
	private Integer id;
	
	@Column(name="stream")
	private String streamfile;
	
	@Column(name="bitrate")
	private Integer bitrate;
	
	@Column(name="format")
	private String format;
	
	@Column(name="scheme")
	private String scheme;
	
	@Column(name="player")
	private String player;
	
	@ManyToOne
	@JoinColumn(name="channel_id")
	private Channel channel;
	
	@ManyToOne
	@JoinColumn(name="language_id")
	private Language language;
	
	@ManyToOne
	@JoinColumn(name="country_id")
	private Country country;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getStreamfile() {
		return streamfile;
	}

	public void setStreamfile(String stream) {
		this.streamfile = stream;
	}

	public Integer getBitrate() {
		return bitrate;
	}

	public void setBitrate(Integer bitrate) {
		this.bitrate = bitrate;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getScheme() {
		return scheme;
	}
	
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getPlayer() {
		return player;
	}
	
	public void setPlayer(String player) {
		this.player = player;
	}
	
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

}
