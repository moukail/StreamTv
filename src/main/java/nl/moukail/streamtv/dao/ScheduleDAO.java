package nl.moukail.streamtv.dao;

import java.util.List;

import nl.moukail.streamtv.entity.Schedule;

public interface ScheduleDAO {
	void addSchedule(Schedule schedule);
	List<Schedule> listSchedule();
	void removeSchedule(Integer id);
	Schedule get(int id);
}