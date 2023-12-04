# BLACKJACK-BERRY-23
BlackJack Final Project

            // Place bets
            int bet = getValidBet();
            player.placeBet(bet);

            // Deal initial cards
            dealInitialCards();

            // Player's turn
            playerTurn();

            // Dealer's turn
            dealerTurn();

            // Determine the winner
            determineWinner();

            // Display game outcome and update bankroll
            displayGameOutcome();
        }

        System.out.println("Game over. You're out of money!");
    }

    private void resetGame() {
        deck = new Deck();
        player.resetHand();
        dealer.resetHand();
        playerTurn = true;
        gameOutcome = "";
    }

    private int getValidBet() {
        Scanner scanner = new Scanner(System.in);
        int bet;

        while (true) {
            System.out.println("Current Bankroll: $" + player.bankroll);
            System.out.print("Enter your bet: ");

            try {
                bet = scanner.nextInt();
                if (bet > 0 && bet <= player.bankroll) {
                    return bet;
                } else {
                    System.out.println("Invalid bet amount. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    private void dealInitialCards() {
        player.addCard(deck.dealCard());
        dealer.addCard(deck.dealCard());
        player.addCard(deck.dealCard());
        dealer.addCard(deck.dealCard());
    }

    private void playerTurn() {
        while (playerTurn) {
            displayGameStatus();

            if (player.hasBlackjack() || player.isBusted()) {
                playerTurn = false;
                break;
            }

            char choice = getPlayerChoice();
            if (choice == 'H') {
                player.addCard(deck.dealCard());
            } else {
                playerTurn = false;
            }
        }
    }

    private void dealerTurn() {
        while (!playerTurn && dealer.shouldHit()) {
            dealer.addCard(deck.dealCard());
        }
    }

    private void determineWinner() {
        int playerValue = player.calculateHandValue();
        int dealerValue = dealer.calculateHandValue();

        if (playerValue == dealerValue || (player.isBusted() && dealer.isBusted())) {
            gameOutcome = "It's a tie!";
            player.tieBet();
        } else if (player.isBusted() || (dealerValue <= 21 && dealerValue > playerValue)) {
            gameOutcome = "Dealer wins!";
        } else {
            gameOutcome = "Player wins!";
            player.winBet();
        }
    }

    private void displayGameOutcome() {
        displayGameStatus();
        System.out.println(gameOutcome);
        System.out.println("Current Bankroll: $" + player.bankroll);
    }

    private void displayGameStatus() {
        System.out.println("------------");
        System.out.println("Player's Hand:");
        player.displayHand();
        System.out.println("Player's Hand Value: " + player.calculateHandValue());
        System.out.println("------------");
        System.out.println("Dealer's Hand:");
        dealer.displayHand();
        System.out.println("------------");
    }

    private char getPlayerChoice() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Do you want to (H)it or (S)tand? ");
            String choice = scanner.next().toUpperCase();

            if (choice.equals("H") || choice.equals("S")) {
                return choice.charAt(0);
            } else {
                System.out.println("Invalid choice. Please enter 'H' or 'S'.");
            }
        }
    }

    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.startGame();
    }
}
