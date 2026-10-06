import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ProjetoListaDeCompras extends Application {
	
	private ArrayList<String> listaDeCompras = new ArrayList<>();
	private ListView<String> listaVisualizavel = new ListView<>(); //Para exibir os itens da lista de compras.

	@Override
	public void start(Stage palco) {
		palco.setTitle("Aplicativo de Lista de Compras");

		TextField textFieldDescricaoItem = new TextField();
		Button botaoAdicionar = new Button("Adicionar");
		Button botaoExportar = new Button("Exportar Lista");

		Label labelAdicionar = new Label("Digite o que deseja adicionar:");
		Label labelListaDeCompras = new Label("Lista de Compras:");

		//Criação do objeto ObservableList a partir da ListaDeCompras
		ObservableList<String> observableListaDeCompras = FXCollections.observableArrayList(listaDeCompras);
		listaVisualizavel.setItems(observableListaDeCompras);

		VBox vBox = new VBox();
		vBox.getChildren().addAll(labelAdicionar, textFieldDescricaoItem, botaoAdicionar);
		vBox.getChildren().addAll(labelListaDeCompras, listaVisualizavel, botaoExportar);
		vBox.setSpacing(10); //Espaçamento vertical entre os componentes.
		vBox.setPadding(new Insets(10)); //Margens internas (espaçamento) para o VBox.

		botaoAdicionar.setOnAction(e -> {
			String item = textFieldDescricaoItem.getText(); //Obtem o texto digitado e armazena na variável item.
			if (!item.isEmpty()) { //Entra no 'if' se o texto não está vazio (ou seja, se o usuário digitou algo).
				listaDeCompras.add(item); //O texto digitado é adicionado à lista listDeCompras.
				listaVisualizavel.getItems().add(item); //Adicionamos o texto à listVisualizavel.
				textFieldDescricaoItem.clear(); //O campo de texto é limpo, removendo o texto digitado.
			}
		});

		botaoExportar.setOnAction(e -> {
			try {
				File arquivo = new File("listaDeCompras.txt");
				PrintWriter writer = new PrintWriter(arquivo); //O PrintWriter é usado para gravar os itens da lista no arquivo.
				for (String item : listaDeCompras) { //Percorre todos os itens da compra.
					writer.println(item); //Escrevemos cada item da lista no arquivo, adiciona uma nova linha para cada item.
				}
				writer.close();
			} catch (Exception ex) {
				System.out.println("Erro Ocorrido: " + ex.getMessage());
			}
		});

		Scene scene = new Scene(vBox, 350, 300);
		palco.setScene(scene);
		palco.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}