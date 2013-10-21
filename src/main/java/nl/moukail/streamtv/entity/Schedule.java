package nl.moukail.streamtv.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author MOUKAIL
 *
 */
@Entity
@Table(name="schedule")
public class Schedule {
	
	@Id
	@Column(name="id")
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="channel_id")
	private Channel channel;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="start")
	private Time start;
	
	@Column(name="timezone")
	private String timezone;
	
	@Column(name="poster")
	private String poster;

	/**
	 * @return the channel
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * @param channel the channel to set
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the start
	 */
	public Time getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Time start) {
		this.start = start;
	}

	/**
	 * @return the start
	 */
	public String getTimeZone() {
		return timezone;
	}

	/**
	 * @param start the start to set
	 */
	public void setTimeZone(String timezone) {
		this.timezone = timezone;
	}
	
	/**
	 * @return the poster
	 */
	public String getPoster() {
		return poster;
	}

	/**
	 * @param poster the poster to set
	 */
	public void setPoster(String poster) {
		this.poster = poster;
	}
}
