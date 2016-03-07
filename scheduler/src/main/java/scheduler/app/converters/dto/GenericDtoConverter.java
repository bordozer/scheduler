package scheduler.app.converters.dto;

import scheduler.app.dto.Dto;
import scheduler.app.models.Model;
import scheduler.app.models.User;

import java.util.List;

public interface GenericDtoConverter<M extends Model, D extends Dto> {

	D toDto(M task);

	List<D> toDtos(List<M> task);

	M toModel(User user, D dto);
}
