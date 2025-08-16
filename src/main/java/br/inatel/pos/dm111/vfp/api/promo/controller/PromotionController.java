package br.inatel.pos.dm111.vfp.api.promo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.pos.dm111.vfp.api.core.ApiException;
import br.inatel.pos.dm111.vfp.api.promo.PromotionRequest;
import br.inatel.pos.dm111.vfp.api.promo.PromotionResponse;
import br.inatel.pos.dm111.vfp.api.promo.service.PromotionService;

@RestController
@RequestMapping("/valefood/promotions")
public class PromotionController {

    private static final Logger log = LoggerFactory.getLogger(PromotionController.class);

    private final PromotionService service;

    public PromotionController(PromotionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PromotionResponse> createPromotion(@RequestBody PromotionRequest request) throws ApiException {
        log.info("Received request to create a new promotion: {}", request);
        
        var response = service.createPromotion(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    
    @GetMapping("/{promotionId}")
    public ResponseEntity<PromotionResponse> getPromotionById(@PathVariable("promotionId") String promotionId) throws ApiException {
        log.info("Received request to retrieve promotion with id: {}", promotionId);
        
        var response = service.searchPromotionById(promotionId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
    
    
    @PutMapping("/{promotionId}")
    public ResponseEntity<PromotionResponse> updatePromotion(@PathVariable("promotionId") String promotionId, @RequestBody PromotionRequest request) throws ApiException {
        log.info("Received request to update promotion with id: {}", promotionId);
        
        var response = service.updatePromotion(request, promotionId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
    
    @DeleteMapping("/{promotionId}")
    public ResponseEntity<Void> deletePromotion(@PathVariable("promotionId") String promotionId) throws ApiException {
        log.info("Received request to delete promotion with id: {}", promotionId);
        
        service.deletePromotion(promotionId);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/restaurants/{restaurantId}")
    public ResponseEntity<List<PromotionResponse>> listRestaurantPromotions(@PathVariable("restaurantId") String restaurantId) throws ApiException {
        log.info("Received request to list promotions for restaurant with id: {}", restaurantId);

        var response = service.listRestaurantPromotions(restaurantId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<PromotionResponse>> listAllPromotions() throws ApiException {
        log.info("Received request to list all promotions.");

        var response = service.listAllPromotions();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<PromotionResponse>> listPromotionsByInterest(@PathVariable("userId") String userId) throws ApiException {
        log.info("Received request to list promotions by interest for user with id: {}", userId);

        var response = service.listPromotionsByInterest(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}