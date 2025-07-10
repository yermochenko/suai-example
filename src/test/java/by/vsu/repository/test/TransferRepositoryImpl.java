package by.vsu.repository.test;

import by.vsu.entity.Account;
import by.vsu.entity.Transfer;
import by.vsu.repository.TransferRepository;

import java.util.Comparator;
import java.util.List;

public class TransferRepositoryImpl extends BaseRepository<Transfer> implements TransferRepository {
	@Override
	public List<Transfer> readByAccount(Long accountId) {
		return getEntities().values().stream()
				.filter(transfer ->
					accountId.equals(transfer.getSender().map(Account::getId).orElse(null)) ||
					accountId.equals(transfer.getReceiver().map(Account::getId).orElse(null))
				)
				.sorted(Comparator.comparing(Transfer::getId))
				.toList();
	}
}
