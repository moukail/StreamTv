package nl.moukail.streamtv.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Serie")
public class Serie extends Vod{
	
}