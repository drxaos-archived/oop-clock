package xaos.clock;

import java.util.List;

import ui.Paintable;

public class Piece implements Paintable {

	private String image;
	protected double centerX, centerY, angle;

	public Piece(String image, double centerX, double centerY, double angle) {
		super();
		this.image = image;
		this.centerX = centerX;
		this.centerY = centerY;
		this.angle = angle;
	}

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
