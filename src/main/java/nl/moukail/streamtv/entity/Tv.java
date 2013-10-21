package nl.moukail.streamtv.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TV")
public class Tv extends Channel{

}