package nl.moukail.streamtv.service;

import java.util.List;

import nl.moukail.streamtv.entity.Vod;

public interface VodService {
	public void addVod(Vod vod);
	public List<Vod> listVod();
	public void removeVod(Integer id);
	public Vod get(Integer id);
}