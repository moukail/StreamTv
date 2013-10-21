package nl.moukail.streamtv.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nl.moukail.streamtv.entity.Schedule;

@Repository
public class ScheduleDAOImpl implements ScheduleDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addSchedule(Schedule schedule) {
		sessionFactory.getCurrentSession().save(schedule);
	}

	@SuppressWarnings("unchecked")
	public List<Schedule> listSchedule() {
		return sessionFactory.getCurrentSession().createQuery("from Schedule").list();
	}

	public void removeSchedule(Integer id) {
		Schedule schedule = (Schedule) sessionFactory.getCurrentSession().load(Schedule.class, id);
		if (null != schedule) {
			sessionFactory.getCurrentSession().delete(schedule);
		}
	}

	public Schedule get(int id) {
		return (Schedule) sessionFactory.getCurrentSession().createQuery("from Schedule where id = " + id).list().get(0);
	}

}
