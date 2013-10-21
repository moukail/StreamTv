/**
 * 
 */
package nl.moukail.streamtv.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author MOUKAIL
 *
 */
@Entity
@Table(name="country")
public class Country {

	@Id
	@Column(name="id")
	@GeneratedValue
	private Integer id;

	@Column(name="name", unique=true)
	private String countryName;
	
	@Column(name="code", unique=true)
	private String countryCode;
	
	@OneToMany(mappedBy="country", cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.TRUE)
	private Collection<Channel> channels = new ArrayList<Channel>();
	
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public Collection<Channel> getChannels() {
		return channels;
	}
	public void setChannels(Collection<Channel> channels) {
		this.channels = channels;
	}
}