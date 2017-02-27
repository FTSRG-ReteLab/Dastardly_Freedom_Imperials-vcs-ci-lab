package hu.bme.mit.train.interfaces;

public interface TrainUser {

	double getJoystickPosition();

	boolean getAlarmFlag();

	void overrideJoystickPosition(double joystickPosition);

}
