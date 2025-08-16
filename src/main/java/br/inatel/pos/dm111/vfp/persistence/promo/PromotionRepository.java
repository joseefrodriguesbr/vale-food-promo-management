package br.inatel.pos.dm111.vfp.persistence.promo;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.inatel.pos.dm111.vfp.persistence.ValeFoodRepository;

public interface PromotionRepository extends ValeFoodRepository<Promotion>
{
	List<Promotion> findByRestaurantId(String restaurantId) throws ExecutionException, InterruptedException;
}
