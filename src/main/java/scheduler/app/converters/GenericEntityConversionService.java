package scheduler.app.converters;

import scheduler.app.entries.DBEntity;
import scheduler.app.models.Model;

public interface GenericEntityConversionService<E extends DBEntity, M extends Model> {

    E populateEntity(E entity, M model);

    M toModel(E entity);
}
