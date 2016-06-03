package com.xxx.yyy.system;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author HungHM5
 *
 */
public class ManagerBaseDTO {
	private Set<ScheduleDTO> schedulesDto = new HashSet<ScheduleDTO>();
	private static ManagerBaseDTO instance = null;

	private ManagerBaseDTO() {
	}

	public synchronized static ManagerBaseDTO getInstance() {
		if (instance == null) {
			instance = new ManagerBaseDTO();
		}

		return instance;
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
