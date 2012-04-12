package xaos.clock;

import java.util.Calendar;

import ui.Scene;

public class Applet extends Scene {

	private static final long serialVersionUID = 6412085542457851877L;

	@Override
	public void init() {
		super.init();
		super.init(600, 623, "bg");

		Clock clock = new Clock();
		clock.setScene(this);
		{
			ClockArrow alarm = new AlarmArrow(297, 302);
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 11);
			alarm.setState(calendar);
			// clock.addArrow(alarm);
			this.addObject(alarm);
		}
		{
			ClockArrow second = new SecondArrow(297, 366);
			clock.addArrow(second);
			this.addObject(second);
		}
		{
			ClockArrow hour = new HourArrow(297, 366);
			clock.addArrow(hour);
			this.addObject(hour);
		}
		{
			Piece pin = new PinBottom(297, 366);
			this.addObject(pin);
		}
		{
			ClockArrow minute = new MinuteArrow(297, 366);
			clock.addArrow(minute);
			this.addObject(minute);
		}
		{
			Piece pin = new PinTop(297, 366);
			this.addObject(pin);
		}
		new Thread(clock).start();
	}

}
