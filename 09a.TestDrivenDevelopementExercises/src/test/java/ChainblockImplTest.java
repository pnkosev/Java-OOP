import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ChainblockImplTest {
    private static final Transaction TRANSACTION = new TransactionImpl(
            1,
            TransactionStatus.SUCCESSFUL,
            "PN",
            "MM",
            100
    );
    private static final int COUNT_TO_FILL = 16;
    private Chainblock chainblock;

    @Before
    public void setup() {
        this.chainblock = new ChainblockImpl();
        this.chainblock.add(TRANSACTION);
    }

    @Test
    public void shouldAddTransactionWithUniqueId() {
        int count = this.chainblock.getCount();

        assertEquals(1, count);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfAddingTransactionWithNonUniqueId() {
        this.chainblock.add(TRANSACTION);
    }

    @Test
    public void shouldReturnTrueIfContainingTransactionById() {
        boolean isContained = this.chainblock.contains(1);

        assertTrue(isContained);
    }

    @Test
    public void shouldReturnFalseIfNonContainingTransactionById() {
        boolean isContained = this.chainblock.contains(2);

        assertFalse(isContained);
    }

    @Test
    public void shouldReturnTrueIfContainingTransaction() {
        boolean isContained = this.chainblock.contains(TRANSACTION);

        assertTrue(isContained);
    }

    @Test
    public void shouldReturnFalseIfContainingTransaction() {
        Transaction transaction = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "YO", "yoyo", 200);
        boolean isContained = this.chainblock.contains(transaction);

        assertFalse(isContained);
    }

    @Test
    public void shouldReturnTheCorrectTransactionIfGetById() {
        Transaction byId = this.chainblock.getById(1);

        assertEquals(TRANSACTION, byId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfOnGetByIdTransactionIsAbsent() {
        Transaction byId = this.chainblock.getById(2);

        assertEquals(TRANSACTION, byId);
    }

    @Test
    public void shouldRemoveByIdCorrectly() {
        this.chainblock.removeTransactionById(1);

        assertEquals(0, this.chainblock.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfRemoveByIdDoesntContainTransaction() {
        this.chainblock.removeTransactionById(2);
    }

    @Test
    public void shouldChangeTransactionStatusIfTransactionExists() {
        int ordinal = TRANSACTION.getStatus().ordinal() + 1;
        TransactionStatus[] statusArray = TransactionStatus.values();
        TransactionStatus newStatus = statusArray[ordinal % statusArray.length];
        this.chainblock.changeTransactionStatus(1, newStatus);

        assertEquals(TRANSACTION.getStatus(), newStatus);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowOnChangeTransactionStatusIfTransactionDoesNotExist() {
        int ordinal = TRANSACTION.getStatus().ordinal() + 1;
        TransactionStatus[] statusArray = TransactionStatus.values();
        TransactionStatus newStatus = statusArray[ordinal % statusArray.length];
        this.chainblock.changeTransactionStatus(2, newStatus);

        assertEquals(TRANSACTION.getStatus(), newStatus);
    }

    @Test
    public void shouldGetAllTransactionByStatusIfStatusExists() {
        int[] statuses = new int[TransactionStatus.values().length];
        fillWithTransactions(COUNT_TO_FILL, statuses);

        Iterable<Transaction> byTransactionStatus = this.chainblock.getByTransactionStatus(TRANSACTION.getStatus());

        int count = 0;
        for (Transaction t : byTransactionStatus) {
            if (t.getStatus().equals(TRANSACTION.getStatus())) {
                count++;
            }
        }

        int expected = statuses[TRANSACTION.getStatus().ordinal()];

        assertEquals(expected, count);
    }

    @Test
    public void shouldGetTransactionByStatusWithBiggestAmountIfStatusExists() {
        int[] statuses = new int[TransactionStatus.values().length];
        fillWithTransactions(COUNT_TO_FILL, statuses);

        Iterable<Transaction> byTransactionStatus = this.chainblock.getByTransactionStatus(TRANSACTION.getStatus());

        List<Double> actual = new ArrayList<>();

        for (Transaction t : byTransactionStatus) {
            actual.add(t.getAmount());
        }

        List<Double> expected = actual.stream().sorted(Double::compareTo).collect(Collectors.toList());
        Collections.reverse(expected);

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowUpOnGetTransactionByStatusIfStatusDoesNotExist() {
        this.chainblock.getByTransactionStatus(TransactionStatus.FAILED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAllSendersWithTransactionStatusWithNonExistingStatusShouldThrow() {
        this.chainblock.getAllSendersWithTransactionStatus(TransactionStatus.FAILED);
    }

    @Test
    public void getAllSendersWithTransactionStatusWithExistingStatusShouldReturnCorrectCount() {
        int[] statuses = new int[TransactionStatus.values().length];
        fillWithTransactions(COUNT_TO_FILL, statuses);

        Iterable<String> allSendersWithTransactionStatus = this.chainblock.getAllSendersWithTransactionStatus(TRANSACTION.getStatus());

        int expected = statuses[TRANSACTION.getStatus().ordinal()];

        int actual = 0;
        for (String t : allSendersWithTransactionStatus) {
            actual++;
        }

        assertEquals(expected, actual);
    }

    @Test
    public void getAllSendersWithTransactionStatusWithExistingStatusShouldReturnInCorrectOrder() {
        fillWithTransactions(COUNT_TO_FILL, null);

        Iterable<String> allSendersWithTransactionStatus = this.chainblock.getAllSendersWithTransactionStatus(TRANSACTION.getStatus());
        Iterable<Transaction> byTransactionStatus = this.chainblock.getByTransactionStatus(TRANSACTION.getStatus());

        List<String> allByTransactionStatus = new ArrayList<>();

        for (Transaction t : byTransactionStatus) {
            allByTransactionStatus.add(t.getFrom());
        }

        assertEquals(allByTransactionStatus, allSendersWithTransactionStatus);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAllReceiversWithTransactionStatusWithNonExistingStatusShouldThrow() {
        this.chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.FAILED);
    }

    @Test
    public void getAllReceiversWithTransactionStatusWithExistingStatusShouldReturnCorrectCount() {
        int[] statuses = new int[TransactionStatus.values().length];
        fillWithTransactions(COUNT_TO_FILL, statuses);

        Iterable<String> allReceiversWithTransactionStatus = this.chainblock.getAllReceiversWithTransactionStatus(TRANSACTION.getStatus());

        int expected = statuses[TRANSACTION.getStatus().ordinal()];

        int actual = 0;
        for (String t : allReceiversWithTransactionStatus) {
            actual++;
        }

        assertEquals(expected, actual);
    }

    @Test
    public void getAllReceiversWithTransactionStatusWithExistingStatusShouldReturnInCorrectOrder() {
        fillWithTransactions(COUNT_TO_FILL, null);

        Iterable<String> allReceiversWithTransactionStatus = this.chainblock.getAllReceiversWithTransactionStatus(TRANSACTION.getStatus());
        Iterable<Transaction> byTransactionStatus = this.chainblock.getByTransactionStatus(TRANSACTION.getStatus());

        List<String> allByTransactionStatus = new ArrayList<>();

        for (Transaction t : byTransactionStatus) {
            allByTransactionStatus.add(t.getTo());
        }

        assertEquals(allByTransactionStatus, allReceiversWithTransactionStatus);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAllOrderedByAmountDescendingThenByIdShouldThrowForEmptyChainBlock() {
        this.chainblock.removeTransactionById(1);
        this.chainblock.getAllOrderedByAmountDescendingThenById();
    }

    @Test
    public void getAllOrderedByAmountDescendingThenByIdShouldReturnCorrectCount() {
        fillWithTransactions(COUNT_TO_FILL, null);

        Iterable<Transaction> allOrderedByAmountDescendingThenById = this.chainblock.getAllOrderedByAmountDescendingThenById();

        int actual = 0;
        for (Transaction t : allOrderedByAmountDescendingThenById) {
            actual++;
        }

        int expected = this.chainblock.getCount();

        assertEquals(expected, actual);
    }

    @Test
    public void getAllOrderedByAmountDescendingThenByIdShouldReturnAllInCorrectOrder() {
        List<Transaction> transactions = this.fillWithTransactionsWithRepetitiveValues();

        List<Transaction> expected = transactions.stream()
                .sorted((a, b) -> {
                    int comparison = Double.compare(b.getAmount(), a.getAmount());

                    if (comparison == 0) {
                        comparison = b.getId() - a.getId();
                    }

                    return comparison;
                })
                .collect(Collectors.toList());

        Iterable<Transaction> actual = this.chainblock.getAllOrderedByAmountDescendingThenById();

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getBySenderOrderedByAmountDescendingShouldThrowIfNoSuchSender() {
        this.chainblock.getBySenderOrderedByAmountDescending("yo");
    }

    @Test
    public void getBySenderOrderedByAmountDescendingShouldReturnCorrectCount() {
        this.fillWithTransactionsWithRepetitiveValues();

        Iterable<Transaction> pnTransactions = this.chainblock.getBySenderOrderedByAmountDescending("PN");

        int actual = 0;

        for (Transaction t : pnTransactions) {
            actual++;
        }

        assertEquals(4, actual);
    }

    @Test
    public void getBySenderOrderedByAmountDescendingShouldReturnAllInCorrectOrder() {
        List<Transaction> expected = this.fillWithTransactionsWithRepetitiveValues();

        List<Transaction> sortedExpected = expected.stream()
                .filter(t -> t.getFrom().equals("PN"))
                .sorted((a, b) -> Double.compare(b.getAmount(), a.getAmount()))
                .collect(Collectors.toList());

        Iterable<Transaction> pnTransactions = this.chainblock.getBySenderOrderedByAmountDescending("PN");

        assertEquals(sortedExpected, pnTransactions);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByReceiverOrderedByAmountThenByIdShouldThrowIfNoSuchTransactions() {
        this.chainblock.getByReceiverOrderedByAmountThenById("MK");
    }

    @Test
    public void getByReceiverOrderedByAmountThenByIdShouldReturnReceiversInCorrectOrder() {
        List<Transaction> expected = this.fillWithTransactionsWithRepetitiveValues();

        Iterable<Transaction> receivers = this.chainblock.getByReceiverOrderedByAmountThenById("MM");

        List<Transaction> sortedExpected = expected.stream()
                .filter(t -> t.getTo().equals("MM"))
                .sorted((a, b) -> {
                    int comparison = Double.compare(b.getAmount(), a.getAmount());
                    if (comparison == 0) {
                        comparison = a.getId() - b.getId();
                    }

                    return comparison;
                })
                .collect(Collectors.toList());

        assertEquals(sortedExpected, receivers);
    }

    @Test
    public void getByTransactionStatusAndMaximumAmountShouldReturnEmptyCollectionIfNoSuchTransaction() {
        this.fillWithTransactionsWithRepetitiveValues();
        Iterable<Transaction> actual = this.chainblock.getByTransactionStatusAndMaximumAmount(TransactionStatus.FAILED, 300);

        int count = 0;
        for (Transaction t : actual) {
            count++;
        }
        assertEquals(0, count);
    }

    @Test
    public void getByTransactionStatusAndMaximumAmountShouldReturnInCorrectOrder() {
        List<Transaction> expected = this.fillWithTransactionsWithRepetitiveValues();

        List<Transaction> orderedExpected = expected.stream()
                .filter(t -> t.getStatus().equals(TRANSACTION.getStatus()) && t.getAmount() <= 200)
                .sorted((a, b) -> Double.compare(b.getAmount(), a.getAmount()))
                .collect(Collectors.toList());

        Iterable<Transaction> actual = this.chainblock.getByTransactionStatusAndMaximumAmount(TRANSACTION.getStatus(), 200);

        assertEquals(orderedExpected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getBySenderAndMinimumAmountDescendingShouldThrowIfNoSuchTransactions() {
        this.fillWithTransactionsWithRepetitiveValues();

        this.chainblock.getBySenderAndMinimumAmountDescending("NP", 300);
    }

    @Test
    public void getBySenderAndMinimumAmountDescendingShouldReturnAllInCorrectOrder() {
        List<Transaction> expected = this.fillWithTransactionsWithRepetitiveValues();

        List<Transaction> orderedExpected = expected.stream()
                .filter(t -> t.getFrom().equals("PN") && t.getAmount() >= 200)
                .sorted((a, b) -> Double.compare(b.getAmount(), a.getAmount()))
                .collect(Collectors.toList());

        Iterable<Transaction> actual = this.chainblock.getBySenderAndMinimumAmountDescending("PN", 200);

        assertEquals(orderedExpected, actual);
    }

    @Test
    public void getAllInAmountRangeShouldReturnEmptyCollectionInNoSuchTransactions() {
        Iterable<Transaction> allInAmountRange = this.chainblock.getAllInAmountRange(20, 50);

        assertFalse(allInAmountRange.iterator().hasNext());
    }

    @Test
    public void getAllInAmountRangeShouldReturnAllInOrderOfInsertion() {
        List<Transaction> transactions = fillWithTransactionsWithRepetitiveValues();
        Iterable<Transaction> actualTransactions = this.chainblock.getAllInAmountRange(100, 200);

        List<Transaction> expectedTransaction = transactions.stream()
                .filter(t -> t.getAmount() >= 100 && t.getAmount() <= 200)
                .collect(Collectors.toList());

        assertEquals(expectedTransaction, actualTransactions);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByReceiverAndAmountRangeShouldThrowIfNoSuchTransactions() {
        this.chainblock.getByReceiverAndAmountRange("SomeImpossibleName", 0, 0.1);
    }

    @Test
    public void getByReceiverAndAmountRangeShouldReturnAllInCorrectOrder() {
        String name = "MK";
        int lo = 100;
        int hi = 200;

        List<Transaction> transactions = fillWithTransactionsWithRepetitiveValues();
        List<Transaction> expectedTransactions = transactions.stream()
                .filter(t -> t.getTo().equals(name)
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

        Iterable<Transaction> actualTransactions = this.chainblock.getByReceiverAndAmountRange(name, lo, hi);

        assertEquals(expectedTransactions, actualTransactions);
    }

    private List<Transaction> fillWithTransactionsWithRepetitiveValues() {
        Transaction t2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "PN", "MM", 199.999);
        Transaction t3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "PN", "MM", 200);
        Transaction t4 = new TransactionImpl(4, TransactionStatus.SUCCESSFUL, "MM", "MK", 200);
        Transaction t5 = new TransactionImpl(5, TransactionStatus.SUCCESSFUL, "MK", "PN", 200.001);
        Transaction t6 = new TransactionImpl(6, TransactionStatus.SUCCESSFUL, "MM", "PN", 99.999);
        Transaction t7 = new TransactionImpl(7, TransactionStatus.SUCCESSFUL, "PN", "MK", 100.001);
        Transaction t8 = new TransactionImpl(8, TransactionStatus.SUCCESSFUL, "MM", "MK", 100.001);

        List<Transaction> transactions = new ArrayList<>();

        transactions.add(TRANSACTION);
        transactions.add(t2);
        transactions.add(t3);
        transactions.add(t4);
        transactions.add(t5);
        transactions.add(t6);
        transactions.add(t7);
        transactions.add(t8);

        // filling the Chainblock
        this.chainblock.add(t2);
        this.chainblock.add(t3);
        this.chainblock.add(t4);
        this.chainblock.add(t5);
        this.chainblock.add(t6);
        this.chainblock.add(t7);
        this.chainblock.add(t8);

        return transactions;
    }

    private void fillWithTransactions(int count, int[] statuses) {
        this.chainblock.removeTransactionById(1);
        TransactionStatus[] statusValues = TransactionStatus.values();

        for (int i = 1; i <= count; i++) {
            int id = i;
            int index = i % statusValues.length;
            TransactionStatus status = statusValues[index];
            if (statuses != null) {
                statuses[index]++;
            }
            String from = "From" + (char) ThreadLocalRandom.current().nextInt(48, 125);
            String to = "To" + (char) ThreadLocalRandom.current().nextInt(48, 125);
            double amount = 5.101 * ThreadLocalRandom.current().nextDouble(100, 200);

            this.chainblock.add(new TransactionImpl(id, status, from, to, amount));
        }
    }
}