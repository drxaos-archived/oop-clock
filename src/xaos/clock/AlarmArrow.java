package xaos.clock;

import java.util.Calendar;

public class AlarmArrow extends ClockArrow {

	public AlarmArrow(double centerX, double centerY) {
		super("alarm", centerX, centerY);
	}

	@Override
	void setState(Calendar calendar) {
		double sec = calendar.get(Calendar.HOUR_OF_DAY) * 60 * 60
				+ calendar.get(Calendar.MINUTE) * 60
				+ calendar.get(Calendar.SECOND);
		angle = sec / (12 * 60 * 60) * Math.PI * 2;
	}

}
