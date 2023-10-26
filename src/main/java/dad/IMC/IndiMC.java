package dad.IMC;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IndiMC extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
	TextField pesoText = new TextField();
	pesoText.setPrefColumnCount(3);
	
	HBox primerH = new HBox(5, new Label("Peso"),pesoText,new Label("Kg"));
	
	
	TextField alturaText = new TextField();
	alturaText.setPrefColumnCount(3);
	
	HBox segundoH = new HBox(5, new Label("Altura"),alturaText, new Label("cm"));
	 
	Label R1 = new Label();
	R1.setDisable(true);

	HBox terceroH = new HBox(5,new Label("Imc:"),R1);
	
	
	
	Label estado = new Label("Bajo peso | Normal | SobrePeso | Obeso");
	
	VBox root = new VBox(5,primerH,segundoH,terceroH,estado);
	root.setAlignment(Pos.CENTER);
	root.setFillWidth(false);
	
	
	Scene scene = new Scene(root, 320, 200);
	
	primaryStage.setTitle("IMC");
	primaryStage.setScene(scene);
	primaryStage.show();
	
 DoubleProperty peso = new SimpleDoubleProperty();
 DoubleProperty altura = new SimpleDoubleProperty();
 DoubleProperty IMC = new SimpleDoubleProperty();
	

 pesoText.textProperty().bindBidirectional(peso,new NumberStringConverter());
 alturaText.textProperty().bindBidirectional(altura,new NumberStringConverter());
 IMC.bind(peso.divide(altura.divide(100).multiply(altura.divide(100))));
 R1.textProperty().bind(IMC.asString("%.2f"));
 
 
 IMC.addListener((o, oV, nV) -> {
	 if(nV.doubleValue()< 18.5) {
		 estado.textProperty().set("Bajo Peso");
	 }else if(nV.doubleValue() >= 18.5 && nV.doubleValue() <25) {
		 estado.textProperty().set("Normal");
	 }else if(nV.doubleValue() >= 25 && nV.doubleValue() <30) {
		 estado.textProperty().set("Sobre Peso");
	 }else if (nV.doubleValue() >= 30) {
		 estado.textProperty().set("Obeso");
	 }
	 
	 
 }) ;

}}
