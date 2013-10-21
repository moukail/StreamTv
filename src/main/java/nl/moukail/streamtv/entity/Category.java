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

@Entity
@Table(name="category")
public class Category {
	
	@Id
	@Column(name="id")
	@GeneratedValue
	private Integer id;
	
	@Column(name="name")
	private String categoryName;
	
	@OneToMany(mappedBy="category", cascade=CascadeType.ALL)//, fetch=FetchType.EAGER
	@LazyCollection(LazyCollectionOption.TRUE)
	private Collection<Channel> channels = new ArrayList<Channel>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Collection<Channel> getChannels() {
		return channels;
	}
	public void setChannels(Collection<Channel> channels) {
		this.channels = channels;
	}
	
}
