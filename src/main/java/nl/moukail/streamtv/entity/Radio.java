package nl.moukail.streamtv.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RADIO")
public class Radio extends Channel{

}