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
import java.util.Collection;
import java.util.List;

public class PaintPane extends BorderPane
{

	// Se agregan los private aunque no cambien nada, siempre la mayor privacidad posible
	// BackEnd
	private final CanvasState canvasState;

	// Canvas y relacionados
	private final Canvas canvas = new Canvas(800, 600);
	private final GraphicsContext gc = canvas.getGraphicsContext2D();
	private Color lineColor = Color.BLACK;
	private Color fillColor = Color.YELLOW;

	// Botones Barra Izquierda

	private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
	private final Button deleteButton = new Button("Borrar");
	private final Button toFrontButton = new Button("Al frente");
	private final Button toBackButton = new Button("Al fondo");
	private final List<FigureButton> figureButtons = new ArrayList<>(); //Un array donde estarán los botones para crear las figuras


	// Dibujar una figura
	private MovablePoint startPoint;

	// Figuras seleccionadas
	private final Collection<Figure> selectedFigures;

	// StatusBar
	private StatusPane statusPane;

	private boolean multipleSelectionInProcess = false; //Se usa para diferenciar cuando se está haciendo una selcción múltiple arrastrando el cursor

	public PaintPane(CanvasState canvasState, StatusPane statusPane)
	{
		//Botones de figuras
		figureButtons.add(new RectangleButton("Rectángulo"));
		figureButtons.add(new CircleButton("Círculo"));
		figureButtons.add(new SquareButton("Cuadrado"));
		figureButtons.add(new LineButton("Línea"));
		figureButtons.add(new EllipseButton("Elipse"));

		this.canvasState = canvasState;
		this.statusPane = statusPane;
		this.selectedFigures = new ArrayList<>(); //Inicialmente, no hay figuras seleccionadas

		// Se agregan los botones después de determinar cómo van a estar
		// acomodados en la pantalla
		List<ToggleButton> toolsArr = new ArrayList<>();
		toolsArr.add(selectionButton);
		toolsArr.addAll(figureButtons);
		List<Button> extraButtonsArr = new ArrayList<>();
		extraButtonsArr.add(deleteButton);
		extraButtonsArr.add(toFrontButton);
		extraButtonsArr.add(toBackButton);
		for(Button button : extraButtonsArr)
		{
			button.setMinWidth(90);
			button.setCursor(Cursor.HAND);
		}
		ToggleGroup tools = new ToggleGroup();
		for (ToggleButton tool : toolsArr)
		{
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


		canvas.setOnMousePressed(event -> startPoint = new MovablePoint(event.getX(), event.getY()));


		canvas.setOnMouseReleased(event -> {
			MovablePoint endPoint = new MovablePoint(event.getX(), event.getY());
			if(startPoint == null)
			{
				return ;
			}
			// Las líneas y los círculos se pueden hacer con el punto final en cualquier lugar en relación
			// con el inicial, así que de pasar esto último, el booleano que indica esto se vuelve true
			boolean needFree = endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY();
			Figure newFigure;
			for (FigureButton button : figureButtons )
			{ // Para crear una figura, si algún botón de figura está seleccionado
				if(button.isSelected())
				{
					selectedFigures.clear();
					// si sucede los descripto arriba, pero la figura a poner no es ni una línea ni un
					// círculo, entonces no hace nada
					if(needFree && !button.isFreeDirectionForCreation())
						return;
					// agrega la figura con los colores establecidos / tamaño del borde y redraw-ea el canvas
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
			// para la selección múltiple es de arriba izquierda hacia abajo a la derecha
			if(selectionButton.isSelected() && !needFree)
			{ // Selección múltiple
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				for (Figure figure: canvasState.figures())
				{
					DrawableMovableFigure dwfigure=(DrawableMovableFigure)figure;//Casteo seguro
					if(dwfigure.isContained(startPoint,endPoint))
					{
						multipleSelectionInProcess=true;
						selectedFigures.add(figure);
						statusPane.updateStatus(label.append(figure).toString());
					}
				}
				if(selectedFigures.isEmpty())
				{
					statusPane.updateStatus("Ninguna figura encontrada");
				}
				redrawCanvas();
			}
		});


		canvas.setOnMouseMoved(event -> { //Se actualiza la statusBar de abajo
			Point eventPoint = new Point(event.getX(), event.getY());
			StringBuilder label = new StringBuilder();
			// figureAtPosition solo devuelve una figura a diferencia de como estaba hecho en un principio
			// de esta manera creemos que se entienden mucho más las etiquetas que aparecen abajo
			Figure result = figureAtPosition(eventPoint);
			if(result!=null)
			{
				statusPane.updateStatus(label.append(result).toString());
			}
			else
			{
				statusPane.updateStatus(eventPoint.toString());
			}
		});


		// Esta parte es para la selección de una figura con un click, usamos una colección
		// con solo un elemento para que sea más claro el código y no tengamos que hacer
		// métodos muy similares, uno con una colección de figuras y otro con una figura
		canvas.setOnMouseClicked(event -> {
			if(selectionButton.isSelected())
			{
				Point eventPoint = new Point(event.getX(), event.getY());
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				Figure result = figureAtPosition(eventPoint);
				if (result!=null && !multipleSelectionInProcess)
				{ //Si se hizo un click sobre una figura
					selectedFigures.clear();
					selectedFigures.add(result);
					statusPane.updateStatus(label.append(result).toString());
				}
				else
				{
					if(multipleSelectionInProcess)
					{ //Si se termino una selección múltiple, entra acá y no hace nada
						multipleSelectionInProcess=false;
						return;
					}
					selectedFigures.clear(); //Si se hizo un click donde no hay nada
					statusPane.updateStatus("Ninguna figura encontrada");
				}
				redrawCanvas();
			}
		});


		canvas.setOnMouseDragged(event -> { //Movimiento de figuras
			if(!checkSelectedAndNull()) //Se chequea que el botón de selección esté presionado y haya por lo menos una figura seleccionada
				return;
			Point eventPoint = new Point(event.getX(), event.getY());
			double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
			double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
			for(Figure figure: selectedFigures)
			{
				MovableFigure Mfigure = (MovableFigure) figure; //Casteo seguro
				Mfigure.moveX(diffX);
				Mfigure.moveY(diffY);
			}
			redrawCanvas();
		});


		// Cambios de colores y del tamaño del borde con el slider
		// Se selecciona un Color del interior
		fillColorPicker.setOnAction(event -> {
			if(!checkSelectedAndNull()) //Se chequea que el botón de selección esté presionado y haya por lo menos una figura seleccionada
				return;
			for(Figure figure : selectedFigures)
			{ //Se cambia en todas las figuras seleccionadas
				DrawableMovableFigure dmfigure = (DrawableMovableFigure) figure;
				dmfigure.setFillColor(fillColorPicker.getValue());
			}
			redrawCanvas();

		});

		// Se selecciona un Color del borde
		borderColorPicker.setOnAction(event -> {
			if(!checkSelectedAndNull()) //Se chequea que el botón de selección esté presionado y haya por lo menos una figura seleccionada
				return;
			for(Figure figure : selectedFigures)
			{ //Se cambia en todas las figuras seleccionadas
				DrawableMovableFigure dmfigure = (DrawableMovableFigure) figure;
				dmfigure.setStrokeColor(borderColorPicker.getValue());
			}
				redrawCanvas();
		});

		// Se selecciona un grosor para el borde
		slider.setOnMouseReleased(event -> {
			if(!checkSelectedAndNull()) //Se chequea que el botón de selección esté presionado y haya por lo menos una figura seleccionada
				return;
			for(Figure figure : selectedFigures)
			{ //Se cambia en todas las figuras seleccionadas
				DrawableMovableFigure dmfigure = (DrawableMovableFigure) figure;
				dmfigure.setStrokeWidth(slider.getValue());
			}
			redrawCanvas();
		});

		// La profundidad de la figura viene determinada directamente por su orden
		// dentro de la colección de CanvasState, así que si se quiere mover arriba,
		// se la remueve de la colección y se la añade última. Para mandarla al fondo
		// es lo mismo, pero agregandola primera
		toFrontButton.setOnAction(event -> {
			if(!checkSelectedAndNull()) //Se chequea que el botón de selección esté presionado y haya por lo menos una figura seleccionada
				 return;
			for(Figure figure : selectedFigures)
			{ //Se mueven todas las figuras seleccionadas
				canvasState.moveFigureToLast(figure);
			}
			redrawCanvas();
		});
		toBackButton.setOnAction(event -> {
			if(!checkSelectedAndNull()) //Se chequea que el botón de selección esté presionado y haya por lo menos una figura seleccionada
				return;
			for(Figure figure : selectedFigures)
			{ //Se mueven todas las figuras seleccionadas
				canvasState.moveFigureToFirst(figure);
			}
			redrawCanvas();

		});


		// El borrado es simplemente eliminar la figura de canvasState
		deleteButton.setOnAction(event -> {
			if(!checkSelectedAndNull()) //Se chequea que el botón de selección esté presionado y haya por lo menos una figura seleccionada
				 return;
			for(Figure figure : selectedFigures)
			{ //Se eliminan todas las figuras seleccionadas
				canvasState.remove(figure);
			}
			selectedFigures.clear();
			redrawCanvas();
		});


		setLeft(buttonsBox);
		setRight(canvas);
	}

	public void redrawCanvas()
	{ //Cada vez que se llama a esta función, se redibujan todas las figuras que existan en canvasState
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		// Se itera por cada figura y se la imprime con el ancho del borde y colores deseados
		for(Figure figure : canvasState.figures())
		{
			// Es seguro el casteo porque si bien canvasState tiene una colección de Figures
			// en la misma se cargan figuras DrawableMovable, las cuales extienden a figuras Movable
			// las cuales extienden de figuras que extienden de Figure, así que son Figures al final del día
			DrawableMovableFigure dmfigure = (DrawableMovableFigure) figure;
			if(selectedFigures.contains(figure))
			{
				gc.setStroke(Color.RED);
			}
			else
			{
				gc.setStroke(dmfigure.getStrokeColor());
			}
			gc.setLineWidth(dmfigure.getStrokeWidth());
			gc.setFill(dmfigure.getFillColor());
			dmfigure.drawFigure(gc);

		}
	}

	//Auxiliar que devuelve la figura sobre la cual está el mouse (la posición se pasa por parámetro)
	private Figure figureAtPosition(Point position)
	{

		for(Figure figure : canvasState.figuresReverse())
		{
			if(figure.pointInFigure(position))
			{
				return figure;
			}
		}
		// Si no encuentra retorna null
		return null;
	}

	//Se chequea que el botón de selección esté presionado y haya por lo menos una figura seleccionada
	private boolean checkSelectedAndNull()
	{
		return selectionButton.isSelected() && !selectedFigures.isEmpty();
	}

}
