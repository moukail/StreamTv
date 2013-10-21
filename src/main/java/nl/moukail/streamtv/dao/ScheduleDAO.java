package nl.moukail.streamtv.dao;

import java.util.List;

import nl.moukail.streamtv.entity.Schedule;

public interface ScheduleDAO {
	public void addSchedule(Schedule schedule);
	public List<Schedule> listSchedule();
	public void removeSchedule(Integer id);
	public Schedule get(int id);
}