package Krunch.gui;

import Krunch.Krunch;
import Krunch.UI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/CriKee.png"));
    private final Image krunchImage = new Image(this.getClass().getResourceAsStream("/images/Krunch.png"));
    private final UI ui = new UI();
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    //private Duke duke;
    private Krunch krunch;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greetingMessage = ui.greet();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greetingMessage, krunchImage));
    }

    /**
     * Injects the Duke instance
     */
    public void setKrunch(Krunch k) {
        krunch = k;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = krunch.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, krunchImage)
        );
        userInput.clear();
    }
}
