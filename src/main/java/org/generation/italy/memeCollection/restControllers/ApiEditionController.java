package org.generation.italy.memeCollection.restControllers;

import org.generation.italy.memeCollection.model.entities.Edition;
import org.generation.italy.memeCollection.model.services.abstractions.AbstractGameService;
import org.generation.italy.memeCollection.model.services.implementations.GenericService;

public class ApiEditionController {
    private GenericService<Edition> service;
    private AbstractGameService gameService;
}
