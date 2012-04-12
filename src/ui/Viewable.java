package ui;

/**
 * Холст для отображения объектов
 */
public interface Viewable {
	/**
	 * Запросить перерисовку холста
	 */
	void repaint();

	/**
	 * Добавить объект на холст
	 * 
	 * @param obj
	 *            отображаемый объект
	 */
	void addObject(Paintable obj);

	/**
	 * Убрать объект с холста
	 * 
	 * @param obj
	 *            отображаемый объект
	 */
	void removeObject(Paintable obj);
}
