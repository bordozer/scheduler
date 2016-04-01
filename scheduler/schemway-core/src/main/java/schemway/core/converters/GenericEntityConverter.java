package schemway.core.converters;

public interface GenericEntityConverter<E extends schemway.core.entities.DBEntity, M extends schemway.core.models.Model> {

	void populateEntity(E entity, M model);

	M toModel(E entity);
}
