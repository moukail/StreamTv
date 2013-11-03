package nl.moukail.streamtv.dao;

import java.util.List;

import nl.moukail.streamtv.entity.Vod;

public interface VodDAO {
	void addVod(Vod vod);
	List<Vod> listVod();
	void removeVod(Integer id);
	Vod get(Integer id);
}
