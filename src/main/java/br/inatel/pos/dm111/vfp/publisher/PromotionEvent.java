package br.inatel.pos.dm111.vfp.publisher;

public record PromotionEvent(String id, String name, String description, String restaurantId, PromotionalProductEvent product)
{
}
