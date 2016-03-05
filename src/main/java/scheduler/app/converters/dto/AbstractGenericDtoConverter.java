package scheduler.app.converters.dto;

import scheduler.app.dto.Dto;
import scheduler.app.models.Model;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractGenericDtoConverter<M extends Model, D extends Dto> implements GenericDtoConverter<M, D> {

	protected abstract Function<M, D> taskMapper();

	public D toDto(final M model) {
		return taskMapper().apply(model);
	}

	public List<D> toDtos(final List<M> models) {
		return models.stream().map(taskMapper()).collect(Collectors.toList());
	}
}
