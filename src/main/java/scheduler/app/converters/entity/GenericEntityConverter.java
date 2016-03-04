package scheduler.app.converters.entity;

import scheduler.app.entries.DBEntity;
import scheduler.app.models.Model;

public interface GenericEntityConverter<E extends DBEntity, M extends Model> {

    void populateEntity(E entity, M model);

    M toModel(E entity);
}
