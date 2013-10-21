package nl.moukail.streamtv.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="episode")
public class Episode {

	@Id
	@Column(name="id")
	@GeneratedValue
	private Integer id;
	
	@Column(name="season")
	private Integer season;
	
	@Column(name="episode")
	private Integer episode;
	
	@Column(name="subtitle")
	private String subtitle;
	
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy="episode", cascade=CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Video> videos = new ArrayList<Video>();
	
	@ManyToOne
	@JoinColumn(name="vod_id")
	private Vod vod;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getSeason() {
		return season;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}
	
	public Integer getEpisode() {
		return episode;
	}

	public void setEpisode(Integer episode) {
		this.episode = episode;
	}
	
	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Collection<Video> getVideos() {
		return videos;
	}
	
	public Vod getVod() {
		return vod;
	}

	public void setVod(Vod vod) {
		this.vod = vod;
	}
}
