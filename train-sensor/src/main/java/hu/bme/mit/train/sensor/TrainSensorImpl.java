package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private double speedLimit = 5;

	public TrainSensorImpl(TrainController controller) {
		this.controller = controller;
	}

	@Override
	public double getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(double speedLimit) {
		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);
	}

}
