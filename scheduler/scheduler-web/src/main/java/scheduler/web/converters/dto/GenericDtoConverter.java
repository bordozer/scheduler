package scheduler.web.converters.dto;

import scheduler.core.models.Model;
import scheduler.core.models.User;
import scheduler.web.dto.Dto;

import java.util.List;

public interface GenericDtoConverter<M extends Model, D extends Dto> {

	D toDto(M task);

	List<D> toDtos(List<M> task);

	M toModel(User user, D dto);
}
