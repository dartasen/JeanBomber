package me.GaVa.JeanBomber.Controller.fxml;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * FXMLController de la fenêtre de score
 */
public class  HighscoreFXMLController extends MainFXMLController {

    private ObservableList<String> scores = FXCollections.observableArrayList("Hero : 500", "test : 5002");

    /*
    @FXML
    private ListView ListeViewLabel;
    */

    private Label[] topScoresLabels = new Label[6];

    @FXML
    private Label top1LabelScore, top2LabelScore, top3LabelScore, top4LabelScore, top5LabelScore, top6LabelScore;

    @FXML
    private void initialize() {
        final int size = scores.size();

        topScoresLabels[0] = top1LabelScore;
        topScoresLabels[1] = top2LabelScore;
        topScoresLabels[2] = top3LabelScore;
        topScoresLabels[3] = top4LabelScore;
        topScoresLabels[4] = top5LabelScore;
        topScoresLabels[5] = top6LabelScore;

        for (int i = 0; i < 6; i++) {
            if (i == size)
                break;
            topScoresLabels[i].setText(scores.get(i));
        }

    }

    public void addScore(String score) {
        scores.add(score);
    }

    /*
    static class ColorRectCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Rectangle rect = new Rectangle(100, 20);

            if (item != null) {
                rect.setFill(Color.web(item));
                setGraphic(rect);
            }
        }
    }*/
}
