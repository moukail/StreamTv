package nl.moukail.streamtv.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Length;


/**
 * @author MOUKAIL
 *
 */
@Entity
@Table(name="language")
public class Language {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="name")
	private String languageName;
	
	@Column(name="code")
	@Length(max=3, min=3, message="max 3 letters")
	private String languageCode;
	
	/*@OneToMany(mappedBy="language", cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.TRUE)
	private Collection<Channel> channels = new ArrayList<Channel>();*/
	
	public String getLanguageName() {
		return languageName;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	
	/*public Collection<Channel> getChannels() {
		return channels;
	}
	public void setChannels(Collection<Channel> channels) {
		this.channels = channels;
	}*/
}
