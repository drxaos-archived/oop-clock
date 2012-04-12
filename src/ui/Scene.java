package ui;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

/**
 * Реализация холста {@link ui.Viewable} для отображения объектов на AWT. При
 * конструировании показывает окно, в котором будут отображаться объекты.
 * Работает с изображениями формата png. Имена изображений указываются без
 * расширений.
 */
public class Scene extends Applet implements Viewable {
	private static final long serialVersionUID = 3156908574585134605L;

	/**
	 * Графический тулкит для загрузки изображений
	 */
	private Toolkit t = getToolkit();

	/**
	 * Дополнительный холст для буферизации кадров
	 */
	private Image buffer;

	/**
	 * Фон сцены
	 */
	private Image bg;

	/**
	 * Имя файла фона
	 */
	protected String background;

	protected boolean loaded = false;

	/**
	 * @param w
	 *            ширина холста
	 * @param h
	 *            высота холста
	 * @param folder
	 *            каталог с изображениями, если указан null, то будет
	 *            использоваться текущий каталог программы
	 * @param background
	 *            имя изображения фона, если указан null, то фон не будет
	 *            загружен
	 */
	public void init(int w, int h, String background) {
		setSize(w, h);
		setVisible(true);

		buffer = new BufferedImage(getWidth(), getHeight(),
				BufferedImage.TYPE_INT_RGB);

		this.background = background;

		if (this.background != null) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					bg = loadImage(Scene.this.background);
					MediaTracker tracker = new MediaTracker(Scene.this);
					tracker.addImage(bg, 0);
					int wait = 0;
					while ((tracker.statusID(0, true) & MediaTracker.COMPLETE) == 0)
						try {
							tracker.waitForAll(200);
							synchronized (tracker) {
								tracker.wait(50);
							}
							wait -= 10;
							((Graphics2D) buffer.getGraphics())
									.setBackground(Color.BLACK);
							buffer.getGraphics().setColor(Color.BLACK);
							buffer.getGraphics().clearRect(0, 0, 600, 623);
							buffer.getGraphics().setColor(Color.WHITE);
							((Graphics2D) buffer.getGraphics()).drawString(
									"Loading...", 280 + ((int) (Math.sin(wait
											* Math.PI / 180) * 30)), 310);
							repaint();
						} catch (InterruptedException e1) {
						}
					loaded = true;
				}
			}).start();
		}
	}

	@Override
	public synchronized void repaint() {
		super.repaint();
	}

	/**
	 * Загрузка изображения из файла
	 * 
	 * @param name
	 *            имя файла
	 * @return объект Image
	 */
	private Image loadImage(String name) {
		ClassLoader loader = this.getClass().getClassLoader();
		if (loader != null) {
			URL url = loader.getResource(name + ".png");
			if (url == null) {
				url = loader.getResource("/" + name + ".png");
			}
			if (url == null) {
				url = loader.getResource("img/" + name + ".png");
			}
			if (url == null) {
				url = loader.getResource("/img/" + name + ".png");
			}
			return t.getImage(url);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public synchronized void paint(Graphics g) {
		g.drawImage(buffer, 0, 0, this);
		if (loaded) {
			Graphics2D g2d = (Graphics2D) buffer.getGraphics();
			g2d.drawImage(bg, 0, 0, this);

			for (Paintable e : paintables.toArray(new Paintable[0])) {
				draw(e, g2d);
			}
		}
	}

	/**
	 * Отрисовка объекта на холсте.
	 * 
	 * @param e
	 *            объект
	 * @param g2d
	 *            холст
	 */
	private void draw(Paintable e, Graphics2D g2d) {
		if (e == null) {
			return;
		}
		double x = e.getX();
		double y = e.getY();
		double a = e.getAngle();
		AffineTransform transform = new AffineTransform();
		transform.translate(x, y);
		transform.rotate(a, 0, 0);
		String v = e.getView();
		if (!views.containsKey(v)) {
			views.put(v, loadImage(v));
		}
		Image img = views.get(v);
		transform.translate(-img.getWidth(null) / 2, -img.getHeight(null) / 2);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(img, transform, this);
		List<Paintable> children = e.getPaintableChildren();
		if (children != null)
			for (Paintable child : children) {
				draw(child, g2d);
			}
	}

	/**
	 * Список отображаемых объектов
	 */
	private List<Paintable> paintables = new ArrayList<Paintable>();

	/**
	 * Список загруженных изображений
	 */
	private Map<String, Image> views = new HashMap<String, Image>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.Viewable#addObject(ui.Paintable)
	 */
	public void addObject(Paintable obj) {
		paintables.add(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.Viewable#removeObject(ui.Paintable)
	 */
	public void removeObject(Paintable obj) {
		paintables.remove(obj);
	}

	private boolean debug = false;

	/**
	 * Включение отладочного режима
	 * 
	 * @param debug
	 *            true - включить отладку
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}
