import BackEnd.RecipeListDatabase;
import BackEnd.UserDatabase;
import FrontEnd.Recipe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RecipeListDatabaseTest {
    
    public static final String testUsername = "testUserNameForTesting";
    public static final String testPassword = "testPasswordForTestUsername";

    public static String userId = "";

    @BeforeEach
    void setUp() {
        if(UserDatabase.usernameExists(testUsername)){
            UserDatabase.deleteUserForce(testUsername);
        }

        userId = UserDatabase.createUser(testUsername, testPassword);
    }
    
    @Test
    void testRecipeListExistsTrue() {
        RecipeListDatabase.createEmptyRecipeList(userId);
        assertTrue(RecipeListDatabase.recipeListExists(userId));
    }

    @Test
    void testRecipeListExistsFalse() {
        assertFalse(RecipeListDatabase.recipeListExists(userId));
    }

    @Test
    void testCreateEmptyRecipeList() {
        RecipeListDatabase.createEmptyRecipeList(userId);
        assertTrue(RecipeListDatabase.recipeListExists(userId));

    }

    @Test
    void testGetRecipelistByIdAsJSON() {
        RecipeListDatabase.createEmptyRecipeList(userId);
        JSONObject listjson = RecipeListDatabase.getRecipelistByIdAsJSON(userId);

        assertEquals("{\"recipeList\":[]}",listjson.toJSONString());

    }

    @Test
    void testDeleteRecipelistById() {
        RecipeListDatabase.createEmptyRecipeList(userId);
        RecipeListDatabase.deleteRecipelistById(userId);
        assertFalse(RecipeListDatabase.recipeListExists(userId));

    }

    @Test
    void testSetRecipelistByIdGivenJSON() {
        RecipeListDatabase.createEmptyRecipeList(userId);
        try{

            JSONParser parser = new JSONParser();
            FileReader fileReader = new FileReader("example.json");

            JSONObject recipeListObj = (JSONObject)parser.parse(fileReader);

            RecipeListDatabase.setRecipeListByIdGivenJSON(userId, recipeListObj);

            JSONObject listjson = RecipeListDatabase.getRecipelistByIdAsJSON(userId);

            assertEquals(recipeListObj.toJSONString(),listjson.toJSONString());

        }catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }
        

    }

    @AfterEach
    void cleanup(){
        if(UserDatabase.usernameExists(testUsername)){
            UserDatabase.deleteUserForce(testUsername);
        }

        if(RecipeListDatabase.recipeListExists(userId)){
            RecipeListDatabase.deleteRecipelistById(userId);
        }
    }

}