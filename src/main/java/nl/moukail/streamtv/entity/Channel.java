package nl.moukail.streamtv.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="channel")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)
public class Channel {

	@Id
	@Column(name="id")
	@GeneratedValue
	private Integer id;

	@Column(name="name")
	private String channelName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="type", insertable = false, updatable = false)
	private String type;
	
	@Column(name="website")
	private String website;
	
	@Column(name="logo")
	private String logo;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="country_id")
	private Country country;
	
	@OneToMany(mappedBy="channel", cascade=CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Stream> streams = new ArrayList<Stream>();
	
	@OneToMany(mappedBy="channel", cascade=CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Schedule> epgs = new ArrayList<Schedule>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Collection<Stream> getStreams() {
		return streams;
	}
	
	/*public Collection<Epg> getEpgs() {
		return epgs;
	}

	public void setEpg(Collection<Epg> epgs) {
		this.epgs = epgs;
	}*/
}
