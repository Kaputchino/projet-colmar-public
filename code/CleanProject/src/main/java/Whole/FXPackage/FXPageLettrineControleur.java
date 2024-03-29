package Whole.FXPackage;

import Whole.Metadonnee;
import Whole.ccmsPackage.Lettrine;
import Whole.ccmsPackage.Tag;

import Whole.daoPackage.LettrineDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;

import java.util.ResourceBundle;

public class FXPageLettrineControleur extends FXMenuBarAbstractControleur implements Initializable {
    public static Lettrine lettrine =new Lettrine();
    @FXML
    Button modifierBtn;
    @FXML
    Button retourBtn;
    @FXML
    Label nbPageLabel;
    @FXML
    Label lienLabel;
    @FXML
    Button ouvrageBtn;
    @FXML
    Button createurBtn;
    @FXML
    Label plagiatLabel;
    @FXML
    Label labelModifier;
    @FXML
    ListView<Metadonnee> metaListView;
    @FXML
    ListView<Tag> tagListView;
    ObservableList<Tag> listTag = FXCollections.observableArrayList();
    ObservableList<Metadonnee> listMeta = FXCollections.observableArrayList();
    @FXML
    ImageView imageLettrine;
    @FXML
    public void modifierScene(ActionEvent event) {
        FXModifierLettrineControleur.lettrine=lettrine;
        ControleurFunctions.changeScene(event, "FxInterfaceModifierLettrine.fxml");
    }

    public void retour(ActionEvent event) {
        ControleurFunctions.changeScene(event, "FxInterfaceLettrines.fxml");
    }

    public void afficheLettrinesParTagname(ActionEvent event) {
        ControleurFunctions.changeScene(event, "FxInterfaceLettrines.fxml");
    }
    @FXML
    public void personnePage(ActionEvent event) {
        if(lettrine.getCreateur()!=null){
            if(lettrine.getCreateur().getId()>-1){
                FXPagePersonneControleur.personne = lettrine.getCreateur();
                ControleurFunctions.changeScene(event, "FxInterfacePagePersonne.fxml");
            }
        }

    }
    @FXML
    public void ouvragePage(ActionEvent event) {
        if(lettrine.getOuvrage()!=null){
            if(lettrine.getOuvrage().getId()>-1){
                FXPageOuvrageControleur.ouvrage = lettrine.getOuvrage();
                ControleurFunctions.changeScene(event, "FxInterfacePageOuvrage.fxml");
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nbPageLabel.setText(""+lettrine.getNbPage());
        lienLabel.setText(lettrine.getLien());
        if(lettrine.getOuvrage()!=null){
            ouvrageBtn.setText(""+lettrine.getOuvrage().toString());
        }
        if(lettrine.getCreateur()!=null){
            createurBtn.setText(""+lettrine.getCreateur().toString());
        }
        plagiatLabel.setText(""+lettrine.getIdentique());
        labelModifier.setText("Lettrine n°"+lettrine.getId());

        for(Metadonnee meta:lettrine.getMetadonnees()){
            listMeta.add(meta);
        }
        metaListView.setItems(listMeta);
        lettrine.setTags(ControleurFunctions.lettrineDAO.chercherTag(lettrine));

        for(Tag tag:lettrine.getTags()){
            listTag.add(tag);
        }
        tagListView.setItems(listTag);
        try {
            imageLettrine.setImage(lettrine.getImage());
        }catch (Exception e){

        }
        if(ControleurFunctions.statut.equals("chercheur")){
            modifierBtn.setDisable(true);
        }
    }
    @FXML
    protected void clickOnTag(MouseEvent event) {
        Tag t = tagListView.getSelectionModel().getSelectedItem();
        if(t!=null){
            FXPageTagControleur.tag = t;
            ControleurFunctions.changeScene(event, "FxInterfacePageTag.fxml");
        }
    }
}
