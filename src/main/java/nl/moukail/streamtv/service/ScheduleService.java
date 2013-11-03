package nl.moukail.streamtv.service;

import java.util.List;

import nl.moukail.streamtv.entity.Schedule;

public interface ScheduleService {
	void addSchedule(Schedule schedule);
	List<Schedule> listSchedule();
	void removeSchedule(Integer id);
	Schedule get(Integer id);
	void fetchSchedule();
	void fetchYle();
	void fetchJCCTV();
}