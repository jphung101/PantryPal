package FrontEnd;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class JSONSaver{
    public static void saveRecipeList(RecipeListDisplay list, String fileName){
        JSONObject recipeList = new JSONObject();
        JSONArray recipeListArr = new JSONArray();
        for(int i = 0; i < list.getChildren().size(); i++){
            if(list.getChildren().get(i) instanceof RecipeSimple){
                JSONObject recipeJSON = new JSONObject();
                RecipeSimple rs = (RecipeSimple) list.getChildren().get(i);
                Recipe recipe = rs.getRecipe();
                recipeJSON.put("recipeName", recipe.getRecipeName());
                recipeJSON.put("ingredients", recipe.getIngredients());
                recipeJSON.put("directions", recipe.getDirections());
                recipeJSON.put("date", recipe.getDateCreated());
                recipeJSON.put("mealType", recipe.getMealType());
                recipeJSON.put("image", recipe.getImg());
                recipeListArr.add(recipeJSON);
            }
        }

        recipeList.put("recipeList", recipeListArr);

        try {

			FileWriter file = new FileWriter(fileName, StandardCharsets.UTF_8);
			file.write(recipeList.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    public static void saveRecipeListData(RecipeListData list, String fileName){
        JSONObject recipeList = new JSONObject();
        JSONArray recipeListArr = new JSONArray();

        ArrayList<Recipe> array = list.getRecipeList();

        for(int i = 0; i < array.size(); i++){
            if(array.get(i) instanceof Recipe){
                JSONObject recipeJSON = new JSONObject();
                Recipe recipe = array.get(i);
                recipeJSON.put("recipeName", recipe.getRecipeName());
                recipeJSON.put("ingredients", recipe.getIngredients());
                recipeJSON.put("directions", recipe.getDirections());
                recipeJSON.put("date", recipe.getDateCreated());
                recipeJSON.put("mealType", recipe.getMealType());
                recipeJSON.put("image", recipe.getImg());
                recipeListArr.add(recipeJSON);
            }
        }

        recipeList.put("recipeList", recipeListArr);

        try {

			FileWriter file = new FileWriter(fileName);
			file.write(recipeList.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    public static void updateJSON(String oldName, Recipe newRecipe){
        
        try{

            JSONParser parser = new JSONParser();
            FileReader fileReader = new FileReader("storage.json");

            JSONObject recipeListObj = (JSONObject)parser.parse(fileReader);

            JSONArray recipeListArr = (JSONArray)recipeListObj.get("recipeList");

            for(int i = 0; i < recipeListArr.size(); i++){
                JSONObject recipe = (JSONObject)recipeListArr.get(i);
                
                if(((String)recipe.get("recipeName")).equals(oldName)){
                    recipe.put("recipeName", newRecipe.getRecipeName());
                    recipe.put("ingredients", newRecipe.getIngredients());
                    recipe.put("directions", newRecipe.getDirections());
                    recipe.put("date", newRecipe.getDateCreated());
                    recipe.put("mealType", newRecipe.getMealType());
                    recipe.put("image", newRecipe.getImg());
                }
            }

            FileWriter file = new FileWriter("storage.json", StandardCharsets.UTF_8);
			file.write(recipeListObj.toJSONString());
			file.flush();
			file.close();

        }catch(IOException e){
            e.printStackTrace();
        }catch(org.json.simple.parser.ParseException e){
            e.printStackTrace();
        }
    }

    public static void removeByName(String name){
        
        try{

            JSONParser parser = new JSONParser();
            FileReader fileReader = new FileReader("storage.json", StandardCharsets.UTF_8);

            JSONObject recipeListObj = (JSONObject)parser.parse(fileReader);

            JSONArray recipeListArr = (JSONArray)recipeListObj.get("recipeList");

            int i = 0;
            for(;i < recipeListArr.size(); i++){
                JSONObject recipe = (JSONObject)recipeListArr.get(i);
                
                if(((String)recipe.get("recipeName")).equals(name)){
                    break;
                }
            }

            recipeListArr.remove(i);

            FileWriter file = new FileWriter("storage.json", StandardCharsets.UTF_8);
			file.write(recipeListObj.toJSONString());
			file.flush();
			file.close();

        }catch(IOException e){
            e.printStackTrace();
        }catch(org.json.simple.parser.ParseException e){
            e.printStackTrace();
        }
    }

    public static JSONObject jsonStringToObject(String jsonString){
        JSONParser p = new JSONParser();
        try{
            JSONObject jsonObj = (JSONObject) p.parse(jsonString);
            return jsonObj;
        }catch(org.json.simple.parser.ParseException e){
            e.printStackTrace();
            return null;
        }
        
    }

    public static void saveRecipeListByJSON(JSONObject recipeListJSONObject){
        try{
            FileWriter file = new FileWriter("storage.json", StandardCharsets.UTF_8);
			file.write(recipeListJSONObject.toJSONString());
			file.flush();
			file.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}