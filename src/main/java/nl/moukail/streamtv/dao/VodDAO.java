package nl.moukail.streamtv.dao;

import java.util.List;

import nl.moukail.streamtv.entity.Vod;

public interface VodDAO {
	public void addVod(Vod vod);
	public List<Vod> listVod();
	public void removeVod(Integer id);
	public Vod get(Integer id);
}
