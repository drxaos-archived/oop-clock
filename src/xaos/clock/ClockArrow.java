package xaos.clock;

import java.util.Calendar;
import java.util.List;

import ui.Paintable;

abstract public class ClockArrow implements Paintable {

	private String image;
	protected double centerX, centerY, angle;

	public ClockArrow(String image, double centerX, double centerY) {
		super();
		this.image = image;
		this.centerX = centerX;
		this.centerY = centerY;
	}

	abstract void setState(Calendar calendar);

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
		return image;
	}

	@Override
	public double getX() {
		return centerX;
	}

	@Override
	public double getY() {
		return centerY;
	}

}
