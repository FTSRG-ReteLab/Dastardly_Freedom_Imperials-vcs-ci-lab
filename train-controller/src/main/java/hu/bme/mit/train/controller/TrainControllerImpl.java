package hu.bme.mit.train.controller;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import hu.bme.mit.train.interfaces.TrainController;

import java.util.Date;

public class TrainControllerImpl implements TrainController {

	private double step = 0;
	private double referenceSpeed = 0;
	private double speedLimit = 0;
	Table<Date, String, Double> Tachograph = HashBasedTable.create();

	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
			referenceSpeed += step;
		}

		enforceSpeedLimit();

		Date current = new Date();
		Tachograph.put(current, "joy", step);
		Tachograph.put(current, "speed", referenceSpeed);
	}

	@Override
	public double getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(double speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(double joystickPosition) {
		this.step = joystickPosition;
		Date current = new Date();
		Tachograph.put(current, "joy", joystickPosition);
		Tachograph.put(current, "speed", referenceSpeed);
	}

	public boolean isEmptyTacho(){
		return Tachograph.isEmpty();
	}

}
