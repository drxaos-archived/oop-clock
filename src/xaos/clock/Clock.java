package xaos.clock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ui.Scene;

public class Clock implements Runnable {
	private Scene scene;
	private List<ClockArrow> arrows = new ArrayList<ClockArrow>();

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public void addArrow(ClockArrow arrow) {
		arrows.add(arrow);
	}

	public void run() {
		// long dbg = System.currentTimeMillis();
		while (true) {
			Calendar calendar = Calendar.getInstance();
			// calendar.setTimeInMillis(dbg += 1000);
			for (ClockArrow arrow : arrows) {
				arrow.setState(calendar);
			}
			if (scene != null)
				scene.repaint();
			synchronized (this) {
				try {
					this.wait(200);
				} catch (InterruptedException e1) {
				}
			}
		}
	}
}
