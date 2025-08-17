package br.inatel.pos.dm111.vfp.api.promo;

public record PromotionResponse(String id, String name, String description, String restaurantId, String restaurantName, PromotionalProductResponse product)
{
}