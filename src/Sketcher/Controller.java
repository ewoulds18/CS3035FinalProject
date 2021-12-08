package Sketcher;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class Controller {
	
	Model model;
	Toolbar t;
	
	public Controller(){
		
		model = Main.model;
		t = Main.view.getToolbar();
		Main.view.addEventHandler(MouseEvent.ANY, new MouseHandler());
		
		t.getRedoButton().setOnMouseClicked(e -> model.redo());
		t.getUndoButton().setOnMouseClicked(e -> model.undo());
	}
	
	public class MouseHandler implements EventHandler<javafx.scene.input.MouseEvent>{
		
		@Override
		public void handle(MouseEvent e) {
			if(e.getEventType() == MouseEvent.MOUSE_PRESSED){
				if(e.getTarget().getClass() == Canvas.class){
					model.setCurStateToDraw();
					if(t.getFreeDrawButton().isSelected()){
						model.freeDraw(e.getX(),e.getY());
					}else if(t.getEraserButton().isSelected()){
						model.erase(e.getX(), e.getY());
					}else if(t.getLineButton().isSelected()){
						model.drawLine(e.getX(), e.getY());
					}else if(t.getRectButton().isSelected()){
						model.drawRect(e.getX(), e.getY());
					}else if(t.getCircleButton().isSelected()){
						model.drawCircle(e.getX(), e.getY());
					}else if(t.getTextButton().isSelected()){
						model.drawText(e.getX(), e.getY(), t.getLineWidth().getValue(), t.getText().getText());
					}
				}
			}
			if(e.getEventType() == MouseEvent.MOUSE_DRAGGED){
				if(e.getTarget().getClass() == Canvas.class){
					model.setCurStateToDragging();
					if(t.getFreeDrawButton().isSelected()){
						model.freeDraw(e.getX(),e.getY());
					}else if(t.getEraserButton().isSelected()){
						model.erase(e.getX(), e.getY());
					}
				}
			}
			if(e.getEventType() == MouseEvent.MOUSE_RELEASED){
				if(e.getTarget().getClass() == Canvas.class){
					model.setCurStateToRelease();
					if(t.getFreeDrawButton().isSelected()){
						model.freeDraw(e.getX(), e.getY());
					}else if(t.getEraserButton().isSelected()){
						model.erase(e.getX(), e.getY());
					}else if(t.getLineButton().isSelected()){
						model.drawLine(e.getX(), e.getY());
					}else if(t.getRectButton().isSelected()){
						model.drawRect(e.getX(), e.getY());
					}else if(t.getCircleButton().isSelected()){
						model.drawCircle(e.getX(), e.getY());
					}
				}
			}
		}
	}
}
