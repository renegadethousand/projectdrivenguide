import java.io.IOException;

public class MainApplication {

    public static void main(String[] args) throws IOException {

        final String fileName = "bank.csv";

        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();

        final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();

        bankStatementAnalyzer.analyze(fileName, bankStatementParser);
    }
}
