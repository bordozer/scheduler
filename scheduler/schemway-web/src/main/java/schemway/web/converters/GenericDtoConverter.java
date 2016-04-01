package schemway.web.converters;

import schemway.core.models.Model;
import schemway.core.models.User;
import schemway.web.dto.Dto;

import java.util.List;

public interface GenericDtoConverter<M extends Model, D extends Dto> {

	D toDto(M task);

	List<D> toDtos(List<M> task);

	M toModel(User user, D dto);
}
