package br.inatel.pos.dm111.vfp.publisher;

import br.inatel.pos.dm111.vfp.persistence.promo.Promotion;


public interface AppPublisher
{
	default Event buildEvent(Promotion promotion, Event.EventType eventType)
	{
		var restaurantPromotion = buildPromotionEvent(promotion);
		return new Event(eventType, restaurantPromotion);
	}

	default PromotionEvent buildPromotionEvent(Promotion promotion)
	{
		PromotionalProductEvent promotionalProductEvent = new PromotionalProductEvent(promotion.product().productId(), promotion.product().promotionalPrice());
		return new PromotionEvent(promotion.id(), promotion.name(), promotion.description(), promotion.restaurantId(),promotionalProductEvent);
	}

	boolean publishCreated(Promotion promotion);
}
