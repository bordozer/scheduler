package scheduler.app.converters.dto;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import scheduler.app.dto.Dto;
import scheduler.app.models.Model;
import scheduler.app.models.User;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractGenericDtoConverter<M extends Model, D extends Dto> implements GenericDtoConverter<M, D> {

	private static final String USER_MUST_NOT_BE_NULL = "User must not be null";
	private static final String DTO_MUST_NOT_BE_NULL = "DTO must not be null";

	protected abstract Function<M, D> taskMapper();

	protected abstract M doConvertToModel(final User user, final D dto);

	@Override
	public M toModel(final User user, final D dto) {
		Assert.notNull(user, USER_MUST_NOT_BE_NULL);
		Assert.notNull(dto, DTO_MUST_NOT_BE_NULL);

		return doConvertToModel(user, dto );
	}

	public D toDto(final M model) {
		Assert.notNull(model, USER_MUST_NOT_BE_NULL);
		return taskMapper().apply(model);
	}

	public List<D> toDtos(final List<M> models) {
		if (CollectionUtils.isEmpty(models)) {
			return Collections.emptyList();
		}
		return models.stream().map(taskMapper()).collect(Collectors.toList());
	}
}
