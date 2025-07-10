package by.vsu.repository.test;

import by.vsu.entity.Entity;
import by.vsu.repository.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BaseRepository<T extends Entity> implements Repository<T> {
	private final Map<Long, T> entities = new HashMap<>();

	@Override
	public Long create(T entity) {
		Long newId = entities.keySet().stream().max(Long::compareTo).orElse(0L) + 1;
		entity.setId(newId);
		entities.put(newId, entity);
		return newId;
	}

	@Override
	public Optional<T> read(Long id) {
		return Optional.ofNullable(entities.get(id));
	}

	@Override
	public void update(T entity) {
		if(entities.containsKey(entity.getId())) {
			entities.put(entity.getId(), entity);
		}
	}

	@Override
	public void delete(Long id) {
		entities.remove(id);
	}

	protected Map<Long, T> getEntities() {
		return entities;
	}
}
