// package FrontEnd;

// import javafx.event.ActionEvent;
// import javafx.scene.Scene;
// import javafx.scene.image.Image;

// public class NewRecipePageFrameController {

//     NewRecipePageFrame view;
//     HTTPRequestModel model;

//     public NewRecipePageFrameController(NewRecipePageFrame view, HTTPRequestModel model) {
//         this.view = view;
//         this.model = model;

//         this.view.setNewBackButtonAction(null);
//         this.view.setNewSaveButtonAction(null);
//         this.view.setNewGenerateButtonAction(null);
//         this.view.setRecordButtonAction(null);
//         this.view.setBreakfastButtonAction(null);
//         this.view.setLunchButtonAction(null);
//         this.view.setDinnerButtonAction(null);
//     }

//     public void handleNewBackButton(ActionEvent event) {
//             RecipeListPageFrame frontPage = new RecipeListPageFrame();
//             stage.setTitle("PantryPal");
//             stage.getIcons().add(new Image(Constants.defaultIconPath));
//             stage.setScene(new Scene(frontPage, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
//             stage.setResizable(false);
//             stage.show();
//     }

//     public void handleNewSaveButton(ActionEvent event) {

//             //add recipe to recipeList
//             list.getChildren().add(new RecipeSimple(recipe));
//             reverse.getChildren().add(0, new RecipeSimple(recipe));
//             //save to .json
//             JSONSaver.saveRecipeList(list);
            
//             HTTPRequestModel httpRequestModel = new HTTPRequestModel(); //TODO: Remove when controller is implemented
//             String response = httpRequestModel.performRecipeListPOSTRequest();

//             //sort tasks, tasks are added at end, just show by reverse order (for loop starting at the end)
//     }

//     public void handleNewGenerateButton(ActionEvent event) {

//     }

//     public void handleRecordButton(ActionEvent event) {

//     }

//     public void handleBreakfastButton(ActionEvent event) {

//     }

//     public void handleLunchButton(ActionEvent event) {

//     }

//     public void handleDinnerButton(ActionEvent event) {

//     }

// }

