import java.util.*;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock {
    private Map<Integer, Transaction> transactions;

    public ChainblockImpl() {
        this.transactions = new LinkedHashMap<>();
    }

    public int getCount() {
        return this.transactions.size();
    }

    public void add(Transaction transaction) {
        if (transactions.containsKey(transaction.getId())) {
            throw new IllegalArgumentException();
        }

        this.transactions.put(transaction.getId(), transaction);
    }

    public boolean contains(Transaction transaction) {
        return this.contains(transaction.getId());
    }

    public boolean contains(int id) {
        return this.transactions.containsKey(id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        if (!this.contains(id)) {
            throw new IllegalArgumentException();
        }
        this.transactions.get(id).setStatus(newStatus);
    }

    public void removeTransactionById(int id) {
        if (!this.contains(id)) {
            throw new IllegalArgumentException();
        }

        this.transactions.remove(id);
    }

    public Transaction getById(int id) {
        if (this.contains(id)) {
            return this.transactions.get(id);
        }

        throw new IllegalArgumentException();
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> transactionsWithStatus = this.transactions.values().stream()
                .filter(t -> t.getStatus().equals(status))
                .sorted((a, b) -> Double.compare(b.getAmount(), a.getAmount()))
                .collect(Collectors.toList());

        if (transactionsWithStatus.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return transactionsWithStatus;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        List<String> senders = this.transactions.values().stream()
                .filter(t -> t.getStatus().equals(status))
                .sorted((a, b) -> Double.compare(b.getAmount(), a.getAmount()))
                .map(Transaction::getFrom)
                .collect(Collectors.toList());

        if (senders.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return senders;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<String> receivers = this.transactions.values().stream()
                .filter(t -> t.getStatus().equals(status))
                .sorted((a, b) -> Double.compare(b.getAmount(), a.getAmount()))
                .map(Transaction::getTo)
                .collect(Collectors.toList());

        if (receivers.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return receivers;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        List<Transaction> receivers = this.transactions.values().stream()
                .sorted((a, b) -> {
                    int comparison = Double.compare(b.getAmount(), a.getAmount());

                    if (comparison == 0) {
                        comparison = b.getId() - a.getId();
                    }

                    return comparison;
                })
                .collect(Collectors.toList());

        if (receivers.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return receivers;
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        List<Transaction> alLBySender = this.transactions.values().stream()
                .filter(t -> t.getFrom().equals(sender))
                .sorted((a, b) -> Double.compare(b.getAmount(), a.getAmount()))
                .collect(Collectors.toList());

        if (alLBySender.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return alLBySender;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        List<Transaction> receivers = this.transactions.values().stream()
                .filter(t -> t.getTo().equals(receiver))
                .sorted((a, b) -> {
                    int comparison = Double.compare(b.getAmount(), a.getAmount());
                    if (comparison == 0) {
                        comparison = a.getId() - b.getId();
                    }

                    return comparison;
                })
                .collect(Collectors.toList());

        if (receivers.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return receivers;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return this.transactions.values().stream()
                .filter(t -> t.getStatus().equals(status) && t.getAmount() <= amount)
                .sorted((a, b) -> Double.compare(b.getAmount(), a.getAmount()))
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        List<Transaction> result = this.transactions.values().stream()
                .filter(t -> t.getFrom().equals(sender) && t.getAmount() >= amount)
                .sorted((a, b) -> Double.compare(b.getAmount(), a.getAmount()))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return result;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        List<Transaction> result = this.transactions.values().stream()
                .filter(t -> t.getTo().equals(receiver)
                        && t.getAmount() >= lo
                        && t.getAmount() <= hi)
                .sorted((a, b) -> {
                    int comparison = Double.compare(b.getAmount(), a.getAmount());
                    if (comparison == 0) {
                        comparison = b.getId() - a.getId();
                    }
                    return comparison;
                })
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return result;
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return this.transactions.values().stream()
                .filter(t -> t.getAmount() >= lo && t.getAmount() <= hi)
                .collect(Collectors.toList());
    }

    public Iterator<Transaction> iterator() {
        return this.transactions.values().iterator();
    }
}
