package co.wedevx.digitalbank.automation.ui.steps.data_transformers;

import co.wedevx.digitalbank.automation.ui.models.AccountCard;
import co.wedevx.digitalbank.automation.ui.models.BankTransaction;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class DataTableTransformer {



    @DataTableType
    public AccountCard accountCardEntry(Map<String, String> entry) {
        String accountName = entry.get("accountName");
        String accountType = entry.get("accountType");
        String ownership = entry.get("ownership");
        long accountNumber = Long.parseLong(entry.get("accountNumber"));
        String interestRate = entry.get("interestRate");
        double balance = Double.parseDouble(entry.get("balance"));

        return new AccountCard(accountName, accountType, ownership, accountNumber, interestRate, balance);
    }

    @DataTableType
    public BankTransaction transactionEntry(Map<String, String> entry) {
        String date = entry.get("date");
        String category = entry.get("category");
        String description = entry.get("description");
        double amount = Double.parseDouble(entry.get("amount"));
        double balance = Double.parseDouble(entry.get("balance"));

        return new BankTransaction(date, category, description, amount, balance);
    }
}
