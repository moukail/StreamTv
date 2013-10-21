package nl.moukail.streamtv.service;

import java.util.List;

import nl.moukail.streamtv.entity.Schedule;

public interface ScheduleService {
	public void addSchedule(Schedule schedule);
	public List<Schedule> listSchedule();
	public void removeSchedule(Integer id);
	public Schedule get(Integer id);
	public void fetchSchedule();
	public void fetchYle();
	public void fetchJCCTV();
}