package main.najah.test;

import org.junit.jupiter.api.*;
import main.najah.code.Recipe;
import main.najah.code.RecipeBook;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

@DisplayName("RecipeBook Test Class")
class RecipeBookTest {

    private RecipeBook recipeBook;

    @BeforeAll
    static void setupAll() {
        System.out.println("Before all tests...");
    }
    
    @BeforeEach
    void setup() {
        System.out.println("Before each test...");
        recipeBook = new RecipeBook();
    }

    @Test
    @DisplayName("Test add recipe successfully")
    void testAddRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Espresso");
        assertTrue(recipeBook.addRecipe(recipe), "Recipe should be added successfully");
    }

    @Test
    @DisplayName("Test add duplicate recipe")
    void testAddDuplicateRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Espresso");

        // Add the recipe once
        recipeBook.addRecipe(recipe);

        // Try to add the same recipe again
        assertFalse(recipeBook.addRecipe(recipe), "Duplicate recipe should not be added");
    }

    @Test
    @DisplayName("Test delete recipe")
    void testDeleteRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Espresso");

        // Add recipe before trying to delete
        recipeBook.addRecipe(recipe);
        
        // Delete the recipe at index 0
        assertEquals("Espresso", recipeBook.deleteRecipe(0), "Recipe name should be 'Espresso'");
    }

    @Test
    @DisplayName("Test edit recipe")
    void testEditRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Espresso");
        Recipe recipe2 = new Recipe();
        recipe2.setName("Cappuccino");

        // Add the original recipe
        recipeBook.addRecipe(recipe);
        
        // Edit the recipe
        assertEquals("Espresso", recipeBook.editRecipe(0, recipe2), "Recipe name should be 'Espresso'");
    }

    @Test
    @DisplayName("Test timeout for adding recipe")
    void testTimeout() {
        Recipe recipe = new Recipe();
        recipe.setName("Espresso");

        // Test the addRecipe method does not exceed 300ms
        assertTimeout(Duration.ofMillis(300), () -> recipeBook.addRecipe(recipe));
    }

    @Test
    @DisplayName("Test get recipes (empty array)")
    void testGetRecipesEmpty() {
        assertEquals(4, recipeBook.getRecipes().length, "Recipe book should have 4 slots initially.");
    }

    @Test
    @DisplayName("Test get recipes (after adding)")
    void testGetRecipesAfterAdding() {
        Recipe recipe = new Recipe();
        recipe.setName("Latte");
        recipeBook.addRecipe(recipe);
        
        assertEquals(4, recipeBook.getRecipes().length, "Recipe book should still have 4 slots after adding.");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("After all tests...");
    }
}
