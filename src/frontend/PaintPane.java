package frontend;

import backend.CanvasState;
import backend.model.Circle;
import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import backend.model.movables.MovableFigure;
import backend.model.movables.MovablePoint;
import frontend.drawablemovable.DrawableMovableFigure;
import frontend.buttons.*;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class PaintPane extends BorderPane {

	// BackEnd
	CanvasState canvasState;

	// Canvas y relacionados
	Canvas canvas = new Canvas(800, 600);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	Color lineColor = Color.BLACK;
	Color fillColor = Color.YELLOW;

	// Botones Barra Izquierda

	ToggleButton selectionButton = new ToggleButton("Seleccionar");
	List<FigureButton> figureButtons = new ArrayList<>();


	// Dibujar una figura
	MovablePoint startPoint;

	// Seleccionar una figura
	Figure selectedFigure;

	// StatusBar
	StatusPane statusPane;

	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		figureButtons.add(new RectangleButton("Rectángulo"));
		figureButtons.add(new CircleButton("Círculo"));
		figureButtons.add(new SquareButton("Cuadrado"));
		figureButtons.add(new LineButton("Línea"));
		figureButtons.add(new EllipseButton("Elipse"));

		this.canvasState = canvasState;
		this.statusPane = statusPane;

		List<ToggleButton> toolsArr = new ArrayList<>();
		toolsArr.add(selectionButton);
		toolsArr.addAll(figureButtons);
		ToggleGroup tools = new ToggleGroup();
		for (ToggleButton tool : toolsArr) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}
		Label borderLabel = new Label("Borde");
		Slider slider = new Slider(1,50,1);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		ColorPicker borderColor = new ColorPicker(lineColor);
		Label fillLabel = new Label("Relleno");
		ColorPicker fillColorPicker = new ColorPicker(fillColor);
		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(toolsArr);
		buttonsBox.getChildren().add(borderLabel);
		buttonsBox.getChildren().add(slider);
		buttonsBox.getChildren().add(borderColor);
		buttonsBox.getChildren().add(fillLabel);
		buttonsBox.getChildren().add(fillColorPicker);
		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);
		gc.setLineWidth(1);
		canvas.setOnMousePressed(event -> {
			startPoint = new MovablePoint(event.getX(), event.getY());
		});
		canvas.setOnMouseReleased(event -> {
			MovablePoint endPoint = new MovablePoint(event.getX(), event.getY());
			if(startPoint == null) {
				return ;
			}
			if(endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY()) {
				return ;
			}
			Figure newFigure = null;
			for (FigureButton button : figureButtons ) {
				if(button.isSelected())
				{
					newFigure = button.createFigure(startPoint,endPoint);
					DrawableMovableFigure dmFig = (DrawableMovableFigure) newFigure; //casteo seguro! :D
					dmFig.setFillColor(fillColorPicker.getValue());
					dmFig.setStrokeColor(borderColor.getValue());
					dmFig.setStrokeWidth(slider.getValue());
					canvasState.addFigure(newFigure);
					startPoint = null;
					redrawCanvas();
					return;
				}
			}

		});
		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			Figure result = figureAtPosition(eventPoint);
			if(result!=null) {
				statusPane.updateStatus(label.append(result).toString());
			} else {
				statusPane.updateStatus(eventPoint.toString());
			}
		});

		canvas.setOnMouseClicked(event -> {
			if(selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				Figure result = figureAtPosition(eventPoint);
				if (result!=null) {
					selectedFigure=result;
					statusPane.updateStatus(label.append(result).toString());
				} else {
					selectedFigure = null;
					statusPane.updateStatus("Ninguna figura encontrada");
				}
				redrawCanvas();
			}
		});
		canvas.setOnMouseDragged(event -> {
			if(selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
				double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
				if(selectedFigure==null) return;
				MovableFigure figure = (MovableFigure) selectedFigure;
				figure.moveX(diffX);
				figure.moveY(diffY);
				redrawCanvas();
			}
		});
		setLeft(buttonsBox);
		setRight(canvas);
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(Figure figure : canvasState.figures()) {
			DrawableMovableFigure dmfigure = (DrawableMovableFigure) figure;
			if(figure == selectedFigure) {
				gc.setStroke(Color.RED);
			} else {
				gc.setStroke(dmfigure.getStrokeColor());
			}
			gc.setLineWidth(dmfigure.getStrokeWidth());
			gc.setFill(dmfigure.getFillColor());
			//DrawableMovableFigure dmfigure = (DrawableMovableFigure) figure;
			dmfigure.drawFigure(gc);

		}
	}
	/* No se usa
	boolean figureBelongs(Figure figure, Point eventPoint) {
		boolean found = false;
		if(figure instanceof Rectangle) {
			Rectangle rectangle = (Rectangle) figure;
			found = eventPoint.getX() > rectangle.getTopLeft().getX() && eventPoint.getX() < rectangle.getBottomRight().getX() &&
					eventPoint.getY() > rectangle.getTopLeft().getY() && eventPoint.getY() < rectangle.getBottomRight().getY();
		} else if(figure instanceof Circle) {
			Circle circle = (Circle) figure;
			found = Math.sqrt(Math.pow(circle.getCenterPoint().getX() - eventPoint.getX(), 2) +
					Math.pow(circle.getCenterPoint().getY() - eventPoint.getY(), 2)) < circle.getRadius();
		}
		return found;
	}*/

	//auxiliar que devuelve la figura sobre la cual está el mouse
	private Figure figureAtPosition(Point position){

		for(Figure figure : canvasState.figuresReverse()){
			if(figure.pointInFigure(position)){
				return figure;
			}
		}
		//si no encuentra retorna null
		return null;
	}

}
