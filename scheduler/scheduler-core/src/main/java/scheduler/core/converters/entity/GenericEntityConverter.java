package scheduler.core.converters.entity;

public interface GenericEntityConverter<E extends scheduler.core.entities.DBEntity, M extends scheduler.core.models.Model> {

	void populateEntity(E entity, M model);

	M toModel(E entity);
}
