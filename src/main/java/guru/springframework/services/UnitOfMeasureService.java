package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
     Set<UnitOfMeasureCommand> listAllUoms();
}
