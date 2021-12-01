package frontend;

import backend.CanvasState;
import backend.model.Figure;
import backend.model.Point;
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
	Button deleteButton = new Button("Borrar");
	Button toFrontButton = new Button("Al frente");
	Button toBackButton = new Button("Al fondo");
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
		List<Button> extraButtonsArr = new ArrayList<>();
		extraButtonsArr.add(deleteButton);
		extraButtonsArr.add(toFrontButton);
		extraButtonsArr.add(toBackButton);
		for(Button button : extraButtonsArr){
			button.setMinWidth(90);
			button.setCursor(Cursor.HAND);
		}
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
		ColorPicker borderColorPicker = new ColorPicker(lineColor);
		Label fillLabel = new Label("Relleno");
		ColorPicker fillColorPicker = new ColorPicker(fillColor);
		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(toolsArr);
		buttonsBox.getChildren().addAll(extraButtonsArr);
		buttonsBox.getChildren().add(borderLabel);
		buttonsBox.getChildren().add(slider);
		buttonsBox.getChildren().add(borderColorPicker);
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
			boolean needFree= endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY();
			Figure newFigure = null;
			for (FigureButton button : figureButtons ) {
				if(button.isSelected())
				{
					if(needFree && !button.isFreeDirectionForCreation())
						return;
					newFigure = button.createFigure(startPoint,endPoint);
					DrawableMovableFigure dmFig = (DrawableMovableFigure) newFigure; //casteo seguro! :D
					dmFig.setFillColor(fillColorPicker.getValue());
					dmFig.setStrokeColor(borderColorPicker.getValue());
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
			if(!checkSelectedAndNull())
				return;
				Point eventPoint = new Point(event.getX(), event.getY());
				double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
				double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
				MovableFigure figure = (MovableFigure) selectedFigure;
				figure.moveX(diffX);
				figure.moveY(diffY);
				redrawCanvas();
		});
		fillColorPicker.setOnAction(event -> {
			if(!checkSelectedAndNull())
				return;
			DrawableMovableFigure figure = (DrawableMovableFigure) selectedFigure;
			figure.setFillColor(fillColorPicker.getValue());
			redrawCanvas();

		});
		borderColorPicker.setOnAction(event -> {
			if(!checkSelectedAndNull())
			return;
				DrawableMovableFigure figure = (DrawableMovableFigure) selectedFigure;
				figure.setStrokeColor(borderColorPicker.getValue());
				redrawCanvas();
		});
		slider.setOnMouseReleased(event -> {
			if(!checkSelectedAndNull())
				return;
			DrawableMovableFigure figure = (DrawableMovableFigure) selectedFigure;
			figure.setStrokeWidth(slider.getValue());
			redrawCanvas();
		});
		toFrontButton.setOnAction(event -> {
			if(!checkSelectedAndNull())
				 return;
			canvasState.moveFigureToLast(selectedFigure);
			redrawCanvas();
		});
		toBackButton.setOnAction(event -> {
			if(!checkSelectedAndNull())
				return;
			canvasState.moveFigureToFirst(selectedFigure);
			redrawCanvas();

		});
		deleteButton.setOnAction(event -> {
			if(!checkSelectedAndNull())
				 return;
			canvasState.remove(selectedFigure);
			redrawCanvas();

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
			dmfigure.drawFigure(gc);

		}
	}

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

	private boolean checkSelectedAndNull(){
		return selectionButton.isSelected() && selectedFigure!=null;
	}

}
