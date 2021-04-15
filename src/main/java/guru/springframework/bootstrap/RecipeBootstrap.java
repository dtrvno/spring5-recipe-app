package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.info("loading Bootstrap data");
    }

    private List<Recipe> getRecipes() {
           List<Recipe> recipes = new ArrayList<>();
        Optional <UnitOfMeasure> teeUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teeUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM0 not found");
        }
        Optional <UnitOfMeasure> tableUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tableUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM1not found");
        }
        Optional <UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM2 not found");
        }
        Optional <UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");
        if (!pinchUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM4 not found");
        }Optional <UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM5 not found");
        }
        Optional <UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }
        Optional <UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableUom = tableUomOptional.get();
        UnitOfMeasure teeUom = teeUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure cupsUom = cupUomOptional.get();
        UnitOfMeasure pintUom= pintUomOptional.get();

        Optional <Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected categpry not found");
        }
        Optional <Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected categpry not found");
        }
        Category americanCategory= americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();
        Recipe guacRecipe= new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1.Cut the avocado, remove flesh:\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. \n" +
                "(See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "2.Mash with a fork:\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "3.Add salt, lime juice, and the rest:\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving."+
                "4.Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down \n"+
                "to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) \n"+
                "Refrigerate until ready to serve.");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Be careful handling chiles if using. \n" +
                "Wash your hands thoroughly after handling and do not touch \n"+
                "your eyes or the area near your eyes with your hands for several hours");
        guacRecipe.setNotes(guacNotes);
        guacRecipe.getIngredients().add(new Ingredient("ripe avocados",new BigDecimal(2),eachUom));
        guacRecipe.getIngredients().add(new Ingredient("Kosher salt",new BigDecimal("0.5"),teeUom));
        guacRecipe.getIngredients().add(new Ingredient("fresh lime juice or lemon juice",new BigDecimal(2),tableUom));
        guacRecipe.getIngredients().add(new Ingredient("minced red onion",new BigDecimal(2),tableUom));
        guacRecipe.getIngredients().add(new Ingredient("serrano chiles",new BigDecimal(2),eachUom));
        guacRecipe.getIngredients().add(new Ingredient("Cilantro",new BigDecimal(2),dashUom));
        guacRecipe.getIngredients().add(new Ingredient("fresgly grated black pepper",new BigDecimal(2),dashUom));
        guacRecipe.getIngredients().add(new Ingredient("ripe tomato,seeds and pulp removed",new BigDecimal("0.5"),eachUom));
        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);
        recipes.add(guacRecipe);

        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);
        tacosRecipe.setDirections("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
           "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos.\n" +
                "I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove \n" +
                "comes wafting through the house. \n" +
                "Today's tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
             "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, \n" +
                "cumin, and sweet orange juice while the grill is heating. \n" +
                "You can also use this time to prepare the taco toppings.\n" +
          "Grill the chicken, then let it rest while you warm the tortillas. \n" +
                "Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!");
        Notes tacosNotes = new Notes();
        tacosNotes.setRecipeNotes("The ancho chiles I use in the marinade are named for their wide shape. \n" +
                "They are large, have a deep reddish brown color when dried, and are mild in flavor with just \n" +
                "a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\n" +
                "I like to put all the toppings in little bowls on a big platter at the center of the table:\n" +
                "avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I \n" +
                "add arugula, as well – this green isn't traditional for tacos, but we always seem to have \n" +
                "some in the fridge and I think it adds a nice green crunch to the tacos.\n" +
                "Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\n" +
                "You could also easily double or even triple this recipe for a larger party.\n" +
                "A taco and a cold beer on a warm day? Now that's living!");
        tacosRecipe.setNotes(tacosNotes);
        tacosRecipe.getIngredients().add( new Ingredient("Ancho Chili Powder",new BigDecimal(2),tableUom));
        tacosRecipe.getIngredients().add( new Ingredient("Dried Oregano",new BigDecimal(1),teeUom));
        tacosRecipe.getIngredients().add( new Ingredient("Dried Cumin",new BigDecimal(1),teeUom));
        tacosRecipe.getIngredients().add( new Ingredient("Sugar",new BigDecimal(1),teeUom));
        tacosRecipe.getIngredients().add( new Ingredient("Salt",new BigDecimal("0.5"),teeUom));
        tacosRecipe.getIngredients().add( new Ingredient("Clove of Garlic",new BigDecimal(1),eachUom));
        tacosRecipe.getIngredients().add( new Ingredient("finaly graed oranfe thigs",new BigDecimal(1),tableUom));
        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);
        recipes.add(tacosRecipe);

        return recipes;
    }
}
