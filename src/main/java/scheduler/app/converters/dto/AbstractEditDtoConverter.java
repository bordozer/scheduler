package scheduler.app.converters.dto;

import scheduler.app.controllers.rest.tasks.item.edit.SchedulerTaskEditDTO;
import scheduler.app.dto.DTO;
import scheduler.app.models.Model;
import scheduler.app.models.SchedulerTask;

import java.util.List;
import java.util.function.Function;

public abstract class AbstractEditDtoConverter<M extends Model, D extends DTO> implements GenericDtoConverter<M, D> {

	protected abstract Function<SchedulerTask, SchedulerTaskEditDTO> taskMapper();

	@Override
	public D toDto(final M model) {
		return null; //taskMapper().apply(model);
	}

	@Override
	public List<D> toDtos(final List<M> models) {
		return null; //models.stream().map(taskMapper()).collect(Collectors.toList());
	}
}
