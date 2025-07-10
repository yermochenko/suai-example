package by.vsu.repository.test;

import by.vsu.entity.Account;
import by.vsu.entity.Entity;
import by.vsu.repository.AccountRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AccountRepositoryImpl extends BaseRepository<Account> implements AccountRepository {
	public AccountRepositoryImpl() {
		add(1L, "A001", "abc", 100_00L, true);
		add(2L, "A002", "mnk", 0L     , false);
		add(3L, "A003", "xyz", 200_00L, true);
	}

	@Override
	public List<Account> readAll() {
		return getEntities().values().stream()
				.sorted(Comparator.comparingLong(Entity::getId))
				.toList();
	}

	@Override
	public List<Account> readActive() {
		return getEntities().values().stream()
				.filter(Account::isActive)
				.sorted(Comparator.comparingLong(Entity::getId))
				.toList();
	}

	@Override
	public Optional<Account> readByNumber(String number) {
		return getEntities().values().stream().filter(account -> account.getNumber().equals(number)).findFirst();
	}

	private void add(Long id, String number, String owner, Long balance, boolean active) {
		Account account = new Account();
		account.setId(id);
		account.setNumber(number);
		account.setOwner(owner);
		account.setBalance(balance);
		account.setActive(active);
		getEntities().put(account.getId(), account);
	}
}
