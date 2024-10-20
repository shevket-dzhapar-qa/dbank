Feature: Creating a new checking account

  Scenario: Creating a new standard individual checking account

    Given The user is logged in as "leo_messi@gmail.com" "cixgap-1pykpE-viptuv"
    When the user creates a new checking account with the following data
      |checkingAccountType | accountOwnership | accountName               | initialDepositAmount |
      | Standard Checking  | Individual       | Leo's Checking            | 100000.0             |
    Then the user should see the green "Successfully created new Standard Checking account named Leo's Checking" message
    And the user should see newly added account card
      | accountName                 | accountType       | ownership  | accountNumber | interestRate | balance   |
      | Leo's Checking              | Standard Checking | Individual | 486137428     | 0.0%          |  100000.00 |
    And the user should see the following transaction
      | date             | category | description               | amount    | balance   |
      | 2024-07-30 16:20 | Income   | 845329818 (DPT) - Deposit |  100000.00 |  100000.00 |
