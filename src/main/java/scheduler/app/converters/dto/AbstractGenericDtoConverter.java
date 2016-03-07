package scheduler.app.converters.dto;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import scheduler.app.dto.Dto;
import scheduler.app.models.Model;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractGenericDtoConverter<M extends Model, D extends Dto> implements GenericDtoConverter<M, D> {

	protected static final String MODEL_MUST_NOT_BE_NULL = "Model must not be null";
	protected static final String DTO_MUST_NOT_BE_NULL = "DTO must not be null";

	protected abstract Function<M, D> taskMapper();

	public D toDto(final M model) {
		Assert.notNull(model, MODEL_MUST_NOT_BE_NULL);
		return taskMapper().apply(model);
	}

	public List<D> toDtos(final List<M> models) {
		if (CollectionUtils.isEmpty(models)) {
			return Collections.emptyList();
		}
		return models.stream().map(taskMapper()).collect(Collectors.toList());
	}
}
