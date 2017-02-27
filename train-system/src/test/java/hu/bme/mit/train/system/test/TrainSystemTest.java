package hu.bme.mit.train.system.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.system.TrainSystem;

public class TrainSystemTest {

	TrainController controller;
	TrainSensor sensor;
	TrainUser user;
	double epsilon = 0.1;
	
	@Before
	public void before() {
		TrainSystem system = new TrainSystem();
		controller = system.getController();
		sensor = system.getSensor();
		user = system.getUser();

		sensor.overrideSpeedLimit(50.0);
	}
	
	@Test
	public void test1() {
		sensor.overrideSpeedLimit(10.0);

		Assert.assertEquals(true, Math.abs(0.0 - controller.getReferenceSpeed()) < epsilon);
		
		user.overrideJoystickPosition(5);

		controller.followSpeed();
		Assert.assertEquals(true, Math.abs(5.0 - controller.getReferenceSpeed()) < epsilon);
		controller.followSpeed();
		Assert.assertEquals(true, Math.abs(10.0 - controller.getReferenceSpeed()) < epsilon);
		controller.followSpeed();
		Assert.assertEquals(true, Math.abs(10.0 - controller.getReferenceSpeed()) < epsilon);
	}

	@Test
	public void test2() {
		user.overrideJoystickPosition(4.0);
		controller.followSpeed();
		user.overrideJoystickPosition(-5.0);
		controller.followSpeed();
		Assert.assertEquals(true, Math.abs(-1.0 - controller.getReferenceSpeed()) < epsilon);
	}

	@Test
	public void test3() { //max speed
		sensor.overrideSpeedLimit(5.0);
		user.overrideJoystickPosition(4.0);
		controller.followSpeed();
		user.overrideJoystickPosition(5.0);
		controller.followSpeed();
		Assert.assertEquals(true, Math.abs(5.0 - controller.getReferenceSpeed()) < epsilon);
	}

	
}
