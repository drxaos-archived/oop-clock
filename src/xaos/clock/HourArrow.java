package xaos.clock;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import ui.Paintable;

public class HourArrow extends ClockArrow {

	private Shadow shadow;

	public HourArrow(double centerX, double centerY) {
		super("hour", centerX, centerY);
		shadow = new Shadow();
	}

	@Override
	void setState(Calendar calendar) {
		double sec = calendar.get(Calendar.HOUR_OF_DAY) * 60 * 60
				+ calendar.get(Calendar.MINUTE) * 60
				+ calendar.get(Calendar.SECOND);
		angle = sec / (12 * 60 * 60) * Math.PI * 2;
	}

	@Override
	public List<Paintable> getPaintableChildren() {
		return Collections.singletonList((Paintable) shadow);
	}

	class Shadow implements Paintable {

		@Override
		public double getAngle() {
			return angle;
		}

		@Override
		public List<Paintable> getPaintableChildren() {
			return null;
		}

		@Override
		public String getView() {
			return "hour-shadow";
		}

		@Override
		public double getX() {
			return centerX - 4;
		}

		@Override
		public double getY() {
			return centerY + 4;
		}
	}
}
