package xaos.clock;

import java.util.Calendar;

public class SecondArrow extends ClockArrow {

	public SecondArrow(double centerX, double centerY) {
		super("sec", centerX, centerY);
	}

	@Override
	void setState(Calendar calendar) {
		double sec = calendar.get(Calendar.SECOND);
		angle = sec / (60) * Math.PI * 2;
	}

}
