package br.inatel.pos.dm111.vfp.api.promo;

public record PromotionRequest(String name, String description, String restaurantId, PromotionalProductRequest product)
{
}