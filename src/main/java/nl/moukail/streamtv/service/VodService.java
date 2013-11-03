package nl.moukail.streamtv.service;

import java.util.List;

import nl.moukail.streamtv.entity.Vod;

public interface VodService {
	void addVod(Vod vod);
	List<Vod> listVod();
	void removeVod(Integer id);
	Vod get(Integer id);
}