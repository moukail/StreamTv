package nl.moukail.streamtv.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Film")
public class Film extends Vod{
	
}