package ui;

import java.util.List;

/**
 * Интерфейс, который должен реализовывать отображаемый объект
 */
public interface Paintable {
	/**
	 * @return координата X объекта
	 */
	double getX();

	/**
	 * @return координата Y объекта
	 */
	double getY();

	/**
	 * @return угол поворота объекта в радианах
	 */
	double getAngle();

	/**
	 * @return имя изображения, которое находится в каталоге views
	 */
	String getView();

	/**
	 * @return список вложенных отображаемых объектов
	 */
	List<Paintable> getPaintableChildren();
}
