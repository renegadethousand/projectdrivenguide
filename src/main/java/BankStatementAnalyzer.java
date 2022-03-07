import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {

    private static final String RESOURCES = "src/main/resources/";

    public void analyze(final String fileName, final BankStatementParser bankStatementParser) throws IOException {

        final Path path = Paths.get(RESOURCES + fileName);
        List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);

        final BankTransactionProcessor bankStatmentProcessor = new BankTransactionProcessor(bankTransactions);

        collectSummary(bankStatmentProcessor);
    }

    private static void collectSummary(BankTransactionProcessor bankStatmentProcessor) {
        System.out.println("The total for all transactions is "
                + bankStatmentProcessor.calculateTotalAmount());

        System.out.println("The total for transactions in January is "
                + bankStatmentProcessor.selectTotalInMonth(Month.JANUARY));

        System.out.println("The total for transactions in February is "
                + bankStatmentProcessor.selectTotalInMonth(Month.FEBRUARY));

        System.out.println("The total salary received is "
                + bankStatmentProcessor.selectTotalForCategory("Salary"));

        System.out.println("The total for transactions in February is and expensive (instance) " +
                bankStatmentProcessor.findTransactions(new BankTransactionIsInFebruaryAndExpensive()));

        System.out.println("The total for transactions in February is and expensive (lambda) " +
                bankStatmentProcessor.findTransactions(bankTransaction ->
                        bankTransaction.getDate().getMonth() == Month.FEBRUARY
                        && bankTransaction.getAmount() >= 1_000));
    }
}
