package xaos.clock;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import ui.Paintable;

public class MinuteArrow extends ClockArrow {
	private Shadow shadow;

	public MinuteArrow(double centerX, double centerY) {
		super("min", centerX, centerY);
		shadow = new Shadow();
	}

	@Override
	void setState(Calendar calendar) {
		double sec = calendar.get(Calendar.MINUTE) * 60
				+ calendar.get(Calendar.SECOND);
		angle = sec / (60 * 60) * Math.PI * 2;
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
			return "min-shadow";
		}

		@Override
		public double getX() {
			return centerX - 8;
		}

		@Override
		public double getY() {
			return centerY + 8;
		}
	}

}
