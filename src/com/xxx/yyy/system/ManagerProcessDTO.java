package com.xxx.yyy.system;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author HungHM5
 *
 */
public class ManagerProcessDTO {
	private Set<ScheduleDTO> schedulesDto = new HashSet<ScheduleDTO>();
	private static ManagerProcessDTO instance = null;

	private ManagerProcessDTO() {
	}

	public synchronized static ManagerProcessDTO getInstance() {
		if (instance == null) {
			instance = new ManagerProcessDTO();
		}

		return instance;
	}

	public void addScheduleDto(ScheduleDTO scheduleDto) {
		schedulesDto.add(scheduleDto);
	}

	public void addScheduleDto() {
		// TODO Auto-generated method stub

	}

	public void createSchedulesDto() {
		// TODO Auto-generated method stub

	}

	public Set<ScheduleDTO> getSchedulesDTO() {
		return schedulesDto;
	}
}
